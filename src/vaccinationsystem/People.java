
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

public class People extends User{
    public static String name;
    
    //Constructor for viewOwnDetail
    People(){

    }
    
    //Method for view own detail
    public String[] viewOwn(String name)
    {
        try 
        {
            FileReader fr = new FileReader("People.txt");
            Scanner sc = new Scanner(fr);    //file to be scanned 
            String Temp;
            String[] peopleData;
            //returns true if there is another line to read  
            while(sc.hasNextLine())  
            {  
                Temp = sc.nextLine();
                peopleData = Temp.split(",");
                
                if (name.equals(peopleData[0])) //Find until get the same username
                {
                    return peopleData;
                }
            }  
            sc.close();

        } 
        catch (FileNotFoundException ex) 
        {
            Logger.getLogger(People.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public void viewMyAppointment(JTable table, String MyName)
    {
        try 
        {
            FileReader fr = new FileReader("Appointment.txt");
            Scanner sc = new Scanner(fr);    //file to be scanned 
            String Temp;
            String[] Temp2;
            String[] Data = null;
            //returns true if there is another line to read  
            while(sc.hasNextLine())  
            {  
                Temp = sc.nextLine();
                Temp2 = Temp.split(",");
                
                People getPersonalData = new People();
                String [] getData = getPersonalData.viewOwn(MyName);
        
                String Name = getData[2];
                
                if (Temp2[0].equals(Name))
                {
                    DefaultTableModel Alltable = (DefaultTableModel)table.getModel();
                    Alltable.addRow(Temp2);
                }

            }  
            sc.close();

        } 
        catch (FileNotFoundException ex) 
        {
            Logger.getLogger(People.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    
    
    public void modifyPersonal(String choosen1, String Data1, String Data2, String Data3, String Data4, String Data5, String Data6, 
            String Data7, String Data8, String pw, String newname, String newphone, String newemail) throws IOException
    {
        try 
        {
            FileReader fr = new FileReader("People.txt");
            Scanner sc = new Scanner(fr);    //file to be scanned 
            String Temp;
            String[] Temp2;
            String delete = "";
            //String pw = ""; 

            //returns true if there is another line to read  
            
                
            while(sc.hasNextLine())  
            {  
                Temp = sc.nextLine();
                Temp2 = Temp.split(",");
                
                if (Temp2[0].equals(choosen1)) //to find the one line to delete
                {
                    delete = Temp; // to get the same data (which will be modified)
                }

                if (Temp != delete) //if not same, then go write the file
                {
                    //Delete line
                    File fixedfile = new File("People.txt");
                    File temporaryy = new File("UpdatedFile.txt");

                    BufferedReader fixedread = new BufferedReader(new FileReader(fixedfile));
                    BufferedWriter fixedwrite = new BufferedWriter(new FileWriter(temporaryy));
                    
                    /*People viewown1 = new People();
                    String[] detail1 = viewown1.viewOwn(Data1);
                    String pw = detail1[1];*/

                    String lineToRemove = Data1+","+Data2+","+Data3+","+Data4+","+Data5+","+Data6+","+Data7+","+Data8;
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
                    File fixedfile1 = new File("People.txt");
                    File temporaryy1 = new File("UpdatedFile.txt");

                    BufferedReader fixedread1 = new BufferedReader(new FileReader(temporaryy1));
                    BufferedWriter fixedwrite1 = new BufferedWriter(new FileWriter(fixedfile1));

                    String currentLine1;

                    while((currentLine1 = fixedread1.readLine()) != null) 
                    {
                        String trimmedLine1 = currentLine1.trim();
                        fixedwrite1.write(currentLine1 + System.getProperty("line.separator"));
                    }
                    fixedwrite1.close(); 
                    fixedread1.close();

                }
                
            } 
            
            //to append modified ammount
            PrintWriter fixedwrite1= new PrintWriter(new BufferedWriter(new FileWriter("People.txt",true)));
            
            /*People viewown = new People();
            String[] detail = viewown.viewOwn(Data1);
            String Passwordd = detail[1];*/

            fixedwrite1.append(Data1+","+pw+","+newname+","+Data4+","+newphone+","+newemail+","+Data7+","+Data8+"\n");
            JOptionPane.showMessageDialog(null, "Modify Success");
            fixedwrite1.close();
            sc.close();

        } 
        catch (FileNotFoundException ex) 
        {
            Logger.getLogger(People.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
