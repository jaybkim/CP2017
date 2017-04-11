#include <iostream>
#include <cstdio>
#include <cstring>
#include "work.h"

using std::cin;
using std::cout;
using std::endl;
using std::string;

Work::Work(string &myfirstName, string &mylastName, int &myphoneNumber, string &myteam) : Person(myfirstName, mylastName, myphoneNumber) 
{
    team = myteam;
}

void Work::setTeam(string myteam)
{
    team = myteam;
}

string Work::getTeam()
{
    return team;
}

void Work::print()
{
    cout << getFirstName() << " " << getLastName() << "_";
    int phonenum = getPhoneNumber();
    int_to_string(phonenum);
    cout << "_" << getTeam() << endl;
}
