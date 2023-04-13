package application;

import application.SceneChanger.Defect_LOG;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SceneChanger extends Application {
	
    public static void main(String[] args) {
        launch(args);
    }
    
    private Scene Defect_Log, Effort_Defect_Logs;
    private TableView<Defect_LOG> Selected_Table = new TableView<Defect_LOG>();
    private TableView<Defect_LOG> Effort_Table = new TableView<Defect_LOG>();
    private TableView<Defect_LOG> Defect_Table = new TableView<Defect_LOG>();
    private int Defect_Number = 1;
    private int Effort_Number = 1;
    
    @Override
    public void start(Stage primaryStage) {
    	
        /**
         * so the effort/defect logs is going to be a tableview (pretty much an excel file) I want to be able to change
         * so that the user can select if they want to view like effort only / defect only or both 
         * 
         * implement a combobox that displays the different tables (effrot/defect) -done for the time being
         * BIG TASK
         * implement a combobox that selected a certain project A-J (project A Effort table) 
         * 	will need to implement some sort of title rename (maybe manager has access to this)
         * 	will need to figure out how exactly how the combobox will work if project b gets deleted in a list of (a b c)
         * 		will the combobox go up one?, will that slot be blank?
         * 		how will this get implemented into selecting the correct project effort/defect table?
         * 			are the table already premade? will they be made when a new project is created or?
         * 
         * finish defect display and logging for individual assignment 5
         *
         * 
         * 
         */
    	
        /*
         * DEFECT CONSOLE
         */
        Button Clear_Defect_Log = new Button ("Clear this Defect Log");
        Button LogDefect = new Button("Log Defect");
        Button Closed = new Button ("Close");
        Button Reopen = new Button ("Reopen");
        Button Update = new Button ("Update the Current Defect");
        Button Delete = new Button ("Delete the Current Defect");
        Button Effort = new Button ("Proceed to the Effort Log Console");
        Button button1 = new Button("Effort and Defect Logs");
        
        TextField Defect_Name = new TextField();
        TextArea Description = new TextArea();

        ComboBox<String> Project = new ComboBox<>();
        
        ListView<String> Injected =  new ListView<>();
        
        Injected.getItems().addAll("Planning", "Information Gathering", "Information Understanding", "Verifying"
        		, "Outlining", "Drafting", "Finalizing", "Team Meeting", "Coach Meeting", "Stakeholder Meeting");
        
        
        ListView<String> Removed = new ListView<>();
        //SAME AS Injected
        Removed.getItems().addAll(Injected.getItems());
        
        ListView<String> Defect_Catagory = new ListView<>();
        
        Defect_Catagory.getItems().addAll("Not Specified", "10-Documention", "20-Syntax", "30-Build, Package", 
        		"40-Assignment", "50-Interface", "60-Checking", "70-Data", "80-Function", "90-System" , "100-Enviroment");
        
        ComboBox<String> Status = new ComboBox<>();
        Status.getItems().addAll("Open", "Closed");
        
        Label defect_title = new Label ("DEFECT CONSOLE");
        Label select_project = new Label ("Select the Project");
        Label defect_name = new Label ("Title Name: ");
        Label inject_step = new Label ("Step when Injected");
        Label removed_step = new Label ("Step when Removed");
        Label defect_catagory = new Label ("Defect Catagory");
        Label Description_Label = new Label("Description: ");
        Label Statuss = new Label("Status");
        
        
        /*
         * LOGS
         */
        
        //creating and adding the columns to the table
        TableColumn<Defect_LOG, Integer> column1 = new TableColumn<>("Number");
        column1.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().get_defect_number()).asObject());
        TableColumn<Defect_LOG, String> column2 = new TableColumn<>("Name");
        column2.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get_defect_namer()));
        TableColumn<Defect_LOG, String> column3 = new TableColumn<>("Description");
        column3.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get_defect_description()));
        TableColumn<Defect_LOG, String> column4 = new TableColumn<>("Injected");
        column4.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get_defect_injected()));
        TableColumn<Defect_LOG, String> column5 = new TableColumn<>("Removed");
        column5.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get_defect_removed()));
        TableColumn<Defect_LOG, String> column6 = new TableColumn<>("Catagory");
        column6.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get_defect_catagory()));
        TableColumn<Defect_LOG, String> column7 = new TableColumn<>("Status");
        column7.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get_defect_status()));
        TableColumn<Defect_LOG, String> column8 = new TableColumn<>("Fix");
        column8.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get_defect_fix()));
        
        Defect_Table.getColumns().add(column1);
        Defect_Table.getColumns().add(column2);
        Defect_Table.getColumns().add(column3);
        Defect_Table.getColumns().add(column4);
        Defect_Table.getColumns().add(column5);
        Defect_Table.getColumns().add(column6);
        Defect_Table.getColumns().add(column7);
        Defect_Table.getColumns().add(column8);
        
        ComboBox<String> Table_Type = new ComboBox<>();
        Table_Type.getItems().addAll("Effort", "Defect");
        
        HBox layout1 = new HBox(20, button1);
        HBox screen_title = new HBox (defect_title);
        HBox screen_name = new HBox (defect_name, Defect_Name);
        HBox screen_description = new HBox (Description_Label, Description);
        HBox screen_injected = new HBox (150,inject_step, removed_step);
        HBox screen_removed = new HBox (Injected, Removed, LogDefect);
        HBox screen_catagory = new HBox (160,defect_catagory, Statuss);
        HBox screen_status = new HBox (Defect_Catagory, Status);
        
        //this is what causes the button to change scenes
        button1.setOnAction(e -> primaryStage.setScene(Effort_Defect_Logs));
        
        LogDefect.setOnAction(e -> { 
        	Defect_Table.getItems().add(new Defect_LOG(Defect_Number, Defect_Name.getText(),Description.getText(),
        			Injected.getSelectionModel().getSelectedItem(),Removed.getSelectionModel().getSelectedItem(),
        			Defect_Catagory.getSelectionModel().getSelectedItem(),Status.getSelectionModel().getSelectedItem(),
        			"TO-DO"));
        	Defect_Number++;
        	
        }); 
        
        // ADD THIS ONCE TRANSER OF DATA WORKS 
        Table_Type.setOnAction(event -> {
        	String Selected_Type = Table_Type.getValue();
            if ("Effort".equals(Selected_Type)) {
            	setColumnsForEffort();
                Selected_Table.setItems(Effort_Table.getItems());
            } else if ("Defect".equals(Selected_Type)) {
            	setColumnsForDefect();
            	Selected_Table.setItems(Effort_Table.getItems());
            }
        });
        
        VBox root = new VBox(10, layout1, screen_title, screen_name, screen_description, screen_injected,
        		screen_removed,screen_catagory, screen_status);
        Defect_Log = new Scene(root, 400, 400);

        // Create scene 2 with a button
        Button button2 = new Button("Defect Console");
        //this is what causes the button to change scenes
        button2.setOnAction(e -> primaryStage.setScene(Defect_Log));
        //layout for button in the middle
        StackPane layout2 = new StackPane();
        layout2.getChildren().addAll(button2);
        VBox root2 = new VBox(10, layout2, Defect_Table, Effort_Table);
        //making the scene
        Effort_Defect_Logs = new Scene(root2, 400, 400);
        
        
        // Set the primary stage with scene 1
        primaryStage.setScene(Defect_Log);
        
        primaryStage.setTitle("Defect Logger Console");
        primaryStage.show();

    }
    
    //FINISH THIS TRANSFERING DATA IS NOT WORKING RIP
    public void setColumnsForEffort() {
        Selected_Table.getColumns().clear(); // Clear existing columns
        Selected_Table.getItems().clear();// Clear existing data stored
        Selected_Table.getColumns().addAll(Effort_Table.getColumns());
        //ObservableList<> selectedData = sourceTableView.getSelectionModel().getSelectedItems();
    }
    
    //FINISH THIS TRANSFERING DATA IS NOT WORKING RIP
    public void setColumnsForDefect() {
        Selected_Table.getColumns().clear(); // Clear existing columns
        Selected_Table.getItems().clear(); // Clear existing data stored
        Selected_Table.getColumns().addAll(Defect_Table.getColumns());
        ObservableList<Defect_LOG> selectedData = Defect_Table.getSelectionModel().getSelectedItems();
        Selected_Table.setItems(selectedData);
    }
    
    public class Defect_LOG{
    	private int defect_number;
    	private String defect_namer;
    	private String defect_description;
    	private String defect_injected;
    	private String defect_removed;
    	private String defect_catagory;
    	private String defect_status;
    	private String defect_fix;
    	
    	//Default Constructor
    	public Defect_LOG() {
    		
    	}
    	//Constructor with params
    	public Defect_LOG(int defect_number, String defect_namer, String defect_description, String defect_injected,
    			String defect_removed, String defect_catagory, String defect_status, String defect_fix) {
    		this.defect_number = defect_number;
    		this.defect_namer = defect_namer;
    		this.defect_description = defect_description;
    		this.defect_injected = defect_injected;
    		this.defect_removed = defect_removed;
    		this.defect_catagory = defect_catagory;
    		this.defect_status = defect_status;
    		this.defect_fix = defect_fix;
    	}
    	//getters
    	public int get_defect_number() {
			return defect_number;
    		
    	}
    	
    	public String get_defect_namer() {
			return defect_namer;
    		
    	}
    	
    	public String get_defect_description() {
			return defect_description;
    		
    	}
    	
    	public String get_defect_injected() {
			return defect_injected;
    		
    	}
    	
    	public String get_defect_removed() {
			return defect_removed;
    		
    	}
    	
    	public String get_defect_catagory() {
			return defect_catagory;
    		
    	}
    	
    	public String get_defect_status() {
			return defect_status;
    		
    	}
    	
    	public String get_defect_fix() {
			return defect_fix;
    		
    	}
    	//setters
    	public void set_defect_number(int defect_number) {
    		this.defect_number = defect_number;
    	}
    	
    	public void set_defect_namer(String defect_namer) {
    		this.defect_namer = defect_namer;
    	}
    	
    	public void set_defect_descrption (String defect_description) {
    		this.defect_description = defect_description;
    	}
    	
    	public void set_defect_injected (String defect_injected) {
    		this.defect_injected = defect_injected;
    	}
    	
    	public void set_defect_removed (String defect_removed) {
    		this.defect_removed = defect_removed;
    	}
    	
    	public void set_defect_catagory (String defect_Catagory) {
    		this.defect_catagory = defect_Catagory;
    	}
    	
    	public void set_defect_status (String defect_status) {
    		this.defect_status = defect_status;
    	}
    	
    	public void set_defect_fix (String defect_fix) {
    		this.defect_fix = defect_fix;
    	}
    }
}