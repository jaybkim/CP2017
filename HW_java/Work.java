public class Work extends Person
{
    private String team;
    
    public Work(String myfirstName, String mylastName, int myphoneNumber, String myteam)
    {
	super(myfirstName, mylastName, myphoneNumber);
	team = myteam;
    }

    public void setTeam(String myteam)
    {
	team = myteam;
    }

    public String getTeam()
    {
	return team;
    }

    public void print()
    {
	System.out.print(getFirstName() + " " + getLastName() + "_");
	int phonenum = getPhoneNumber();
	int_to_string(phonenum);
	System.out.println("_" + getTeam());
    }
};    
