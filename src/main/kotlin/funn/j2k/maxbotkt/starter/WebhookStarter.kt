package funn.j2k.maxbotkt.starter

import funn.j2k.maxbotkt.Bot
import funn.j2k.maxbotkt.exception.InvalidWebhookSecretException
import funn.j2k.maxbotkt.json
import funn.j2k.maxbotkt.model.update.Update
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.post
import io.ktor.server.routing.routing
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.time.Duration.Companion.seconds

class WebhookStarter(
    private val host: String = "0.0.0.0",
    private val port: Int = 0,
    private val path: String = "/webhook",
    private val publicUrl: String? = null,
    private val updateTypes: List<String>? = null,
    private val secret: String? = null,
) : BotStarter {
    override fun start(
        scope: CoroutineScope,
        bot: Bot,
        onUpdate: suspend (Update) -> Unit,
    ): Job {
        val server = embeddedServer(Netty, configure = {
            connector {
                host = this@WebhookStarter.host
                port = this@WebhookStarter.port
            }
            shutdownGracePeriod = 3000
        }) {
            install(ContentNegotiation) {
                json(json)
            }

            webhookModule(bot, onUpdate)
        }

        return scope.launch {
            server.start(wait = false)

            try {
                while (isActive) {
                    delay(1.seconds)
                }
            } finally {
                server.stop(gracePeriodMillis = 3000, timeoutMillis = 5000)
            }
        }

    }

    fun Application.webhookModule(bot: Bot, onUpdate: suspend (Update) -> Unit) {
        publicUrl?.let { url ->
            runBlocking {
                bot.setWebhook(url, updateTypes, secret)
            }
        }

        publicUrl?.let { url ->
            monitor.subscribe(ApplicationStopped) {
                runBlocking {
                    bot.deleteWebhook(url)
                }
            }
        }

        routing {
            post(path) {
                if (secret != null) {
                    val receivedSecret = call.request.header("X-Max-Bot-Api-Secret")
                    if (secret != receivedSecret)
                        throw InvalidWebhookSecretException()
                }

                try {
                    val update = call.receive<Update>()
                    onUpdate(update)
                    call.respond(HttpStatusCode.OK)
                } catch (e: Exception) {
                    e.printStackTrace()
                    call.respond(HttpStatusCode.InternalServerError)
                }
            }
        }
    }
}