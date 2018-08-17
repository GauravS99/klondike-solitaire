import java.applet.*;
import java.awt.*;
import hsa.Console;

public class Gameboard
{

	private Deck cardDeck = new Deck ();
	private Deck deposit = new Deck ();
	private Pile[] piles = new Pile [7];
	private Foundation[] foundations = new Foundation [4];
	private int screenHeight;
	private int screenWidth;
	private int border;
	private int cardSeparation;
	private int cardWidth;
	private int gameState = 0;
	private Color boardColor = new Color (0, 100, 0);
	private int moves = 0;
	private boolean showMoves = false;

	public Gameboard (int sw, int sh)
	{
		screenHeight = sh;
		screenWidth = sw;

		border = screenWidth / 15;
		cardSeparation = border / 2;
		cardWidth = ((screenWidth - (2 * border) - (6 * cardSeparation))) / 7;

		fillDeck ();
		cardDeck.shuffle ();
		setupLayout ();
	}


	public void fillDeck ()
    { //fills a deck with the cards in a normal deck

    	String suits = Card.suitList;
    	String values = Card.numList;

    	for (int i = 0 ; i < values.length () ; i++)
    	{
    		for (int j = 0 ; j < suits.length () ; j++)
    		{

    			Card temp = new Card (values.substring (i, i + 1), suits.substring (j, j + 1));
    			cardDeck.addCard (temp);
    		}
    	}

    }


    public void setupLayout ()
    {

	//Setup the PILES =================================================

    	int pilesY = screenHeight / 4 + (screenHeight / 12);
    	int pilesX = border + (cardWidth / 2);

    	for (int i = 0 ; i < piles.length ; i++)
    	{
    		piles [i] = new Pile ();
    		piles [i].setCenter (pilesX, pilesY);
    		piles [i].setWidth (cardWidth);
    		pilesX += cardWidth + cardSeparation;
    	}

    	for (int i = 0 ; i < 7 ; i++)
    	{
    		for (int j = 0 ; j < i + 1 ; j++)
    		{
    			piles [i].addCard (cardDeck.removeCard ());

    			Card added = piles [i].getCard (j);
    			if (j < i)
    				added.flip ();
    		}
    	}

	//setup the DECK =======================================================

    	pilesX = border + (cardWidth / 2);
    	pilesY -= ((int) Math.round (cardWidth * 1.3) + cardSeparation);

    	cardDeck.setWidth (cardWidth);
    	cardDeck.setCenter (pilesX, pilesY);

    	pilesX += cardSeparation + cardWidth;

    	deposit.setWidth (cardWidth);
    	deposit.setCenter (pilesX, pilesY);
    	deposit.flipDeck ();

    	pilesX += (cardSeparation * 2) + (cardWidth * 2);

    	for (int i = 0 ; i < foundations.length ; i++)
    	{
    		foundations [i] = new Foundation (Card.suitList.substring (i, i + 1));
    		foundations [i].setWidth (cardWidth);
    		foundations [i].setCenter (pilesX, pilesY);
    		foundations [i].flipDeck ();
    		pilesX += cardWidth + cardSeparation;
    	}

    }


    public int getGameState ()
    {

    	return gameState;

    }


    public void setGameState (int s)
    {
    	gameState = s;
    }


    public boolean isWon () 
    {//tells if the game has been won or not

    	for (int i = 0 ; i < piles.length ; i++)
    	{
    		if (!(piles [i].isEmpty () || isDraggable (piles [i], 0)))
    			return false;
    	}

    	return true;
    }


    public void restart ()
    { //resets game
    	cardDeck = new Deck ();
    	deposit = new Deck ();
    	piles = new Pile [7];
    	foundations = new Foundation [4];
    	showMoves = false;

    	Color boardColor = new Color (0, 100, 0);
    	int moves = 0;

    	fillDeck ();
    	cardDeck.shuffle ();

    	setupLayout ();

    }


    public void setBoardColor (Color c)
    {
    	boardColor = c;
    }


    public void toggleShowMoves ()
    {//toggles whether moves are shown or not
    	showMoves = !showMoves;
    }


    public int findPile (int x, int y)
    { //given and x and y, tells which pile has been clicked on. -1 if pile not found


    	if (y > screenHeight / 4 + (screenHeight / 12) - (((int) Math.round (cardWidth * 1.3)) / 2))
    	{
    		for (int i = 0 ; i < piles.length ; i++)
    		{
    			if (x > piles [i].getX () - (cardWidth / 2) && x < piles [i].getX () + (cardWidth / 2))
    			{
    				return i;
    			}
    		}
    	}

    	return -1;
    }


    public int findFoundation (int x, int y)
    {//find which foundation has been clicked on given an x and y 

    	int left;
    	int right;
    	int up;
    	int down;


    	for (int i = 0 ; i < foundations.length ; i++)
    	{
    		left = foundations [i].getX () - (foundations [i].getWidth () / 2);
    		right = left + foundations [i].getWidth ();
    		up = foundations [i].getY () - (foundations [i].getHeight () / 2);
    		down = up + foundations [i].getHeight ();

    		if (x > left && x < right && y > up && y < down)
    		{
    			return i;
    		}

    	}

    	return -1;


    }


    public boolean isDeckClicked (int x, int y)
    {	//returns true or false if the deck has been clicked

    	int left = cardDeck.getX () - (cardDeck.getWidth () / 2);
    	int right = left + cardDeck.getWidth ();
    	int up = cardDeck.getY () - (cardDeck.getHeight () / 2);
    	int down = up + cardDeck.getHeight ();
    	return (x > left && x < right && y > up && y < down);
    }


    public boolean isDepositClicked (int x, int y)
    { //returns true or false if the deposit 

    	int left = deposit.getX () - (deposit.getWidth () / 2);
    	int right = left + deposit.getWidth ();
    	int up = deposit.getY () - (deposit.getHeight () / 2);
    	int down = up + deposit.getHeight ();

    	return (x > left && x < right && y > up && y < down);

    }


    public boolean isDraggable (Card one, Card two) 
    {//Card one is the one on the Top

    	int pos = Card.numList.indexOf (one.getValue ());

    	if (one.isFacedown () || two.isFacedown ())
    		return false;
    	if (pos + 1 > Card.numList.length () - 1)
    		return false;
    	if (!two.getValue ().equals (Card.numList.substring (pos + 1, pos + 2)))
    		return false;
    	if (one.isBlack () == two.isBlack ())
    		return false;

    	return true;

    }


    public boolean isDraggable (Pile p, int index)
    { //Given a pile, tells you if it is draggable or not starting from an index

    	for (int i = index ; i < p.getSize () - 1 ; i++)
    	{
    		if (!isDraggable (p.getCard (i), p.getCard (i + 1)))
    			return false;
    	}
    	return true;
    }



    public boolean isFoundationable (Foundation f, Card c)
    { //tells user if the current card can be put on a foundation
    	if (f.isEmpty ())
    	{
    		return (c.getValue ().equals ("A") && c.getSuit ().equals (f.getSuit ()));
    	}


    	int pos = Card.numList.indexOf (f.getCard (f.getSize () - 1).getValue ());

    	if (!f.getSuit ().equals (c.getSuit ()))
    		return false;
    	if (pos == 0 || Card.numList.indexOf (c.getValue ()) != pos - 1)
    	{
    		return false;
    	}

    	return true;
    }


    public void snapBack (int x, int y, Pile p)
    { //snaps back the card given the old x and y
    	int pile = findPile (x, y);
    	if (pile != -1)
    	{
    		piles [pile].addPile (p);
    	}
    	else if (isDepositClicked (x, y))
    	{
    		deposit.addCard (p.removeCard (0));
    	}

    }


    public void autoFlip ()
    {//checks if there any cards to flip upwards
    	for (int i = 0 ; i < piles.length ; i++)
    	{
    		if (!piles [i].isEmpty () && piles [i].getCard (piles [i].getSize () - 1).isFacedown ())
    		{
    			piles [i].getCard (piles [i].getSize () - 1).flip ();
    		}
    	}

    }



    public boolean isEmptyPile(){
   	//tells you if there is is an available empty pile for a king
    	for (int i = 0; i < piles.length; i++ ){
    		if(piles[i].isEmpty())
    			return true;
    	}

    	return false;
    }
    public boolean noMovesHelper (Card c, Card previous) 
    {// returns true or false, weather or not to stop searching
    	for (int i = 0 ; i < piles.length ; i++)
    	{
    		if(previous != null){
    			if ((!piles [i].isEmpty () && isDraggable (piles [i].getCard (piles [i].getSize () - 1), c) && previous.isFacedown()) || (c.getValue().equals("A")) || (c.getValue().equals("K") && isEmptyPile()))
    				return true;
    		}
    		else{
    			if ((!piles [i].isEmpty () && isDraggable (piles [i].getCard (piles [i].getSize () - 1), c)) || c.getValue().equals("A") || (c.getValue().equals("K") && isEmptyPile()))
    				return true;
    		}
    	}

    	return false;
    }


    public boolean noMoves ()
    {//returns true or false whether there are no moves left


    	for (int i = 0 ; i < piles.length ; i++)
    	{
    		if(!piles[i].isEmpty()){
    			for(int j = 0; j < piles[i].getSize(); j++){
    				if(isDraggable(piles[i], j)){
    					if(j != 0){
    						if (noMovesHelper (piles[i].getCard(j), piles[i].getCard(j-1))){
    							//System.out.println("Can Reveal Flipped Card " + i + " " + j);
    							return false;
    						}       
    					}
    					else{
    						if (!piles[i].getCard(j).getValue().equals("K") && noMovesHelper (piles[i].getCard(j),null)){
    							//System.out.println("Can create empty pile");
    							return false;
    						}
    					}
    					break;
    				}

    			}

    		}



    	}

    	for (int i = cardDeck.getSize () - 1; i >=0 ; i--)
    	{
    		if (noMovesHelper (cardDeck.getCard (i), null)){
    			//System.out.println("In Deck " + cardDeck.getCard(i).getValue() + " " + cardDeck.getCard(i).getSuit() );
    			return false;
    		}
    	}

    	for (int i = 0 ; i < deposit.getSize () ; i++)
    	{
    		if (noMovesHelper (deposit.getCard (i), null)){
    			//System.out.println("In Deposit " + deposit.getCard(i).getValue() + " " + deposit.getCard(i).getSuit() );
    			return false;
    		}
    	}


    	return true;
    }


    public Pile clickHandler (int x, int y)
    { //Handles all clicks 

    	int pile = findPile (x, y);

    	if (pile != -1 && !piles [pile].isEmpty ())
	{ //user clicks on a pile
		Pile current = piles [pile];
		int index = current.findCard (x, y);



		if (index != -1)
		{
			if (isDraggable (current, index))
			{
				Pile split = current.splitPile (index);
				return split;
			}
		}

	}
	else if (isDeckClicked (x, y))
	{ //user clicks on a deck

		if (cardDeck.isEmpty () && !deposit.isEmpty ())
		{
			for (int i = deposit.getSize () - 1 ; i >= 0 ; i--)
			{
				cardDeck.addCard (deposit.removeCard (i));
			}
			moves++;
		}
		else if (!cardDeck.isEmpty ())
		{
			deposit.addCard (cardDeck.removeCard (cardDeck.getSize () - 1));
			moves++;
		}


	}

	else if (isDepositClicked (x, y) && !deposit.isEmpty ())
	{ //deposit clicked
		Pile draw = new Pile ();
		draw.setCenter (deposit.getX (), deposit.getY ());
		draw.addCard (deposit.removeCard (deposit.getSize () - 1));
		draw.setWidth (cardWidth);
		return draw;
	}


	return null;

}




public boolean releaseHandler (int x, int y, Pile p)
    { ////Handles all releases and returns true or false depending on whether anything happened
    	if (p != null && !p.isEmpty ())
    	{

    		int pile = findPile (x, y);


    		if (pile != -1)
	    { //user release on a pile
	    	Pile current = piles [pile];

	    	if (!piles [pile].isEmpty ())
	    	{

	    		int index = current.findCard (x, y);
	    		if (index == current.getSize () - 1)
	    		{
	    			if (isDraggable (current.getCard (index), p.getCard (0)))
	    			{
	    				current.addPile (p);
	    				moves++;
	    				autoFlip ();
	    				return true;
	    			}
	    		}
	    	}


	    	else if (piles [pile].isEmpty () && p.getCard (0).getValue ().equals ("K"))
		{ //if user drags king to empty pile
			piles [pile].addPile (p);
			moves++;
			autoFlip ();
			return true;
		}

	}

	else if (findFoundation (x, y) != -1)
	{//used drops card on foundation

		Foundation current = foundations [findFoundation (x, y)];

		if (p.getSize () == 1 && isFoundationable (current, p.getCard (0)))
		{
			current.addPile (p);
			moves++;
			autoFlip ();
			return true;
		}

	}
}

return false;

}



public void drawBoard (Graphics g)
{ //draws the entire board
	if (gameState == 0)
	{

		g.setColor (boardColor);
		g.fillRect (0, 0, screenWidth, screenHeight);

		int fheight = screenHeight / 4;
		final double CONVERSION = 0.74878;

		Font label = new Font ("Century Gothic", Font.BOLD, (int) Math.round (fheight * CONVERSION));
		g.setColor (Color.black);

		g.setFont (label);
		g.drawString ("Klondike", screenWidth / 4 - screenWidth / 20, screenHeight / 2 - screenHeight / 6);
		g.drawString ("Solitaire", screenWidth / 4 - screenWidth / 20, screenHeight / 4 + fheight);


		fheight = screenHeight / 9;
		label = new Font ("Century Gothic", Font.BOLD, (int) Math.round (fheight * CONVERSION));
		g.setFont (label);
		g.drawString ("By Gaurav Sharma", screenWidth / 2 - screenWidth / 4, screenHeight - screenHeight / 4);
	}
	else if (gameState == 1)
	{

		g.setColor (boardColor);
		g.fillRect (0, 0, screenWidth, screenHeight);


		if (showMoves)
		{
			int fheight = screenHeight / 25;
			final double CONVERSION = 0.74878;

			Font label = new Font ("Century Gothic", Font.BOLD, (int) Math.round (fheight * CONVERSION));
			g.setColor (Color.black);

			g.setFont (label);
			g.drawString ("Moves: " + moves, border + (cardWidth * 2) + cardWidth / 6 + cardSeparation, screenHeight / 4 + (screenHeight / 12) - ((int) Math.round (cardWidth * 1.3) + cardSeparation));

		}

		for (int i = 0 ; i < foundations.length ; i++)
		{
			foundations [i].draw (g);
		}

		for (int i = 0 ; i < piles.length ; i++)
		{
			piles [i].draw (g);
		}

		cardDeck.draw (g);
		deposit.draw (g);

	}
	else if (gameState == 2)
	{
		g.setColor (boardColor);
		g.fillRect (0, 0, screenWidth, screenHeight);

		int fheight = screenHeight / 4;
		final double CONVERSION = 0.74878;

		Font label = new Font ("Century Gothic", Font.BOLD, (int) Math.round (fheight * CONVERSION));
		g.setColor (Color.black);

		g.setFont (label);
		g.drawString ("You", screenWidth / 4 - screenWidth / 20, screenHeight / 2 - screenHeight / 6);
		g.drawString ("Win!", screenWidth / 4 - screenWidth / 20, screenHeight / 4 + fheight);

		fheight = screenHeight / 8;

		label = new Font ("Century Gothic", Font.BOLD, (int) Math.round (fheight * CONVERSION));
		g.setColor (Color.black);

		g.setFont (label);
		g.drawString ("Total Moves: " + moves, screenWidth / 4 - screenWidth / 20, screenHeight / 2 + screenHeight / 4);



	}

	else
	{


		for (int i = 0 ; i < foundations.length ; i++)
		{
			foundations [i].draw (g);
		}

		for (int i = 0 ; i < piles.length ; i++)
		{
			piles [i].draw (g);
		}

		cardDeck.draw (g);
		deposit.draw (g);


		g.setColor (boardColor);
		g.fillRect (0, 0, screenWidth, screenHeight);

		int fheight = screenHeight / 8;
		final double CONVERSION = 0.74878;

		Font label = new Font ("Century Gothic", Font.BOLD, (int) Math.round (fheight * CONVERSION));
		g.setColor (Color.black);

		g.setFont (label);
		g.drawString ("No more moves", screenWidth / 4 - screenWidth / 20, screenHeight / 2 + screenHeight / 4);


	}


}
}
