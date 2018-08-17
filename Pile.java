import java.awt.*;      // Java's Abstract Windowing Toolkit package - includes class Color
import hsa.Console; 
import java.util.*;

public class Pile extends Deck{

	private int interval = 0;

	private void setup(){


		int currentY = getY();

		for(int i = 0; i < getSize(); i ++){
			Card current = getCard(i);
			current.setCenter(getX(), currentY);
			currentY += interval;
		}


	}

	public void draw(Graphics g){
		interval = getHeight()/4;
		
		if(!isEmpty()){
			setup();

			for(int i = 0; i < getSize(); i ++)
				getCard(i).draw(g);
		}
		else{
			super.draw(g);

		}

	}

	public int findCard(int x, int y){ //given x and y, finnd the card that is under this point


		if(!isEmpty()){
			if(x > getX() - (getWidth()/2) && x < getX() + (getWidth()/2))
			{
				Card current;
				for(int i = 0; i < getSize() - 1; i++){
					current = getCard(i);
					if(y > current.getY() - (current.getHeight()/2) && y < current.getY() - (current.getHeight()/2) + interval)
						return i;
				}

				current = getCard(getSize() - 1); 

				if(y > current.getY() - (current.getHeight()/2) && y < current.getY() + (current.getHeight()/2))
					return getSize() - 1;

			}
		}

		return -1;

	}

	public Pile splitPile(int index){ //given an index, split the current pile into two separate ones 

		if(getSize() != 0){
			Pile p = new Pile();
			p.setCenter(getCard(index).getX(), getCard(index).getY());

			int times = getSize() - index;
			for(int i = 0; i < times; i++){
				p.addCard(removeCard(index));
			}


			p.setColor(getColor());
			p.setWidth(getWidth());

			return p;
		}

		else{
			return null;
		}
	}



}
