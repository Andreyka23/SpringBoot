package com.geekbrains.testclient;

import org.apache.coyote.Response;

import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        try (Socket socket = new Socket("localhost", 8189)) {
            StringBuilder request = new StringBuilder();
            request.append("POST /market/test HTTP/1.1").append("\r\n");
            request.append("Host: ").append("localhost:8189").append("\r\n");
            request.append("Accept: ").append("test/plain;charset=UTF-8").append("\r\n");
            request.append("Connection: ").append("close").append("\r\n");
            request.append("Content-Type: ").append("test/plain;charset=UTF-8").append("\r\n");
            request.append("\r\n");
            socket.getOutputStream().write(request.toString().getBytes("UTF-8"));
            socket.getOutputStream().flush();

            System.out.println(socket.getInputStream());

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null)
                System.out.println(inputLine);
            in.close();

        }

    }
}
