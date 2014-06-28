package greenDB;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class PasswordDialog extends JDialog implements ActionListener {
  private JTextField user;
  private JPasswordField password;
  private String username, passwd;
  private static String[] info;
  private static boolean set = false;
  
  public PasswordDialog(final JFrame owner) {
    // set the dialog title and size
    super(owner, "Login", true);
    setSize(280, 150);
    user = new JTextField(10);
    user.addActionListener(this);
    password = new JPasswordField(10);
    password.addActionListener(this);
    // Create the center panel which contains the fields for entering information
    JPanel center = new JPanel();
    center.setLayout(new GridLayout(3,2));    // 3 rows leaves a nice space between
    center.add(new JLabel(" Enter UserName:"));
    center.add(user);
    center.add(new JLabel(" Enter Password"));
    center.add(password);
    // Create the south panel which contains the buttons
    JPanel south = new JPanel();
    JButton submitButton = new JButton("Submit");
    submitButton.setActionCommand("SUBMIT");
    submitButton.addActionListener(this);
    
    JButton helpButton = new JButton("Help");
    south.add(submitButton);
    south.add(helpButton);
    // Add listeners to the help button
    helpButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent aEvent) { // The user has asked for help
        JOptionPane.showMessageDialog(owner,
            "You username and password are the same as those\n" + 
            "you use to access your O'Reilly School of Technology courses.\n");
      }
    });
    // Add the panels to the dialog window
    Container contentPane = getContentPane();
    contentPane.add(center, BorderLayout.CENTER);
    contentPane.add(south, BorderLayout.SOUTH);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    String cmd = e.getActionCommand();
    if ("SUBMIT".equals(cmd)) {   // process the inputs
      username = user.getText();
      char[] input = password.getPassword();
      passwd = new String(input);
      // to verify it is working, uncomment this line
//      System.out.println("User is " + username + ", password is " + passwd);
      info = new String[2];
      info[0] = username;
      info[1] = passwd;
      set = true;                 // now can send info back
      dispose();
    }
  }
  
  public static void main(String[] args) {    // create the frame first and then give it that frame as owner
    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    final PasswordDialog addPassword = new PasswordDialog(frame);
    addPassword.setVisible(true);
    System.exit(0);         // terminates
  }
  
  public static String[] login(Object sender) {   // object who requested login is the sender
    JFrame frame = new JFrame();                  // attempt is to make as reusable as possible
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    final PasswordDialog addPassword = new PasswordDialog(frame);
    addPassword.setVisible(true);
    while (!set)                                  // wait until user has put information in before return values
      try {
        Thread.sleep(5000);
      } catch (InterruptedException e) {};
    return info;
  }

}
