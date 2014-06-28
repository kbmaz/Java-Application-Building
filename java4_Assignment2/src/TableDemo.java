import java.awt.*;
import javax.swing.*;


public class TableDemo extends JFrame {
  public TableDemo() throws HeadlessException{
    initializeUI();
  }
  
  private void initializeUI() {
    String[] columnNames = {"First Name", "Last Name", "Birthday", "Phone", "Address", "City", "Zip"};
  
  Object[][] rowData = {
      {"Kimberly", "Mirkes", "10/12/72", "555-555-5555", "123 Road", "Saint Louis", "63112"},
      {"Brady", "Mirkes", "1/25/71", "555-555-5555", "123 Road", "Saint Louis", "63112"},
      {"Marsha", "Brown", "4/5/56", "123-456-7890", "256 Hwy", "Kewaunee", "54216"},
      {"John", "Biely", "12/7/80", "423-583-1849", "8473 Court", "Denmark", "54213"},
      {"Ruth", "Johnson", "1/10/53", "593-950-4983", "534 Jefferson", "Sturgeon Bay", "54235"}
    };

  JTable table = new JTable(rowData, columnNames);
  JScrollPane pane = new JScrollPane(table);
  
  setTitle("JTable Demo");
  setSize(new Dimension(400,200));
  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  
  setLayout(new BorderLayout());
  
  getContentPane().add(pane, BorderLayout.CENTER);
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        new TableDemo().setVisible(true);
      }
    });
  }
}

