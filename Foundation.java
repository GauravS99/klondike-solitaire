import java.awt.*;      // Java's Abstract Windowing Toolkit package - includes class Color
import hsa.Console; 
import java.util.*;


public class Foundation extends Deck{


	private String suit;

	public Foundation(String s){
		suit = s;

	}

	public String getSuit(){
		return suit;
	}

	public void draw(Graphics g){

		super.draw(g);

		int x = getX();
		int y = getY();
		int h = getHeight();
		int w = getWidth(); 

		if(isEmpty()){

			Shape s;

			if(suit.equals("D"))    
				s = new Diamond();
			else if (suit.equals("H"))
				s = new Heart();
			else if (suit.equals("S"))
				s = new Spade();        
			else 
				s = new Club();


			s.setHeight(h/3);
			s.setCenter(x,y);

			g.setColor(Color.WHITE);
			g.fillRect(x - (w/2) + 1, y - (h/2) + 1, w - 2, h - 2);
			s.draw(g);

		}


	}


}