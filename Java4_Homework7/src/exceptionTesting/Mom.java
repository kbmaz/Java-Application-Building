package exceptionTesting;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Mom {
  
  public void getToDoHomework() throws FileNotFoundException{
    FileFetcher testMe = new FileFetcher();
    try {
      testMe.getHomework();
    }
    catch(IOException e) {
      System.out.println("Mom caught the I/O Exception.");
    }
  }
  
  public static void main(String[] args)throws IOException {
    //Note: This is VERY BAD programming.  Do no throw exceptions in main methods.
    Mom parent1 = new Mom();
    parent1.getToDoHomework();
  }

}
