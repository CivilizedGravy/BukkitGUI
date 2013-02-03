import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class BukkitGui extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JButton stserver;
	public JButton stopserver;
	public JButton reload;
	public JButton chatlog;
	public int scrl = 300;
	public JPanel jp = new JPanel();
	
	
	//log
	public JPanel list = new JPanel();
	

	public JTextArea msgLog;
	public JTextField cmd;
	public  JScrollPane sbrText;
	public ChatLogButton cb = new ChatLogButton("Chat Log", 400, 300);

	public BukkitGui() {

		addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent e) {
				try {

					BufferedWriter writer = new BufferedWriter(
							new OutputStreamWriter(
									StartBukkitServerEvent.p.getOutputStream()));
					String input = "stop";
					input += "\n";

					writer.write(input);
					writer.flush();
				} catch (Exception en) {
					// TODO Auto-generated catch block

					System.exit(0);
				}
				System.exit(0);
			}
		});

		
		
		jp.setLayout(new FlowLayout());
		list.setLayout(new FlowLayout());
		setLayout(new FlowLayout());
		DownloadBukkitListener dl = new DownloadBukkitListener(this);
		if (!new File(System.getProperty("user.dir").concat(
				"/Bukkit/craftbukkit.jar")).exists()) {
			System.out.println(System.getProperty("user.dir").concat(
					"/Bukkit/craftbukkit.jar"));
			dl.dlBukkit();
		}

		//setResizable(false);
		setSize(650, 436);

		setTitle("BukkitGui");
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
		msgLog = new JTextArea();
		sbrText = new JScrollPane(msgLog);
		cmd = new JTextField();
		sbrText.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		msgLog.setPreferredSize(new Dimension(630, scrl));
		msgLog.setEditable(false);
		sbrText.setPreferredSize(new Dimension(630, 300));
		cmd.setPreferredSize(new Dimension(630 , 25));
		cmd.addKeyListener(new KeyListener() {
			
			
			public void keyTyped(KeyEvent t) {
				
			}
			
			public void keyReleased(KeyEvent r) {
				
			}
			
			public void keyPressed(KeyEvent p) {
				if(p.getKeyCode() == KeyEvent.VK_ENTER){
					String input = new String();
					try {
						
						BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(StartBukkitServerEvent.p.getOutputStream()));
						input = cmd.getText();
						input += "\n";
						
							writer.write(input);
							writer.flush();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					cmd.setText("");
					System.out.println(input);
				}
			}
		});
		
		msgLog.setLineWrap(true);
		stserver = new JButton("Start Bukkit Server");
		stopserver = new JButton("Stop Server");

		reload = new JButton("Reload Server");
		//chatlog = new JButton("ChatLog");
		stserver.addActionListener(new StartBukkitServerEvent(this));

		stopserver.addActionListener(new StopServerEvent(this));
		reload.addActionListener(new ReloadSeverEvent(this));
		//chatlog.addActionListener(cb);

		stopserver.setEnabled(false);
		reload.setEnabled(false);

		jp.setPreferredSize(new Dimension(640, 60));
		list.setPreferredSize(new Dimension(640, 400));
		stserver.setPreferredSize(new Dimension(150, 50));

		stopserver.setPreferredSize(new Dimension(150, 50));
		reload.setPreferredSize(new Dimension(150, 50));
		//chatlog.setPreferredSize(new Dimension(150, 50));
		
		jp.add(stserver, BorderLayout.NORTH);
		//jp.add(chatlog, BorderLayout.NORTH);
		jp.add(stopserver, BorderLayout.NORTH);
		jp.add(reload, BorderLayout.NORTH);
		list.add(sbrText, BorderLayout.NORTH);
		list.add(cmd, BorderLayout.SOUTH);

		getContentPane().add(jp, BorderLayout.NORTH);
		getContentPane().add(list, BorderLayout.WEST);
		list.revalidate();
		// add memory allocation
		// add logger
		// add more commands
		// add list of online players

	}
	public void printString(String text) {

		msgLog.append(text);
		msgLog.append("\n");
		//msgLog.setPreferredSize(new Dimension(400, i += 15));
		msgLog.getCaret().setDot(msgLog.getText().length());
		sbrText.scrollRectToVisible(msgLog.getVisibleRect());

	}

	public static void main(String[] args) {
		BukkitGui bg = new BukkitGui();

		bg.setVisible(true);

	}
}
