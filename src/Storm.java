
//Jason Wada
//COP2552.0M1
// Storm Statistics

public class Storm {
	// Attributes of a storm
    private String name;
    private int category;
    private int month;
    private int day;
    private int year;

    public Storm(String name, int category, int month, int day, int year) {	 // Constructor to initialize a storm object
        this.name = name;
        this.category = category;
        this.month = month;
        this.day = day;
        this.year = year;
    }
// Getter and setter methods to retrieve and set storm attributes
    public String getName() {		
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}

