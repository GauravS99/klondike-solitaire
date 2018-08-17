import java.awt.*;  
import hsa.Console;

abstract class Suit extends Shape{

	public Suit(){
		setHeight(75);
	}

	public void setHeight(int height){
		super.setHeight(height);
		super.setWidth((int)Math.round(height * 0.8));
	}

	public void setWidth(int width){
		super.setHeight((int)Math.round(width * 1.2));
		super.setWidth(width);
	}

	abstract void draw(Graphics g);

	abstract void draw(Console c);

}
