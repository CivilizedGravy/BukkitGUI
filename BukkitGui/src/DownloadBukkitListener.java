import java.awt.FlowLayout;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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
		String path = System.getProperty("user.dir").concat("/");
		System.out.println("Path:" + path);

		File dlpath = new File(path + "Bukkit/");
		if (!dlpath.exists()) {
			dlpath.mkdir();
		}
		System.out.println("dlpath:" + dlpath);
		byte[] fileData;
		try {
			url = new URL("http://dl.bukkit.org/latest-rb/craftbukkit.jar");
			con = url.openConnection();
			dis = new DataInputStream(con.getInputStream());
			fileData = new byte[con.getContentLength()];
			progressBar.setMinimum(0);
			progressBar.setMaximum(con.getContentLength());
			System.out.println("Downloading Latest Bukkit!");
			for (int x = 0; x < fileData.length; x++) {

				fileData[x] = dis.readByte();
				double d = (x / (con.getContentLength())); // too small for
															// percentage :I

				progressBar.setValue(x);
				//System.out.println(x + " of " + con.getContentLength());
			}

			dis.close();
			fos = new FileOutputStream(new File(dlpath + "/craftbukkit.jar"));
			fos.write(fileData);
			fos.close();
			jf.setVisible(false);
			System.out.println("Done!");
		} catch (MalformedURLException m) {
			System.out.println(m);
		} catch (IOException io) {
			System.out.println(io);
		}
	}

}