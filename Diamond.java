import java.awt.*;  
import hsa.Console;

public class Diamond extends Suit{

	public Diamond(){
		super.setColor(Color.RED);
	}

	public void setColor(Color color){


	}

	public void draw(Graphics g){

		int[] x = new int[4];
		int[] y = new int[4];

		x [0] = getX() - (int)(getWidth() / 2); 
		y [0] = getY();
		x [1] = getX();
		y [1] = getY() + (int)(getHeight() / 2);
		x [2] = getX() + (int)(getWidth() / 2);
		y [2] = getY();
		x [3] = getX();
		y [3] = getY() - (int)(getHeight() / 2);

		g.setColor(getColor());
		g.fillPolygon(x,y,4);

	}


	public void draw(Console c){

		int[] x = new int[4];
		int[] y = new int[4];

		x[0] = getX() - (int)(getWidth() / 2); 
		y [0] = getY();
		x [1] = getX();
		y [1] = getY() + (int)(getHeight() / 2);
		x [2] = getX() + (int)(getWidth() / 2);
		y [2] = getY();
		x [3] = getX();
		y [3] = getY() - (int)(getHeight() / 2);

		c.setColor(getColor());
		c.fillPolygon(x,y,4);

	}



}
