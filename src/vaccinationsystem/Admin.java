
package vaccinationsystem;

import java.io.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Admin extends User{ 
    
    //Constructor for view people
    Admin()
    {
        
    }
    
    //Method for view people in Jtable
    public void viewPeople(JTable table)
    {
        try 
        {
            FileReader fr = new FileReader("People.txt");
            Scanner sc = new Scanner(fr);
            String Temp;
            String[] Temp2;
            
            //returns true if there is another line to read  
            while(sc.hasNextLine())  
            {  
                Temp = sc.nextLine();
                Temp2 = Temp.split(",");
                String[] Data = {Temp2[0], Temp2[2], Temp2[3], Temp2[4], Temp2[5], Temp2[6], Temp2[7]};
                
                DefaultTableModel Alltable = (DefaultTableModel)table.getModel();
                Alltable.addRow(Data);
                
            }  
            sc.close();

        } 
        catch (FileNotFoundException ex) 
        {
            Logger.getLogger(People.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    //Method for view people in Jtable
    public void viewPeople2(JTable table)
    {
        try 
        {
            FileReader fr = new FileReader("People.txt");
            Scanner sc = new Scanner(fr);
            String Temp;
            String[] Temp2;

            
            //returns true if there is another line to read  
            while(sc.hasNextLine())  
            {  
                Temp = sc.nextLine();
                Temp2 = Temp.split(",");
                String[] Data = {Temp2[0], Temp2[1], Temp2[2], Temp2[3], Temp2[4], Temp2[5], Temp2[6], Temp2[7]};
                
                DefaultTableModel Alltable = (DefaultTableModel)table.getModel();
                Alltable.addRow(Data);
                
            }  
            sc.close();

        } 
        catch (FileNotFoundException ex) 
        {
            Logger.getLogger(People.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    //Method to Search People
    public void SearchBy(JTable table, int index, String choosen)
    {
        try 
        {
            FileReader fr = new FileReader("People.txt");
            Scanner sc = new Scanner(fr);  
            String Temp;
            String[] Temp2;

            //returns true if there is another line to read  
            while(sc.hasNextLine())  
            {  
                Temp = sc.nextLine();
                Temp2 = Temp.split(",");

                if (Temp2[index].equals(choosen))
                {
                    String[] Data = {Temp2[0], Temp2[2], Temp2[3], Temp2[4], Temp2[5], Temp2[6], Temp2[7]};
                    DefaultTableModel Alltable = (DefaultTableModel)table.getModel();
                    Alltable.addRow(Data);
                }
            }  
            sc.close();

        } 
        catch (FileNotFoundException ex) 
        {
            Logger.getLogger(People.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void modify(String choosen1, String Data1, String Data2, String Data3, String Data4, String Data5, String Data6, String Data7,
            String newemail, String DOBFixed, String newphone, String newic, String newtype, String pw) throws IOException
    {
        try 
        {
            FileReader frr = new FileReader("People.txt");
            Scanner scc = new Scanner(frr);  
            String Temp;
            String[] Temp2;
            String delete = "";

            //returns true if there is another line to read    
            while(scc.hasNextLine())  
            {  
                Temp = scc.nextLine();
                Temp2 = Temp.split(",");
                
                ArrayList<String> Data = 
                        new ArrayList<>(Arrays.asList(Temp2[0], Temp2[1], Temp2[2], Temp2[3], Temp2[4], Temp2[5], Temp2[6], Temp2[7]));
                
                

                if (Temp2[0].equals(choosen1)) //to find the one line to delete
                {
                    delete = Temp; // to get the same data (which will be modified)
                }

                if (Temp != delete) //if not same, then go write the file
                {
                    //Delete line
                    File fixedfile = new File("People.txt");
                    File tempFile = new File("UpdatedFile.txt");

                    BufferedReader fixedread = new BufferedReader(new FileReader(fixedfile));
                    BufferedWriter fixedwrite = new BufferedWriter(new FileWriter(tempFile));
                    

                    String lineToRemove = Data1+","+pw+","+Data2+","+Data3+","+Data4+","+Data5+","+Data6+","+Data7;
                    String currentLine;

                    while((currentLine = fixedread.readLine()) != null) {
                        // trim newline when comparing with lineToRemove
                        String trimmedLine = currentLine.trim();
                        if(trimmedLine.equals(lineToRemove)) continue;
                        fixedwrite.write(currentLine + System.getProperty("line.separator"));
                    }
                    fixedwrite.close(); 
                    fixedread.close();  //Delete line
                    
                    //re write the txt file from the temp txt file
                    File inputFile1 = new File("People.txt");
                    File tempFile1 = new File("UpdatedFile.txt");

                    BufferedReader reader1 = new BufferedReader(new FileReader(tempFile1));
                    BufferedWriter writer1 = new BufferedWriter(new FileWriter(inputFile1));

                    String currentLine1;

                    while((currentLine1 = reader1.readLine()) != null) 
                    {
                        String trimmedLine1 = currentLine1.trim();
                        writer1.write(currentLine1 + System.getProperty("line.separator"));
                    }
                    writer1.close(); 
                    reader1.close();

                }
                
            } 
            
            //to append modified ammount
            PrintWriter writer1= new PrintWriter(new BufferedWriter(new FileWriter("People.txt",true)));

            writer1.append(Data1+","+pw+","+Data2+","+newic+","+newphone+","+newemail+","+newtype+","+DOBFixed+"\n");
            JOptionPane.showMessageDialog(null, "Modify Success");
            writer1.close();
            scc.close();

        } 
        catch (FileNotFoundException ex) 
        {
            Logger.getLogger(People.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
