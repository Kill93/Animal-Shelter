package application;

import java.io.Serializable;
import java.util.Calendar;

public class Adoption extends Category implements Serializable{
	
	private boolean neutered,chipped,vaccinated,reserved;
	String status;
	Calendar date;
	PersonList interested;
	
	public Adoption(Calendar date) {
		super(date,"Adoption");
		neutered = false;
		chipped = false;
		vaccinated = false;
		reserved = false;
		interested = new PersonList();
	}
	
	//*****************Getters and Setters**************//

	public boolean isNeutered() {
		return neutered;
	}


	public void setNeutered(boolean neutered) {
		this.neutered = neutered;
	}


	public boolean isChipped() {
		return chipped;
	}


	public void setChipped(boolean chipped) {
		this.chipped = chipped;
	}


	public boolean isVaccinated() {
		return vaccinated;
	}


	public void setVaccinated(boolean vaccinated) {
		this.vaccinated = vaccinated;
	}


	public boolean isReserved() {
		return reserved;
	}


	public void setReserved(boolean reserved) {
		this.reserved = reserved;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}
	
	public void setInterested(PersonList p){
		this.interested = p;
	}
	
	public String toString(){
		String output = "\nAdoption Date: "+this.getDate().getTime();
		output+="\nNeutered: "+isNeutered();
		output+="\nChipped: "+isChipped();
		output+="\nVaccinated: "+isVaccinated();
		output+="\nReserved: "+isReserved();
		output+="\nStatus: "+getStatus();
		
		String interestedParties = "";
		for(Person p:interested.people){
			interestedParties+="\n\t"+p.getName()+" - "+p.getPhoneNumber();
		}
		if(interestedParties.equals("")){
			interestedParties = "No interested People";
		}
		output+="\nInterested Parties: "+interestedParties;
		return output;
	}
	public void print(){
		System.out.println(toString());
	}
}
