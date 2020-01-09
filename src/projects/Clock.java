package projects;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
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
		this.month = calendar.get(Calendar.MONTH) + 1;
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
		renderClockArms(pane);
	}

	/*
	 * offset = pane.getW() / 2; x = offset + Math.cos(Math.toRadians(rotation)) *
	 * radiusOffset; y = offset + Math.sin(Math.toRadians(rotation)) * radiusOffset;
	 */
	private void renderHours(ClockPane pane) {

		//starts rotation at 60`
		double rotation = 300;
		// makes digit's circle fit within the outer circle
		double radiusOffset = radius - radius * 0.14;
		DoubleBinding offsetx = pane.widthProperty().divide(2);
		DoubleBinding offsety = pane.heightProperty().divide(2);

		// Loop through 12 hour digits
		for (int i = 1; i < 13; i++) {
			Text text = new Text(i + "");
			DoubleBinding x = offsetx.add(Math.cos(Math.toRadians(rotation)) * radiusOffset).subtract(radius * 0.076);
			DoubleBinding y = offsety.add(Math.sin(Math.toRadians(rotation)) * radiusOffset).add(radius * 0.066);
			text.xProperty().bind(x);
			text.yProperty().bind(y);
			text.setFont(Font.font("Actor", FontWeight.BOLD, pane.getW() * 0.08));
			text.setFill(Color.WHITE);
			pane.getChildren().add(text);
			rotation += 30;
		}
	}

	private void renderClockCircle(ClockPane pane) {
		radius = Math.min(pane.getW(), pane.getH()) * 0.4;
		double centerX = pane.getW() / 2;
		double centerY = pane.getH() / 2;
		Circle circle = new Circle(centerX, centerY, radius);

		circle.centerXProperty().bind(pane.widthProperty().divide(2));
		circle.centerYProperty().bind(pane.heightProperty().divide(2));
		circle.setFill(Color.rgb(182, 223, 228, 0.99));
		circle.setStroke(Color.rgb(228, 221, 182, 0.99));
		circle.setStrokeWidth(10);
		pane.getChildren().add(circle);
	}
	
	private void renderClockArms(ClockPane pane) {
		//355 at 3pm; += 30 per hour
		int rotation = 355 + (this.hour * 30);
		DoubleBinding x = pane.widthProperty().divide(2);
		DoubleBinding y = pane.heightProperty().divide(2);
		DoubleBinding endX = x.add(Math.cos(Math.toRadians(rotation)) * (radius - radius * 0.14)).subtract(radius * 0.076);
		DoubleBinding endY = y.add(Math.sin(Math.toRadians(rotation)) * (radius - radius * 0.14)).add(radius * 0.066);
		Line hourHand = new Line(0,0,0,0);
		hourHand.startXProperty().bind(x);
		hourHand.startYProperty().bind(y);
		hourHand.endXProperty().bind(endX);
		hourHand.endYProperty().bind(endY);
		hourHand.setStrokeWidth(5);
		hourHand.setStroke(Color.GREEN);
		pane.getChildren().add(hourHand);
	}

}
