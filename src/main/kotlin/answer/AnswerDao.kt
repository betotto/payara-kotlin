package answer

import java.sql.Connection
import java.sql.Statement

data class Answer (
        val idAnswer: Int = -1,
        var text: String = "")

private const val insertAnswerQuery = "INSERT INTO answer (text, idQuestion) VALUES (?, ?)"

fun insertAnswerWithQuestion(answer: Answer, idQuestion: Int, con: Connection): Answer {
    log.debug("Add answer with text ${answer.text}")

    return con.prepareStatement(insertAnswerQuery, Statement.RETURN_GENERATED_KEYS).use { ps ->
        ps.setString(1, answer.text)
        ps.setInt(2, idQuestion)
        ps.execute()
        ps.generatedKeys.use { generatedKeys ->
            generatedKeys.next()
            Answer(idAnswer = generatedKeys.getInt(1), text = answer.text)
        }
    }
}