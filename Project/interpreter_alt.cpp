#include <iostream>
#include <cstring>
#include <vector>
#include <map>
#include <stdlib.h>
#include <iosfwd>
#include <fstream>
#include <sstream>

using namespace std;

class svg
{
    public:
	bool get_selected();
	void set_selected(bool selected_tmp);
	string get_svg_tag_name();
	void set_svg_tag_name(string svg_tag_name_tmp);
	svg* get_parent();
	void set_parent(svg *parent_tmp);
	svg* get_child(int index);
	void add_child(svg* child_tmp);;
	void remove_child(int index);
	int get_children_num();
	void set_children_num(int num);
	void erase(int index);
	void set_unique_id(string id);
	string get_unique_id();
	map<string, string> get_attr();
	void set_attr(map<string, string> attr);
	map<string, string> get_binded_data();
	void set_binded_data(map<string, string> binded_data_tmp);
	void clear_attr();

    private:
	bool selected;
	string svg_tag_name;
	svg* parent;
	vector<svg*> children;
	int children_num;
	string unique_id;
	map<string, string> attr;
	map<string, string> binded_data;
};

string svg::get_svg_tag_name()
{
    return svg_tag_name;
}

void svg::set_svg_tag_name(string svg_tag_name_tmp)
{
    svg_tag_name = svg_tag_name_tmp;
}

bool svg::get_selected()
{
    return selected;
}

void svg::set_selected(bool selected_tmp)
{
    selected = selected_tmp;
}

svg* svg::get_parent()
{
    return parent;
}

void svg::set_parent(svg *parent_tmp)
{
    parent = parent_tmp;
}

svg* svg::get_child(int index)
{
    return children.at(index);
}

void svg::add_child(svg* child_tmp)
{
    children.push_back(child_tmp);
}

int svg::get_children_num()
{
    return children_num;
}

void svg::set_children_num(int num)
{
    children_num = num;
}

void svg::erase(int index)
{
    children.erase(children.begin()+index);
}

void svg::set_unique_id(string id)
{
    unique_id = id;
}

string svg::get_unique_id()
{
    return unique_id;
}

map<string, string> svg::get_attr()
{
    return attr;
}

void svg::set_attr(map<string, string> m)
{
    attr = m;
}

void svg::clear_attr()
{
    attr.clear();
}

map<string, string> svg::get_binded_data()
{
    return binded_data;
}

void svg::set_binded_data(map<string, string> binded_data_tmp)
{
    binded_data = binded_data_tmp;
}

class csv
{
    public :
        void read_field_name_type(ifstream& fin);
        void read_data(ifstream& fin);
        vector<string> get_field_name();
        vector<string> get_field_type();
        map<string, map<string, string>> get_data();
    private :
        vector<string> field_name;
        vector<string> field_type;
        map<string, map<string, string>> data;
};

void csv::read_field_name_type(ifstream& fin)
{
    string line;
    getline(fin, line); // first line of csv file (field name)

    string delimiter = ",";
    size_t pos = 0;
    string name;
    while ((pos = line.find(delimiter)) != std::string::npos)
    {
        name = line.substr(0, pos);
        field_name.push_back(name);
        line.erase(0, pos + delimiter.length());
    }
    field_name.push_back(line); // add last word of line

    getline(fin, line); // second line of csv file (field type)
    pos = 0;
    string type;
    while ((pos = line.find(delimiter)) != std::string::npos)
    {
        type = line.substr(0, pos);
        field_type.push_back(type);
        line.erase(0, pos + delimiter.length());
    }
    field_type.push_back(line); // add last word of line
}

void csv::read_data(ifstream& fin)
{
    string attr;
    string unique_id;
    string delimiter = ",";
    string line;
    while (getline(fin, line))
    {
        size_t pos = 0;
        map<string, string> m;
        int i = 0;
        while ((pos = line.find(delimiter)) != std::string::npos)
        {
            attr = line.substr(0, pos);
            m[field_name.at(i)] = attr;
            i++;
            line.erase(0, pos + delimiter.length());
        }
        m[field_name.back()] = line; // add last word of line
        unique_id = m.find(field_name.at(0)) -> second;
        data[unique_id] = m;
    }
}

vector<string> csv::get_field_name()
{
    return field_name;
}

vector<string> csv::get_field_type()
{
    return field_type;
}

map<string, map<string, string>> csv::get_data()
{
    return data;
}

void append(string svg_tag_name, svg* parent)
{
    svg* child = new svg();
    child -> clear_attr();
    child -> set_selected(true);
    child -> set_svg_tag_name(svg_tag_name);
    child -> set_parent(parent);
    child -> set_children_num(0);
    parent -> add_child(child);
    int child_num = parent -> get_children_num();
    parent -> set_children_num(child_num+1);
}
    
void select(string svg_tag_name, svg* selected_svg)
{
    selected_svg -> set_selected(false);
    svg* child_svg;
    int i;
    for (i=0; i<selected_svg -> get_children_num(); i++)
    {
	child_svg = selected_svg -> get_child(i);
	if (strcmp((child_svg -> get_svg_tag_name()).c_str(), svg_tag_name.c_str())==0)
	{
	    child_svg -> set_selected(true);
	}
    }
}

void remove(svg* parent_of_selected_scope)
{
    int i;
    svg* child_svg;
    int children_num = parent_of_selected_scope -> get_children_num();
    for (i=children_num-1; i>=0; i--)
    {
	child_svg = parent_of_selected_scope -> get_child(i);
	if (child_svg -> get_selected())
	{
	    parent_of_selected_scope -> erase(i);
	    parent_of_selected_scope -> set_children_num(--children_num);
	}
    }
}

void print(ofstream &fout, svg* parent_svg, int index)
{
    fout << "<" << parent_svg -> get_svg_tag_name();
    map<string, string> attr = parent_svg -> get_attr();

    for (auto iter= attr.begin(); iter!=attr.end(); iter++)
    {
	fout << " " << iter->first << "=" << '"' << iter->second << '"'; 
    }
    fout << ">";

    int i;
    for (i=0; i<parent_svg -> get_children_num(); i++)
    {
	svg* child_svg = parent_svg -> get_child(i);
	print(fout, child_svg, index);
	if (child_svg -> get_selected())
	    index++;
    }

    fout << "</" << parent_svg -> get_svg_tag_name() << ">";
}

int main(int argc, char **argv)
{
    svg* root = new svg();
    root -> set_selected(true);
    root -> set_svg_tag_name("html");
    root -> set_parent(NULL);
    root -> set_children_num(0);
    root -> clear_attr();
    svg* parent_of_selected_scope = NULL;
    string recipe;

    svg* child = new svg();
    child -> set_children_num(0);
    child -> clear_attr();
    int i;

    vector<string> field_name;
    vector<string> field_type;
    string svg_tag_name;

    while (true)
    {
	string command;
	cin >> command;
	string svg_attr_name;
	int csv_index;

	ifstream fin;
	
	if (strcmp(command.c_str(), "append")==0)
	{
	    cin >> svg_tag_name;
	    if (parent_of_selected_scope == NULL)
	    {
		child = root;
		append(svg_tag_name, child);
		parent_of_selected_scope = child;
	    }
	    else
	    {
		for (i=0; i<parent_of_selected_scope -> get_children_num(); i++)
		{
		    child = parent_of_selected_scope -> get_child(i);
		    if (child -> get_selected())
		    {
			append(svg_tag_name, child);
			parent_of_selected_scope = child;
		    }
		}
	    }
	}
	else if (strcmp(command.c_str(), "select")==0 || strcmp(command.c_str(), "selectAll")==0)
	{
	    cin >> svg_tag_name;
	    if (parent_of_selected_scope == NULL) // root-selection
	    {
		select(svg_tag_name, root);
		parent_of_selected_scope = root;
	    }
	    else
	    {
		for (i=0; i<parent_of_selected_scope -> get_children_num(); i++)
		{
		    child = parent_of_selected_scope -> get_child(i);
		    if (child -> get_selected())
		    {
			select(svg_tag_name, child);
			parent_of_selected_scope = child;
			break;
		    }
		}
	    }
	}
	else if (strcmp(command.c_str(), "remove")==0)
	{
	    remove(parent_of_selected_scope);
	    parent_of_selected_scope = parent_of_selected_scope -> get_parent();
	}
	else if (strcmp(command.c_str(), "end")==0)
	{
	    if (parent_of_selected_scope == NULL)
		return 0;
	    else
	    {
		for (i=0; i<parent_of_selected_scope -> get_children_num(); i++)
		{
		    child = parent_of_selected_scope -> get_child(i);
		    if (child -> get_selected())
		    {
			child -> set_selected(false);
		    }
		}
		parent_of_selected_scope -> set_selected(true);
		parent_of_selected_scope = parent_of_selected_scope -> get_parent();
	    }
	}
	else if (strcmp(command.c_str(), "enter")==0)
	{
	    cin >> csv_index;
	    fin.open(argv[csv_index]);
            csv enter_csv;
            enter_csv.read_field_name_type(fin);
            field_name = enter_csv.get_field_name();
            field_type = enter_csv.get_field_type();
            enter_csv.read_data(fin);
            map<string, map<string, string>> m = enter_csv.get_data();

	    for (i=0; i<parent_of_selected_scope -> get_children_num(); i++)
	    {
		child = parent_of_selected_scope -> get_child(i);
		if (child -> get_selected())
		{
		    child -> set_selected(false);
		    string child_id = child -> get_unique_id();
		    for (auto p : m)
		    {
			if (strcmp(child_id.c_str(), p.first.c_str()) == 0)
			{
			    child -> set_binded_data(p.second);
			    m.erase(p.first);
			}
		    }
		    break;
		}
	    }
	    if (m.size() > 0)
	    {
		int index = 0;
		while (m.size() > 0)
		{
		    auto p = m.begin();
		    svg* child_new = new svg();
		    child_new -> clear_attr();
		    child_new -> set_svg_tag_name(svg_tag_name);
		    child_new -> set_selected(true);
		    child_new -> set_parent(child);
		    std::advance(p, index);
		    string key = p->first;
		    child_new -> set_unique_id(key);
		    child_new -> set_binded_data(p->second);
		    child_new -> set_children_num(0);
		    child -> add_child(child_new);
		    int children_num_before = child -> get_children_num();
		    child -> set_children_num(children_num_before + 1);
		    m.erase(key);
		}
	    }
	    fin.close();
	}
	else if (strcmp(command.c_str(), "update")==0)
	{
	    cin >> csv_index;

	    fin.open(argv[csv_index]);
            csv update_csv;
            update_csv.read_field_name_type(fin);
            field_name = update_csv.get_field_name();
            field_type = update_csv.get_field_type();
            update_csv.read_data(fin);
            map<string, map<string, string>> m = update_csv.get_data();
	    for (i=0; i<parent_of_selected_scope -> get_children_num(); i++)
	    {
		child = parent_of_selected_scope -> get_child(i);
		if (child -> get_selected())
		{
		    string child_id = child -> get_unique_id();
		    bool exist = false;
		    for (auto p : m)
		    {
			if (strcmp(child_id.c_str(), p.first.c_str()) == 0)
			{
			    exist = true;
			    child -> set_binded_data(p.second);
			    m.erase(p.first);
			}
		    }
		    if (exist == false)
			child -> set_selected(false);
		}
	    }
	    fin.close();
	}
	else if (strcmp(command.c_str(), "exit")==0)
	{
	    cin >> csv_index;

	    fin.open(argv[csv_index]);
            csv exit_csv;
            exit_csv.read_field_name_type(fin);
            field_name = exit_csv.get_field_name();
            field_type = exit_csv.get_field_type();
            exit_csv.read_data(fin);
            map<string, map<string, string>> m = exit_csv.get_data();

	    for (i=0; i<parent_of_selected_scope -> get_children_num(); i++)
	    {
		child = parent_of_selected_scope -> get_child(i);
		if (child -> get_selected())
		{
		    string child_id = child -> get_unique_id();
		    bool exist = false;
		    for (auto p : m)
		    {
			if (strcmp(child_id.c_str(), p.first.c_str()) == 0)
			{
			    exist = true;
			    child -> set_binded_data(p.second);
			    m.erase(p.first);
			}
		    }
		    if (exist == true)
			child -> set_selected(false);
		}
	    }
	    fin.close();
	}
	else if (strcmp(command.c_str(), "cattr")==0)
	{
	    string svg_attr_value;
	    cin >> svg_attr_name;
	    cin >> svg_attr_value;
	    for (i=0; i<parent_of_selected_scope -> get_children_num(); i++)
	    {
		child = parent_of_selected_scope -> get_child(i);
		if (child -> get_selected())
		{
		    map<string, string> m = child -> get_attr();
		    if (m.size()!=0)
		    {
			map<string, string>::iterator it = m.find(svg_tag_name);
			if (it != m.end())
			{
			    m.erase(it->first);
			}
		    }
		    m[svg_attr_name] = svg_attr_value;
		    child -> set_attr(m);
		}
	    }
	}
	else if (strcmp(command.c_str(), "tattr")==0)
	{
	    double x_multiplier, y_multiplier;
	    cin >> x_multiplier >> y_multiplier;
	    map<string, string> attr;
	    int cnt = 0;
	    for (i=0; i<parent_of_selected_scope -> get_children_num(); i++)
	    {
		child = parent_of_selected_scope -> get_child(i);
		attr = child -> get_attr();
		if (child -> get_selected())
		{
		    string transform = std::string("translate(") + std::to_string(x_multiplier * cnt) + "," + std::to_string(y_multiplier * cnt) + ")";
		    cnt++;
		    attr["transform"] = transform;
		    child -> set_attr(attr);
		}
	    }
	}
	    
	else if (strcmp(command.c_str(), "dattr")==0)
	{
	    double mul;
	    double add;
	    string datum_field_name;

	    string dattr_line;
	    getline(cin, dattr_line);
	    stringstream ss(dattr_line);
	    string tmp;
	    vector<string> arg;
	    while (ss >> tmp)
	    {
		arg.push_back(tmp);

		if (ss.peek()==' ')
		    ss.ignore();
	    }

	    svg_attr_name = arg.at(0);
	    datum_field_name = arg.at(1);
	    map<string, string> binded_data;
	    map<string, string> attr;
	    double value;
	    for (i=0; i<parent_of_selected_scope -> get_children_num(); i++)
	    {
		child = parent_of_selected_scope -> get_child(i);
		if (child->get_selected())
		{
		    binded_data = child -> get_binded_data();
		    string value_str = binded_data.find(datum_field_name) -> second;
		    attr = child -> get_attr();
		    if (arg.size()==2) // no mul and add
		    {
			attr[svg_attr_name] = value_str;
		    }
		    else if (arg.size()==3) // only mul
		    {
			mul = atof(arg.at(2).c_str());
			value = atof(value_str.c_str());
			attr[svg_attr_name] = to_string(value * mul);
		    }
		    else if (arg.size()==4) // both mul and add
		    {
			mul = atof(arg.at(2).c_str());
			add = atof(arg.at(3).c_str());
			value = atof(value_str.c_str());
			attr[svg_attr_name] = to_string(value * mul + add);
		    }
		    child -> set_attr(attr);
		}
	    }
	}
	else if (strcmp(command.c_str(), "print")==0)
	{
	    ofstream fout;
	    string output_name;
	    cin >> output_name;
	    fout.open(output_name);
	    print(fout, root, 0);
	    fout.close();
	}
    }
}
