//Jason Wada
//COP2552.0M1
// Storm Statistics

import java.util.Comparator;

class SortName implements Comparator<Storm> {
    public int compare(Storm a, Storm b) {		// Compare method to compare two storms based on their names
        String nameA = a.getName().toLowerCase();
        String nameB = b.getName().toLowerCase();
        return nameA.compareTo(nameB);
    }
}

class SortCategory implements Comparator<Storm> { 
    public int compare(Storm a, Storm b) {        // Compare based on category
        int categoryA = a.getCategory();
        int categoryB = b.getCategory();
        return Integer.compare(categoryA, categoryB);
    }
}

class SortYear implements Comparator<Storm> {  
    public int compare(Storm a, Storm b) {     // Compare based on year
        int yearA = a.getYear();
        int yearB = b.getYear();
        return Integer.compare(yearA, yearB);
    }
}

class SortMonth implements Comparator<Storm> {
    public int compare(Storm a, Storm b) {     // Compare based on month
        int monthA = a.getMonth();
        int monthB = b.getMonth();
        return Integer.compare(monthA, monthB);
    }
}

