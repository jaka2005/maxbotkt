package funn.j2k.maxbotkt.starter

import funn.j2k.maxbotkt.Bot
import funn.j2k.maxbotkt.model.update.Update
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job

interface BotStarter {
    fun start(scope: CoroutineScope, bot: Bot, onUpdate: suspend (Update) -> Unit): Job
}