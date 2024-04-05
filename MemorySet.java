package application;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class MemorySet extends Application {
	ArrayList<Martyr> martyrs = new ArrayList<>();
	Label[] labels = new Label[martyrs.size()];
	ArrayList<Label> labelsList = new ArrayList<>(Arrays.asList(labels)); // convert the array to array list
	Label nameLabel = new Label(" ");
	Label response = new Label(" ");

	public void start(Stage stage) throws Exception {
		readFromFile();
		HBox hboxFirst = new HBox(5); // hbox for the image and the label
		Label one = new Label("طـوفـان الأقـصى");
		one.setStyle("-fx-font-style: italic; -fx-font-weight: bold;");
		one.setFont(new Font(25));
		Image image = new Image("https://scontent.fjrs4-1.fna.fbcdn.net/v/t1.15752-9/421442151_356351827193216_1741238743822834179_n.png?_nc_cat=103&ccb=1-7&_nc_sid=8cd0a2&_nc_ohc=nGH_xK5Kx6gAX80QA0r&_nc_ht=scontent.fjrs4-1.fna&oh=03_AdRqGYBNJa9Ok3wn4jBkIJNi1SSDXfjyvAj96H9Slaq0vA&oe=65DCA289");
		ImageView view = new ImageView(image);
		view.setFitHeight(100); // determine the height of the image
		view.setFitWidth(100); // determine the width of the image
		hboxFirst.getChildren().addAll(view,one); // add the image and the label to the pane
		hboxFirst.setAlignment(Pos.CENTER); // set the position of the pane center
		VBox vbox = new VBox(20); // vbox for the first interface
		vbox.setStyle("-fx-background-color: #E6FFEE;"); 
		Polygon triangle = new Polygon();
		triangle.getPoints().addAll(
		      -20.0, -20.0, 
		       20.0, -20.0,  // coordinates of the triangle
		       0.0,  20.0); 
		triangle.setFill(Color.RED);
		Button testButton = new Button("Meomry Test Window");
		testButton.setStyle("-fx-font-style: italic; -fx-font-weight: bold;");
		Button createButton = new Button("Create Martyr List Window");
		createButton.setStyle("-fx-font-style: italic; -fx-font-weight: bold;");
		vbox.getChildren().addAll(hboxFirst,triangle,createButton, testButton); // add all nodes to the pane
		vbox.setAlignment(Pos.CENTER);
		Scene mainScene = new Scene(vbox, 400, 400);
		stage.setScene(mainScene);
		stage.setTitle("The War On Gaza");
		stage.show();
		
		// create martyr list window interface
		StackPane createPane = new StackPane(); 
		HBox hbox = new HBox(10);
		Label label = new Label("Add Martyr: (Name date of martyrdom) ");
		label.setFont(new Font(15));
		label.setStyle("-fx-font-style: italic; -fx-font-weight: bold;");
		TextField textFieldData = new TextField();
		textFieldData.setPrefColumnCount(10);
		Button addToFile = new Button("Add to File");
		addToFile.setStyle("-fx-font-style: italic; -fx-font-weight: bold;");
		hbox.getChildren().addAll(label, textFieldData, addToFile);
		hbox.setAlignment(Pos.CENTER);
		VBox vbox1 = new VBox(10);
		vbox1.setAlignment(Pos.CENTER);
		Label labelError = new Label("");
		labelError.setFont(new Font(20));
		labelError.setTextFill(Color.RED);
		Button Prior = new Button("Prior");
		Prior.setStyle("-fx-font-style: italic; -fx-font-weight: bold;");
		vbox1.getChildren().addAll(hbox, labelError, Prior);
		createPane.getChildren().addAll(vbox1);
		createPane.setStyle("-fx-background-color: #E6FFEE;"); 
		Scene sceneC = new Scene(createPane, 600, 200);
		
		
		// test memory window interface 
		BorderPane borderPane = new BorderPane();
		Button back = new Button("Prior");
		back.setStyle("-fx-font-style: italic; -fx-font-weight: bold;");
		VBox vpane = new VBox(15);
		Label firstLine = new Label("Test Your Memory");
		firstLine.setFont(new Font(20));
		firstLine.setStyle("-fx-font-style: italic; -fx-font-weight: bold;");
		Label secondLine = new Label("Hey, my friend! Test your memory to see if you remeber who was martyred before");
		secondLine.setStyle("-fx-font-style: italic; -fx-font-weight: bold;");
		Label thirdLine = new Label(
				"Pick two Martyr Names from the following list, Enter them in  the boxes in the correct order(date of death), and then press \n                                                                                 the 'Submit' button");
		thirdLine.setStyle("-fx-font-style: italic; -fx-font-weight: bold;");
		thirdLine.setTextFill(Color.LIGHTSEAGREEN);
		vpane.getChildren().addAll(firstLine, secondLine, thirdLine);
		vpane.setAlignment(Pos.CENTER);
		borderPane.setTop(vpane);
		HBox hbox2 = new HBox(15);
		TextField first = new TextField();
		Label middle = new Label("Martyred Before");
		middle.setTextFill(Color.LIGHTSEAGREEN);
		middle.setStyle("-fx-font-style: italic; -fx-font-weight: bold;");
		TextField second = new TextField();
		hbox2.getChildren().addAll(first, middle, second);
		HBox hButtons = new HBox(10);
		Button submit = new Button("Submit");
		submit.setStyle("-fx-font-style: italic; -fx-font-weight: bold;");
		Button clear = new Button("Clear");
		clear.setStyle("-fx-font-style: italic; -fx-font-weight: bold;");
		ComboBox<String> combobox = new ComboBox<>();
		combobox.getItems().addAll("BurlyWood", "Beige", "LightBlue", "LightGray");
		combobox.setStyle("-fx-font-style: italic; -fx-font-weight: bold;");
		hButtons.getChildren().addAll(submit, clear, combobox);
		VBox vButtom = new VBox(15);
		response.setStyle("-fx-font-style: italic; -fx-font-weight: bold;");
		vButtom.getChildren().addAll(hbox2, hButtons, response, back);
		vButtom.setAlignment(Pos.CENTER);
		hButtons.setAlignment(Pos.CENTER);
		hbox2.setAlignment(Pos.CENTER);
		borderPane.setBottom(vButtom);
		borderPane.setStyle("-fx-background-color: #E6FFEE;"); 
		Scene sceneT = new Scene(borderPane, 800, 600);
		borderPane.setMargin(vpane, new javafx.geometry.Insets(20, 0, 0, 0));
		borderPane.setMargin(vButtom, new javafx.geometry.Insets(0, 0, 20, 0));

		nameLabel.setFont(new Font(20));
		nameLabel.setStyle("-fx-font-style: italic; -fx-font-weight: bold;");
		for (int i = 0; i < martyrs.size(); i++) {
			nameLabel = new Label(martyrs.get(i).getName());
			labelsList.add(nameLabel);
			nameLabel.setFont(new Font(20));
			nameLabel.setStyle("-fx-font-style: italic; -fx-font-weight: bold;");
		}
		FlowPane Fpane = new FlowPane();
		Fpane.setHgap(10);
		Fpane.setVgap(10);
		for (int i = 0; i < labelsList.size(); i++) {
			Fpane.getChildren().add(labelsList.get(i));
		}

		borderPane.setCenter(Fpane);
		borderPane.setMargin(Fpane, new javafx.geometry.Insets(50, 0, 0, 40));
		
		// operations of buttons
		createButton.setOnAction(e -> {
			stage.setScene(sceneC);
			stage.setTitle("Add Martyr to the file");
		});
		 
		testButton.setOnAction(e -> {
			stage.setScene(sceneT);
			stage.setTitle("Memory Test");
		});

		Prior.setOnAction(e -> {
			stage.setScene(mainScene);
			stage.setTitle("The War on Gaza");
			
		});

		back.setOnAction(e -> {
			stage.setScene(mainScene);
			stage.setTitle("The War on Gaza");
		});

		addToFile.setOnAction(e -> {
			try {
				String[] textFieldSplit1 = textFieldData.getText().split(" "); // split the text field to check if name and date are entered
				String[] textFieldSplit2 = new String[3];
				if (textFieldSplit1.length == 2) {
					textFieldSplit2 = textFieldSplit1[1].split("/");  // split the date to check if it allowed or not
				}

				if (textFieldSplit1.length == 2 && textFieldSplit2.length == 3) {
					String name = textFieldSplit1[0];
					Martyr martyr = new Martyr(textFieldSplit1[1], textFieldSplit1[0]); // date terms 
					if (Integer.parseInt(textFieldSplit2[2]) > 2024 || Integer.parseInt(textFieldSplit2[2]) < 1
							|| Integer.parseInt(textFieldSplit2[0]) > 31 || Integer.parseInt(textFieldSplit2[0]) < 1
							|| Integer.parseInt(textFieldSplit2[1]) > 12 || Integer.parseInt(textFieldSplit2[1]) < 1) {
						labelError.setText("The Date Not Allowed");
						labelError.setStyle("-fx-font-style: italic; -fx-font-weight: bold;");
						labelError.setTextFill(Color.RED);

					} else if (inList(name) == false) { // to check if the name in the list or not
						martyrs.add(martyr);
						martyr.writeToBinaryFile(); // add the martyr to the file if dose not exist in the list
						nameLabel = new Label(name + "   ");
						nameLabel.setFont(new Font(20));
						nameLabel.setStyle("-fx-font-style: italic; -fx-font-weight: bold;");
						labelsList.add(nameLabel); // add the name to the array list
						Fpane.getChildren().clear();
						for (int i = 0; i < labelsList.size(); i++) {
							Fpane.getChildren().add(labelsList.get(i));
						}
						labelError.setText("Added Successfully");
						labelError.setTextFill(Color.GREEN);
						labelError.setStyle("-fx-font-style: italic; -fx-font-weight: bold;");
					} else {
						labelError.setText("Added Before");
						labelError.setTextFill(Color.RED);
						labelError.setStyle("-fx-font-style: italic; -fx-font-weight: bold;");
					}
				} else if (textFieldSplit1.length != 2) {
					labelError.setText("You Must Add Name and Date!!");
					labelError.setStyle("-fx-font-style: italic; -fx-font-weight: bold;");
				} else if (textFieldSplit2.length != 3) {
					labelError.setText("The Date Form is: day/month/year, Check it!");
					labelError.setStyle("-fx-font-style: italic; -fx-font-weight: bold;");
					labelError.setTextFill(Color.RED);
				}

			} catch (NumberFormatException ex) { // if the user enter sting instead of numbers
				labelError.setText("The Date Cannot Be a String, Check it!");
				labelError.setStyle("-fx-font-style: italic; -fx-font-weight: bold;");
				labelError.setTextFill(Color.RED);
			}
		});

		clear.setOnAction(e -> {
			first.clear();
			second.clear();
			response.setText(" ");

		});
		
	

		submit.setOnAction(e -> {
			String FC = first.getText();
			String SC = second.getText();
			String FD = " ", SD = " ";
			// cases in the test memory interface
			if (FC.isEmpty() && SC.isEmpty()) {
				response.setText(" Enter names in both boxes. Then press Submit.");
			} else if (inList(SC) == false && inList(FC) == false) {
				response.setText("Neither entry is in the name list.");
			} else if (inList(FC) == false) {
				response.setText(" First entry not in name list – check spelling.");
			} else if (inList(SC) == false) {
				response.setText(" Second entry not in name list – check spelling.");
			} else if (FC.equals(SC)) {
				response.setText(" You entered the same names. Try again.");
			} else if (inList(FC) && inList(SC)) {
				for (int i = 0; i < martyrs.size(); i++) {
					if (martyrs.get(i).getName().equals(FC)) {
						FD = martyrs.get(i).getDateOfMartyrdom();
					} else if (martyrs.get(i).getName().equals(SC)) {
						SD = martyrs.get(i).getDateOfMartyrdom();
					}
				}

				String[] Fdate = FD.split("/");
				String[] Cdate = SD.split("/");
				// compare between two the dates
				Date date1 = new Date(Integer.parseInt(Fdate[2]), Integer.parseInt(Fdate[1]),
						Integer.parseInt(Fdate[0]));
				Date date2 = new Date(Integer.parseInt(Cdate[2]), Integer.parseInt(Cdate[1]),
						Integer.parseInt(Cdate[0]));

				if (date1.compareTo(date2) == -1) {
					response.setText("You are correct:)");
				}

				else if (date1.compareTo(date2) == 1) {
					response.setText(" Wrong. Try again.");
				}

				else {
					response.setText("They martyred in The same Day!");
				}
			}
		});
		
		combobox.setOnAction(e -> {
			if(combobox.getValue().equals("Beige")) {
				borderPane.setStyle("-fx-background-color: beige;");
				thirdLine.setTextFill(Color.BROWN);
				middle.setTextFill(Color.BROWN);
			}
			else if(combobox.getValue().equals("BurlyWood")) {
				borderPane.setStyle("-fx-background-color: BURLYWOOD;");
				thirdLine.setTextFill(Color.AZURE);
				middle.setTextFill(Color.AZURE);
			}
			else if(combobox.getValue().equals("LightBlue")) {
				borderPane.setStyle("-fx-background-color: lightblue;");
				thirdLine.setTextFill(Color.AZURE);
				middle.setTextFill(Color.AZURE);
			}
			else if(combobox.getValue().equals("LightGray")) {
				borderPane.setStyle("-fx-background-color: LIGHTGRAY;");
				thirdLine.setTextFill(Color.PURPLE);
				middle.setTextFill(Color.PURPLE);
			}
		});

	}

	public boolean inList(String word) { // to check if the names are exist before or not
		for (int i = 0; i < martyrs.size(); i++) {
			if (martyrs.get(i).getName().equals(word)) {
				return true;
			}
		}
		return false;
	}

	public void readFromFile() {
		try {
			File file = new File("MartyrsList.dat");
			FileInputStream input = new FileInputStream(file);
			DataInputStream readInput = new DataInputStream(input);
			while (input.available() > 0) {
				String[] data = readInput.readUTF().split(" ");
				String name = data[0];
				String date = data[1];
				readInput.readChar();
				Martyr martyr = new Martyr(date, name);
				martyrs.add(martyr);
			}
			readInput.close();
		} catch (IOException ex) {
			System.out.println("There is Something wrong");
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

}
