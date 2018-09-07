package questionModule

fun getAllQuestions(): HashMap<String, List<Question>> = hashMapOf("questions" to findAllQuestions())
