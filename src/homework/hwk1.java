package homework;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.DefaultCaret;

public class hwk1 extends JFrame implements KeyListener, Runnable {

	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private static JTextArea output;

	private static Thread t1;
	private static String msg = "";
	private static hwk1 inp;
	private Boolean flag = false;

	public hwk1(String name) {
		super(name);
		// JTextArea output = new JTextArea(20,30) // Can this statement replace the
		// next one?

		output = new JTextArea(20, 30); // create JTextArea in which all messages are shown.
		DefaultCaret caret = (DefaultCaret) output.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE); // JTextArea always set focus on the last message appended.

		add(new JScrollPane(output)); // add a Scroll bar to JFrame, scrolling associated with JTextArea object
		setSize(500, 500); // when lines of messages exceeds the line capacity of JFrame, scroll bar scroll
							// down.
		setVisible(true);
		output.addKeyListener(this); // Adds the specified key listener to receive key events from this component.
	}

	public void inputter(KeyEvent e) {
		if (t1 != null) {
			flag = false;
			t1.interrupt();
			try {
				t1.join();
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}

		if (e.getKeyCode() != KeyEvent.VK_ENTER) {
			hwk1.msg += e.getKeyChar();
		} else if (e.getKeyCode() == KeyEvent.VK_ENTER && msg.length() > 0) {
			if (hwk1.msg.equals("exit")) {
				System.exit(0);
			}
			t1 = new Thread(hwk1.inp, hwk1.msg);
			hwk1.msg = "";
			t1.start();
			flag = true;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		inputter(e);
	}

	@Override
	//
	public void keyReleased(KeyEvent e) {
	}

	public static void main(String[] args) {
		hwk1.inp = new hwk1("A JFrame and KeyListener Demo");
		inp.addWindowListener(
				new WindowAdapter() {
					public void windowClosing(WindowEvent e) {
						System.exit(0);
					}
				});
	}

	@Override
	public void run() {
		while (flag) {
			try {
				Thread.sleep(1000);
				output.append(Thread.currentThread().getName() + "\n");
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
	}
}
