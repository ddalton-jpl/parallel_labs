package edu.ewu.ytian.displayinput;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.DefaultCaret;

public class Inputter extends JFrame implements KeyListener, Runnable {

	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private static JTextArea output;

	public Inputter(String name) {
		super(name);
		// JTextArea output = new JTextArea(20,30) // Can this statement replace the
		// next one?

		output = new JTextArea(20, 30); // create JTextArea in which all messages are shown.
		DefaultCaret caret = (DefaultCaret) output.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE); // JTextArea always set focus on the last message appended.

		//
		add(new JScrollPane(output)); // add a Scroll bar to JFrame, scrolling associated with JTextArea object
		setSize(500, 500); // when lines of messages exceeds the line capacity of JFrame, scroll bar scroll
							// down.
		setVisible(true);
		output.addKeyListener(this); // Adds the specified key listener to receive key events from this component.
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		output.append("Pressed a Key on Keyboard!\n");
	}

	@Override
	//
	public void keyReleased(KeyEvent e) {

		int keyCode = e.getKeyCode(); // study KeyEvent class API
		if (keyCode == KeyEvent.VK_ENTER)
			output.append("Key Released, you just pressed an Enter Key!\n");
		else
			output.append("Key Released, you just pressed the character \'" + e.getKeyChar() + "\'\n");
	}

	public static void main(String[] args) throws InterruptedException {
		Inputter inp = new Inputter("A JFrame and KeyListener Demo");
		inp.addWindowListener(
				new WindowAdapter() {
					public void windowClosing(WindowEvent e) {
						System.exit(0);
					}
				});

		Thread t1 = new Thread(inp, "Thread-1");
		Thread t2 = new Thread(inp, "Thread-2");
		t1.start();
		t2.start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(1000);
				output.append("Mesasge from thread -->" + Thread.currentThread().getName() + "\n");
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
	}
}
