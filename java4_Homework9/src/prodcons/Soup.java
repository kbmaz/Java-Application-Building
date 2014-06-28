package prodcons;

import java.util.*;

public class Soup {
  
  private ArrayList<String> buffer = new ArrayList<String>();   //buffer holds what is in the soup
  private int capacity = 6;
  
  public synchronized String eat() {                            //synchronized makes others BLOCKED
    while(buffer.isEmpty()) {                                   //cannot eat if nothing is there, so check to see if it is empty
      try {
        wait();                                                 //if so, we WAIT until someone puts something there
      } catch(InterruptedException e) {}                        //doing so temporarily allows other synchronized methods to run(specifically-add)
    }                                                           //we won't get out of this while until somthing is there to eat
    String toReturn = buffer.get((int)(Math.random()*buffer.size())); //get a random alphabet in the soup
    buffer.remove(toReturn);                                          //remove it so no one else can eat it
    buffer.trimToSize();                                              //reduce the size of the buffer to fit how many pieces are there
    notifyAll();                                                      //tell anyone WAITING that we have eaten something and are done
    return(toReturn);                                                 //return the alphabet piece to the consumer who asked to eat it
  }
  
  public synchronized void add(String c) {                      //synchronized makes others BLOCKED                                                         //we will not get out of this while until something has been eaten to make room
    while(buffer.size()==capacity) {
      try {
        wait();
      } catch(InterruptedException e) {}
    }
    buffer.add(c);                                              //add another alphabet piece to the soup
    notifyAll();                                                //tell anyone WAITING that we have added something and are done
  }

  public synchronized Object[] getContents() {                  //see ArrayList about ConcerrentModificationException
    Object[] temp = buffer.toArray();                           //check out API for ArrayList to see this toArray() method
    return (temp);                                              //make a clean copy so contents do not change when getting &/or displaying it
  }
  
  /*public ArrayList<String>getContents(){
    return(buffer);
  }*/
  public void emptyBuffer() {
    buffer.clear();
  }
}
