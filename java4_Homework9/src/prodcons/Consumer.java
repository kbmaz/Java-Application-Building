package prodcons;

public class Consumer extends Thread {
  private Soup soup;
  private MyTableSetting bowlView;
  private Thread consumerThread;
  
  public Consumer(MyTableSetting bowl, Soup s) {
    bowlView = bowl;                              //the consumer is given the GUI that will show what is happening
    soup = s;                                     //the consumer is given the soup--the monitor
  }
  
  public void run() {
    String c;
    while(consumerThread != null) {
    for(int i = 0; i < 10; i++) {                 //stop thread when know there are no more coming; here we know there will only be 10
      c = soup.eat();                             //eat it from the soup
      System.out.println("Ate a letter: " + c);   //show what happened in Console
      bowlView.repaint();                         //show it in the bowl
      
      try {
        sleep((int)(Math.random()*3000));         //have consumer sleep a little longer or somtimes we never see the alphabets
      } catch(InterruptedException e) {}
    }
    }
  }
  public void start() {
    if(consumerThread == null) {
      consumerThread = new Thread(this);
      consumerThread.start();
    }
  }
  public void stopThread() {
    consumerThread = null;
  }
}
