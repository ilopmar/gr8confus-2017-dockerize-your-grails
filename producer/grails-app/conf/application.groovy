rabbitmq {
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

environments {
    development {
        rabbitmq {
            connections = [
                [
                    host    : 'localhost',
                    username: 'guest',
                    password: 'guest'
                ]
            ]
        }
    }
    production {
        rabbitmq {
            connections = [
                [
                    host    : 'rabbitmq',
                    username: 'guest',
                    password: 'guest'
                ]
            ]
        }
    }
}
