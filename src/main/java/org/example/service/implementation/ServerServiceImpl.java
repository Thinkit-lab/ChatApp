package org.example.service.implementation;

import org.example.service.ClientHandler;
import org.example.service.ServerService;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerServiceImpl implements ServerService {
    private ServerSocket serverSocket;

    public ServerServiceImpl(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    @Override
    public void startServer() {

        while (!serverSocket.isClosed()) {
            try {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected successfully");
                ClientHandler clientHandler = new ClientHandler(socket);

                Thread thread = new Thread(clientHandler);
                thread.start();
            } catch (IOException e) {
                closeServerSocket();
            }
        }

    }

    @Override
    public void closeServerSocket() {
        if (serverSocket != null){
            try {
                serverSocket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

//    public static void main(String[] args) throws IOException {
//        ServerSocket serverSocket = new ServerSocket(8080);
//        ServerServiceImpl serverService = new ServerServiceImpl(serverSocket);
//        serverService.startServer();
//    }
}
