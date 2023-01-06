package org.example.service.implementation;

import org.example.service.ClientService;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientServiceImpl implements ClientService {
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String username;

    public ClientServiceImpl(Socket socket, String username) {
        try {
            this.socket = socket;
            this.username = username;
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e){
            closeAll(socket, bufferedReader, bufferedWriter);
        }
    }

    public void sendMessage() {
        try {
            bufferedWriter.write(username);
            bufferedWriter.newLine();
            bufferedWriter.flush();

            Scanner scanner = new Scanner(System.in);
            while (socket.isConnected()) {
                String clientMessage = scanner.nextLine();
                if (!clientMessage.equals("exit")){
                    bufferedWriter.write(username + ": " + clientMessage);
                    bufferedWriter.newLine();
                    bufferedWriter.flush();
                }
                closeAll(socket, bufferedReader, bufferedWriter);
            }
        } catch (IOException e) {
            closeAll(socket, bufferedReader, bufferedWriter);
        }
    }

    public void listenToMessage() {
        new Thread(() -> {
            while (socket.isConnected()) {
                try {
                    String message = bufferedReader.readLine();
                    System.out.println(message);
                } catch (IOException e) {
                    closeAll(socket, bufferedReader, bufferedWriter);
                }
            }
        }).start();
    }

    public void closeAll(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
        try {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
            if (socket != null){
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public static void main(String[] args) throws IOException {
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Enter your username for the group chat");
//        String username = scanner.nextLine();
//        Socket socket = new Socket("localhost", 8080);
//
//        ClientServiceImpl clientService = new ClientServiceImpl(socket, username);
//        System.out.println("Go ahead and send a message");
//        clientService.listenToMessage();
//        clientService.sendMessage();
//    }
}
