#ifndef _FAMILY_H_
#define _FAMILY_H_
#include <iostream>
#include <cstdio>
#include <cstring>
#include "person.h"

using std::cin;
using std::cout;
using std::endl;
using std::string;

class Family : public Person
{
    public:
	Family(string &, string &, int &, string &);
	void setBirthday(string);
	string getBirthday();
	int dates_of_month(int);
	int dDay();
	void print();
    private:
	string birthday;
};
#endif
