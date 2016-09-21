package application;

import java.io.Serializable;
import java.util.ArrayList;

public class PersonList implements Serializable {
	ArrayList<Person> people;
	
	//instantiate arrayList		
	public PersonList(){
		people = new ArrayList<Person>();
	}
	
	public void addPerson(Person p){
		people.add(p);
	}
	//returns person by position
	public Person getPerson(int i){
		return people.get(i);
	}
	
	//search people by name returns position
	public int searchPeople(String name){
		int i = 0;
		for(Person p:people){
			if(p.getName().toUpperCase().equals(name.toUpperCase())){
				return i;
			}
			i++;
		}
		return -1;
	}
	
	//return a string arraylist so we can populate the dropdown menu
	public ArrayList<String> getPeopleForComboBox(){
		ArrayList<String> names = new ArrayList<String>();
		
		for(Person a: people){
			names.add(a.getName());
		}
		return names;
	}
}
