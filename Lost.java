package application;


import java.io.Serializable;
import java.util.Calendar;

public class Lost extends Category implements Serializable{


	private String location;
	


	public Lost(Calendar date,String location) {
		super(date,"Lost");
		this.location = location;
		
	}
	
	//*****************Getters and Setters**************//

	public String getLocation(){
		return location;
	}

	public String toString(){
		String output = "Current time is : " + date.getTime()+ " " + "\nLocation "+ location;
		return output;
	}

	public void print()
	{
		System.out.println(toString());
	}

}
