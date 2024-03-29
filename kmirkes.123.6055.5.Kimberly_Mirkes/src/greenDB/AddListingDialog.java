package greenDB;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/* class AddListingDialog */
/**
 * A dialog box for adding a new listing to the Listings table.  The AddListingDialog has text
 * fields for gathering the new listing's last name, first name, and phone number.  All of the text
 * fields are assigned an InputListener, which is responsible for enable the "Add" button once 
 * all fields contain data.  This dialog box is displayed when the user clicks the "+" button on
 * the application frame window.
 * 
 * @author David Green
 * @version 1.0
 */
public class AddListingDialog extends JDialog {
  /** A text field for entering the new phone listing's last name */
  private JTextField lNameField = new JTextField(16);
  /** A text field for entering the new phone listing's last name */
  private JTextField fNameField = new JTextField(16);
  /** A text field for entering the new phone listing's last name */
  private JTextField areaCodeField = new JTextField(2);
  /** A text field for entering the new phone listing's last name */
  private JTextField prefixField = new JTextField(2);
  /** A text field for entering the new phone listing's last name */
  private JTextField suffixField = new JTextField(3);
  /** A button which, when clicked, will add the new listing to the Listings table */
  private JButton addButton;
  
  /* AddListingDialog */
  /**
  * The AddListingDialog constructor. Creates a dialog box for adding a new listing to the
  * Listings table.
  * @param owner the Frame from which the dialog is displayed.
  */
  public AddListingDialog(final JFrame owner) {
    // set the dialog title and size
    super(owner, "Add Listing", true);
    setSize(280, 150);
    
    // Create the center panel which contains the field for entering the new listing
    JPanel center = new JPanel();
    center.setLayout(new GridLayout(3, 2));
    center.add(new JLabel(" Last Name:"));
    center.add(lNameField);
    center.add(new JLabel(" First Name:"));
    center.add(fNameField);
    
    // Here we create a panel for the phone number fields and add it to the center panel.
    JPanel pnPanel = new JPanel();
    pnPanel.add(new JLabel("("));
    pnPanel.add(areaCodeField);
    pnPanel.add(new JLabel(") "));
    pnPanel.add(prefixField);
    pnPanel.add(new JLabel("-"));
    pnPanel.add(suffixField);
    center.add(new JLabel(" Phone Number:"));
    center.add(pnPanel);
    
    // Create the south panel, which contains the buttons
    JPanel south = new JPanel();
    addButton = new JButton("Add");
    JButton cancelButton = new JButton("Cancel");
    addButton.setEnabled(false);
    south.add(addButton);
    south.add(cancelButton);
    
    // Add listeners to the fields and buttons
    lNameField.getDocument().addDocumentListener(new InputListener());
    fNameField.getDocument().addDocumentListener(new InputListener());
    areaCodeField.getDocument().addDocumentListener(new InputListener());
    prefixField.getDocument().addDocumentListener(new InputListener());
    suffixField.getDocument().addDocumentListener(new InputListener());
    
    areaCodeField.getDocument().addDocumentListener(new PhoneDocumentListener(areaCodeField, 3));
    prefixField.getDocument().addDocumentListener(new PhoneDocumentListener(prefixField, 3));
    suffixField.getDocument().addDocumentListener(new PhoneDocumentListener(suffixField, 4));
    
     
    areaCodeField.addFocusListener(new PhoneFocusListener());
    prefixField.addFocusListener(new PhoneFocusListener());
    suffixField.addFocusListener(new PhoneFocusListener());
    
    // listeners to close the window
    addButton.addActionListener(
        new ActionListener() {
          public void actionPerformed(ActionEvent aEvent) {
      
            DatabaseManager ownersDB = ((PhoneBookFrame)owner).getDBManager();
            ownersDB.doInsertQuery(buildQuery());
            dispose();
          }
        });
    
    cancelButton.addActionListener(
        new ActionListener() {
          public void actionPerformed(ActionEvent aEvent) {
            dispose();
          }
        });
    
    // Add the panel to the dialog window
    Container contentPane = getContentPane();
    contentPane.add(center, BorderLayout.CENTER);
    contentPane.add(south, BorderLayout.SOUTH);
  }
  
  /* buildQuery */
  /**
   * This method builds an insert statement for inserting a new record into the Listings table.
   * The insert statement is returned as a string. The insert statement will include the last name,
   * first name, area code, prefix, and extension that the user entered in the add listing dialog
   * box.
   * <pre>
   * PRE:  All of the fields in the Add Listing Dialog box contain data.
   * POST: A SQL insert statement is returned that inserts a new listing into the Listings table.
   * </pre>
   * @return a SQL insert statement that will insert a new listing in the Listings table.
   */
  public String buildQuery() {
    // Get the data entered by the user, trim the white space and change to upper case
    String query = "";
    String last = lNameField.getText().trim().toUpperCase();
    String first = fNameField.getText().trim().toUpperCase();
    String ac = areaCodeField.getText().trim().toUpperCase();
    String pre = prefixField.getText().trim().toUpperCase();
    String sfx = suffixField.getText().trim().toUpperCase();
    
    // Replace any single quote chars with a space char so the string will not get truncated by SQL
    last = last.replace('\'', ' ');
    first = first.replace('\'', ' ');
    ac = ac.replace('\'', ' ');
    pre = pre.replace('\'', ' ');
    sfx = sfx.replace('\'', ' ');
    
    // build and return the insert statement
    return new String("insert into Listings values (null, '" + last + "', '" + 
        first + "', '" +
        ac + "', '" +
        pre + "', '" +
        sfx + "')");
  }
  
  /* inner class InputListener */
  /**
   * This inner class is a Listener for the text fields of the Add Listing Dialog Box.
   * The listener keeps track of whether all fields (last name, first name, area code,
   * prefix, and extension) have data entered in them. If all fields contain data, the
   * "Add" button of the Add Listing Dialog box is enabled for the user. If any one of
   * the fields is empty or if the phone number fields contain fewer characters than
   * required, the "Add" button is unavailable.
   *
   * @author David Green
   * @version 1.0
   */
  class InputListener implements DocumentListener {

    /* insertUpdate */
    /**
     * This method is called when data is put in the text field, either by typing or by a paste operation.
     * This method tracks the number of characters entered in the field. If all fields, last name,
     * first name, area code, prefix, and extension have data and the phone number fields contain the correct
     * number of characters (that is, 3 characters for area code and prefix and 4 characters for extension),
     * then the Add Listing Dialog box "Add" button is enabled.
     * <pre>
     * PRE:
     * POST: Add Listing Dialog Box "Add" button is enabled if all fields have the required number of characters
     * entered.
     * </pre>
     * @param dEvent the document event
     */
    public void insertUpdate(DocumentEvent dEvent) {
      // If first name and last name have data and phone number is complete
      // enable the add button, give it focus and make it clickable if
      // user presses Enter.
      if (lNameField.getDocument().getLength() > 0 &&
          fNameField.getDocument().getLength() > 0 &&
          areaCodeField.getDocument().getLength() == 3 &&
          prefixField.getDocument().getLength() == 3 &&
          suffixField.getDocument().getLength() == 4) {
        
        addButton.setEnabled(true);
        if (dEvent.getDocument() == suffixField.getDocument()) {
          addButton.requestFocus();
          getRootPane().setDefaultButton(addButton);
        }
      }
    }

    /* removeUpdate */
    /**
     * This method is called when data is removed from the text field, either by backspacing or highlighting and
     * deleting. This method will track the number of characters removed from the field. If any of the fields
     * last name, first name, area code, prefix, and extension contain less than the required number of characters
     * the "Add" button of the Add Listing Dialog box is disabled.
     * <pre>
     * PRE:
     * POST: Add Listing Dialog Box "Add" button is disabled if any of the fields contain less than the required
     *       number of characters.
     * </pre>
     * @param dEvent the document event
     */
    public void removeUpdate(DocumentEvent dEvent) {
      // If last name or first name don't have data or phone number
      // is not complete, disable the Add button.
      if (lNameField.getDocument().getLength() == 0 ||
          fNameField.getDocument().getLength() == 0 ||
          areaCodeField.getDocument().getLength() < 3 ||
          prefixField.getDocument().getLength() < 3 ||
          suffixField.getDocument().getLength() < 4)
        
        addButton.setEnabled(false);
    }
    
    /** Not implemented */
    public void changedUpdate(DocumentEvent dEvent) {}
    
  } //End InputListener inner class
} //End AddListingDialog class
