package greenDB;

import javax.swing.JFrame;

/* class SimplePhoneBook */
/**
 * This is the entry point of the SimplePhoneBook application.  SimplePhoneBook is a Java application
 * that uses JDBC to store, retrieve, add and delete phone number listings in a mySQL
 * database.  The SimplePhoneBook class instantiates the application window frame and displays it
 * on screen.
 * 
 * @author David Green
 * @version 1.0
 */
public class SimplePhoneBook {
  /* main */
  /**
   * The entry point for the SimplePhoneBook application.  The main method instantiates the
   * applications's frame window and displays it.
   * <pre>
   * PRE:
   * POST: SimplePhoneBook started.
   * </pre>
   * @param args  not used.
   */
  public static void main(String[] args) {    
    // Instantiate the phone book frame window and display it.
    
    PhoneBookFrame pbFrame = new PhoneBookFrame();
    pbFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    pbFrame.setVisible(true);
  }
}  // End SimplePhoneBook class
