package backend;
class Snake extends Mover implements Element{
	
	@Override
	public int action(Player p, StringBuilder sb) {
		// snake moves the player down
		if(p.getCurrPos()!=top)
			throw new IllegalStateException("Player's position doesn't match with snake's");
		System.out.println(p+"goes down with a snake to position "+bottom);
		sb.append("Il giocatore scende con il serpente fino a "+bottom+'\n');
		return bottom;
	}

	@Override
	public int getActionPoint() {
		return this.getTop();
	}
	

}
