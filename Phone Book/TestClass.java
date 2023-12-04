
import java.util.*;

public class TestClass{

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);   
        PhoneBook P = new PhoneBook(input);
        
        
        //Example entries
        P.addPerson("John", "Smith", "123 ABC", "New York", "(167)-877-6378");
        P.addPerson("Joe", "Smith", "125 AAC", "New Hampshire", "(124)-533-9988");
        P.addPerson("Jane", "Doe", "456 DEF", "Austin", "(555)-555-5555");

        //Displays menu for the program
        P.menu();

    }

}

