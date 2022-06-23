package backend;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public enum Deck {
	INSTANCE;
		
	private int N=50; //total number of cards
	
	private List<Card> deck=new LinkedList<>();
	
	public void setDeck(List<Card> deck) {
		this.deck=deck;
	}
	public List<Card> getDeck(){
		return deck;
	}
	
	void generateDeck() {
		for(int i=0; i<5; i++)
			for(int j=0; j<N/5; j++) {
				switch(i) {
				case 0 : 
					deck.add(Card.BENCH); break;
				case 1:
					deck.add(Card.REST); break;
				case 2:
					deck.add(Card.SPRING); break;
				case 3:
					deck.add(Card.DACES); break;
				case 4:
					deck.add(Card.NOSTOP); break;
				}
			}
		shuffle();
	}
	
	void shuffle() {
		Collections.shuffle(deck);
	}
	
	public Card pick() {
		Card c= deck.remove(0);
		return c;
	}
	
	public void putBackCard(Card c) {
		deck.add(deck.size(), c);
	}
	
	@Override
	public String toString() {
		StringBuilder sb= new StringBuilder();
		sb.append("TOTAL CARDS NUMBER IS: "+deck.size()+'\n');
		sb.append("____________");
		sb.append('\n');
		for(Card c : deck) sb.append(c.toString()+'\n');
		sb.append("_____________");
		return sb.toString();
	}
	
	
	public static void main(String[]args) {
		Deck d= Deck.INSTANCE;
		d.generateDeck();
		System.out.print(d);
		
		System.out.println(d.pick());
	
		System.out.println(d);
	}
	
	

}
