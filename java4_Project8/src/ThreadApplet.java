import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class ThreadApplet extends Applet implements Runnable {
  
  Thread t1 = new Thread(this);
  Thread t2 = new Thread(this);
  Thread t3 = new Thread(this);
  
  TextArea TA1 = new TextArea(15,50);
  TextArea TA2 = new TextArea(15,50);
  TextArea TA3 = new TextArea(15,50);
  
  public void init() {
    resize(600,750);
    JFrame frame = new JFrame("Three Threads");
    frame.setLayout(new BorderLayout());
    
    ButtonPanel button = new ButtonPanel();
    frame.add(button);
    frame.pack();
    frame.setVisible(true);
    add(TA1); 
    add(TA2); 
    add(TA3);
  }
  
  public void run() {
  }
    
  private class ButtonPanel extends JPanel {
    public ButtonPanel() {
      JButton T1Btn = new JButton("Thread 1");
      JButton T2Btn = new JButton("Thread 2");
      JButton T3Btn = new JButton("Thread 3");
      
      add(T1Btn);
      add(T2Btn);
      add(T3Btn);
      
      T1Btn.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          try {
            t1.start();
            TA1.append("\nThread 1 started at " + new Date().toString());
            System.out.println("\nThread 1 started at " + new Date().toString());
          for(int i = 0; i < 4; i++) {
            long start = (System.currentTimeMillis());
            t1.sleep((int)(Math.random() * 1000));
            TA1.append("\nThread 1 has slept for " + (System.currentTimeMillis() - start) + "ms\nand time is now " + new Date().toString());
            System.out.println("\nThread 1 has slept for " + (System.currentTimeMillis() - start) + "ms\nand time is now " + new Date().toString());
          }
          }
          catch(IllegalThreadStateException ex) {
            System.out.println("Thread has already begun.");
          }
          catch(InterruptedException ex) {
            ex.printStackTrace();
          }
        }
      });
      
      T2Btn.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          try {
            t2.start();
            TA2.append("\nThread 2 started at " + new Date().toString());
            System.out.println("\nThread 2 started at " + new Date().toString());
          for(int i = 0; i < 4; i++) {
            long start = (System.currentTimeMillis());
            t2.sleep((int)(Math.random() * 500));
            TA2.append("\nThread 2 has slept for " + (System.currentTimeMillis() - start) + "ms\nand time is now " + new Date().toString());
            System.out.println("\nThread 2 has slept for " + (System.currentTimeMillis() - start) + "ms\nand time is now " + new Date().toString());
          }
          }
          catch(IllegalThreadStateException ex) {
            System.out.println("Thread has already begun.");
          }
          catch(InterruptedException ex) {
            ex.printStackTrace();
          }
        }
      });
      
      T3Btn.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          try {
            t3.start();
            TA3.append("\nThread 3 started at " + new Date().toString());
            System.out.println("\nThread 3 started at " + new Date().toString());
          for(int i = 0; i < 4; i++) {
            long start = (System.currentTimeMillis());
            t3.sleep((int)(Math.random() * 1500));
            TA3.append("\nThread 3 has slept for " + (System.currentTimeMillis() - start) + "ms\nand time is now " + new Date().toString());
            System.out.println("\nThread 3 has slept for " + (System.currentTimeMillis() - start) + "ms\nand time is now " + new Date().toString());
          }
          }
          catch(IllegalThreadStateException ex) {
            System.out.println("Thread has already begun.");
          }
          catch(InterruptedException ex) {
            ex.printStackTrace();
          }
        }
      });
    }
  }
  
}
