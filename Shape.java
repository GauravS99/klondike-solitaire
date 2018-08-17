import java.awt.*;      // Java's Abstract Windowing Toolkit package - includes class Color
import hsa.Console; 

abstract class Shape
{
	private int iHeight = 50;
	private int iWidth = 50;
	private int  iCenterX = 50;
	private int iCenterY = 50;
	private Color iColor = Color.BLUE;
	
	public int getHeight() {
		return iHeight;
	}
	public void setHeight(int height) {
		iHeight = height;
	}
	public int getWidth() {
		return iWidth;
	}
	public void setWidth(int width) {
		iWidth = width;
	}
	public int getX() {
		return iCenterX;
	}
	public int getY() {
		return iCenterY;
	}

	public void setCenter(int x, int y){
		iCenterX = x;
		iCenterY = y;
	}
	public Color getColor() {
		return iColor;
	}
	public void setColor(Color color) {
		iColor = color;
	}       

	abstract void draw(Graphics g);

	public void erase(Graphics g){
		Color old = iColor;
		iColor = Color.WHITE;
		draw(g);
		iColor = old;
	}
	
	
}