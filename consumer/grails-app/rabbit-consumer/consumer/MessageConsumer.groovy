package consumer

class MessageConsumer {

    static rabbitConfig = [
        queue: "reverse"
    ]

    void handleMessage(String message) {
        println "Message received: ${message}"

        new Message(message: message.reverse()).save()
    }
}
