#include <iostream>
#include <cstdio>
#include <cstring>
#include <ctime>

#include "family.h"

using std::cin;
using std::cout;
using std::endl;
using std::string;

Family::Family(string &myfirstName, string &mylastName, int &myphoneNumber, string &myBirthday) : Person(myfirstName, mylastName, myphoneNumber) 
{
    birthday = myBirthday;
}

void Family::setBirthday(string myBirthday)
{
    birthday = myBirthday;
}

string Family::getBirthday()
{
    return birthday;	    
}

int Family::dates_of_month(int month)
{
    if (month == 2)
	return 28;
    else if (month == 4 || month == 6 || month == 9 || month == 11)
	return 30;
    else
	return 31;
}

int Family::dDay()
{
    int bM = (birthday[2]-'0')*10 + (birthday[3]-'0');  // MM of YYMMDD
    int bD = (birthday[4]-'0')*10 + (birthday[5]-'0');	// DD of YYMMDD 

    time_t t = time(0);
    struct tm *now = localtime(&t);
    int cM = (now->tm_mon) + 1;  // current month
    int cD = now->tm_mday;       // current date

    int dday = 0;
    int i;

    for (i=cM; i<=12; i++)
	dday = dday + dates_of_month(i);
    for (i=1; i<bM; i++)
	dday = dday + dates_of_month(i);
    dday = dday - cD + bD;

    if (dday >= 365)          // if bMbD >= cMcD
	dday = dday - 365;

    return dday;
}

void Family::print()
{
    cout << getFirstName() << " " << getLastName() << "_";
    int phonenum = getPhoneNumber();
    int_to_string(phonenum);
    cout << "_" << getBirthday() << "_" <<  dDay() << endl; 
}
