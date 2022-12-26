
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

public class Vaccine {
    public String VaccineName;
    public String VaccineCenter;
    public int Ammount;

    //Get Set Method
    public String getVaccineName() {
        return VaccineName;
    }

    public void setVaccineName(String VaccineName) {
        this.VaccineName = VaccineName;
    }

    public String getVaccineCenter() {
        return VaccineCenter;
    }

    public void setVaccineCenter(String VaccineCenter) {
        this.VaccineCenter = VaccineCenter;
    }

    public int getAmmount() {
        return Ammount;
    }

    public void setAmmount(int Ammount) {
        this.Ammount = Ammount;
    }
    
    //Constructor for Add Vaccine
    Vaccine (String VaccineCenter, String VaccineName, int Ammount)
    {
        this.VaccineName = VaccineName;
        this.VaccineCenter = VaccineCenter;
        this.Ammount = Ammount;
    }
    
    Vaccine (String VaccineCenter)
    {
        this.VaccineCenter = VaccineCenter;
    }
    
    Vaccine ()
    {
    }
    
    //Method for Add Vaccine
    public void AddVaccine()
    {
        File file = new File("Vaccine.txt");
        try 
        {
            PrintWriter vaccine= new PrintWriter(new BufferedWriter(new FileWriter("Vaccine.txt",true)));
            vaccine.append(VaccineCenter+","+VaccineName+","+Ammount+"\n");
            vaccine.close();
            JOptionPane.showMessageDialog(null, "Success");
        } 
        
        catch (IOException ioe) 
        {
            ioe.printStackTrace();
        }
    }
    
    //Method to modify vaccine
    public void modify(String choosen1, String choosen2, String Data1, String Data2, String Data3) throws IOException
    {
        try 
        {
            FileReader fr = new FileReader("Vaccine.txt");
            Scanner sc = new Scanner(fr); 
            String Temp;
            String[] Temp2;
            String delete = "";

            //returns true if there is another line to read  
            while(sc.hasNextLine())  
            {  
                Temp = sc.nextLine();
                Temp2 = Temp.split(",");
                
                ArrayList<String> Data = 
                        new ArrayList<>(Arrays.asList(Temp2[0], Temp2[1], Temp2[2]));

                //if vac center == textbox and vacc type == textbox
                if (Temp2[0].equals(choosen1) && Temp2[1].equals(choosen2)) //to find the one line to delete
                {
                    delete = Temp; // to get the same data (which will be modified)
                }

                if (Temp != delete) //if not same, then go write the file
                {

                    //Delete line
                    File fixedfile = new File("Vaccine.txt");
                    File temporaryy = new File("UpdatedFile.txt");

                    BufferedReader fixedread = new BufferedReader(new FileReader(fixedfile));
                    BufferedWriter fixedwrite = new BufferedWriter(new FileWriter(temporaryy));

                    String lineToRemove = Data1+","+ Data2+","+Data3;
                    String currentLine;

                    while((currentLine = fixedread.readLine()) != null) {
                        // trim newline when comparing with lineToRemove
                        String trimmedLine = currentLine.trim();
                        if(trimmedLine.equals(lineToRemove)) continue;
                        fixedwrite.write(currentLine + System.getProperty("line.separator"));
                    }
                    fixedwrite.close(); 
                    fixedread.close(); 

                    //re write the txt file from the temp txt file
                    File fixedfile1 = new File("Vaccine.txt");
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
            PrintWriter fixedwrite1= new PrintWriter(new BufferedWriter(new FileWriter("Vaccine.txt",true)));

            int newAmmount = Integer.parseInt(Data3) + Ammount;
            String newAmmount2 = Integer.toString(newAmmount);

            fixedwrite1.append(Data1+","+ Data2+","+newAmmount2+"\n");
            fixedwrite1.close();
            JOptionPane.showMessageDialog(null, "Success");
            sc.close();

        } 
        catch (FileNotFoundException ex) 
        {
            Logger.getLogger(People.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    //Method to modify vaccine number
    public void modify(String choosen1, String choosen2, String Data1, String Data2, String Data3, String Data4) throws IOException
    {
        try 
        {
            FileReader fr = new FileReader("Vaccine.txt");
            Scanner sc = new Scanner(fr);    //file to be scanned 
            String Temp;
            String[] Temp2;
            String delete = "";

            //returns true if there is another line to read  
            while(sc.hasNextLine())  
            {  
                Temp = sc.nextLine();
                Temp2 = Temp.split(",");
                
                ArrayList<String> Data = 
                        new ArrayList<>(Arrays.asList(Temp2[0], Temp2[1], Temp2[2]));

                //if vac center == textbox and vacc type == textbox
                if (Temp2[0].equals(choosen1) && Temp2[1].equals(choosen2)) //to find the one line to delete
                {
                    delete = Temp; // to get the same data (which will be modified)
                }

                if (Temp != delete) //if not same, then go write the file
                {

                    //Delete line
                    File fixedfile = new File("Vaccine.txt");
                    File temporaryy = new File("UpdatedFile.txt");

                    BufferedReader fixedread = new BufferedReader(new FileReader(fixedfile));
                    BufferedWriter fixedwrite = new BufferedWriter(new FileWriter(temporaryy));

                    String lineToRemove = Data1+","+ Data2+","+Data3;
                    String currentLine;

                    while((currentLine = fixedread.readLine()) != null) {
                        // trim newline when comparing with lineToRemove
                        String trimmedLine = currentLine.trim();
                        if(trimmedLine.equals(lineToRemove)) continue;
                        fixedwrite.write(currentLine + System.getProperty("line.separator"));
                    }
                    fixedwrite.close(); 
                    fixedread.close(); 

                    //re write the txt file from the temp txt file
                    File fixedfile1 = new File("Vaccine.txt");
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
            PrintWriter fixedwrite1= new PrintWriter(new BufferedWriter(new FileWriter("Vaccine.txt",true)));

            int newAmmount = Integer.parseInt(Data4);
            String newAmmount2 = Integer.toString(newAmmount);

            fixedwrite1.append(Data1+","+ Data2+","+newAmmount2+"\n");
            JOptionPane.showMessageDialog(null, "Modify Success");
            fixedwrite1.close();
            sc.close();

        } 
        catch (FileNotFoundException ex) 
        {
            Logger.getLogger(People.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
     

    //Method to check duplication
    public String[] check()
    {
        try 
        {
            FileReader fr = new FileReader("Vaccine.txt");
            Scanner sc = new Scanner(fr); 
            String Temp;
            String[] Temp2;
            //returns true if there is another line to read  
            while(sc.hasNextLine())  
            {  
                Temp = sc.nextLine();
                Temp2 = Temp.split(",");

                if (Temp2[0].equals(VaccineCenter) && Temp2[1].equals(VaccineName))
                {
                    return Temp2;
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
    
    //Method to View Vaccine
    public void viewVaccine(JTable table, int index, String choosen)
    {
        try 
        {
            FileReader fr = new FileReader("Vaccine.txt");
            Scanner sc = new Scanner(fr);    //file to be scanned 
            String Temp;
            String[] Temp2;
            String[] Data = null;
            //returns true if there is another line to read  
            while(sc.hasNextLine())  
            {  
                Temp = sc.nextLine();
                Temp2 = Temp.split(",");

                if (Temp2[index].equals(choosen))
                {
                    //Data = Data.addAll(Data, Temp2);
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
    
    //Method to View Vaccine all
    public void viewVaccineAll(JTable table)
    {
        try 
        {
            FileReader fr = new FileReader("Vaccine.txt");
            Scanner sc = new Scanner(fr); 
            String Temp;
            String[] Temp2;
            String[] Data = null;
            //returns true if there is another line to read  
            while(sc.hasNextLine())  
            {  
                Temp = sc.nextLine();
                Temp2 = Temp.split(",");

                DefaultTableModel Alltable = (DefaultTableModel)table.getModel();
                Alltable.addRow(Temp2);
                
            }  
            sc.close();

        } 
        catch (FileNotFoundException ex) 
        {
            Logger.getLogger(People.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    //Method to Delete Vaccine
    public void delete(String Data1, String Data2, String Data3) throws IOException
    {
        File fixedfile = new File("Vaccine.txt");
        File temporaryy = new File("UpdatedFile.txt");

        BufferedReader fixedread = new BufferedReader(new FileReader(fixedfile));
        BufferedWriter fixedwrite = new BufferedWriter(new FileWriter(temporaryy));

        String lineToRemove = Data1+","+ Data2+","+Data3;
        String currentLine;

        while((currentLine = fixedread.readLine()) != null) {
            // trim newline when comparing with lineToRemove
            String trimmedLine = currentLine.trim();
            if(trimmedLine.equals(lineToRemove)) continue;
            fixedwrite.write(currentLine + System.getProperty("line.separator"));
        }
        fixedwrite.close(); 
        fixedread.close(); 

        //re write the txt file from the temp txt file
        File fixedfile1 = new File("Vaccine.txt");
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
    
    public void Search(JTable table, int index, String choosen, int index2, String Choosen2)
    {
        try 
        {
            FileReader fr = new FileReader("Vaccine.txt");
            Scanner sc = new Scanner(fr); 
            String Temp;
            String[] Temp2;
            //String[] Data = null;
            //returns true if there is another line to read  
            while(sc.hasNextLine())  
            {  
                Temp = sc.nextLine();
                Temp2 = Temp.split(",");
                

                if (Temp2[index].equals(choosen) && Temp2[index2].equals(Choosen2))
                {
                    String[] Data = {Temp2[0], Temp2[1], Temp2[2]};
                    
                    DefaultTableModel Alltable = (DefaultTableModel)table.getModel();
                    Alltable.addRow(Data);
                }
            }  
            sc.close();

        } 
        catch (FileNotFoundException ex) 
        {
            Logger.getLogger(Appointment.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
