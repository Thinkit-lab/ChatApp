package org.example.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.net.Socket;

public interface ClientService {
    void sendMessage();
    void listenToMessage();
    void closeAll(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter);

}
