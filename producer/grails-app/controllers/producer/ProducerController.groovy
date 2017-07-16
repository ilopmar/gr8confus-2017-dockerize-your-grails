package producer

import com.budjb.rabbitmq.publisher.RabbitMessagePublisher

class ProducerController {

    private static final String EXCHANGE = "exchange.reverse"
    private static final String ROUTING_KEY = "reverseKey"

    RabbitMessagePublisher rabbitMessagePublisher

    def send(String msg) {
        msg = msg ?: "Hello GR8Conf!"

        rabbitMessagePublisher.send(EXCHANGE, ROUTING_KEY, msg)

        render "Message sent: ${msg}"
    }
}