import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;


public class ReloadServerListener implements ActionListener {
	private BukkitGui gui;
	public ReloadServerListener(BukkitGui gui) {
		this.gui = gui;
	}

	public void actionPerformed(ActionEvent arg0) {

		gui.msgLog.setText("");
		Thread reload = new Thread(new Runnable(){

			public void run() {
				try {
					
					BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(StartBukkitServerListener.p.getOutputStream()));
					String input = "reload";
					input += "\n";
					
						writer.write(input);
						writer.flush();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}				
			}
			
		});
		reload.start();
	}

}
