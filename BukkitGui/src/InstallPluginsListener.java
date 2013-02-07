import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileFilter;
import javax.swing.text.Utilities;

public class InstallPluginsListener extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BukkitGui gui;
	public JFileChooser fc = new JFileChooser();

	public InstallPluginsListener(BukkitGui gui) {
		this.gui = gui;

	}

	public void actionPerformed(ActionEvent arg0) {

		int diag = fc.showOpenDialog(this);
		
		File f = fc.getSelectedFile();
		
		File plugfold = new File(System.getProperty("user.dir").concat(
				"/plugins/"));

		if (!plugfold.exists()) {
			plugfold.mkdir();
		}

		if (diag == JFileChooser.APPROVE_OPTION) {
			
			if (getExtension(f).equalsIgnoreCase("jar")) {
				try {
					FileInputStream in = new FileInputStream(f);
					FileOutputStream out = new FileOutputStream(new File(
							plugfold.toString(), fc.getName(f)));
					byte[] buf = new byte[1024];
					int len;
					int i = 0;
					while ((len = in.read(buf)) > 0) {

						out.write(buf, 0, len);

					}
					gui.printString(fc.getName(f).replace(".jar", "")
							+ " installed, reload to take effect.");
					in.close();
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else{
				System.out.println("Not jar");
			}
		}

	}
	 public static String getExtension(File f) {
	        String ext = null;
	        String s = f.getName();
	        int i = s.lastIndexOf('.');

	        if (i > 0 &&  i < s.length() - 1) {
	            ext = s.substring(i+1).toLowerCase();
	        }
	        return ext;
	    }

}
