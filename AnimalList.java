package application;

import java.io.Serializable;
import java.util.ArrayList;

public class AnimalList implements Serializable {
	
	private ArrayList <Animal> animals = null;

	private static AnimalList instance;
	
	/*
	 * Because main and controller are going to use the same list. 
	 * We need to use the same version of the list and update it
	 * Need only one instance of the list
	 */
	
	//if null then returns new instance 
	//if not null it returns current instance thats up to date
	
	public static AnimalList getInstance(){
		if(instance == null){
			return new AnimalList();
		}
		else{
			return instance;
		}
	}
	
	public AnimalList()
	{
		animals = new ArrayList <Animal>();
		instance = this;
	}
	
	public void addAnimal(Animal a){
		animals.add(a);
	}


	public ArrayList <Animal> getList(){
		return animals;
	}

	public void removeAnimal(int i){

		if((i>-1) && (i < animals.size()))
			animals.remove(i);

	}
	
	public boolean removeAnimalByName(String name){
		boolean removed = false;
		name = name.toUpperCase();
		for(int i=0; i<animals.size(); i++)
			if(getAnimal(i).getName().toUpperCase().equals(name)){
				animals.remove(i);
				removed = true;
			}
		
		return removed;
	}
	
	public Animal searchAnimal(int id){
		for(Animal a :animals){
			if(a.getID()==id){
				return a;
			}
		}

		return null;
	}

	public int getAnimalbyPosition(int id){
		int i = 0;
		for(Animal a :animals){
			if(a.getID()==id){
				return i; 
			}
			i++;
		}

		return -1;
	}
	
	public Animal getAnimal(int i)
	{
		if((i>-1) && (i < animals.size()))
		{
			return animals.get(i);
		}
		return null;
	}

	public int getSize()
	{
		return animals.size();
	}
	
	public void print(){
		System.out.println(toString());
	}
	
	public String toString(){
		String output = "";
		
		for(Animal an:animals){
			
			if(an.getCategory() != null){
				output +="*************************"
						+"\n*Amimal Details"+ "*"
						+"\n*************************"
						+"\nName: " + an.getName()
						+"\nID: " + an.getID() + "\nAge: " +
					    +an.getAge() + "\nBreed : " +an.getBreed() + "\nType : (CAT/DOG) " + an.getaType()
					    +"\nDescription : "+ an.getDescription() + "\nGender : " 
					    +an.getgenderMale()  + "\nCategory : " + an.getCategory().getType()  
						+an.getCategory().toString()+"\n";
				if(an.getCategory() instanceof Adoption){
				}
				else{
					output += "Owner : " + an.getCategory().getPerson().getName()+"\n"+"\n";
				}
			}
		}
		
		
		if(output.equals("")){
			output = "No animals in system";
		}
		return output;
	}
	public ArrayList<String> getAnimalsForComboBox(){
		ArrayList<String> names = new ArrayList<String>();
		
		for(Animal a: animals){
			names.add(a.getName());
		}
		return names;
	}
	
	public void setList(ArrayList<Animal> list){
		this.animals =list ;		
		
	}
}
