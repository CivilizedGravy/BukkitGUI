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
					System.out.println(new File("").getAbsolutePath());
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
						gui.printString(line);
						if (gui.msgLog.getLineCount() > 15) {

							gui.msgLog.setPreferredSize(new Dimension(400,
									gui.scrl += 20));
							gui.msgLog.getCaret().setDot(
									gui.msgLog.getText().length());
							gui.sbrText.scrollRectToVisible(gui.msgLog
									.getVisibleRect());
							gui.list.revalidate();
					
						}
						if (gui.msgLog.getLineCount() > 512) {

							Element root = gui.msgLog.getDocument().getDefaultRootElement();
							Element first = root.getElement(0);
							gui.msgLog.getDocument().remove(first.getStartOffset(), first.getEndOffset());
							gui.list.revalidate();}

					}
					in.close();
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
