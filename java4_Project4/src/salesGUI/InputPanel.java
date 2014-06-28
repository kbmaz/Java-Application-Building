package salesGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class InputPanel extends JPanel implements ActionListener {
  JPanel topPanel, middlePanel, bottomPanel, leftPanel, rightPanel;
  SalesApp app;
  JLabel prompt, doneLabel, jlSalesBar;
  JLabel[] jlSales;
  JButton done;
  JTextField[] jtfSales;
  JTextField jtfSalesBar;
  int numPeople;
  int [] sales;
  int goal;
  
  public InputPanel(SalesApp container, int numPeople, int gridX) {
    this.app = container;
    this.numPeople = numPeople;
    sales = new int[numPeople];
    this.setLayout(new BorderLayout());
    topPanel = new JPanel();
    topPanel.setLayout(new FlowLayout());
    middlePanel = new JPanel(new GridLayout(numPeople, gridX));
    bottomPanel = new JPanel();
    bottomPanel.setLayout(new FlowLayout());
    leftPanel = new JPanel();
    rightPanel = new JPanel();
    add("North", topPanel);
    add("Center", middlePanel);
    add("South", bottomPanel);
    add("East", rightPanel);
    add("West", leftPanel);
    jlSales = new JLabel[numPeople];
    jtfSales = new JTextField[numPeople];
    prompt = new JLabel("Give values for each salesperson:");
    topPanel.add(prompt);
    
    for (int x=0; x<numPeople; x++) {
      jlSales[x] = new JLabel("SalesPerson " + (x+1));
      jtfSales[x] = new JTextField("0", 8);
      middlePanel.add(jlSales[x]);
      middlePanel.add(jtfSales[x]);
    }
    jlSalesBar = new JLabel("Enter a value for the sales goal");
    bottomPanel.add(jlSalesBar);
    jtfSalesBar = new JTextField("0",8);
    bottomPanel.add(jtfSalesBar);
    doneLabel = new JLabel("Click when all are entered:");
    bottomPanel.add(doneLabel);
    done = new JButton("All Set");
    bottomPanel.add(done);
    done.addActionListener(this);
  }
  
  public void actionPerformed(ActionEvent event) {
    if(event.getSource() instanceof JButton) {
      if((JButton)event.getSource() == done) {
        for(int x=0; x<numPeople; x++) {
          sales[x]   = Integer.parseInt(jtfSales[x].getText());
        }
        app.setSales(sales);
        goal = Integer.parseInt(jtfSalesBar.getText());
        app.setSalesBar(goal);
        } 
      }
    }
  }

