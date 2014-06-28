package salesGUI;

import javax.swing.*;
import java.text.DecimalFormat;

public class OutputPanel extends JPanel {
  
  JLabel jlSalesOutput;
  JPanel leftPanel, rightPanel;
  JLabel jlSalesBar;
  JTextField jtfSalesBar;
  JButton done;
  SalesApp app;
  int salesBar;
  int[] sales;
  
  public OutputPanel(SalesApp container) {
    app = container;
    sales = app.getSales();
    leftPanel = new JPanel();
    rightPanel = new JPanel();
    add("East", rightPanel);
    add("West", leftPanel);
    jlSalesOutput = new JLabel();
    rightPanel.add(jlSalesOutput);
    jlSalesOutput.setText("");
  }
  
  public void refreshOutput() {
    jlSalesOutput.setText("");
  }
  
  protected void writeOutputMaxMin() {
    DecimalFormat df1 = new DecimalFormat("####.##");
    app.calculateMinMax();
    
    int min = sales[0];
    int max = sales[0];
    
    String txtOutput = "<html>Max Min Figures<br>________________________<br>";;
    
    for(int x = 0; x < sales.length; x++) {
      if(sales[x] > max) {
        max = sales[x];
      }
      else if(sales[x] < min) {
        min = sales[x];
      }
    }
    txtOutput += "<br>The lowest sales belongs to sales person " + 
        (app.getMin() + 1) + " with $" + sales[app.getMin()] + "<br>";
    txtOutput += "<br>The highest sales belongs to sales person " + 
        (app.getMax() + 1) + " with $" + sales[app.getMax()] + "<br>";
    
    //txtOutput += createSalesBarInfo();
    txtOutput += "</html>";
    
    jlSalesOutput.setText(txtOutput);
    validate();
    repaint();
  }
  protected void writeOutput() {
    DecimalFormat df1 = new DecimalFormat("####.##");
    //Build the output string like an HTML doc
    String txtOutput = 
        "<html>Sales Figures<br>________________________<br>";
    for(int x = 0; x < sales.length; x++) {
      txtOutput += "Sales Person " + (x + 1) + ": $" + sales[x] + "<br>";
    }
    txtOutput += "<br>The total sales were: $" + 
        app.getTotalSales() + "<br>";
    txtOutput += "The average sales was: $" + df1.format(app.getAverage()) +
        "<br><br>";
    txtOutput += createSalesBarInfo();
    txtOutput += "</html>";
    
    jlSalesOutput.setText(txtOutput);
    validate();
    repaint();
  }
  
  protected String createSalesBarInfo() {
    String salesBarOutput = "";
    int overSalesBar = 0;
    int [] performance = app.determineTopSalesPeople();
    int[] sales = app.getSales();
    DecimalFormat df1 = new DecimalFormat("####.##");
    
    for (int x = 0; x < sales.length; x++) {
      if(performance[x] == 1) {
        overSalesBar++;
        salesBarOutput += "Sales person " + (x + 1) +
            " sold more than the sales goal with sales of $" + df1.format(sales[x])+ "<br>";
      }
      else if(performance[x] == 0) {
        salesBarOutput += "Sales person " + (x + 1) +
            " exactly reached the sales goal with sales of $" + df1.format(sales[x])+ "<br>";
      }
      else if(performance[x] == -1) {
        salesBarOutput += "Sales person " + (x + 1) +
            " did not sell more than the sales goal, with sales of $" + df1.format(sales[x]) + "<br>";
      }
    }
       if(overSalesBar == 1) 
        salesBarOutput += "Only " + overSalesBar +
            " salesperson sold more than the sales goal of $" + df1.format(app.getBar()) + "<br><br>";
        else 
          salesBarOutput += overSalesBar +
          " sales people sold more than the sales goal of $" + df1.format(app.getBar()) + "<br><br>"; 
        return salesBarOutput;
      }
}
