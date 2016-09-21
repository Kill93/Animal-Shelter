package application;

import java.io.Serializable;
import java.util.Calendar;

public class Category implements Serializable{
	
	Person contact;
	Calendar date;
	private String type;


	public Category(Calendar date,String type) {
		this.date = date;
		this.type = type;
	}
	
	//*****************Getters and Setters**************//

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Calendar getDate(){
		return date;
	}
	
	public String toString(){
		String output = "Current time is : " + date.getTime();
		return output;
	}

	public void print()
	{
		System.out.println(toString());
	}
	
	public void setPerson(Person p){
		this.contact = p;
	}
	
	public Person getPerson(){
		return contact;
	}
	
}
