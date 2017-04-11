import java.util.Calendar;

public class Family extends Person
{
    private String birthday;

    public Family(String myfirstName, String mylastName, int myphoneNumber, String myBirthday)
    {
	super(myfirstName, mylastName, myphoneNumber);
	birthday = myBirthday;
    }

    public void setBirthday(String myBirthday)
    {
	birthday = myBirthday;
    }

    public String getBirthday()
    {
	return birthday;
    }

    public int dates_of_month(int month)
    {
	if (month == 2)
	    return 28;
	else if (month == 4 || month == 6 || month == 9 || month == 11)
	    return 30;
	else
	    return 31;
    }

    public int dDay()
    {
	int bM = (birthday.charAt(2)-'0')*10 + (birthday.charAt(3)-'0'); // MM of YYMMDD
	int bD = (birthday.charAt(4)-'0')*10 + (birthday.charAt(5)-'0'); // DD of YYMMDD

	Calendar now = Calendar.getInstance();
	int cM = now.get(Calendar.MONTH) + 1;     // current month
	int cD = now.get(Calendar.DAY_OF_MONTH); // current date	

	int dday = 0;
	int i;

	for (i=cM; i<=12; i++)
	    dday = dday + dates_of_month(i);
        for (i=1; i<bM; i++)
	    dday = dday + dates_of_month(i);	    	    
	dday = dday - cD + bD;

	if (dday >= 365)     // if bMbD >= cMcD
	    dday = dday - 365;

	return dday;
    }

    public void print()
    {
	System.out.print(getFirstName() + " " + getLastName() + "_");
	int phonenum = getPhoneNumber();
	int_to_string(phonenum);
	System.out.println("_" + getBirthday() + "_" + dDay());
    }
};
