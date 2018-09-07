package questionModule

import com.google.gson.Gson
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Path("/question")
class QuestionService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getAll")
    fun getAllQuestionsApi(): Response = Response.status(200).entity(Gson().toJson(getAllQuestions())).build()
}