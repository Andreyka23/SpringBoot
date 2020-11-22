package ru.geekbrains.rabbitmq.blog;

import com.rabbitmq.client.*;

import java.util.Scanner;

public class RabbitMqBlogApp {

    private static final String BLOG_QUEUE_NAME = "rabbit_blog";

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.exchangeDeclare(BLOG_QUEUE_NAME, BuiltinExchangeType.DIRECT);

            Scanner in = new Scanner(System.in);
            while (true) {
                System.out.print("Введите тему статьи (php, java, c++): ");
                String category = in.nextLine();
                System.out.print("Введите текст статьи: ");
                String text = in.nextLine();

                if (category.equals("exit") || text.equals("exit")) {
                    break;
                } else {
                    channel.basicPublish(BLOG_QUEUE_NAME, "programming." + category, null, text.getBytes("UTF-8"));
                    System.out.println(" [x] Статья отправлена: '" + text + "'");
                }
            }
            in.close();
        }
    }
}
