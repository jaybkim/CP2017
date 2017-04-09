#ifndef _PERSON_H_
#define _PERSON_H_
#include <iostream>
#include <cstdio>
#include <cstring>

using std::cin;
using std::cout;
using std::endl;
using std::string;

class Person
{
    public:
	Person(string &, string &, int &);
	void setFirstName(string &);
	string getFirstName();
	void setLastName(string &);
	string getLastName();
	void setPhoneNumber(int &);
 	int getPhoneNumber();
	void int_to_string(int &);
	void print();
    private:
	string firstName;
	string lastName;
	int phoneNumber;
};
#endif
