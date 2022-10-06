import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.DefaultCaret;

public class lab2 extends JFrame implements KeyListener, Runnable {

    private static final long serialVersionUID = 1L;
    /**
     * 
     */
    private static JTextArea output;

    private static Thread t1;
    private static Thread t2;
    private static lab2 inp;

    public lab2(String name) {
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

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    //
    public void keyReleased(KeyEvent e) {
    }

    public static void main(String[] args) {
        lab2.inp = new lab2("A JFrame and KeyListener Demo");
        inp.addWindowListener(
                new WindowAdapter() {
                    public void windowClosing(WindowEvent e) {
                        System.exit(0);
                    }
                });

        t1 = new Thread(lab2.inp, "Printer");
        t2 = new Thread(lab2.inp, "Writer");

        t1.start();
        t2.start();
    }

    @Override
    public void run() {
        for (int i = 0; i <= 50; i++) {
            try {
                Thread.sleep(100);
                if (i == 26 & Thread.currentThread().getName() == "Writer") {
                    output.append("Printer got his work half done! \n");
                    t2.interrupt();
                } else if (Thread.currentThread().getName() == "Printer") {
                    output.append("Message i = " + i + ", from Thread " + Thread.currentThread().getName() + "\n");
                }
            } catch (InterruptedException e1) {
                if (Thread.currentThread().getName() == "Writer") {
                    output.append("Waiter has done its work, terminating. \n");
                }
            }
        }
        t1.interrupt();
        output.append("Both Waiter and Printer have finished their work! \n");
    }
}
