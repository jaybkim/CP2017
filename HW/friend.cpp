#include <iostream>
#include <cstdio>
#include <cstring>
#include "friend.h"

using std::cin;
using std::cout;
using std::endl;
using std::string;

Friend::Friend(string &myfirstName, string &mylastName, int &myphoneNumber, int &myage) : Person(myfirstName, mylastName, myphoneNumber) 
{
    age = myage;
}

void Friend::setAge(int myage)
{
    age = myage;
}

int Friend::getAge()
{
    return age;
}

void Friend::print()
{
    cout << getFirstName() << " " << getLastName() << "_";
    int phoneNumber = getPhoneNumber();
    int_to_string(phoneNumber);
    cout << "_" << getAge() << endl;
}
