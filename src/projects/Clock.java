package projects;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class Clock {
	private int year;
	private int month;
	private int day;
	private int hour;
	private int minute;
	private int second;
	private double radius;


	public Clock() {
		Calendar calendar = new GregorianCalendar();
		this.year = calendar.get(Calendar.YEAR);
		this.month = calendar.get(Calendar.MONTH);
		this.day = calendar.get(Calendar.DAY_OF_MONTH);
	}

	private int getDay() {
		return this.day;
	}

	private int getMonth() {
		return this.year;
	}

	private int getYear() {
		return this.year;
	}

	public String getTimeString() {
		return "Current Date: " + this.month + "-" + this.day + "-" + this.year;
	}
	
	public void renderClock(ClockPane pane) {
		renderClockCircle(pane);
		renderHours(pane);
	}
	
	private void renderHours(ClockPane pane) {
		
		double rotation = 300;
		double offset = pane.getW() / 2;
		//-10 to make digits circle fit within clock circle
		double radiusOffset = radius - 10;
		// Loop through 12 hour digits
		for (int i = 1; i < 13; i++) {
			double x = offset + Math.cos(Math.toRadians(rotation)) * radiusOffset;
			double y = offset + Math.sin(Math.toRadians(rotation)) * radiusOffset;
			Text text = new Text(x, y, i + "");
			text.xProperty().bind((pane.widthProperty().divide(2)).add(Math.cos(Math.toRadians(rotation)) * radiusOffset).subtract(6));
			text.yProperty().bind((pane.heightProperty().divide(2)).add(Math.sin(Math.toRadians(rotation)) * radiusOffset).add(5));
			pane.getChildren().add(text);
			rotation += 30;
		}
	}
	
	public void renderClockCircle(ClockPane pane) {
		
		radius = Math.min(pane.getW(), pane.getH()) * 0.8 * 0.5;
		double centerX = pane.getW() / 2;
		double centerY = pane.getH() / 2;
		Circle circle = new Circle(centerX, centerY, radius);

		circle.centerXProperty().bind(pane.widthProperty().divide(2));
		circle.centerYProperty().bind(pane.heightProperty().divide(2));
		circle.setFill(Color.WHITE);
		circle.setStroke(Color.AQUAMARINE);
		pane.getChildren().add(circle);
	}
	
	
	
	
	
}
