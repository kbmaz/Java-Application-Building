package exceptionTesting;

import java.io.FileNotFoundException;


public class Dad {
  
  public void parentalCollaboration() {
    Mom spouse = new Mom();
    try {
      spouse.getToDoHomework();
    }
    catch(FileNotFoundException e) {
      System.out.println("Dad caught the File Not Found Exception.");
    }
  }
  
  public static void main(String[] args) {
    Dad parent2 = new Dad();
    parent2.parentalCollaboration();
  }

}
