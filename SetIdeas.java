import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.FileChooser;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SetIdeas extends Application {

    @Override
    public void start(Stage primaryStage) {
        Button generateButton = new Button("Generate ECIdeas.txt");
        generateButton.setOnAction(e -> generateFile());

        StackPane root = new StackPane();
        root.getChildren().add(generateButton);

        Scene scene = new Scene(root, 300, 250);

        primaryStage.setTitle("Startup Problem Translator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void generateFile() {
        List<String> problems = getPotentialProblems();
        List<String> responses = getResponses();

        // Ensure both lists have the same size
        if (problems.size() != responses.size()) {
            System.out.println("Error: Lists have different sizes.");
            return;
        }

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save ECIdeas.txt");
        File file = fileChooser.showSaveDialog(null);

        if (file != null) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                for (int i = 0; i < problems.size(); i++) {
                    writer.write("Problem: " + problems.get(i) + "\n");
                    writer.write("Response: " + responses.get(i) + "\n\n");
                }
                System.out.println("ECIdeas.txt generated successfully.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private List<String> getPotentialProblems() {
        // Implement this method to get potential startup problems from your GUI
        // For example:
        List<String> problems = new ArrayList<>();
        problems.add("Problem 1");
        problems.add("Problem 2");
        problems.add("Problem 3");
        return problems;
    }

    private List<String> getResponses() {
        // Implement this method to get responses to the problems from your GUI
        // For example:
        List<String> responses = new ArrayList<>();
        responses.add("Response 1");
        responses.add("Response 2");
        responses.add("Response 3");
        return responses;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
