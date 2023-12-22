import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
//import javafx.scene.layout.StackPane;
//I worked on the homework assignment alone, using only course materials provided.
/**
 * Represents a problem space to base a startup around
 * @author Vania M
 * @version 1.0
 */
public class StarterUpper2 extends Application {
    @Override
    public void start(Stage mainStage) throws FileNotFoundException, Exception {
        final ArrayList<StartUpIdea> startupList = new ArrayList<StartUpIdea>();
        final BorderPane borderPane = new BorderPane();
        final File newFile = new File("./newFileName.txt");

        FileInputStream input = new FileInputStream("src/businesshw10.png");
        Image img = new Image(input);
        ImageView imgview = new ImageView(img);
        imgview.setFitWidth(125);
        imgview.setFitHeight(125);

        Label label8 = new Label("Vania Munjar's Starter Upper");
        GridPane.setConstraints(label8, 0, 0);


        Label label1 = new Label("What is the problem?");
        GridPane.setConstraints(label1, 0, 2);
        final TextField textField1 = new TextField();
        GridPane.setConstraints(textField1, 1, 2);

        Label label2 = new Label("Who is the target customer?");
        GridPane.setConstraints(label2, 0, 3);
        final TextField textField2 = new TextField();
        GridPane.setConstraints(textField2, 1, 3);

        Label label3 = new Label("How badly does the customer NEED this problem fixed (1-10)?");
        GridPane.setConstraints(label3, 0, 4);
        final TextField textField3 = new TextField();
        GridPane.setConstraints(textField3, 1, 4);

        Label label4 = new Label("How many people do you know who might experience this problem?");
        GridPane.setConstraints(label4, 0, 5);
        final TextField textField4 = new TextField();
        GridPane.setConstraints(textField4, 1, 5);

        Label label5 = new Label("How big is the target market?");
        GridPane.setConstraints(label5, 0, 6);
        final TextField textField5 = new TextField();
        GridPane.setConstraints(textField5, 1, 6);

        Label label6 = new Label("Who are the competitors/existing solutions?");
        GridPane.setConstraints(label6, 0, 7);
        final TextField textField6 = new TextField();
        GridPane.setConstraints(textField6, 1, 7);

        Label label7 = new Label("What are some marketing tactics you have in mind?");
        GridPane.setConstraints(label7, 0, 8);
        final TextField textField7 = new TextField();
        GridPane.setConstraints(textField7, 1, 8);


        GridPane root = new GridPane();
        Button btn1 = new Button("Add Idea");
        root.setVgap(30.0);
        root.setHgap(30.0);
        root.setAlignment(Pos.CENTER);
        root.setMinSize(800.0, 400.0);
        root.getChildren().addAll(new Node[]{label8, label1, textField1,
                label2, textField2, label3, textField3, label4, textField4, label5, textField5,
                label6, textField6, label7, textField7});
        root.setPadding(new Insets(10.0, 10.0, 10.0, 10.0));
        EventHandler<ActionEvent> handler = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                String theProblem = textField1.getText();
                String targetCustomer = textField2.getText();
                String customerBadly = textField3.getText();
                String peopleExperience = textField4.getText();
                String targetMarketSize = textField5.getText();
                String competitors = textField6.getText();
                String marketIdeas = textField7.getText();

                try {
                    if (!theProblem.equals("") && !targetCustomer.equals("")
                            && !customerBadly.equals("") && !peopleExperience.equals("")
                            && !targetMarketSize.equals("")
                            && !competitors.equals("") && !marketIdeas.equals("")) {
                        int b = Integer.parseInt(customerBadly);
                        int c = Integer.parseInt(peopleExperience);
                        int d = Integer.parseInt(targetMarketSize);
                        if (b <= 10 && b >= 1 && c >= 0 && d >= 0) {
                            return;
                        } else {
                            Alert anAlert = new Alert(AlertType.ERROR);
                            anAlert.setTitle("ERROR");
                            anAlert.setContentText("The integer you provided as a response to one of the questions "
                                    + "is out of bounds. Please try again.");
                            anAlert.show();
                        }
                    } else {
                        Alert a = new Alert(AlertType.ERROR);
                        a.setTitle("ERROR");
                        a.setContentText("One of your text fields is empty. Please try again.");
                        a.show();
                    }
                } catch (NumberFormatException var14) {
                    Alert e = new Alert(AlertType.ERROR);
                    e.setTitle("ERROR");
                    e.setContentText("What you have inputed is not a valid integer for a question.Please try again.");
                    e.show();
                }

            }
        };
        btn1.setOnAction(handler);
        Button ideaSorter = new Button("Sort Ideas");
        GridPane.setConstraints(ideaSorter, 0, 7);
        ideaSorter.setOnAction((event) -> {
            Collections.sort(startupList);
        });


        Button deleter = new Button("Reset");
        EventHandler<ActionEvent> handler2 = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                Alert warning = new Alert(AlertType.WARNING, "This "
                        + "button resets the form. Are you sure you want to proceed?",
                        new ButtonType[]{ButtonType.OK, ButtonType.CANCEL});
                warning.setTitle("WARNING");
                warning.setContentText("This button resets the form. Are you sure you want to proceed?");
                Optional<ButtonType> display = warning.showAndWait();
                if (display.get() == ButtonType.OK && newFile.exists()) {
                    newFile.delete();
                    startupList.clear();
                    textField1.setText("");
                    textField2.setText("");
                    textField3.setText("");
                    textField4.setText("");
                    textField5.setText("");
                    textField6.setText("");
                    textField7.setText("");
                }

            }
        };
        deleter.setOnAction(handler2);
        Button buttonSave = new Button("Save your ideas");
        buttonSave.setOnAction((event) -> {
            FileUtil.saveIdeasToFile(startupList, newFile);
        });


        Button delete = new Button("Delete a Previous Startup Idea");
        EventHandler<ActionEvent> handler3 = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                TextInputDialog textDialog = new TextInputDialog();
                textDialog.setHeaderText("What's the name of the startup idea that you want to delete?");
                Optional<String> aResult = textDialog.showAndWait();
                TextField input = textDialog.getEditor();
                String theText = input.getText();
                if (theText.equals("")) {
                    System.out.println("Please enter a valid name. You currently have not entered anything.");
                } else {
                    int count = 0;

                    for (int i = 0; i < startupList.size(); ++i) {
                        if (((StartUpIdea) startupList.get(i)).equals(theText)) {
                            startupList.remove(i);
                            ++count;
                        }
                    }

                    if (count == 0) {
                        System.out.println("The name of the startup idea cannot be found. Please try again.");
                    } else {
                        System.out.println("Deleted successfully!");
                    }
                }

                textDialog.show();
            }
        };
        delete.setOnAction(handler3);
        final ObservableList<String> ideaString = FXCollections.observableArrayList();
        Button display = new Button("Display my Ideas");
        EventHandler<ActionEvent> handler5 = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                ListView<String> listView = new ListView<>(ideaString);
                ideaString.clear();

                for (int i = 0; i < startupList.size(); ++i) {
                    ideaString.add(((StartUpIdea) startupList.get(i)).toString());
                }

                listView.setItems(ideaString);
                listView.setStyle("-fx-control-inner-background:pink;");
                borderPane.setRight(listView);
            }
        };
        display.setOnAction(handler5);

        HBox box = new HBox(10.0);
        box.getChildren().addAll(new Node[]{btn1, ideaSorter, deleter, buttonSave, delete, display});
        box.setAlignment(Pos.CENTER);
        VBox box2 = new VBox(60.0);
        box2.setAlignment(Pos.CENTER);
        box2.getChildren().addAll(new Node[]{root, box});
        borderPane.setCenter(box2);

        try {
            String song = "/Users/vaniamunjar/CS 1331/HW 103/recording.mp3";
            Media pathway = new Media((new File(song)).toURI().toString());
            MediaPlayer playsBackgroundMusic = new MediaPlayer(pathway);
            playsBackgroundMusic.play();
        } catch (Exception var43) {
            System.out.println(var43.getMessage());
        }

        borderPane.setStyle("-fx-background-color:pink");
        Scene scene = new Scene(borderPane, 800.0, 800.0);
        mainStage.setTitle("Problem Ideation Form");
        mainStage.setScene(scene);
        mainStage.show();
    }
}

