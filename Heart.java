import java.awt.*;  
import hsa.Console;

public class Heart extends Suit{

	public Heart(){
		super();
		super.setColor(Color.RED);
	}

	public void setColor(Color color){


	}

	public void draw(Console c){

		int iPointsX[] = new int [5];
		int iPointsY[] = new int [5];

		int iCentreX = getX();
		int iCentreY = getY();
		int iWidth = getWidth();
		int iHeight = getHeight(); 

		iPointsX [0] = iCentreX - iWidth / 2;
		iPointsY [0] = iCentreY;
		iPointsX [1] = iCentreX + iWidth / 2;
		iPointsY [1] = iCentreY;
		iPointsX [2] = iCentreX;
		iPointsY [2] = iCentreY + iHeight / 2;
		iPointsX [3] = iCentreX - iWidth / 2;
		iPointsY [3] = iCentreY - iHeight / 4;
		iPointsX [4] = iCentreX;
		iPointsY [4] = iCentreY - iHeight / 4;

		c.setColor (getColor());

		c.fillArc (iPointsX [3], iPointsY [3], iWidth / 2, iHeight / 2, 0, 180);
		c.fillArc (iPointsX [4], iPointsY [4], iWidth / 2, iHeight / 2, 0, 180);
		c.fillPolygon (iPointsX, iPointsY, 3);

	}

	public void draw(Graphics g){

		int iPointsX[] = new int [5];
		int iPointsY[] = new int [5];

		int iCentreX = getX();
		int iCentreY = getY();
		int iWidth = getWidth();
		int iHeight = getHeight(); 


		iPointsX [0] = iCentreX - iWidth / 2;
		iPointsY [0] = iCentreY;
		iPointsX [1] = iCentreX + iWidth / 2;
		iPointsY [1] = iCentreY;
		iPointsX [2] = iCentreX;
		iPointsY [2] = iCentreY + iHeight / 2;
		iPointsX [3] = iCentreX - iWidth / 2;
		iPointsY [3] = iCentreY - iHeight / 4;
		iPointsX [4] = iCentreX;
		iPointsY [4] = iCentreY - iHeight / 4;

		g.setColor (getColor());

		g.fillArc (iPointsX [3], iPointsY [3], iWidth / 2, iHeight / 2, 0, 180);
		g.fillArc (iPointsX [4], iPointsY [4], iWidth / 2, iHeight / 2, 0, 180);
		g.fillPolygon (iPointsX, iPointsY, 3);
	}



}
