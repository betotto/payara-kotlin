import org.eclipse.microprofile.openapi.annotations.Operation
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Path("/hello")
class HelloService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/world")
    @Operation(summary = "Get all questions",
            description = "Get a list of all the questions in database, without answers")
    fun getAllQuestions(): Response = Response.status(200).entity("Hello World").build()

}
