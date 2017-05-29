#include <iostream>
#include <cstring>
#include <vector>
#include <map>
#include <stdlib.h>
#include <iosfwd>
#include <fstream>
#include <sstream>
#include "svg.h"

using namespace std;

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
