package backend;
import java.util.Set;

/**
 * 
 * @author alfredo
 * 
 * Bench (and others) are made as enum, so once located on the map they must be added to the game map
 * before another bench is located.
 * This is important because the pos variable can change and must be saved by the gameMap before.
 *
 */

public enum Bench implements Element{
	INSTANCE
	;
	
	private int pos;

	@Override
	public int action(Player p, StringBuilder sb) {
		// makes the player stop for 1 round, returns player position
		if(p.hasNoStopCard()) {
			p.noStopCardConsumed();
			Deck.INSTANCE.putBackCard(Card.NOSTOP);
			System.out.println(p+" has a no-stop card and keeps playing");
			sb.append("Il giocatore ha una No-Stop card e continua a giocare.+'\n");
		}else {
			p.setRoundsToWait(1);
			System.out.println(p+" must wait one round to play again.");
			sb.append("Il giocatore deve attendere un turno."+'\n');
		}
		return p.getCurrPos();
	}

	@Override
	public void locateElement(Set<Integer> set, int... pos) {
		if(pos.length>1)
			throw new IllegalArgumentException("Bench only works on one position.");
		if(set.contains(pos[0]))
			throw new IllegalArgumentException("Can't put in this position, already taken.");
		this.pos=pos[0];
		set.add(pos[0]);
		
	}

	@Override
	public int getActionPoint() {
		return pos;
	}

}
