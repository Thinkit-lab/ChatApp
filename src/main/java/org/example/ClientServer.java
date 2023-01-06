package org.example;

import org.example.service.implementation.ClientServiceImpl;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClientServer {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your username for the group chat");
        String username = scanner.nextLine();
        Socket socket = new Socket("localhost", 8080);

        ClientServiceImpl clientService = new ClientServiceImpl(socket, username);
        System.out.println("Go ahead and send a message");
        clientService.listenToMessage();
        clientService.sendMessage();
    }
}
