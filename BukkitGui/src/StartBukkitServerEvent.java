import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class StartBukkitServerEvent implements ActionListener {
	private BukkitGui gui;

	public StartBukkitServerEvent(BukkitGui gui) {
		this.gui = gui;
	}
	public static Process p;
	public static boolean isstarted = false;
	


	public void actionPerformed(ActionEvent e) {
		Thread start = new Thread(new Runnable(){
			

			public void run() {
				try {
				
					isstarted = true;
				
					
					
					
					gui.stserver.setEnabled(false);
					gui.stopserver.setEnabled(true);
					gui.reload.setEnabled(true);
					System.out.println(new File("").getAbsolutePath());
					String line;
					 p = Runtime
							.getRuntime()
							.exec("java -jar " + new File(System.getProperty ("user.dir").concat("/Bukkit") , "craftbukkit.jar"));
					BufferedReader in = new BufferedReader(new InputStreamReader(
							p.getErrorStream()));

					while ((line = in.readLine()) != null) {
						
							gui.scrl += 20;
							gui.msgLog.setPreferredSize(new Dimension(630, gui.scrl));
							gui.printString(line);
							gui.list.revalidate();
							if(gui.msgLog.getText().split("\n").length > 100){
								gui.msgLog.setText("");
							}
						
					}
					in.close();
				} catch (IOException e1) {

					e1.printStackTrace();
				}
			}
			
		});
	
	
		start.start();
		
		
		
	}

}
