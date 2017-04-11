#ifndef _FRIEND_H_
#define _FRIEND_H_
#include <iostream>
#include <cstdio>
#include <cstring>
#include "person.h"

using std::cin;
using std::cout;
using std::endl;
using std::string;

class Friend : public Person
{
    public:
	Friend(string &, string &, int &, int &);
	void setAge(int);
	int getAge();
	void print();
    private:
	int age;
};
#endif
