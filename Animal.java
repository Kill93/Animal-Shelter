package application;

import java.io.Serializable;

public class Animal implements Serializable{

	private int ID,age;
	private String name,description,aType,breed,colour;
	private boolean genderMale;
	private static int id = 1;
	Category myCat;
	
	
	//Main constructor
	Animal(String name, boolean genderMale, String description,String aType) {
		this.name = name;
		this.genderMale = genderMale;
		this.description = description;
		this.aType = aType;
		this.ID = id;
		id++;
		myCat = null;
	}

	Animal(String name,int age,boolean genderMale, String description,String aType, String breed,String colour){
		this.ID = id;
		id++;
		this.name = name;
		this.age = age;
		this.genderMale = genderMale;
		this.description = description;
		this.aType = aType;
		this.breed = breed;
		this.colour = colour;
	}
	//**********************Getters and Setters *****************//
	public Category getCategory() {
		return myCat;
	}
	public void setCategory(Category myCat) {
		this.myCat = myCat;
	}
	
	public void setID(int ID)
	{
		this.ID = ID;
	}
	public int getID()
	{
		return ID;
	}


	public void setAge(int age)
	{
		this.age = age;
	}
	public int getAge()
	{
		return age;
	}
	
	public void setBreed(String breed)
	{
		this.breed = breed;
	}
	public String getBreed()
	{
		return breed;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	public String getName()
	{
		return name;
	}
	
	public void setColour(String colour)
	{
		this.colour = colour;
	}
	public String getColour()
	{
		return colour;
	}
	
	public void setaType(String aType)
	{
		this.aType = aType;
	}
	public String getaType()
	{
		return aType;
	}
	
	
	public void setDescription(String description)
	{
		this.description = description;
	}
	public String getDescription()
	{
		return description;
	}
	
	
	public void setgenderMale(boolean genderMale)
	{
		this.genderMale = genderMale;
	}
	public boolean getgenderMale()
	{
		return genderMale;
	}
	

	
	
    public String toString()
    {
        String output = "Animal Name : " + this.name + " " + "Animal ID : " + this.ID + "Animal Age: " +
    this.age + "Animal breed: " +this.breed + "Animaml type : " + this.aType + 
    "Animal Description : "+ this.description + "Animal genderMale : " + this.genderMale;
        return output; 
    }
    
    public void print()
    {
        System.out.println(toString());
    }
}

