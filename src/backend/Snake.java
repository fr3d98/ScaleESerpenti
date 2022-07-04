package backend;
class Snake extends Mover implements Element{
	
	private static final long serialVersionUID = 4392718637502907414L;

	@Override
	public int action(Player p, StringBuilder sb) {
		// snake moves the player down
		if(p.getCurrPos()!=top)
			throw new IllegalStateException("Player's position doesn't match with snake's");
		sb.append("Il giocatore scende con il serpente fino a "+bottom+'\n');
		return bottom;
	}

	@Override
	public int getActionPoint() {
		return this.getTop();
	}
	

}
