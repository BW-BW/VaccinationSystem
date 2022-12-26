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

public class Appointment {
    public String Name;
    public String VaccineCentre;
    public String VaccineType;
    public String BookingDate;
    public String BookingTime;

    
    //Get set
    public String getBookingDate() {
        return BookingDate;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getVaccineCentre() {
        return VaccineCentre;
    }

    public void setVaccineCentre(String VaccineCentre) {
        this.VaccineCentre = VaccineCentre;
    }

    public String getVaccineType() {
        return VaccineType;
    }

    public void setVaccineType(String VaccineType) {
        this.VaccineType = VaccineType;
    }

    public void setBookingDate(String BookingDate) {
        this.BookingDate = BookingDate;
    }

    public String getBookingTime() {
        return BookingTime;
    }

    public void setBookingTime(String BookingTime) {
        this.BookingTime = BookingTime;
    }
    
    //constructor
    Appointment(){
        
    }
    
    //for booking
    Appointment(String Name, String VaccineCentre, String VaccineType, String BookingDate, String BookingTime) {
        this.Name = Name;
        this.VaccineCentre = VaccineCentre;
        this.VaccineType = VaccineType;
        this.BookingDate = BookingDate;
        this.BookingTime = BookingTime;
    } 
    
    public void adminbook() {
        File book = new File("Appointment.txt");
        try 
        {
            PrintWriter appoint = new PrintWriter(new BufferedWriter(new FileWriter("Appointment.txt",true)));
            appoint.append(Name + "," + VaccineCentre + "," + VaccineType + "," + BookingDate + "," + BookingTime + "\n");
            appoint.close();
            JOptionPane.showMessageDialog(null, "Success");
        } 
        
        catch (IOException ioe) 
        {
            ioe.printStackTrace();
        }
    }    
    
    public void viewAppointmentAll(JTable table)
    {
        try 
        {
            FileReader fr = new FileReader("Appointment.txt");
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

    public void SearchBy(JTable table, int index, String choosen)
    {
        try 
        {
            FileReader fr = new FileReader("Appointment.txt");
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
                    String[] Data = {Temp2[0], Temp2[1], Temp2[2], Temp2[3], Temp2[4]};
                    
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

    public void delete(String Data1, String Data2, String Data3, String Data4, String Data5) throws IOException {
            File fixedfile = new File("Appointment.txt");
            File temporaryy = new File("TempAppointFile.txt");

            BufferedReader fixedread = new BufferedReader(new FileReader(fixedfile));
            BufferedWriter fixedwrite = new BufferedWriter(new FileWriter(temporaryy));

            String lineToRemove = Data1+","+ Data2+","+Data3+","+Data4+","+Data5;
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
            File fixedfile1 = new File("Appointment.txt");
            File temporaryy1 = new File("TempAppointFIle.txt");

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
    
    public void modify(String choosen1, String choosen2, String Data1, String Data2, 
            String Data3, String Data4, String Data5, String newcenter, 
            String BookDateFixed, String newtime) throws IOException
    {
        try 
        {
            FileReader fr = new FileReader("Appointment.txt");
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
                        new ArrayList<>(Arrays.asList(Temp2[0], Temp2[1], Temp2[2], Temp2[3], Temp2[4]));

                //if vac center == textbox and vacc type == textbox
                if (Temp2[0].equals(choosen1) && Temp2[1].equals(choosen2)) //to find the one line to delete
                {
                    delete = Temp; // to get the same data (which will be modified)
                }

                if (Temp != delete) //if not same, then go write the file
                {

                    //Delete line
                    File fixedfile = new File("Appointment.txt");
                    File temporaryy = new File("TempAppointment.txt");

                    BufferedReader fixedread = new BufferedReader(new FileReader(fixedfile));
                    BufferedWriter fixedwrite = new BufferedWriter(new FileWriter(temporaryy));

                    String lineToRemove = Data1+","+Data2+","+Data3+","+Data4+","+Data5;
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
                    File fixedfile1 = new File("Appointment.txt");
                    File temporaryy1 = new File("TempAppointment.txt");

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
            PrintWriter fixedwrite1= new PrintWriter(new BufferedWriter(new FileWriter("Appointment.txt",true)));
            String NewCenter = newcenter;
            String NewDate = BookDateFixed;
            String NewTime = newtime;
            
            fixedwrite1.append(Data1+","+NewCenter+","+Data3+","+NewDate+","+NewTime+"\n");
            JOptionPane.showMessageDialog(null, "Modify Success");
            fixedwrite1.close();
            sc.close();

        } 
        catch (FileNotFoundException ex) 
        {
            Logger.getLogger(People.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String[] checkAppointment()
    {
        try 
        {
            FileReader fr = new FileReader("Appointment.txt");
            Scanner sc = new Scanner(fr);
            String Temp;
            String[] Temp2;
            //returns true if there is another line to read
            while(sc.hasNextLine())
            {
                Temp = sc.nextLine();
                Temp2 = Temp.split(",");

                if (Temp2[0].equals(Name))
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

    public String[] checkAvailability()
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

                if (Temp2[0].equals(VaccineCentre) && Temp2[1].equals(VaccineType))
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
}






