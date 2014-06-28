package exceptionTesting;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class FileFetcher {
  String aLine = "";      //we will look at the file one line at a time
  FileReader myFile;      //declare FileReader and BufferedReader as instance variables
  BufferedReader in;
  
  public void getHomework() throws FileNotFoundException, IOException {
      JFrame frame = new JFrame();
      JFileChooser chooser = new JFileChooser();
      
      chooser.showOpenDialog(frame);
      
      try {
      myFile = new FileReader(chooser.getSelectedFile().toString()); //create a Reader for a file--we will define this file next
      System.out.println("I did get here");
      in = new BufferedReader(myFile);                               //wrap the FileReader in a class that lets us manipulate it
    }
    catch(FileNotFoundException e) {
      System.out.println("This file is inaccessible or does not exist.  Please select a valid file.");
      System.exit(0);
    }
    while(aLine != null) {
      try {
        aLine = in.readLine();                                //read in a line of the file
      }
      catch(IOException e) {
        System.out.println("Now we have a different problem!");
      }
      if(aLine != null)
        System.out.println(aLine);
    }
  }
  
  public static void main(String[] args) {
    FileFetcher testMe = new FileFetcher();
    try {
      testMe.getHomework();
    }
    catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }

}
