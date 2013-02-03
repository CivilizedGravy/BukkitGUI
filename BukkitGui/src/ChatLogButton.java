import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class ChatLogButton extends JFrame implements ActionListener {

	public JTextArea msgLog;
	public JScrollPane sbrText; // Scroll pane for text area
	public int chatscrl = 300;
	public JPanel p = new JPanel();
	public ChatLogButton(String title ,int width , int height) {
		super(title);
		setSize(width, height); //400 300
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
		sbrText.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		msgLog.setPreferredSize(new Dimension(width- 10, chatscrl));
		sbrText.setPreferredSize(new Dimension(width - 10, height- 40));
		msgLog.setLineWrap(true);
		msgLog.setText("\t  ~Bukkit Gui Chat Log~\n");
		
		p.add(sbrText);
		getContentPane().add(p, BorderLayout.CENTER);

		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				setVisible(false);
			}
		});
		
		setVisible(false);
	}


	public void actionPerformed(ActionEvent arg0) {
		//new ChatLogButton();
		setVisible(true);
		

	}

	int i = 800;

	public void printString(String text) {

		msgLog.append(text);
		msgLog.append("\n");
		//msgLog.setPreferredSize(new Dimension(400, i += 15));
		msgLog.getCaret().setDot(msgLog.getText().length());
		sbrText.scrollRectToVisible(msgLog.getVisibleRect());

	}
}
