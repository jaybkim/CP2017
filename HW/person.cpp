#include <iostream>
#include <cstdio>
#include <cstring>
#include <cmath>
#include "person.h"

using std::cin;
using std::cout;
using std::endl;
using std::string;

Person::Person(string &myfirstName, string &mylastName, int &myphoneNumber)
{
    firstName = myfirstName;
    lastName = mylastName;
    phoneNumber = myphoneNumber;
}
void Person::setFirstName(string &myfirstName)
{
    firstName = myfirstName;
}
string Person::getFirstName()
{
    return firstName;
}
void Person::setLastName(string &mylastName)
{
    lastName = mylastName;
}
string Person::getLastName()
{
    return lastName;
}	
void Person::setPhoneNumber(int &myphoneNumber)
{
    phoneNumber = myphoneNumber;
}
int Person::getPhoneNumber()
{
    return phoneNumber;
}
void Person::int_to_string(int &phoneNumber_int)
{
    int front;
    int mid;
    int back;

    front =  phoneNumber_int / 100000000;
    mid   = (phoneNumber_int % 100000000) / 10000;
    back  =  phoneNumber_int % 10000;
}

void Person::print()
{
    cout << getFirstName() << " " << getLastName() << "_";
    int phoneNumber = getPhoneNumber();
    int_to_string(phoneNumber);
    cout << endl;
}
