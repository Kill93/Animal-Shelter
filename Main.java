package application;


import java.io.Serializable;
import java.util.Calendar;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class Main extends Application implements Serializable {


	static AnimalList shelterAnimals = AnimalList.getInstance();
	static PersonList people;

	private Controller controll;
	Stage window;
	Button button;
	private static  Button      lostAnimal,foundAnimal,adoptedAnimal,removeAnimal,listAllAnimals,quit,save;
	private static  TextArea    details;

	public void start(Stage primaryStage) {

		window = primaryStage;
		controll = new Controller();
		controll.setPersonlist(people);
		window.setTitle("Animal Shelter");

		lostAnimal = new Button ("Lost Animal");
		lostAnimal.setOnAction(e -> controll.newLostAnimal("Lost Animal Details",0));
		foundAnimal = new Button ("Found Animal");
		foundAnimal.setOnAction(e -> controll.newLostAnimal("Found Animal Details",1));

		adoptedAnimal = new Button ("Adoption");
		adoptedAnimal.setOnAction(e -> controll.newAdoptedAnimal());
		details = new TextArea("Animal Sanctuary");

		removeAnimal = new Button ("Remove Animal");
		removeAnimal.setOnAction(e -> controll.removeAnimal());
		listAllAnimals = new Button ("Display all the animals");
		listAllAnimals.setOnAction(e ->details.setText(shelterAnimals.toString()));
		quit = new Button ("Quit");
		quit.setOnAction(e->System.exit(0));
		save = new Button ("Save");
		save.setOnAction(e-> controll.save());


		VBox row1 = new VBox(20);

		row1.getChildren().addAll(lostAnimal,foundAnimal,adoptedAnimal,removeAnimal,listAllAnimals,quit,save);


		HBox layout = new HBox(30);
		layout.setPadding(new Insets(20, 20, 20, 20));
		layout.getChildren().addAll(row1);

		HBox row2 = new HBox(10);
		row2.getChildren().addAll(details);
		layout.getChildren().addAll(row2);

		Scene scene = new Scene(layout, 700, 500);
		window.setScene(scene);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		window.show();}


	public static void main(String[] args) {
		Animal animal1 = new Animal("Lucky",7,true,"small","Dog","Poodle","Grey");
		Animal animal2 = new Animal("Rosie",12,false,"big","Dog","Terrier","Golden");
		Animal animal3 = new Animal("Treasure",15,false,"small","Cat","Persian","Black");

		Person p1 =new Person ("Killian","Cahersiveen","killian@cit.ie", 872928740);
		Person p2 =new Person ("Ruairi","Kildare","rurs@gmail.ie", 863864544);
		Person p3 =new Person ("Eric","Cork","eric@hotmail.com", 863345783);
		Calendar date = Calendar.getInstance();
		
		people = new PersonList();

		people.addPerson(p1);
		people.addPerson(p2);
		people.addPerson(p3);
		
		shelterAnimals.addAnimal(animal1);
		shelterAnimals.addAnimal(animal2);
		shelterAnimals.addAnimal(animal3);

		Category cat1 = new Lost(date,"CIT");
		Category cat2 = new Found(date,"CIT");
		Category cat3 = new Adoption(date);

		cat1.setPerson(p1);
		cat2.setPerson(p2);
		cat3.setPerson(p3);

		animal1.setCategory(cat1);
		animal2.setCategory(cat2);
		animal3.setCategory(cat3);
		
		
		launch(args);
	}
}
