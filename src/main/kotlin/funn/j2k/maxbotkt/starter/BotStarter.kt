package funn.j2k.maxbotkt.starter

import funn.j2k.maxbotkt.bot.IBot
import funn.j2k.maxbotkt.model.update.Update
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job

// TODO: starter should be able to consume all variants of IBot
//  (but it's require delicate work around of Bot implementations)
interface BotStarter<Bot : IBot> {
    fun start(scope: CoroutineScope, bot: Bot, onUpdate: suspend Bot.(Update) -> Unit): Job
}