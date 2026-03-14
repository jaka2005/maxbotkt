package funn.j2k.maxbotkt.starter

import funn.j2k.maxbotkt.bot.IBot
import funn.j2k.maxbotkt.model.Marker
import funn.j2k.maxbotkt.model.update.Update
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

val REQUEST_DELAY = 1.seconds / 30

class LongPollingStarter() : BotStarter<IBot> {
    override fun start(scope: CoroutineScope, bot: IBot, onUpdate: suspend IBot.(Update) -> Unit): Job = scope.launch {
        var marker: Marker? = null
        while (true) {
            val (updates, newMarker) = bot.getUpdates(marker = marker, limit = 100)
            updates.forEach {
                try {
                    bot.onUpdate(it)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

            marker = newMarker
            delay(REQUEST_DELAY)
        }
    }
}
