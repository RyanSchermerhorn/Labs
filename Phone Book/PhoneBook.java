import java.util.*;

public class PhoneBook {

    //Scanner input = new Scanner(System.in);

    
    ListNode fName = new ListNode();
    ListNode lName = new ListNode();
    ListNode address = new ListNode();
    ListNode city = new ListNode();
    ListNode phone = new ListNode();
    int listSize = 0;
    Scanner input;
    public PhoneBook(Scanner i){
        input = i;
    }

    public void addPerson(String fN, String lN, String ad, String ci, String ph){
        fName.addData(fN);
        lName.addData(lN);
        address.addData(ad);
        city.addData(ci);
        phone.addData(ph);
        listSize++;
    }

   public void addPersonManual(){
        System.out.println("First Name:");
        fName.addData(input.next());
        System.out.println("Last Name:");
        lName.addData(input.next());
        System.out.println("Address");
        address.addData(input.next());
        System.out.println("City:");
        city.addData(input.next());
        System.out.println("Phone:");
        phone.addData(input.next());
        listSize++;
    }

    public void removePerson(int entry, int Size){
        
        entry--;
        fName.remove(entry, Size);
        lName.remove(entry, Size);
        address.remove(entry, Size);
        city.remove(entry, Size);
        phone.remove(entry, Size);
        listSize--;
    }

    public String getFirst(int entry){
        return fName.getData(entry);
    }

    public String getLast(int entry){
        return lName.getData(entry);
    }

    public String getAddress(int entry){
        return address.getData(entry);
    }

    public String getCity(int entry){
        return city.getData(entry);
    }

    public String getPhone(int entry){
        return phone.getData(entry);
    }

    public int search(String n){ //Searches for a matching entry
        int entry = 0;
        int dupecheck = 0;
        int matches[] = new int[listSize + 1];
        System.out.println(getLast(1));
        if(getLast(1).equals(n))
        {
            System.out.println("Match");
        }
        for (int i = 0; i <= listSize; i++)
        {
            if(entry != 0 )
            {
                matches[dupecheck] = entry;
                entry = 0;
            }
            if((getLast(i).equals(n) || getFirst(i).equals(n) || getAddress(i).equals(n) || getCity(i).equals(n) || getPhone(i).equals(n)) && (entry == 0))
            {
                matches[dupecheck] = i;
                entry = i; 
                dupecheck++; 
            }
        }
        entry = matches[0];

        if(dupecheck > 1)
        {
            System.out.println("-Warning: " + dupecheck + " entries with the same information found for: " + n + "");
            for (int i = 0; i < dupecheck; i++)
            {
                System.out.print("Entry #" + (i + 1) + ": ");
                System.out.println(toString(matches[i]));
            }
            System.out.print("Input the entry number you would like to use: ");
            entry = matches[input.nextInt() - 1];
        }
        else if(entry == 0)
        {
            System.out.println("-Warning: No matches found for: " + n + "\n");
        }

        return entry;
    }

    public String toString(int entry){
        
        String a = "";
        if(exists(entry))
        {
            a += "" + entry + ": ";

            a += lName.getData(entry) + ", " + fName.getData(entry) + ":\n  ";

            a += phone.getData(entry) + "\n  ";

            a += address.getData(entry) + ", " + city.getData(entry);
        }

        return (a);
    }

    public void showContents()
    {
        boolean empty = true;
        for(int i = 0; i <= listSize; i++)
        {
            if(exists(i))
            {
                System.out.println(toString(i));
                empty = false;
            }
        }
        if(empty)
        {
            System.out.println("\n-List is Empty\n");
        }
    }

    public boolean exists(int entry)
    {
        boolean exist = false;
        if(lName.getData(entry) != "Empty" && lName.getData(entry) != null)
        {
            exist = true;
        }

        return exist;
    }

    public void menu(){

        String scan;
        char entry;
        int entryId;
        
        do
        {   //Menu Options and inputs
            System.out.println("\n'A': Adds an entry to the phonebook");
            System.out.println("'D': Displays all contents of the phonebook");
            System.out.println("'S': Searches for and returns entries with matches to an input piece of data");
            System.out.println("'P': Displays the entry at a specific index location");
            System.out.println("'G': Displays one information type of an entry");
            System.out.println("'R': Removes an entry");
            System.out.println("'C': Ends the program");
            System.out.print("\n\t\tChoose an option: ");
            scan = input.next();
            entry = scan.charAt(0);

            switch (entry)
            {
                case 'A': //Allows the user to add an entry to the phonebook
                case 'a': 
                    addPersonManual();
                    break;
                case 'D': //Displays all contents of the phonebook
                case 'd': 
                    showContents();
                    break;
                case 'S': //Searches for and returns entries with matches to an input piece of data
                case 's': 
                    System.out.print("Enter data to search for: ");
                    String s = input.next();
                    entryId = search(s);
                    System.out.println("Entry ID: " + entryId);
                    break;
                case 'P': //Displays the entry at a specific ID
                case 'p': 
                    do{
                        System.out.print("Enter the ID of the entry to display (Between " + 1 + " and " + listSize + "): ");
                        entryId = input.nextInt();
                    }while(entryId < 1 || entryId > listSize);

                    System.out.print(toString(entryId));
                    break;
                case 'G': //Displays one information type of an entry
                case 'g': 
                    do{
                        System.out.print("Enter the ID of the entry to display (Between " + 1 + " and " + listSize + "): ");
                        entryId = input.nextInt();
                    }while(entryId < 1 || entryId > listSize);
                    System.out.print("Enter Type of data to display: ");
                    System.out.println("1 for first name, 2 for last name,");
                    System.out.println("3 for Address, 4 for city,");
                    System.out.print("and 5 for Phone number: ");
                    
                    switch (input.nextInt()) //checks Which data to return
                    {
                        case 1:
                            System.out.println(getFirst(entryId));
                            break;
                        case 2:
                            System.out.println(getLast(entryId));
                            break;
                        case 3:
                            System.out.println(getAddress(entryId));
                            break;
                        case 4:
                            System.out.println(getCity(entryId));
                            break;
                        case 5:
                            System.out.println(getPhone(entryId));
                            break;
                        default: 
                            System.out.println("Invalid data type"); 
                    }
                    break;
                case 'R': //Removes an entry
                case 'r': 
                    System.out.print("Enter the ID of the entry to remove (Between " + 1 + " and " + listSize + "):  ");    
                    removePerson(input.nextInt(), listSize);
                        break;
                case 'C': //Closes the program
                case 'c': entry = 'C';
                    System.out.print("Goodbye");    
                    break;
                default: System.out.println("\t\tInvalid Entry, Try again");

            }

        }while (entry != 'C');
    }
}