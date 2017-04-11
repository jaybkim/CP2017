public class Person
{
    private String firstName;
    private String lastName;
    int phoneNumber;

    public Person(String myfirstName, String mylastName, int myphoneNumber)
    {
	firstName = myfirstName;
	lastName = mylastName;
	phoneNumber = myphoneNumber;
    }

    public void setFirstName(String myfirstName)
    {
	firstName = myfirstName;
    }

    public String getFirstName()
    {
	return firstName;
    }

    public void setLastName(String mylastName)
    {
	lastName = mylastName;
    }

    public String getLastName()
    {
	return lastName;
    }

    public void setPhoneNumber(int myphoneNumber)
    {
	phoneNumber = myphoneNumber;
    }

    public int getPhoneNumber()
    {
	return phoneNumber;
    }
        
    public void int_to_string(int phoneNumber_int)
    {
	int front;
	int mid;
	int back;

	front = phoneNumber_int / 100000000;
	mid = (phoneNumber_int % 100000000) / 10000;
	back = phoneNumber_int % 10000;

	System.out.print("0" + front + "-" + mid + "-" + back);
    }

    public void print()
    {
	System.out.print(getFirstName() + " " + getLastName() + "_");
	int phoneNumber = getPhoneNumber();
	int_to_string(phoneNumber);
	System.out.print("\n");
    }
};
