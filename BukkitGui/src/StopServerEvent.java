import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;


public class StopServerEvent implements ActionListener {
	private BukkitGui gui;
	public StopServerEvent(BukkitGui gui) {
		this.gui = gui;
	}

	public void actionPerformed(ActionEvent arg0) {
		
		Thread stop = new Thread(new Runnable(){

			public void run() {
				try {
					gui.stserver.setEnabled(true);
					gui.stopserver.setEnabled(false);
					gui.reload.setEnabled(false);
					BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(StartBukkitServerEvent.p.getOutputStream()));
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
