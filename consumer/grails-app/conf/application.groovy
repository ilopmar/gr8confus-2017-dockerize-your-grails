rabbitmq {
    connections = [
        [
            host: 'localhost',
            username: 'guest',
            password: 'guest'
        ]
    ]
    queues = [
        [
            name: 'reverse',
            durable: true
        ]
    ]
}
