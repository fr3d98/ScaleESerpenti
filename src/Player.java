class Player {
	
	private int currPos; //posizione corrente
	private int lastPos; //posizione precedente
	private int cardinal; //posizione cardinale
	private int lastScore;
	
	private boolean noStopCard;
	
	public Player() {
		currPos=1;
		noStopCard=false;
		lastPos=0;
	}
	
	public boolean hasNoStopCard() {return noStopCard;}
	
	public void noStopCardTaken() {noStopCard=true;}
	
	public void noStopCardConsumed() {
		if(!hasNoStopCard())
			throw new IllegalStateException("Player doesn't hava a stop card!");
		noStopCard=false;
	}
	
	public void moveTo(int pos) {
		lastPos=currPos;
		currPos=pos;
	}
	
	public void moveForward(int q) {
		lastPos=currPos;
		currPos+=q;
	}
	
	public void moveBackward(int q) {
		lastPos=currPos;
		currPos-=q;
	}
	
	public int getCardinal() {
		return cardinal;
	}
	public void setCardinal(int cardinal) {
		this.cardinal = cardinal;
	}
	public int getCurrPos() {
		return currPos;
	}
	public void setCurrPos(int currPos) {
		this.currPos = currPos;
	}
	public int getLastPos() {
		return lastPos;
	}
	public void setLastPos(int lastPos) {
		this.lastPos = lastPos;
	}

	public int getLastScore() {
		return lastScore;
	}

	public void setLastScore(int lastScore) {
		this.lastScore = lastScore;
	}
	
	
	
}
