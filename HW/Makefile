SOURCES=console.cpp family.cpp friend.cpp person.cpp work.cpp
OBJECTS=$(SOURCES:.cpp=.o)

CC=g++

phonebook: $(OBJECTS)
	g++ -o $@ $(OBJECTS)

clean:
	rm -f $(OBJECTS) phonebook
