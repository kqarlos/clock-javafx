package projects;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class DisplayClock extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		Clock clock = new Clock();
		String timeString = clock.getTimeString();
		Label currentTime = new Label(timeString);
		currentTime.setStyle("-fx-font-size:20px; -fx-font-weight: bold; -fx-text-fill:rgb(104, 137, 128, 0.99); -fx-padding: 10 0 0 0;");
		Label title = new Label("Time Now");
		title.setStyle("-fx-font-size:25px; -fx-font-weight: bold; -fx-text-fill:rgb(104, 137, 128, 0.99); -fx-padding: 10 0 0 0;");


		BorderPane pane = new BorderPane();
		pane.setStyle("-fx-background-color: white;");
		pane.setTop(title);
		pane.setCenter(new ClockPane(clock, 450, 450));
		pane.setBottom(currentTime);

		BorderPane.setAlignment(currentTime, Pos.TOP_CENTER);
		BorderPane.setAlignment(title, Pos.TOP_CENTER);

		Scene scene = new Scene(pane, 550, 550);
		primaryStage.setTitle("Clock");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}

}
