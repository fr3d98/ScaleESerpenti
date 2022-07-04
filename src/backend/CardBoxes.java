package backend;
import java.util.Set;

public enum CardBoxes implements Element {
	INSTANCE;

	private int pos;
	
	@Override
	public int action(Player p, StringBuilder sb) {
		Card c= Deck.INSTANCE.pick();
		sb.append("Il giocatore pesca una carta: "+c+'\n');
		switch (c) {
		case BENCH:
			Deck.INSTANCE.putBackCard(c);
			return Bench.INSTANCE.action(p,sb);
		case REST:
			Deck.INSTANCE.putBackCard(c);
			return Rest.INSTANCE.action(p,sb);
		case SPRING:
			Deck.INSTANCE.putBackCard(c);
			return Spring.INSTANCE.action(p,sb);
		case DACES:
			Deck.INSTANCE.putBackCard(c);
			return DaceBoxes.INSTANCE.action(p,sb);
		case NOSTOP:
			if(p.hasNoStopCard()) Deck.INSTANCE.putBackCard(c);
			else
				p.noStopCardTaken();
			return p.getCurrPos();
		default:
			throw new IllegalStateException("NO MORE CASES!");
		}
	}

	@Override
	public void locateElement(Set<Integer> set, int... pos) {
		if(pos.length!=1)
			throw new IllegalArgumentException("CardBox has only one position!");
		int p=pos[0];
		if(set.contains(p))
			throw new IllegalArgumentException("Positon already used, choose differently.");
		this.pos=p;
		set.add(p);
	}

	@Override
	public int getActionPoint() {
		return pos;
	}

}
