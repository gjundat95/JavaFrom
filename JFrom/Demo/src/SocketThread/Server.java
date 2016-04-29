package SocketThread;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) {
		
		ServerSocket listener = null;
		System.out.println("Waitting...");
		int clientNumber = 0;
		String line = null;
		
		try {			
			listener = new ServerSocket(9999);
			
		} catch (IOException e) {
			System.out.println(e);
			System.exit(1);
		}
		
		try {
			while(true){
				Socket socketOfServer = listener.accept();
				new ListenerClient(clientNumber++, socketOfServer).start();
			}

		} catch (IOException e) {
			e.printStackTrace();
		} 

			
		
	}

}

class ListenerClient extends Thread{
	int clientNumber;
	Socket socketOfServer;

	public ListenerClient(int stt, Socket socket) {
		this.clientNumber = stt;
		this.socketOfServer = socket;
	}

	@Override
	public void run() {
		System.out.println("--Client ID:"+clientNumber+"--Adress:"+socketOfServer.getInetAddress().getHostName());
		try {
			
			BufferedReader is = new BufferedReader(new InputStreamReader(socketOfServer.getInputStream()));
			BufferedWriter os = new BufferedWriter(new OutputStreamWriter(socketOfServer.getOutputStream()));
			System.out.println("Ban kinh la: "+is.readLine());
			os.flush();
			os.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Stop Server");
		
	}
	
	
}

