package funn.j2k.maxbotkt.starter

import funn.j2k.maxbotkt.Bot
import funn.j2k.maxbotkt.model.Marker
import funn.j2k.maxbotkt.model.update.Update
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

val REQUEST_DELAY = 1.seconds / 30

class LongPollingStarter() : BotStarter {
    override fun CoroutineScope.start(bot: Bot, onUpdate: suspend (Update) -> Unit): Job = launch {
        var marker: Marker? = null
        while (true) {
            val (updates, newMarker) = bot.getUpdates(marker = marker, limit = 100)
            updates.forEach {
                try {
                    onUpdate(it)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

            marker = newMarker
            delay(REQUEST_DELAY)
        }
    }
}
