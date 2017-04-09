#ifndef _WORK_H_
#define _WORK_H_
#include <iostream>
#include <cstdio>
#include <cstring>
#include "person.h"

using std::cin;
using std::cout;
using std::endl;
using std::string;

class Work : public Person
{
    public:
	Work(string &, string &, int &, string &);
	void setTeam(string);
	string getTeam();
	void print();
    private:
	string team;
};
#endif
