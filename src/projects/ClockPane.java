package projects;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.scene.shape.Line;

class ClockPane extends Pane {

	private Clock clock;
	private double w, h;

	//class
	
	public ClockPane(Clock clock, double w, double h) {
		this.w = w;
		this.h = h;
		this.clock = clock;
		this.clock.renderClock(this);

	}

	public double getW() {
		return w;
	}

	public void setW(double w) {
		this.w = w;
	}

	public double getH() {
		return h;
	}

	public void setH(double h) {
		this.h = h;
	}



}