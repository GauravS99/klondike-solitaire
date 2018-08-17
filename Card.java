import java.awt.*;  
import hsa.Console;

public class Card extends Shape{

	public static final String numList = "KQJT98765432A";
	public static final String suitList = "DHSC";

	private String value = "A";
	private String suit = "D";
	private boolean facedown = false;

	public Card(){
		super.setColor(Color.BLACK);
		setHeight(100);
	}

	public Card(String v, String s){
		super.setColor(Color.BLACK);
		setHeight(100);
		setValue(v);
		setSuit(s);
	}

	public void setHeight(int height){
		super.setHeight(height);
		super.setWidth((int)Math.round(height * 0.7));
	}

	public void setWidth(int width){
		super.setWidth(width);
		super.setHeight((int)Math.round(width * 1.3));
	}

	public String getValue(){
		return value;
	}
	public String getSuit(){
		return suit;
	}
	public boolean isFacedown(){
		return facedown;
	}

	public void setValue(String s){
		if(numList.indexOf(s) != -1)
			value = s;

	}

	public void setSuit(String s){
		if(suitList.indexOf(s) != -1)
			suit = s;

	}

	public void flip(){
		facedown = !facedown;
	}

	public boolean isBlack(){

		return suit.equals("S") || suit.equals("C");
	}

	public void draw(Graphics g){


		int x = getX();
		int y = getY();
		int h = getHeight();
		int w = getWidth(); 
		g.setColor (getColor());                

		if(facedown){
			g.fillRect(x - (w/2), y - (h/2), w, h );
			g.setColor(Color.RED);
			g.drawRect(x - (w/2), y - (h/2), w, h );
		}
		else{
			Shape s;

			g.drawRect(x - (w/2), y - (h/2), w, h );
			g.setColor(Color.WHITE);
			g.fillRect(x - (w/2) + 1, y - (h/2) + 1, w - 2, h - 2);

			if(suit.equals("D"))    
				s = new Diamond();
			else if (suit.equals("H"))
				s = new Heart();
			else if (suit.equals("S"))
				s = new Spade();        
			else 
				s = new Club();


			s.setHeight(h/6);
			s.setCenter(x + (w/2) - (w/5), y - (h/2) + (h/7));
			s.draw(g);

			s.setHeight(h/6);
			s.setCenter(x - (w/2) + (w/5), y + (h/2) - (h/7));
			s.draw(g);


			int fheight = h / 5;
			final double CONVERSION = 0.74878; 

			Font label = new Font("Arial", Font.BOLD, (int)Math.round(fheight * CONVERSION));

			g.setColor (s.getColor());  

			g.setFont(label);
			g.drawString(value, x - (w/2) + (w/6), y - (h/2) + (h/5));
			g.drawString(value, x + (w/2) - (w/4), y + (h/2) - (h/11));


		}


	}
}
