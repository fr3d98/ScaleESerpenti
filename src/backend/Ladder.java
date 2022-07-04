package backend;

class Ladder extends Mover implements Element {
	
	private static final long serialVersionUID = -4530870137475453434L;

	@Override
	public int action(Player p, StringBuilder sb) {
		//the ladder moves the players up
		if(p.getCurrPos()!=bottom)
			throw new IllegalStateException("Player's position doesn't match with ladder's");
		sb.append("Il giocatore sale con la scala fino a "+ top+'\n');
		return top;
	}

	@Override
	public int getActionPoint() {
		return this.getBottom();
	}
}
