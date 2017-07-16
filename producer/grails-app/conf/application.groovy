rabbitmq {
    connections = [
        [
            host: 'localhost',
            username: 'guest',
            password: 'guest'
        ]
    ]
    exchanges = [
        [
            name: 'exchange.reverse',
            type: 'topic'
        ]
    ]
    queues = [
        [
            name: 'reverse',
            durable: true,
            exchange: 'exchange.reverse',
            binding: 'reverseKey'
        ]
    ]
}