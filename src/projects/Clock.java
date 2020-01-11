package projects;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/*
 * Clock with current date and time
 * Clock renders into a pane and updates its properties every second
 */
public class Clock {
	private int year;
	private int month;
	private int day;
	private int hour;
	private int minute;
	private int second;
	private double radius;

	/*
	 * Creates a clock object with current time attributes
	 */
	public Clock() {
		Calendar calendar = new GregorianCalendar();
		this.year = calendar.get(Calendar.YEAR);
		this.month = calendar.get(Calendar.MONTH) + 1;
		this.day = calendar.get(Calendar.DAY_OF_MONTH);
		this.hour = calendar.get(Calendar.HOUR);
		this.minute = calendar.get(Calendar.MINUTE);
		this.second = calendar.get(Calendar.SECOND);
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

	/*
	 * @return String with current date
	 */
	public String getTimeString() {
		return "Current Date: " + this.month + "-" + this.day + "-" + this.year;
	}

	/*
	 * Calls to update and render clock every second
	 */
	public void renderClock(ClockPane pane) {
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			public void run() {
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						update();
						renderClockCircle(pane);
						renderHours(pane);
						renderClockArms(pane);
					}
				});
			}
		};
		timer.scheduleAtFixedRate(task, 0, 1000l);
	}

	/*
	 * Updates clock properties
	 */
	public void update() {
		Calendar calendar = new GregorianCalendar();
		this.year = calendar.get(Calendar.YEAR);
		this.month = calendar.get(Calendar.MONTH) + 1;
		this.day = calendar.get(Calendar.DAY_OF_MONTH);
		this.hour = calendar.get(Calendar.HOUR);
		this.minute = calendar.get(Calendar.MINUTE);
		this.second = calendar.get(Calendar.SECOND);
	}

	/*
	 * offset = pane.getW() / 2; x = offset + Math.cos(Math.toRadians(rotation)) *
	 * radiusOffset; y = offset + Math.sin(Math.toRadians(rotation)) * radiusOffset;
	 * Renders digits on clock pane
	 * @param ClockPane
	 */
	private void renderHours(ClockPane pane) {

		// starts rotation at 30 degrees + 30 degrees per hour
		double rotation = 300;
		// makes digit's circle fit within the outer circle
		double radiusOffset = radius - radius * 0.15;
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

	/*
	 * Renders clock circle
	 * @param ClockPane
	 */
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

	/*
	 * Renders clock arms
	 * @param ClockPane
	 */
	private void renderClockArms(ClockPane pane) {
		// 295 at 1pm; += 30 per hour
		int hourRotation = 300 + ((this.hour - 1) * 30);
		double hourRadiusOffset = radius - radius * 0.50;
		DoubleBinding x = pane.widthProperty().divide(2);
		DoubleBinding y = pane.heightProperty().divide(2);
		DoubleBinding endX = x.add(Math.cos(Math.toRadians(hourRotation)) * hourRadiusOffset);
		DoubleBinding endY = y.add(Math.sin(Math.toRadians(hourRotation)) * hourRadiusOffset);
		// create hour hand
		Line hourHand = new Line(0, 0, 0, 0);
		hourHand.startXProperty().bind(x);
		hourHand.startYProperty().bind(y);
		hourHand.endXProperty().bind(endX);
		hourHand.endYProperty().bind(endY);
		hourHand.setStrokeWidth(5);
		hourHand.setStrokeLineCap(StrokeLineCap.ROUND);
		hourHand.setStroke(Color.rgb(104, 137, 128, 0.99));
		pane.getChildren().add(hourHand);

		// 265 at 0 mins += 6 per min
		int minRotation = 300 + ((this.minute - 5) * 6);
		double minRadiusOffset = radius - radius * 0.40;
		DoubleBinding minEndX = x.add(Math.cos(Math.toRadians(minRotation)) * minRadiusOffset);
		DoubleBinding minEndY = y.add(Math.sin(Math.toRadians(minRotation)) * minRadiusOffset);
		// create min hand
		Line minHand = new Line(0, 0, 0, 0);
		minHand.startXProperty().bind(x);
		minHand.startYProperty().bind(y);
		minHand.endXProperty().bind(minEndX);
		minHand.endYProperty().bind(minEndY);
		minHand.setStrokeWidth(3);
		hourHand.setStrokeLineCap(StrokeLineCap.ROUND);
		minHand.setStroke(Color.rgb(104, 137, 128, 0.99));
		pane.getChildren().add(minHand);

		// 265 at 0 secs += 6 per sec
		int secRotation = 300 + ((this.second - 5) * 6);
		double secRadiusOffset = radius - radius * 0.30;
		DoubleBinding secEndX = x.add(Math.cos(Math.toRadians(secRotation)) * secRadiusOffset);
		DoubleBinding secEndY = y.add(Math.sin(Math.toRadians(secRotation)) * secRadiusOffset);
		// create sec hand
		Line secHand = new Line(0, 0, 0, 0);
		secHand.startXProperty().bind(x);
		secHand.startYProperty().bind(y);
		secHand.endXProperty().bind(secEndX);
		secHand.endYProperty().bind(secEndY);
		secHand.setStrokeWidth(1);
		secHand.setStrokeLineCap(StrokeLineCap.ROUND);
		secHand.setStroke(Color.rgb(104, 137, 128, 0.99));
		pane.getChildren().add(secHand);

		Line dot = new Line(0, 0, 0, 0);
		dot.startXProperty().bind(x);
		dot.startYProperty().bind(y);
		dot.endXProperty().bind(x);
		dot.endYProperty().bind(y);
		dot.setStrokeWidth(25);
		dot.setStrokeLineCap(StrokeLineCap.ROUND);
		dot.setStroke(Color.rgb(104, 137, 128, 0.99));
		pane.getChildren().add(dot);
	}

}
