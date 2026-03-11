package funn.j2k.maxbotkt.serializers

import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.serializer
import kotlin.reflect.KClass

interface PolymorphicType<T : Any> {
    val klass: KClass<out T>
}

@OptIn(InternalSerializationApi::class)
fun <T : Any> PolymorphicType<T>.serializer() = klass.serializer()