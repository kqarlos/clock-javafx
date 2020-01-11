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

public class DisplayClock extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		Clock clock = new Clock();
		String timeString = clock.getTimeString();
		Label lblCurrentTime = new Label(timeString);
		Label title = new Label("Current Date and Time");

		BorderPane pane = new BorderPane();
		pane.setTop(title);
		pane.setCenter(new ClockPane(clock, 450, 450));
		pane.setBottom(lblCurrentTime);

		BorderPane.setAlignment(lblCurrentTime, Pos.TOP_CENTER);
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
