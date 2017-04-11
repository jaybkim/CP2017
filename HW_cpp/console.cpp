#include <iostream>
#include <cstdio>
#include <cstring>
#include <vector>
#include "person.h"
#include "work.h"
#include "friend.h"
#include "family.h"

using namespace std;

class Console
{
    public:
	void initialState()
	{
	    cout << "Phone Book" << endl;
	    cout << "1. Add person" << endl;
	    cout << "2. Remove person" << endl;
	    cout << "3. Print phone book" <<endl;
	}

	int printAddPerson()
	{
	    int type;

	    cout << "Select Type" << endl;
	    cout << "1. Person" << endl;
	    cout << "2. Work" << endl;
	    cout << "3. Family" << endl;
	    cout << "4. Friend" << endl;
	    cout << "CP-2016-19702>";
	    cin >> type;

	    return type;
	}

	int string_to_int(string &phonenum)
	{
	    int i;
	    int phonenum_int = 0;

	    for (i=0; i<phonenum.size(); i++)
	    {
		if (phonenum[i] >= '0' && phonenum[i] <= '9') // if phonenum[i] == '-' do nothing
		    phonenum_int = phonenum_int * 10 + (phonenum[i] - '0');
	    }

	    return phonenum_int;
	}

	Person addPerson()
	{
	    string fname, lname, phonenum;

	    cout << "Name: ";
	    cin >> fname >> lname; 
	    cout << "Phone_number: ";
	    cin >> phonenum;
	    int phonenum_int = string_to_int(phonenum);

	    Person p(fname, lname, phonenum_int);
	    cout << "Succesfully added new person." << endl;

	    return p;
	}

	Work addWork()
	{
	    string fname, lname, phonenum, team;

	    cout << "Name: ";
	    cin >> fname >> lname; 
	    cout << "Phone_number: ";
	    cin >> phonenum;
	    cout << "Team: ";
	    cin >> team;
	    int phonenum_int = string_to_int(phonenum);

	    Work w(fname, lname, phonenum_int, team);
	    cout << "Succesfully added new person." << endl;

	    return w;
	}

	Family addFamily()
	{
	    string fname, lname, phonenum, birthday;

	    cout << "Name: ";
	    cin >> fname >> lname; 
	    cout << "Phone_number: ";
	    cin >> phonenum;
	    cout << "Birthday: ";
	    cin >> birthday;
	    int phonenum_int = string_to_int(phonenum);

	    Family fa(fname, lname, phonenum_int, birthday);
	    cout << "Succesfully added new person." << endl;

	    return fa;
	}

	Friend addFriend()
	{
	    string fname, lname, phonenum;
	    int age;

	    cout << "Name: ";
	    cin >> fname >> lname; 
	    cout << "Phone_number: ";
	    cin >> phonenum;
	    cout << "Age: ";
	    cin >> age;
	    int phonenum_int = string_to_int(phonenum);

	    Friend fr(fname, lname, phonenum_int, age);
	    cout << "Succesfully added new person." << endl;

	    return fr;
	}

	int rmPerson()
	{
	    int index;

	    cout << "Enter index of person: ";
	    cin >> index;

	    return index;
	}
};


int main()
{
   vector<Person> phonebook;
   vector<int> type; //type1: Person, 2: Work, 3: Family, 4: Friend
   string command;
   Console c;

   while (1)
   {
	cout << "CP-2016-19702>";
	getline(cin, command);

	if (command == "1")
	{
	    int category = c.printAddPerson();
	    if (category == 1)
	    {
		phonebook.push_back(c.addPerson());
		type.push_back(1);
	    }
	    else if (category == 2)
	    {
		phonebook.push_back(c.addWork());
		type.push_back(2);
	    }
	    else if (category == 3)
	    {
		phonebook.push_back(c.addFamily());
		type.push_back(3);
	    }
	    else if (category == 4)
	    {
		phonebook.push_back(c.addFriend());
		type.push_back(4);
	    }
	    getline(cin, command);  // to consume '\n' which was at the end of
				    // phone_number inoput (in c.add*() function) 
	}
	else if (command == "2")
	{
	    int index = c.rmPerson();
	    if (phonebook.size()<index)
		cout << "Person does not exist!" << endl;
	    else
	    {
		phonebook.erase(phonebook.begin()+index-1);
		type.erase(type.begin()+index-1);
		cout << "A person is successfully deleted from the Phone Book!" << endl;
	    }
	    getline(cin, command);  // to consume '\n' which was at the end of
				    // index inoput (in c.rmPersin() function) 
	}
	else if (command == "3")
	{
	    cout << "Phone Book Print" << endl;
	    int i;
	    for (i=0; i<phonebook.size(); i++)
	    {
		cout << i+1 << ". ";
		if (type[i]==1)
		    phonebook[i].print();
		else if (type[i]==2)
		    phonebook[i].print();
		else if (type[i]==3)
		    phonebook[i].print();
		else if (type[i]==4)
		    phonebook[i].print();
	    }
	}
	else if (command == "exit")
	    break;
	else   // when enter key is pressed
	    c.initialState();
    }
}
