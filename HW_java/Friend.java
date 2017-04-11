public class Friend extends Person
{
    private int age;

    public Friend(String myfirstName, String mylastName, int myphoneNumber, int myage)
    {
	super(myfirstName, mylastName, myphoneNumber);
	age = myage;
    }

    public void setAge(int myage)
    {
	age = myage;
    }

    public int getAge()
    {
	return age;
    }

    public void print()
    {
	System.out.print(getFirstName() + " " + getLastName() + "_");
	int phoneNumber = getPhoneNumber();
	int_to_string(phoneNumber);
	System.out.println("_" + getAge());	
    }
};
