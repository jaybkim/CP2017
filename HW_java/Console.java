import java.util.Scanner;
import java.util.ArrayList;

public class Console
{
    Scanner scanner = new Scanner(System.in);

    public void initialState()
    {
	System.out.println("Phone Book");
	System.out.println("1. Add person");
	System.out.println("2. Remove person");
	System.out.println("3. Print phone book");
    }

    public int printAddPerson()
    {
	int type;

	System.out.println("Select Type");
	System.out.println("1. Person");
	System.out.println("2. Work");
	System.out.println("3. Family");
	System.out.println("4. Friend");
	System.out.print("CP-2016-19702>");
	type = scanner.nextInt();
	
	return type;
    }

    public int string_to_int(String phonenum)
    {
	int i;
	int phonenum_int = 0;

	for (i=0; i<phonenum.length(); i++)
	    if (phonenum.charAt(i) >= '0' && phonenum.charAt(i) <='9')
		phonenum_int = phonenum_int * 10 + (phonenum.charAt(i) - '0');
	
	return phonenum_int;
    }

    public Person addPerson()
    {
	String fname, lname, phonenum;

	System.out.print("Name: ");
	fname = scanner.next();
	lname = scanner.next();
	System.out.print("Phone_number: ");
	phonenum = scanner.next();
	int phonenum_int = string_to_int(phonenum);

	Person ptr = new Person(fname, lname, phonenum_int);
	System.out.println("Successfully added new person.");

	return ptr;
    }

    public Work addWork()
    {
	String fname, lname, phonenum, team;

	System.out.print("Name: ");
	fname = scanner.next();
	lname = scanner.next();
	System.out.print("Phone_number: ");
	phonenum = scanner.next();
	System.out.print("Team: ");
	team = scanner.next();
	int phonenum_int = string_to_int(phonenum);

	Work ptr = new Work(fname, lname, phonenum_int, team);
	System.out.println("Successfully added new person.");

	return ptr;
    }

    public Family addFamily()
    {
	String fname, lname, phonenum, birthday;

	System.out.print("Name: ");
	fname = scanner.next();
	lname = scanner.next();
	System.out.print("Phone_number: ");
	phonenum = scanner.next();
	System.out.print("Birthday: ");
	birthday =  scanner.next();
	int phonenum_int = string_to_int(phonenum);

	Family ptr = new Family(fname, lname, phonenum_int, birthday);
	System.out.println("Successfully added new person.");

	return ptr;
    }

    public Friend addFriend()
    {
	String fname, lname, phonenum;
	int age;

	System.out.print("Name: ");
	fname = scanner.next();
	lname = scanner.next();
	System.out.print("Phone_number: ");
	phonenum = scanner.next();
	System.out.print("Age: ");
	age = scanner.nextInt();
	int phonenum_int = string_to_int(phonenum);

	Friend ptr = new Friend(fname, lname, phonenum_int, age);
	System.out.println("Successfully added new person.");

	return ptr;
    }

    int rmPerson()
    {
	int index;

	System.out.print("Enter index of person: ");
	index = scanner.nextInt();

	return index;
    }

    public static void main(String[] args)
    {
	ArrayList<Person> phonebook = new ArrayList<>();
	String command;
	Scanner scanner = new Scanner(System.in);

	while (true)
	{
	    System.out.print("CP-2016-19702>");
	    command = scanner.nextLine();

	    if (command.equals("1"))
	    {
		Console console = new Console();
		int category = console.printAddPerson(); // 1: Person, 2: Work, 3: Family, 4: Friend
		if (category == 1)
		    phonebook.add(console.addPerson());
		else if (category == 2)
		    phonebook.add(console.addWork());
		else if (category == 3)
		    phonebook.add(console.addFamily());
		else if (category == 4)
		    phonebook.add(console.addFriend());
	    }
	    else if (command.equals("2"))
	    {
		Console console = new Console();
		int index = console.rmPerson();
		if (phonebook.size() < index || index <= 0)
		    System.out.println("Person does not exist!");
		else
		{
		    phonebook.remove(index-1);
		    System.out.println("A Person is successfully deleted form the Phone Book!");
		}
	    }
	    else if (command.equals("3"))
	    {
		if (phonebook.size() > 0)
	       	{
		    System.out.println("Phone Book Print");
		    int i = 0;
		    for (Person person : phonebook)
		    {
			System.out.print((i+1) + ". ");
			person.print();
			i++;
		    }
		}
	    }
	    else if (command.equals("exit"))
		break;
	    else  // when enter key is pressed
	    {
		Console console = new Console();
		console.initialState();
	    }
	}
    }
};
