package application;


import java.io.Serializable;
import java.util.Calendar;

public class Found extends Category implements Serializable{


	private String location;

	public Found(Calendar date,String location) {
		super(date,"Found");
		this.location = location;
	}

	//*****************Getters and Setters**************//
	
	public String getLocation(){
		return this.location;
	}

	public String toString(){
		String output = "Current time is : " + date.getTime()+ " " + "\nLocation is :"+ location;
		return output;
	}

	public void print()
	{
		System.out.println(toString());
	}

}