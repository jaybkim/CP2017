#ifndef  _SVG_H_
#define _SVG_H_
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
#endif
