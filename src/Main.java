//Jason Wada
//COP2552.0M1
// Storm Statistics

import java.awt.Font;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;


public class Main {
    public static void main(String[] args) {
    	
        ArrayList<Storm> storms = new ArrayList<Storm>(); // Creates an ArrayList to store Storm objects

        readInput(storms);								 // Read input data from file into the storms ArrayList

        JFrame f = new JFrame();							// Create a JFrame for displaying options using JOptionPane
        String s ="Florida Major Hurricanes 1950-2020\n\n"
        		+ "Press 1 to Sort by Storm Name\n" + "Press 2 to Sort by Storm Category\n"
                + "Press 3 to Sort by Storm Year\n" + "Press 4 to Sort by Storm Month\n"
                + "Press 5 to Display Average Storm Category\n" + "Press 6 to Display the Most Active Year\n"
                + "Press 7 to Display Total by Category\n" + "Press 8 to Display Total by Year\n"
                + "Press 9 to Exit";
        String s1 = JOptionPane.showInputDialog(f, s);		// Display menu and get user input

        switch (Integer.parseInt(s1)) {
            case 1:
                sortAndDisplay(storms, new SortName(), "SortByName.txt", "Name");
                break;
            case 2:
                sortAndDisplay(storms, new SortCategory(), "SortByCategory.txt", "Category");
                break;
            case 3:
                sortAndDisplay(storms, new SortYear(), "SortByYear.txt", "Year");
                break;
            case 4:
                sortAndDisplay(storms, new SortMonth(), "SortByMonth.txt", "Month");
                break;
            case 5:
                calculateAndDisplayAverageCategory(storms);
                break;
            case 6:
                findAndDisplayMostActiveYear(storms);
                break;
            case 7:
                aggregateByCategoryAndDisplay(storms);
                break;
            case 8:
                aggregateByYearAndDisplay(storms);
                break;
            case 9:
                System.exit(0);
        }
    }
    	// Method to sort storms and display based on given comparator	
    public static void sortAndDisplay(ArrayList<Storm> storms, Comparator<Storm> comparator, String fileName, String option) {
        JFrame f = new JFrame();
        StringBuilder s = new StringBuilder("Florida Major Hurricanes 1950-2020\n\n" 
        		+ "Sort by Hurricane " + option + "\n\nPress 1 for Ascending Order\n" + "Press 2 for Descending Order");
        int choice = Integer.parseInt(JOptionPane.showInputDialog(f, s));
        
        s = new StringBuilder("Major Florida Hurricanes 1950-2020\n\n"); // Create a StringBuilder to build the display string
        if (choice == 1) {
            storms.sort(comparator);
            s.append("Sort by " + option + " in Ascending Order\n\n");   //  sort data in ascending or descending
        } else {
            storms.sort(comparator.reversed());
            s.append("Sort by " + option + " in Descending Order\n\n");
        }

        s.append("Name      Category    Date\n");
        for (Storm st : storms) {
        	
        	
        	String formatedRow = st.getName();
        	s.append(formatedRow);
        for(int i = formatedRow.length(); i < 12; i++) {
        	s.append(" ");
        }
        formatedRow += st.getCategory();
           s.append(st.getCategory()).append("       ").append(st.getMonth()).append("-")
                   .append(st.getDay()).append("-").append(st.getYear()).append("\n");
                  
        }
        JTextArea textbox = new JTextArea(s.toString());	// Create a JTextArea to display the sorted storms
        textbox.setFont(new Font("monospaced", Font.PLAIN, 12));
        JOptionPane.showMessageDialog(f, textbox);
        writeToFile(s.toString(), fileName);
    }
    	// Method to calculate and display the average storm category
    public static void calculateAndDisplayAverageCategory(ArrayList<Storm> storms) {
        JFrame f = new JFrame();
        double totalCategory = 0;						// Initialize a variable to store the total category
        for (Storm st : storms) {
            totalCategory += st.getCategory();
        }
        
        if (storms.size() != 0) {						// Check if there are storms
        	totalCategory /= storms.size();
        	JOptionPane.showMessageDialog(f, " Major Florida Hurricanes 1950 - 2020\n\n"	// Display the average category
        			+ "Average Storm category by Saffir-Simpson Scale\n\n"
        			+ "Average Storm category " + String.format("%.2f", totalCategory));
        }
        else
        	JOptionPane.showMessageDialog( f, "No storms input");
    }

    public static void findAndDisplayMostActiveYear(ArrayList<Storm> storms) {		 // Method to find and display the most active year
        JFrame f = new JFrame();
        int[] years = new int[2021 - 1950 + 1]; 		// Initialize an array 
        for (Storm st : storms) {
            int year = st.getYear();
            if (year >= 1950 && year <= 2020) {
                years[year - 1950]++;
            }
        }
     // Initialize variables to store the most active year and the maximum number
        int maxStorms = 0;
        int mostActiveYear = 0;
        int tiedStorms = 0;
        for (int i = 0; i < years.length; i++) {		// Find the most active year and count tied storms
            if (years[i] > maxStorms) {
                maxStorms = years[i];
                mostActiveYear = i + 1950;
                tiedStorms = 0;
            }
            
            if (years[i] == maxStorms) {
                tiedStorms++;
            }      
        }
        
     // Display messages based on the most active year and number of storms
        if (storms.size() == 0)
        	JOptionPane.showMessageDialog( f, "No storms input");
        
        else if (tiedStorms == 1)
        	JOptionPane.showMessageDialog(f, "Most active storm year: " + mostActiveYear + " (" + maxStorms + " storms)");
        
        else {
        		
        	for (int i = 0; i < years.length; i++) {
        		 if (years[i] == maxStorms && (i + 1950) != mostActiveYear) {
        			 JOptionPane.showMessageDialog( f, "Major Florida Hurricanes 1950 - 2020\n\n" +"Most Active Year\n\n" 
        		 +"Most active storm year is tied with " 
        					 + (i + 1950)+ "\nand " + mostActiveYear + " each having " + maxStorms + " named storms");;
        		 }
            }
        }
    }
     
    public static void aggregateByCategoryAndDisplay(ArrayList<Storm> storms) {		 // Method to aggregate storms by category and display
        JFrame f = new JFrame();
        StringBuilder s = new StringBuilder( "Major Florida Hurricanes 1950 - 2020\n\n"
        		+ "Aggregate Totals by Category (Saffir-Simpson scale)\n\n"
        		+ "Total number of major hurricanes listed: " + storms.size()+ "\n\n" );
     // Initialize an array to store the number of storms per category
        int[] categories = new int[5]; 
        for (Storm st : storms) {
            categories[st.getCategory() - 1]++;
        }
        for (int i = 4; i >= 0; i--) {
            s.append(" Total Category ").append(i + 1).append(" hurricanes: ").append(categories[i]).append("\n");	 // Append category totals to the display string
        }
        JOptionPane.showMessageDialog(f, s.toString());
    }
 // Method to aggregate storms by year and display
    public static void aggregateByYearAndDisplay(ArrayList<Storm> storms) {
        JFrame f = new JFrame();
        StringBuilder s = new StringBuilder("Major Florida Hurricanes 1950 - 2020\n\n"
        +"Aggregate total by year\n\n" +"Year	   Number of Storms\n");
        int[] years = new int[2021 - 1950 + 1]; 
        for (Storm st : storms) {
            years[st.getYear() - 1950]++;
        }
        for (int i = 0; i < years.length; i++) {
            if (years[i] > 0) {
                s.append(i + 1950).append("        ").append(years[i] + "\n");
            }
        }
        JOptionPane.showMessageDialog(f, s.toString());
    }

    public static void writeToFile(String data, String fileName) {		// Method to write data to a file
        try {
            File directory = new File("C:\\SFC\\COP2552\\Project4");	// Create a directory if it doesn't exist
            if (!directory.exists()) {
                directory.mkdirs();
            }
            FileWriter writer = new FileWriter("C:\\SFC\\COP2552\\Project4\\" + fileName);	// Write data to file
            writer.write(data);
            writer.close();
            JOptionPane.showMessageDialog(null, "Data has been successfully written to the output file: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 // Method to read input data from file
    private static void readInput(ArrayList<Storm> storms) {
        try {
            File myObj = new File("NamedFloridaHurricanes.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {		// Read each line and create Storm objects
                String data[] = myReader.nextLine().split(",");
                String name = data[0];
                data = data[1].split(":");
                int category = Integer.parseInt(data[0]);
                data = data[1].split("/");
                int month = Integer.parseInt(data[0]);
                int day = Integer.parseInt(data[1]);
                int year = Integer.parseInt(data[2]);
                
                Storm s = new Storm(name, category, month, day, year);
                storms.add(s);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
