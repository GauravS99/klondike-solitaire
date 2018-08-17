import java.awt.*;  
import hsa.Console;

public class Spade extends Suit{

	public Spade(){
		super();
		super.setColor(Color.BLACK);
	}

	public void setColor(Color color){


	}

	public void draw (Console c)
	{
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
		iPointsY [2] = iCentreY - iHeight / 2;
		iPointsX [3] = iCentreX - iWidth / 2;
		iPointsY [3] = iCentreY - iHeight / 4;
		iPointsX [4] = iCentreX;
		iPointsY [4] = iCentreY - iHeight / 4;

		int triPointsX[] = new int [3];
		int triPointsY[] = new int [3];

		triPointsX [0] = iCentreX;
		triPointsY [0] = iCentreY;
		triPointsX [1] = iCentreX - iWidth / 8;
		triPointsY [1] = iCentreY + iHeight / 2;
		triPointsX [2] = iCentreX + iWidth / 8;
		triPointsY [2] = iCentreY + iHeight / 2;

		c.setColor (getColor());
		c.fillArc (iPointsX [3], iPointsY [3], iWidth / 2, iHeight / 2, 180, 180);
		c.fillArc (iPointsX [4], iPointsY [4], iWidth / 2, iHeight / 2, 180, 180);
		c.fillPolygon (iPointsX, iPointsY, 3);
		c.fillPolygon (triPointsX, triPointsY, 3);
	}


	public void draw (Graphics g)
	{
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
		iPointsY [2] = iCentreY - iHeight / 2;
		iPointsX [3] = iCentreX - iWidth / 2;
		iPointsY [3] = iCentreY - iHeight / 4;
		iPointsX [4] = iCentreX;
		iPointsY [4] = iCentreY - iHeight / 4;

		int triPointsX[] = new int [3];
		int triPointsY[] = new int [3];

		triPointsX [0] = iCentreX;
		triPointsY [0] = iCentreY;
		triPointsX [1] = iCentreX - iWidth / 8;
		triPointsY [1] = iCentreY + iHeight / 2;
		triPointsX [2] = iCentreX + iWidth / 8;
		triPointsY [2] = iCentreY + iHeight / 2;

		g.setColor (getColor());
		g.fillArc (iPointsX [3], iPointsY [3], iWidth / 2, iHeight / 2, 180, 180);
		g.fillArc (iPointsX [4], iPointsY [4], iWidth / 2, iHeight / 2, 180, 180);
		g.fillPolygon (iPointsX, iPointsY, 3);
		g.fillPolygon (triPointsX, triPointsY, 3);
	}

}
