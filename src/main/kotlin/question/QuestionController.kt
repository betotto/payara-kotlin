package question

import org.slf4j.Logger
import org.slf4j.LoggerFactory

val log: Logger = LoggerFactory.getLogger(Question::class.java)

fun getAllQuestions(): HashMap<String, List<Question>> = hashMapOf("questions" to findAllQuestions())

fun addQuestion(question: Question): Question {
    return insertQuestion(question)
}

/*
fun main(args: Array<String>) {
    println(getAllQuestions())
   // println(addQuestion(Question(text = "prueba de fuego dos", answers = listOf(Answer(text="Answer1"), Answer(text = "Anxwer2")))))
}

*/