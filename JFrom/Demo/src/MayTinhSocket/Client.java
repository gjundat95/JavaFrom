package MayTinhSocket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

	public static void main(String[] args) {
		
		// Dia chi may chu
		final String serverHost = "localhost";
		Socket socketOfClient = null;
		BufferedReader is;
		BufferedWriter os;
			
			try {
				
				// Connect to Server localhost  Port 9999
				socketOfClient = new Socket(serverHost, 9999);
				// Tao luong nhan va gui du lieu
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
				
				// Gui du lieu len server
				os.write("Xin chao Server !");
				os.newLine();
				os.flush();
				os.write("Tao La Client !");
				os.newLine();
				os.flush();
				os.write("Exit");
				os.newLine();
				os.flush();
				
				// Nhan du lieu ve
				String responsive;
				while( (responsive = is.readLine()) != null ){
					System.out.println("Server :"+responsive);
					if(responsive.indexOf("Goodbye") != -1){
						break;
					}
				}
				
				// Thoat 
				os.close();
				is.close();
				socketOfClient.close();
				
			} catch (IOException e) {
				e.printStackTrace();
			}

	}

}
