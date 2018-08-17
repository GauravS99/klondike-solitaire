    import java.awt.*;  
    import hsa.Console;

    public class Club extends Suit{

	public Club(){
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
	    triPointsY [0] = iCentreY - iHeight / 4;
	    triPointsX [1] = iCentreX - iWidth / 8;
	    triPointsY [1] = iCentreY + iHeight / 2;
	    triPointsX [2] = iCentreX + iWidth / 8;
	    triPointsY [2] = iCentreY + iHeight / 2;

	    c.setColor (getColor());

	    c.fillOval (iPointsX [3], iPointsY [3], iWidth / 2, iHeight / 2);
	    c.fillOval (iPointsX [4], iPointsY [4], iWidth / 2, iHeight / 2);
	    c.fillOval (iCentreX - iWidth / 4, iCentreY - 4 * (iHeight / 7), iWidth / 2, iHeight / 2);
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
	    triPointsY [0] = iCentreY - iHeight / 4;
	    triPointsX [1] = iCentreX - iWidth / 8;
	    triPointsY [1] = iCentreY + iHeight / 2;
	    triPointsX [2] = iCentreX + iWidth / 8;
	    triPointsY [2] = iCentreY + iHeight / 2;

	    g.setColor (getColor());
	    
	    g.fillOval (iPointsX [3], iPointsY [3], iWidth / 2, iHeight / 2);
	    g.fillOval (iPointsX [4], iPointsY [4], iWidth / 2, iHeight / 2);
	    g.fillOval (iCentreX - iWidth / 4, iCentreY - 4 * (iHeight / 7), iWidth / 2, iHeight / 2);
	    g.fillPolygon (triPointsX, triPointsY, 3);

	}
    }
