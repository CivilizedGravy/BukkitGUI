import java.awt.FlowLayout;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class DownloadBukkitListener {
	private BukkitGui gui;

	public DownloadBukkitListener(BukkitGui gui) {
		this.gui = gui;
	}

	public void dlBukkit() {
		Thread dl = new Thread(new Runnable(){
			
			@Override
			public void run() {
				gui.setVisible(false);
				JFrame jf = new JFrame("Downloading");
				jf.setResizable(false);
				jf.setSize(200, 50);
				jf.setLayout(new FlowLayout());
				jf.setLocationRelativeTo(null);
				
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (UnsupportedLookAndFeelException e) {
					e.printStackTrace();
				}

				JProgressBar progressBar;

				progressBar = new JProgressBar();
				progressBar.setValue(0);
				progressBar.setStringPainted(true);
				jf.add(progressBar);

				jf.setVisible(true);
				URL url;
				URLConnection con;
				DataInputStream dis;
				FileOutputStream fos;
				URL purl;
				URLConnection pcon;
				DataInputStream pdis;		//p for plugin
				FileOutputStream pfos;
				String path = System.getProperty("user.dir").concat("/");
				System.out.println("Path:" + path);

				File dlpath = new File(path + "Bukkit/");
				if (!dlpath.exists()) {
					dlpath.mkdir();
				}
				System.out.println("dlpath:" + dlpath);
				byte[] fileData;
				byte[] pfileData;
				try {
					url = new URL("http://dl.bukkit.org/latest-rb/craftbukkit.jar");
					con = url.openConnection();
					dis = new DataInputStream(con.getInputStream());
					fileData = new byte[con.getContentLength()];
					progressBar.setMinimum(0);
					progressBar.setMaximum(con.getContentLength());
					System.out.println("Downloading Latest Bukkit!");
					// plugin :https://dl.dropbox.com/u/28451008/BukkitGuiPlugin.jar
					for (int x = 0; x < fileData.length; x++) {

						fileData[x] = dis.readByte();
						

						progressBar.setValue(x);
						
					}
					fos = new FileOutputStream(new File(dlpath + "/craftbukkit.jar"));
					fos.write(fileData);
					fos.close();

					dis.close();
					purl = new URL("https://dl.dropbox.com/u/28451008/BukkitGuiPlugin.jar");
					pcon = purl.openConnection();
					pdis = new DataInputStream(pcon.getInputStream());
					pfileData = new byte[pcon.getContentLength()];
					//progressBar.setMinimum(0);
					//progressBar.setMaximum(pcon.getContentLength());
					System.out.println("Downloading BukkitGui Plugin!");
					// plugin :https://dl.dropbox.com/u/28451008/BukkitGuiPlugin.jar
					for (int x = 0; x < pfileData.length; x++) {

						pfileData[x] = pdis.readByte();
						

						progressBar.setValue(x);
						
					}
					pfos = new FileOutputStream(new File(System.getProperty("user.dir").concat(
							"/plugins/" + purl.getFile().split("/")[purl.getFile().split("/").length - 1])));
					pfos.write(pfileData);
					pfos.close();
					pdis.close();
					
					gui.setVisible(true);
					jf.setVisible(false);
					System.out.println("Done!");
				} catch (MalformedURLException m) {
					System.out.println(m);
				} catch (IOException io) {
					System.out.println(io);
				}
			}
			
		});
		dl.start();
		
	}

}
