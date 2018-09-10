package question

import answer.Answer
import answer.insertAnswerWithQuestion
import config.getDataSource
import org.simpleflatmapper.jdbc.JdbcMapperFactory
import java.sql.Statement
import kotlin.streams.toList

private const val queryAllQuestions = "SELECT q.idQuestion, q.text, a.idAnswer AS answers_idAnswer, a.text AS answers_text " +
        "FROM question q LEFT JOIN answer a ON q.idQuestion = a.idQuestion"
private const val insertQuestionQuery = "INSERT INTO question (text) VALUES (?)"

data class Question (
        val idQuestion: Int = -1,
        var text: String = "",
        var answers: List<Answer> = emptyList())

var mapper = JdbcMapperFactory.newInstance()
        .fieldMapperErrorHandler {
            _, _, target, _ -> log.info("Question with no answers $target")
        }
        .addKeys("idQuestion")
        .newMapper<Question>(Question::class.java)

fun findAllQuestions(): List<Question> {
    log.debug("Getting all questions")
    log.debug("Query: $queryAllQuestions")

    return getDataSource().connection.use { con ->
        con.prepareStatement(queryAllQuestions).use { ps ->
            ps.executeQuery().use { rs -> mapper.stream(rs).toList() }
        }
    }
}

fun insertQuestion(question: Question): Question {
    log.debug("Add question with text ${question.text}")
    return getDataSource().connection.use { con ->
        val newQuestion = con.prepareStatement(insertQuestionQuery, Statement.RETURN_GENERATED_KEYS).use { ps ->
            ps.setString(1, question.text)
            ps.executeUpdate()
            ps.generatedKeys.use { rs ->
                var idQuestion = -1
                if(rs.next()) {
                    idQuestion = rs.getInt(1)
                }
                Question(idQuestion = idQuestion, text = question.text)
            }
        }

        if(!question.answers.isEmpty()) {
            newQuestion.answers = question.answers.map { a -> insertAnswerWithQuestion(a, newQuestion.idQuestion, con) }
        }
        con.commit()
        return newQuestion
    }
}
