package question

import com.google.gson.Gson
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Path("/question")
class QuestionService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getAll")
    fun getAllQuestionsApi(): Response {
        val questions = getAllQuestions()
        return if(questions.isEmpty()) {
            Response.status(404).build()
        } else {
            Response.status(200).entity(Gson().toJson(questions)).build()
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/add")
    fun addQuestionApi(question: String): Response {
        val gson = Gson()
        val newQuestion = addQuestion(gson.fromJson(question, Question::class.java))

        return Response.status(201).entity(Gson().toJson(newQuestion)).build()

    }
}