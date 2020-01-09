package projects;

import java.util.Date;

import javafx.application.Application;
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
		
//		ClockPane clock = new ClockPane();
//		String timeString = clock.getHour() + ":" + clock.getMin() + ":" + clock.getSecond();
//		Label lblCurrentTime = new Label(timeString);
//		
//		BorderPane pane = new BorderPane();
//		pane.setCenter(clock);
//		pane.setBottom(lblCurrentTime);
//		BorderPane.setAlignment(lblCurrentTime, Pos.TOP_CENTER);
//		
//		Scene scene = new Scene(pane, 250, 250);
//		primaryStage.setTitle("Clock");
//		primaryStage.setScene(scene);
//		primaryStage.show();
		
		
		//My implementation
		Clock clock = new Clock();
		String timeString = clock.getTimeString();
		Label lblCurrentTime = new Label(timeString);
		Label title = new Label("Current Date and Time");

		
		BorderPane pane = new BorderPane();
		pane.setTop(title);
		pane.setCenter(new ClockPane(clock, 250, 250));
		pane.setBottom(lblCurrentTime);
		
		BorderPane.setAlignment(lblCurrentTime, Pos.TOP_CENTER);
		BorderPane.setAlignment(title, Pos.TOP_CENTER);

		
		Scene scene = new Scene(pane, 250, 250);
		primaryStage.setTitle("Clock");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}

}
