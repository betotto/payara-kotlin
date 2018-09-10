
import question.QuestionService
import java.util.HashSet
import javax.ws.rs.ApplicationPath
import javax.ws.rs.core.Application

@ApplicationPath("/services")
class QuestionsApplication : Application() {
    private val singletons = HashSet<Any>()

    init {
        singletons.add(QuestionService())
    }

    override fun getSingletons(): Set<Any> {
        return singletons
    }
}