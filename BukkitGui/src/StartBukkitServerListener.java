import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.text.BadLocationException;
import javax.swing.text.Element;

public class StartBukkitServerListener implements ActionListener {
	private BukkitGui gui;

	public StartBukkitServerListener(BukkitGui gui) {
		this.gui = gui;
	}

	public static Process p;
	public static boolean isstarted = false;

	public void actionPerformed(ActionEvent e) {
		Thread start = new Thread(new Runnable() {

			public void run() {
				try {

					isstarted = true;

					gui.stserver.setEnabled(false);
					gui.stopserver.setEnabled(true);
					gui.reload.setEnabled(true);
					System.out.println("Working in: " + new File("").getAbsolutePath());
					String line;
					p = Runtime.getRuntime().exec(
							"java -jar "
									+ new File(System.getProperty("user.dir")
											.concat("/Bukkit"),
											"craftbukkit.jar"));
					BufferedReader in = new BufferedReader(
							new InputStreamReader(p.getErrorStream()));

					while ((line = in.readLine()) != null) {

						// gui.msgLog.setPreferredSize(new Dimension(630,300));
						gui.printString(line);// Your version of CraftBukkit is
												// out of date.
						if (gui.msgLog.getLineCount() > 15) {

							gui.msgLog.setPreferredSize(new Dimension(400,
									gui.scrl += 20));
							gui.msgLog.getCaret().setDot(
									gui.msgLog.getText().length());
							gui.sbrText.scrollRectToVisible(gui.msgLog
									.getVisibleRect());
							gui.list.revalidate();
							if (gui.msgLog.getLineCount() > 512) {

								Element root = gui.msgLog.getDocument()
										.getDefaultRootElement();
								Element first = root.getElement(0);
								gui.msgLog.getDocument().remove(
										first.getStartOffset(),
										first.getEndOffset());
								gui.list.revalidate();
							}

						}
						if(line.contains("Your version of CraftBukkit is out of date.")){
							DownloadBukkitListener dl = new DownloadBukkitListener(gui);
							StopServer ss = new StopServer(gui);
							ss.Stop();
							gui.printString("Updating to latest recommended Bukkit Build");
							dl.dlBukkit();
							
						}

					}
//server stopped code
					gui.stserver.setEnabled(true);
					gui.stopserver.setEnabled(false);
					gui.reload.setEnabled(false);
					in.close();
					gui.printString("Server Stopped");
					System.out.println("Server stopped");
				} catch (IOException e1) {

					e1.printStackTrace();
				} catch (BadLocationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		});

		start.start();

	}

}
