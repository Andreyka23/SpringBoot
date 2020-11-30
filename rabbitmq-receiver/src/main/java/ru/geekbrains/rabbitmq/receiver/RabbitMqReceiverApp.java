package ru.geekbrains.rabbitmq.receiver;

import com.rabbitmq.client.*;

import java.util.Scanner;

public class RabbitMqReceiverApp {

    private static final String BLOG_QUEUE_NAME = "rabbit_blog";

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        String queueName = channel.queueDeclare().getQueue();
        System.out.println("My queue name: " + queueName);
        Scanner in = new Scanner(System.in);
        System.out.print("Введите на какой канал подписываетесь (php, java, c++): ");
        String category = in.nextLine();
        in.close();
        channel.queueBind(queueName, BLOG_QUEUE_NAME, "programming."+category);

        System.out.println(" [*] Ожидание статей");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Новая статья '" + message + "'");
        };

        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> { });
    }
}
