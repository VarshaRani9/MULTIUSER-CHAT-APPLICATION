package com.company.chatapp.users.network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import com.company.chatapp.utils.ConfigReader;

public class Server {

	ServerSocket serverSocket;

	/* FOR SINGLRE CLIENT HANDSHAKING
	 * public Server() throws IOException { int PORT =
	 * Integer.parseInt(ConfigReader.getValue("PORTNO")); serverSocket = new
	 * ServerSocket(PORT);
	 * System.out.println("Server started waiting for client connection"); Socket
	 * socket = serverSocket.accept(); System.out.println("Client joined");
	 * InputStream in = socket.getInputStream(); byte arr[] = in.readAllBytes();
	 * String str = new String(arr);
	 * System.out.println("Message from Client : "+str); in.close(); socket.close();
	 * }
	 */
	
//	FOR MULTIPLE CLIENTS HANDSHAKING
	ArrayList<ServerWorker> workers = new ArrayList<>();
	public Server() throws IOException {
		int PORT = Integer.parseInt(ConfigReader.getValue("PORTNO"));
		serverSocket = new ServerSocket(PORT);
		System.out.println("Server started waiting for client connection");
		handleClientRequest();
	}
	public void handleClientRequest() throws IOException {
		while(true) {
			Socket clientSocket = serverSocket.accept();//Handshaking
//			Per client a thread
			ServerWorker serverWorker = new ServerWorker(clientSocket,this);// creating a new thread
			workers.add(serverWorker);
			serverWorker.start();
			}
	}
	
	public static void main(String[] args) throws IOException {
		Server server = new Server();
	}

}
