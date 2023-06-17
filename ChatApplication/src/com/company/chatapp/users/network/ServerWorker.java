package com.company.chatapp.users.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

//public class ServerWorker implements Runnable{
//	@Override
//	public void run() {}
//	public static void main(String[] args) {
//		ServerWorker job = new ServerWorker();
////		Assign the job to a thread
//		Thread worker = new Thread(job);
//		worker.start();
//	}
//}

public class ServerWorker extends Thread{
	private Socket clientSocket;
	private InputStream in;
	private OutputStream out;
	private  Server server;
	public ServerWorker(Socket clientSocket, Server server) throws IOException {
		this.server = server;
		this.clientSocket = clientSocket;
		in = clientSocket.getInputStream();// Data read
		out = clientSocket.getOutputStream();// Data write
		System.out.println("New client arrived !!");
	}
	@Override
	public void run() {
//	Read data from  the client and broadcast the data to all
		BufferedReader br = new BufferedReader(new InputStreamReader(in));  
		String line;
		try {
		while(true){
				line = br.readLine();// needs \n to stop reading data
				if(line.equalsIgnoreCase("quit")) {
					break;// End chat for a particular client
				}
// data sent to only that particular client
                // out.write(line.getBytes());
//		broadcast to all client
				for(ServerWorker serverWorker : server.workers) {
					line = line + "\n";
					serverWorker.out.write(line.getBytes());
				}
				
		} 
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
			if(br!=null)br.close();
			if(in!=null)in.close();
			if(out!=null)out.close();
			if(clientSocket!=null)clientSocket.close();
			}
			catch(Exception e){
				e.printStackTrace(); 
			}
		}
		
	}
}
