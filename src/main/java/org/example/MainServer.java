package org.example;

import org.example.service.implementation.ServerServiceImpl;

import java.io.IOException;
import java.net.ServerSocket;

public class MainServer {
        public static void main(String[] args) throws IOException {
            ServerSocket serverSocket = new ServerSocket(8080);
            ServerServiceImpl serverService = new ServerServiceImpl(serverSocket);
            serverService.startServer();
        }
}