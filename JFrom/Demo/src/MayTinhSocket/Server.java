package MayTinhSocket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.*;

public class Server {

	public static void main(String[] args) {
		
		ServerSocket listener = null;
		String line = null;
		BufferedReader is;
		BufferedWriter os;
		Socket socketOfServer;
		// Ban khong the mot tao cong nho hon 1023, neu khong duoc cap quyen admin
		// port available 1 - 65535
		// Create port 9999
		try {
			listener = new ServerSocket(9999);
			//System.out.println("Tao port thanh cong.");
			
		} catch (IOException e) {
			System.out.println("Khong the tao port.");
			e.printStackTrace();
			System.exit(1);
		}
		
		try {
			System.out.println("Listener port ...");
			// Cho ket loi tu phia client
			// Nhan du lieu gui den tu client
			socketOfServer = listener.accept();
			System.out.println("Accept a client!");
			
			// Khoi tao luong du lieu de nhan File tu Client gui den
			// Su dung cac luong du lieu de doc nghi du lieu tuy y
			is = new BufferedReader(new InputStreamReader(socketOfServer.getInputStream()));
			os = new BufferedWriter(new OutputStreamWriter(socketOfServer.getOutputStream()));
			
			while(true){
				
				// Doc du lieu duoc gui toi server
				line = is.readLine();
				
				// Gui du lieu toi client
				os.write(line);
				os.newLine(); 						// Xuong dong
				os.flush(); 						// Day du lieu di
				
				// Neu Client gui toi Exit thi thoat
				if(line.equals("Exit")){
					os.write("Goodbye");
					os.newLine();
					os.flush();
					break;
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("Server Stop");
		
	}

}
