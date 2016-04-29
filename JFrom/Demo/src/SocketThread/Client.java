package SocketThread;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) {
		
		final String serverHost = "localhost";
		Socket socketOfClient = null;
		BufferedWriter os;
		BufferedReader is;
		String line = null;
		Scanner input = new Scanner(System.in);
		
		try {
			socketOfClient = new Socket(serverHost, 9999);
			is = new BufferedReader(new InputStreamReader(socketOfClient.getInputStream()));
			os = new BufferedWriter(new OutputStreamWriter(socketOfClient.getOutputStream()));
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return;
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		
		try {
			os.write("5");
			os.flush();
			os.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
