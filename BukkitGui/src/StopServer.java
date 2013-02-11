import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;


public class StopServer {
	BukkitGui gui;
	public StopServer(BukkitGui gui) {
		// TODO Auto-generated constructor stub
		this.gui = gui;
	}
	public void Stop(){
		Thread stop = new Thread(new Runnable(){

			public void run() {
				try {
					gui.stserver.setEnabled(true);
					gui.stopserver.setEnabled(false);
					gui.reload.setEnabled(false);
					BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(StartBukkitServerListener.p.getOutputStream()));
					String input = "stop";
					input += "\n";
					
						writer.write(input);
						writer.flush();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}				
			}
			
		});
		stop.start();
	}
}
