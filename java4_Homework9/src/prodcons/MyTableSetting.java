package prodcons;

import java.applet.Applet;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyTableSetting extends Applet {
  Soup s;
  Producer p1;                            //we need as Instance Variables so we can access outside of the init()
  Consumer c1;
  MyTableSetting thisTable = this;
  int bowlLength = 150;
  int bowlWidth = 220;
  int bowlX = 60;
  int bowlY = 10;
  String justAte;
  
  public void init() {
    setSize(400,200);
    s = new Soup();
    p1 = new Producer(this, s);                                   //don't declare here again or it is only local
    c1 = new Consumer(this, s);
    
    Button start = new Button("Start");
    Button stop = new Button("Stop");
    Panel bottom = new Panel();
    
    setLayout(new BorderLayout());
    bottom.add(stop);
    bottom.add(start);
    add("South", bottom);
    
    p1.start();
    c1.start();
  
  stop.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
      s.emptyBuffer();
      
      if(c1.getState() == Thread.State.RUNNABLE) {
        c1.stopThread();
        p1.stopThread();
        repaint();
      }
    }
  });
  
  start.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
      p1 = new Producer(thisTable, s);
      c1 = new Consumer(thisTable, s);
      
      p1.start();
      c1.start();
    }
  });
}
  public void paint(Graphics g) {
    int x;
    int y;
    g.setColor(Color.orange);
    g.fillOval(bowlX, bowlY, bowlWidth, bowlLength);
    g.setColor(Color.cyan);
    g.fillOval(10, 25, 40, 55);
    g.fillOval(25, 80, 8, 75);
    g.drawOval(bowlX, bowlY, bowlWidth, bowlLength);
    Font standardFont = getFont();                                //tell what just ate in spoon
    Font bigFont = new Font("Helvetica", Font.BOLD, 20);
    g.setFont(bigFont);
    if(justAte != null) {
      g.drawString(justAte, 25, 55);
      justAte = null;
    }
    else {
      g.setFont(standardFont);
      g.drawString("waiting", 13, 55);
      g.setFont(bigFont);
    }
    Object[] contents = s.getContents();                              //bring back fresh array of Object
    for(Object each : contents) {                                     //no longer tied in memory to buffer in soup
      x = bowlX + bowlWidth/4 + (int)(Math.random()*(bowlWidth/2));
      y = bowlY + bowlLength/4 + (int)(Math.random()*(bowlLength/2));
      g.drawString((String)each, x, y);                               //show alphabet piece being eaten
    }

    if(c1.getState()==Thread.State.TIMED_WAITING) {                   //note access to enumerated types for Thread States
      checkState();                                                   //get last repaint() if so see TERMINATED
    }
  }
  
  public void recentlyAte(String morsel) {
    justAte = morsel;
  }
  
  public void checkState() {
    try{Thread.sleep(1000);
  }catch(InterruptedException e) {}                                   //even Applet has a Thread, this command puts this (Applet's) Thread to sleep
  repaint();
  }
}

