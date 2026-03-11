package funn.j2k.maxbotkt.exception

class InvalidWebhookSecretException(): Exception("Received secret doesnt match declared in webhook")