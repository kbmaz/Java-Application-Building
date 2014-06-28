package salesGUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SalesUserInterface extends JFrame {
  SalesApp app;
  JMenuBar mb;
  JMenu m, m1, data;
  JMenuItem q, r, s, t;
  InputPanel savedInputPanel;
  static InputPanel inputPanel, previousInputPanel;
  JLabel peopleLabel;
  JTextField peopleField;
  JButton jbNumPeople, done;
  int numPeople;
  boolean processed;  
  
  public SalesUserInterface(SalesApp myApp) {
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    app = myApp;
    app.setMyUserInterface(this);
    setLayout(new BorderLayout());
    setPreferredSize(new Dimension(600, 600));
    mb = new JMenuBar();
    setJMenuBar(mb);
    m = new JMenu("File");
    data = new JMenu("Options");
    mb.add(m);
    mb.add(data);
    data.add(r = new JMenuItem ("Reset"));
    data.add(s = new JMenuItem("Retrieve Previous"));
    data.add(t = new JMenuItem("Return"));
    m.add(q = new JMenuItem("Exit"));
    
    q.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        System.exit(0);
      }
    });

    r.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        Reset();
        }    
    });
    
    s.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        RetrievePrevious();
      }
    });
    
    t.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        Return();
      }
    });
    
    InitPanel specifyNumber = new InitPanel();
    add("North", specifyNumber);
    
    pack();
    setVisible(true);
  }
  
  public void Reset() {
      System.out.println("reset data");
      peopleField.setText(null);
      inputPanel.jtfSalesBar.setText("0");    
      inputPanel.removeAll();
      previousInputPanel.removeAll();
      inputPanel.updateUI();
      inputPanel.repaint();
      app=new SalesApp();
    }
    
    public void RetrievePrevious() {
      if (inputPanel !=null && previousInputPanel !=null) {
        remove(inputPanel);
        add(previousInputPanel, BorderLayout.CENTER);
        validate();
        repaint();
      }
      else {
        System.out.println("A set of sales data has not been stored yet");
      }
    }
    
    public void Return() {
      remove(previousInputPanel);
      add(inputPanel, BorderLayout.CENTER);
      validate();
      repaint(); 
    }
    
  private class InitPanel extends JPanel{
    public InitPanel() {
      peopleLabel = new JLabel("Enter the number of sales people");
      add(peopleLabel);
      peopleField = new JTextField(5);
      add(peopleField);
      jbNumPeople = new JButton("Submit");
      add(jbNumPeople);
      jbNumPeople.addActionListener(new NumSalesPeopleListener());
    }
  }
  
  private class NumSalesPeopleListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
      if(inputPanel != null) {
        previousInputPanel = inputPanel;
        remove(inputPanel);
        app = new SalesApp();
      }
      numPeople = Integer.parseInt(peopleField.getText());
      inputPanel = new InputPanel(app, numPeople, 2);
      add("Center", inputPanel);
      SalesUserInterface.this.validate();
    }
  }
}

