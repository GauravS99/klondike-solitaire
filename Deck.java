import java.awt.*;      // Java's Abstract Windowing Toolkit package - includes class Color
import hsa.Console; 
import java.util.*;

public class Deck extends Shape{
	
	private Vector cards = new Vector();
	private boolean facedown = true;
	
	public Deck(){
		super.setColor(Color.BLACK);
		setHeight(100);
	}

	public void setHeight(int height){
		super.setHeight(height);
		super.setWidth((int)Math.round(height * 0.7));

		for(int i = 0; i < cards.size(); i ++)
			getCard(i).setHeight(height);

	}

	public void setWidth(int width){
		super.setWidth(width);
		super.setHeight((int)Math.round(width * 1.3));

		for(int i = 0; i < cards.size(); i ++)
			getCard(i).setWidth(width);
		
	}

	public void addCard(Card c){
		
		c.setWidth(getWidth());
		c.setCenter(getX(), getY());

		cards.add(c);
	}

	public void addCard(Card c, int pos){

		c.setWidth(getWidth());
		c.setCenter(getX(), getY());

		cards.add(pos, c);
	}

	public void flipDeck(){
		facedown = !facedown;
	}


	public Card removeCard(){
		Card old = getCard(cards.size() - 1);
		cards.remove(cards.size() - 1);
		return old;
	}

	public Card removeCard(int pos){
		Card old = getCard(pos);
		cards.remove(pos);
		return old;
	}

	public Card getCard(int pos){
		return (Card)cards.get(pos);
	}

	public int getSize(){
		return cards.size();
	}

	public void setCenter(int x, int y){
		super.setCenter(x,y);

		for(int i = 0; i < cards.size(); i ++)
			getCard(i).setCenter(x,y);
	}


	public boolean isEmpty(){
		return cards.isEmpty();

	}

	
	public void draw(Graphics g){

		int x = getX();
		int y = getY();
		int h = getHeight();
		int w = getWidth();

		g.setColor(getColor());

		if (!isEmpty()){
			Card top = getCard(cards.size() - 1);
			if(facedown){
				top.flip();
				top.draw(g);
				top.flip();
			}
			else{
				top.draw(g);
			}
		}
		else{
			g.drawRect(x - (w/2), y - (h/2), w, h );
		}
	}

	public void shuffle(){

		for(int j = 0; j < 2; j++){ //number of passes
			for(int i = 0; i < cards.size(); i++ ){

				int rand = (int)(Math.random() * cards.size());

				cards.add(i, cards.remove(rand));

			}
		}

	}


		public void addPile(Pile p){ //given a pile, add another pile to the end
			int size = p.getSize();
			for(int i = 0; i < size; i ++)
				addCard(p.removeCard(0));
		}


	}
