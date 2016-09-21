package application;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SaveAndLoad {
	
	String fileName;
	
	public SaveAndLoad(String file){
		this.fileName = file;
	}
	
		//serializable
		public void saveToFile (AnimalList al){
			try {
				FileOutputStream fileOutput = new FileOutputStream(fileName);
				ObjectOutputStream objectOutput = new ObjectOutputStream (fileOutput);
				objectOutput.writeObject(al);
			}catch(Exception e){
			}
			
		}
		
		public AnimalList readFromFile (){
			AnimalList al;
			try {
				FileInputStream fileOutput = new FileInputStream(fileName);
				ObjectInputStream objectInput = new ObjectInputStream (fileOutput);
				al = (AnimalList) objectInput.readObject();
				return al;
			}catch(Exception e){
			}
			
			return null;
			
		}
}
