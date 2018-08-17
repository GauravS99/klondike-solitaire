/// The "YukonSolitaire" class.
import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class KlondikeSolitaire extends Applet implements ActionListener, MouseListener, MouseMotionListener
{
    // Place instance variables here

    Graphics g;

    int screenWidth = 900;
    int screenHeight = 800;
    Gameboard game;
    Pile dragPile;
    int oldX;
    int oldY;
    boolean okToMove = false;

    BorderLayout lm = new BorderLayout ();

    Panel pde = new Panel ();
    Button buttonStart = new Button ("Start");
    Button buttonQuit = new Button ("Quit");

    Panel inGame = new Panel ();
    Checkbox showMoves = new Checkbox ("Show Moves", false);
    Choice backgroundColor = new Choice ();


    public void init ()
    {

	g = getGraphics (); // gets canvas created by browser-
	setSize (screenWidth, screenHeight);
	game = new Gameboard (screenWidth, screenHeight);

	setLayout (lm);

	buttonStart.setFont (new Font ("Arial", Font.PLAIN, screenHeight / 20));
	buttonQuit.setFont (new Font ("Arial", Font.PLAIN, screenHeight / 20));

	buttonStart.addActionListener (this);
	buttonQuit.addActionListener (this);

	pde.setLayout (new BorderLayout ());
	inGame.setLayout (new GridLayout ());

	backgroundColor.add ("Green");
	backgroundColor.add ("White");
	backgroundColor.add ("Black");


	pde.add ("East", buttonStart);
	pde.add ("West", buttonQuit);

	inGame.add (backgroundColor);
	inGame.add (showMoves);



	add ("South", pde);

	addMouseListener (this);
	addMouseMotionListener (this);

	// Place the body of the initialization method here
    } // init method


    public void update (Graphics g)
    {
	Graphics offgc;
	Image offscreen = null;
	Dimension d = size ();

	// create the offscreen buffer and associated Graphics
	offscreen = createImage (screenWidth, screenHeight);
	offgc = offscreen.getGraphics ();
	// clear the exposed area
	offgc.setColor (getBackground ());
	offgc.fillRect (0, 0, d.width, d.height);
	offgc.setColor (getForeground ());
	// do normal redraw
	paint (offgc);
	// transfer offscreen to window
	g.drawImage (offscreen, 0, 0, this);
    }


    public void paint (Graphics g)
    {
	game.drawBoard (g);

	if (dragPile != null)
	{
	    dragPile.draw (g);
	}

    }


    public boolean action (Event e, Object b)
    {

	if (e.target instanceof Checkbox)
	{
	    game.toggleShowMoves ();
	}


	if (e.target instanceof Choice)
	{
	    if (backgroundColor.getSelectedIndex () == 0)
	    {
		game.setBoardColor (new Color (0, 100, 0));
	    }
	}

	if (e.target instanceof Choice)
	{
	    if (backgroundColor.getSelectedIndex () == 1)
	    {
		game.setBoardColor (new Color (245, 245, 245));
	    }
	}


	if (e.target instanceof Choice)
	{
	    if (backgroundColor.getSelectedIndex () == 2)
	    {
		game.setBoardColor (new Color (20, 20, 20));
	    }
	}


	repaint ();
	return true;
    }



    public void actionPerformed (ActionEvent e)
    {
	Object objSource = e.getSource ();

	if (objSource instanceof Button)
	{
	    if (objSource == buttonStart && game.getGameState () == 0)
	    {
		remove (pde);
		game.setGameState (1);
		buttonStart.setLabel ("Restart");
		inGame.add (buttonStart);
		inGame.add (buttonQuit);
		add ("South", inGame);

	    }
	    if (objSource == buttonStart && game.getGameState () != 0)

		{
		    game.restart ();
		}


	    if (objSource == buttonQuit)

		{
		    System.exit (0);
		}

	    repaint ();

	}
    }


    public void mouseClicked (MouseEvent e)
    {

    }


    public void mouseEntered (MouseEvent e)
    {


    }


    public void mouseExited (MouseEvent e)
    {
    }


    public void mousePressed (MouseEvent e)
    {

	if (game.getGameState () == 1)
	{
	    if (dragPile == null)
	    {
		okToMove = true;
		dragPile = game.clickHandler (e.getX (), e.getY ());
		if (dragPile != null)
		{

		    oldX = dragPile.getX ();
		    oldY = dragPile.getY ();
		}

		repaint ();
	    }
	}


    }


    public void mouseReleased (MouseEvent e)
    {

	if (game.getGameState () == 1)
	{
	    okToMove = false;
	    if (dragPile != null)
	    {

		if (game.releaseHandler (e.getX (), e.getY (), dragPile))
		{
		    if (game.isWon ())
		    {
			game.setGameState (2);
		    }

		    if (game.noMoves ())
		    {
			game.setGameState (3);
		    }
		}
		else
		{
		    game.snapBack (oldX, oldY, dragPile);
		}

		repaint ();
	    }
	}

	dragPile = null;
    }


    public void mouseDragged (MouseEvent e)
    {

	if (game.getGameState () == 1)
	{
	    if (okToMove == true && dragPile != null)
	    {
		dragPile.setCenter (e.getX (), e.getY ());
		repaint ();
	    }
	}

    }


    public void mouseMoved (MouseEvent e)
    {
    }
}
