import java.awt.Font;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.URL;

public class StartupMessages {
	BukkitGui gui;

	public StartupMessages(BukkitGui gui) throws IOException {
		this.gui = gui;
	
		boolean portfw = false;
		URL whatismyip = new URL("http://checkip.amazonaws.com/");
		BufferedReader in = new BufferedReader(new InputStreamReader(
		                whatismyip.openStream()));
		String ip = in.readLine(); //you get the IP as a String
		System.out.println("Server IP Address:  " + ip);
		System.out.println("Checking if you Port Forwarded...");
		 
			Socket ServerSok = new Socket();
			try {
				ServerSok.connect(new InetSocketAddress(ip, 25565), 5000);
				
				System.out.println("That'll do pig, That'll do.");
				gui.printString("Server already running!");
				
				ServerSok.close();
			} catch (SocketTimeoutException e) {
				gui.printString("You didn't Port Forward!");
				System.out.println("Nope :I");
				portfw = true;
				ServerSok.close();
			}catch(ConnectException ce){
				System.out.println("Its all good!");
				portfw = true;
				ServerSok.close();
			}
			
		
		
		if(portfw){
			gui.printString("Thanks for downloading! Tell your friends that your server ip is " + ip, new Font("Arial", 0, 13));
			gui.printString("");
		}
			
		
	}
}
