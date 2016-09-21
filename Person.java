package application;

import java.io.Serializable;

public class Person implements Serializable{

	private String name,address,email;
	private int phoneNumber;


	public Person(String name, String address, String email, int phoneNumber) {
		this.name = name;
		this.address = address;
		this.email = email;
		this.phoneNumber = phoneNumber;

	}

	//*****************Getters and Setters**************//
	public void setName(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return name;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	public String getAddress()
	{
		return address;
	}
	
	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getEmail()
	{
		return email;
	}
	
	public void setPhoneNumber(int phoneNumber)
	{
		this.phoneNumber = phoneNumber;
	}

	public int getPhoneNumber()
	{
		return phoneNumber;
	}
	
	
    public String toString()
    {
        String output = "Person's name: "+ this.name + "\n" + "Phone number: "+this.phoneNumber +"\n" + "Address: " +this.address+"\n" + "E-Mail Address: " + this.email + "";
        return output;
    }
    
    public void print()
    {
        System.out.println(toString());
    }
    
	
}

