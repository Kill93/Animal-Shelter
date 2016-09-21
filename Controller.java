package application;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Controller {

	private Stage primaryStage;
	private AnimalList al;
	private PersonList pl;

	public Controller() {
		al = AnimalList.getInstance();
		primaryStage = new Stage();
	}

	public void addAnimal(String name, int age, boolean genderMale, String description, String aType, String breed,
			String colour) {
		Animal a = new Animal(name, age, genderMale, description, aType, breed, colour);
		al.addAnimal(a);
		System.out.println(name + " is now added to  the listq!");
	}
	// from Main
	public void setPersonlist(PersonList pl){
		this.pl = pl;
	}

	public void removeAnimal(String name) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Please enter the name of the dog  : ");
		name = sc.nextLine();
		al.removeAnimalByName(name);
		System.out.println(name + " is now removed from our list");

		sc.close();
	}

	public void newLostAnimal(String title, int i) {

		GridPane pane = new GridPane();
		pane.setAlignment(Pos.CENTER);
		pane.setHgap(10);
		pane.setVgap(10);
		pane.setPadding(new Insets(5, 5, 5, 5));
		Scene scene = new Scene(pane, 450, 475); 
		
		primaryStage.setScene(scene);

		Text sceneTitle = new Text(title);
		sceneTitle.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
		pane.add(sceneTitle, 0, 0, 1, 1);
		Label name = new Label("Name:");
		pane.add(name, 0, 1); 
		
		final TextField nameField = new TextField();
		pane.add(nameField, 1, 1);
		Label age = new Label("Age:");
		final TextField ageField = new TextField();
		pane.add(age, 0, 2);
		pane.add(ageField, 1, 2);

		CheckBox genderMale = new CheckBox();
		genderMale.setText("Male");
		genderMale.setSelected(true);
		pane.add(genderMale, 1, 3);

		Label colour = new Label("Colour: ");
		pane.add(colour, 0, 4);
		final TextField colourField = new TextField();
		pane.add(colourField, 1, 4);

		Label breed = new Label("Breed: ");
		pane.add(breed, 0, 5);
		final TextField breedField = new TextField();
		pane.add(breedField, 1, 5);

		Label description = new Label("Description: ");
		pane.add(description, 0, 6);
		final TextField descriptionField = new TextField();
		pane.add(descriptionField, 1, 6); 
		
		Label type = new Label("Type: ");
		pane.add(type, 0, 7);
		final TextField typeField = new TextField();
		pane.add(typeField, 1, 7);

		Label location = new Label("Location: ");
		pane.add(location, 0, 8);
		final TextField locationField = new TextField();
		pane.add(locationField, 1, 8);

		Label error = new Label("");
		pane.add(error, 0, 9);

		Button addAnimalButton = new Button("Add Animal");
		HBox hbox = new HBox(10);
		hbox.setAlignment(Pos.BOTTOM_CENTER); 
		
		hbox.getChildren().add(addAnimalButton);
		pane.add(hbox, 1, 10);
		primaryStage.setTitle("New Animal");
		scene.getStylesheets().add(getClass().getResource("addanimal.css").toExternalForm());
		primaryStage.show();

		// lost/found event handler
		addAnimalButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent t) {
	
				boolean add = true;
				String errorMessage = "";
				String name = nameField.getText();
				String age = ageField.getText();
				String animalColour = colourField.getText();
				String animalBreed = breedField.getText();
				String desc = descriptionField.getText();
				String animalType = typeField.getText();
				String loc = locationField.getText();

				if (name.equals("")) {
					add = false;
					errorMessage = "Enter animal Name";
				}

				if (animalColour.equals("")) {
					add = false;
					errorMessage = "Enter animal Colour";
				}

				if (animalBreed.equals("")) {
					add = false;
					errorMessage = "Enter animal Breed";
				}

				if (desc.equals("")) {
					add = false;
					errorMessage = "Enter animal Description";
				}

				animalType = animalType.toUpperCase();
				if (animalType.equals("") || !(animalType.equals("CAT") || animalType.equals("DOG"))) {
					add = false;
					errorMessage = "Enter animal Type(cat or dog)";
				}

				boolean gen = genderMale.isSelected();

				int animalAge = 0;
				try {
					animalAge = Integer.parseInt(age); // CHANGING INTO AN INT
				} catch (NumberFormatException e) {
					add = false;
					errorMessage = "Please enter a numeric age";
				}

				// if add is true then all fields entered correctly
				if (add) {
					Category c;
 
					Calendar d = Calendar.getInstance();
					Date date = d.getTime();
 
					d.setTime(date);
					if (i == 0) {
						c = new Lost(d, loc);
					} else {
						c = new Found(d, loc);
					}
 
					Person p = new Person("test", "Test", "Test", 123456);
 
					c.setPerson(p);
 
					Animal newAnimal = new Animal(name, animalAge, gen, desc, animalType, animalBreed, animalColour);
 
					newAnimal.setCategory(c);
 
					al.addAnimal(newAnimal);
					error.setText("Animal added with name: " + name);
					
					Alert a = new Alert(AlertType.INFORMATION);
			        a.setTitle("Animal added");
			        a.setHeaderText(newAnimal.getName()+" has been added");
			        a.setResizable(false);
			        a.setContentText(newAnimal.getName()+" is now on the !"+newAnimal.getCategory().getType()+" list!");
			        a.showAndWait();
			        
 
					primaryStage.close();
				} else {
 
					error.setText(errorMessage);
				}
			}
		});

	}

	@SuppressWarnings("unchecked")
	public void newAdoptedAnimal() {

		GridPane pane = new GridPane();
		pane.setAlignment(Pos.CENTER);
		pane.setHgap(10);
		pane.setVgap(10);
		pane.setPadding(new Insets(5, 5, 5, 5)); 
		Scene scene = new Scene(pane, 600, 475);
													
		primaryStage.setScene(scene);

		Text sceneTitle = new Text("Add Animal for Adoption");
		sceneTitle.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
		pane.add(sceneTitle, 0, 0, 1, 1);
		Label name = new Label("Select Animal:");
		pane.add(name, 0, 1);// 
								

		final ComboBox<String> animalComboBox = new ComboBox();
		animalComboBox.getItems().addAll(al.getAnimalsForComboBox());
		pane.add(animalComboBox, 1, 1);

		CheckBox neutered = new CheckBox();
		neutered.setText("Neutered");
		neutered.setSelected(false);
		pane.add(neutered, 1, 2);
		
		CheckBox chipped = new CheckBox();
		chipped.setText("Chipped");
		chipped.setSelected(false);
		pane.add(chipped, 1, 3);
		
		CheckBox vaccinated = new CheckBox();
		vaccinated.setText("Vaccinated");
		vaccinated.setSelected(false);
		pane.add(vaccinated, 1, 4);
		
		CheckBox reserved = new CheckBox();
		reserved.setText("Reserved");
		reserved.setSelected(false);
		pane.add(reserved, 1, 5);
		
		Label status = new Label("Animal Status:");
		pane.add(status, 0, 6);								
		
		final ComboBox<String> statusComboBox = new ComboBox();
		statusComboBox.getItems().addAll(
				"inTraining","Ready","Not Ready"
				);

		pane.add(statusComboBox, 1, 6);

		final MenuButton interested = new MenuButton("Interested People");  
        final ArrayList<CheckMenuItem> selectedPeople = new ArrayList<CheckMenuItem>();
        for(Person p:pl.people){
        	CheckMenuItem i = new CheckMenuItem(p.getName()+" - "+p.getPhoneNumber());
        	selectedPeople.add(i);
        }
        interested.getItems().addAll(selectedPeople);  
  
        final ListView<String> selectedItems = new ListView<>();  
        for (final CheckMenuItem item : selectedPeople) {  
            item.selectedProperty().addListener(new ChangeListener<Boolean>() {  
                @Override  
                public void changed(ObservableValue<? extends Boolean> obs,  
                        Boolean wasPreviouslySelected, Boolean isNowSelected) {  
                    if (isNowSelected) {  
                        selectedItems.getItems().add(item.getText());  
                    } else {  
                        selectedItems.getItems().remove(item.getText());  
                    }  
                }  
            });  
        }  
        Label interestedParties = new Label("People Interested(Multi-Select):");
		pane.add(interestedParties, 0, 7);	
		
        pane.add(interested, 1, 7);
        
        Label dateLabel = new Label("Adoption Date: ");
		pane.add(dateLabel, 1, 9);
		
		Label selectedDate = new Label("");
		pane.add(selectedDate, 2, 9);
		
        final DatePicker datePicker = new DatePicker();
        datePicker.setOnAction(new EventHandler() {
            public void handle(Event t) {
                LocalDate date = datePicker.getValue();
                selectedDate.setText("" + date);
            }
        });
        pane.add(datePicker,0,9);
        
        Label errorLabel = new Label("");
        pane.add(errorLabel, 0, 10);
		
        Button addAdoptionButton = new Button("Add To Adopted List");
		
        addAdoptionButton.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
			public void handle(ActionEvent arg0) {
        		errorLabel.setText("");
        		Animal adoptedAnimal = null;
    			boolean add = true;
				try{
					String animalName = (String) animalComboBox.getSelectionModel().getSelectedItem().toString(); 
					int i = 0;
					for(String s:animalComboBox.getItems()){
						if(s.equals(animalName)){
							adoptedAnimal = al.getAnimal(i);
						}
						i++;
					}
					
					System.out.println(adoptedAnimal.getName());
					
				}				
				catch(NullPointerException e){
					errorLabel.setText("Please Select an Animal for Adoption");
					add = false;
				}
				
				boolean isNeutered = neutered.isSelected();
				boolean isVaccinated = vaccinated.isSelected();
				boolean isChipped = chipped.isSelected();
				boolean isReserved = reserved.isSelected();

				String animalStatus = "";
				try{
					animalStatus = (String) animalComboBox.getSelectionModel().getSelectedItem().toString(); 
				}				
				catch(NullPointerException e){
					errorLabel.setText("Please Select an Animal Status for Adoption");
					add = false;
				}
				
				Calendar adoptionDate = Calendar.getInstance();
				if(selectedDate.getText().equals("")){
					add = false;
					errorLabel.setText("Please select an adoption date");
				}else{

					SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
					Date d;
					try {
						d = format1.parse(selectedDate.getText());
						adoptionDate.setTime(d); 
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					 
				}
				
				PersonList interestedPeople = new PersonList();
				for(String people: selectedItems.getItems()){
					for(Person p:pl.people){
						String valueToMatch = p.getName()+" - "+p.getPhoneNumber();
						if(valueToMatch.equals(people)){
							interestedPeople.addPerson(p);
						}
					}
				}
				if(add){
					Adoption adoption = new Adoption(adoptionDate);
					adoption.setChipped(isChipped);
					adoption.setNeutered(isNeutered);
					adoption.setInterested(interestedPeople);
					adoption.setReserved(isReserved);
					adoption.setStatus(animalStatus);
					adoption.setVaccinated(isVaccinated);
					al.getAnimal(al.getAnimalbyPosition(adoptedAnimal.getID())).setCategory(adoption);
					
					Alert a = new Alert(AlertType.INFORMATION);
			        a.setTitle("Animal updated");
			        a.setHeaderText(adoptedAnimal.getName()+" has been updated");
			        a.setResizable(false);
			        a.setContentText(adoptedAnimal.getName()+" is now available for adoption!");
			        a.showAndWait();
			        
					primaryStage.close();
				}
			}
		});
        
        pane.add(addAdoptionButton, 1, 11);
        
        primaryStage.setTitle("Adoption");
		scene.getStylesheets().add(getClass().getResource("addanimal.css").toExternalForm());
		primaryStage.show();

	}

	public void save() {
		SaveAndLoad save = new SaveAndLoad("animal.ser");
		save.saveToFile(al);
	}

	public void readFromFile(){
		SaveAndLoad load = new SaveAndLoad("animal.ser");
		al = load.readFromFile();
	}
	public void removeAnimal() {

		GridPane pane = new GridPane();
		pane.setAlignment(Pos.CENTER);
		pane.setHgap(10);
		pane.setVgap(10);
		pane.setPadding(new Insets(5, 5, 5, 5));
		Scene scene = new Scene(pane, 600, 475);
		primaryStage.setScene(scene);
		
		
		Label name = new Label("Animal Name: ");
		pane.add(name, 0, 1);
		final TextField nameField = new TextField();
		pane.add(nameField, 1, 1);

		Button searchButton = new Button("Remove Animal by Name");
		HBox hbox = new HBox(10);
		hbox.setAlignment(Pos.BOTTOM_RIGHT);
		hbox.getChildren().add(searchButton);
		pane.add(hbox, 2, 1);

		Label error = new Label("");
		pane.add(error, 0, 6);
		searchButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent t) {

				String search = nameField.getText();

				if (search.equals("")) {
					error.setText("Please enter a name to search");
				} else {
					boolean success = al.removeAnimalByName(search);
					if (success) {
						Alert a = new Alert(AlertType.INFORMATION);
				        a.setTitle("Animal updated");
				        a.setHeaderText(search+" has been removed");
				        a.setResizable(false);
				        a.setContentText(search+" is no longer on the system!");
				        a.showAndWait();

						primaryStage.close();
					} else {

						error.setText(search + " not found");
					}
				}
			}

		});
		
		final ComboBox<String> animalComboBox = new ComboBox();
		animalComboBox.getItems().addAll(al.getAnimalsForComboBox());
		pane.add(animalComboBox, 1, 10);
		
		Label selectanimal = new Label("Select Animal:");
		pane.add(selectanimal, 0, 10);
		

		Button removeButton = new Button("Remove selected Animal");
		removeButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent t) {
				Animal removedAnimal = null;
    			boolean add = true;
				try{
					String animalName = (String) animalComboBox.getSelectionModel().getSelectedItem().toString(); 

					int i = 0;
					for(String s:animalComboBox.getItems()){
						if(s.equals(animalName)){
							removedAnimal = al.getAnimal(i);
						}
						i++;
					}
					
					boolean success = al.removeAnimalByName(removedAnimal.getName());
					if (success) {
						Alert a = new Alert(AlertType.INFORMATION);
				        a.setTitle("Animal updated");
				        a.setHeaderText(removedAnimal.getName()+" has been removed");
				        a.setResizable(false);
				        a.setContentText(removedAnimal.getName()+" is no longer on the system!");
				        a.showAndWait();

						primaryStage.close();
					} else {
						error.setText(removedAnimal.getName() + " not removed");
					}
					
				}				
				catch(NullPointerException e){
					error.setText("Please Select an Animal to be removed");
					add = false;
				}
				
				
			}

		});
		HBox hbox2 = new HBox(10);
		hbox2.setAlignment(Pos.BOTTOM_RIGHT);
		hbox2.getChildren().add(removeButton);
		pane.add(hbox2, 2, 10);
		primaryStage.setTitle("Remove Animal");
		scene.getStylesheets().add(getClass().getResource("addanimal.css").toExternalForm());
		primaryStage.show();
	}
	
	
}
