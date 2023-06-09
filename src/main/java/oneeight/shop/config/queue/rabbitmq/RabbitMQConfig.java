package oneeight.shop.config.queue.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Value("${spring.rabbitmq.host}")
    private String host;

    @Value("${spring.rabbitmq.port}")
    private int port;

    @Value("${spring.rabbitmq.username}")
    private String username;

    @Value("${spring.rabbitmq.password}")
    private String password;

    @Value("${spring.rabbitmq.virtual-host}")
    private String virtualHost;

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(host);
        connectionFactory.setPort(port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        connectionFactory.setVirtualHost(virtualHost);
        return connectionFactory;
    }
    @Bean
    public RabbitTemplate rabbitTemplate() {
        return new RabbitTemplate(connectionFactory());
    }

    @Bean
    public Queue emailQueue() {
        return new Queue("email-queue");
    }

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange("email-exchange");
    }

    @Bean
    public Binding binding(Queue emailQueue, DirectExchange exchange) {
        return BindingBuilder.bind(emailQueue).to(exchange).with("email-routing-key");
    }
}

    /*Здесь мы определяем бин connectionFactory, который устанавливает параметры подключения к RabbitMQ,
        бин rabbitTemplate для отправки сообщений, бин emailQueue для создания очереди,
        бин exchange для создания обменника и бин binding для связывания очереди и обменника.*/

