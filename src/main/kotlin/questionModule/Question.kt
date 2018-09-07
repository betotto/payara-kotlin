package questionModule

import config.getDataSource
import org.slf4j.Logger
import org.slf4j.LoggerFactory

val log: Logger = LoggerFactory.getLogger(Question::class.java)
const val queryAllQuestions = "SELECT idQuestion, text FROM question"

data class Question (
        val idQuestion: Int = -1,
        var text: String = "")

fun findAllQuestions(): List<Question> {
    log.debug("Getting all questions")
    log.debug("Query: $queryAllQuestions")

    return getDataSource().open().use {
        con -> con.createQuery(queryAllQuestions)
            .executeAndFetch(Question::class.java)
    }.toList()

}
