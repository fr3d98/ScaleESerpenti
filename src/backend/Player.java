package backend;

import java.io.Serializable;

class Player implements Serializable{
	
	private static final long serialVersionUID = 7937254411535277933L;
	private int currPos; //posizione corrente
	private int lastPos; //posizione precedente
	private int cardinal; //posizione cardinale
	private int lastScore;
	private int roundsToWait;
	
	private boolean noStopCard;
	
	
	public Player() {
		currPos=1;
		noStopCard=false;
		lastPos=0;
		roundsToWait=0;
	}
	
	public void reset() {
		currPos=1;
		noStopCard=false;
		lastPos=0;
		roundsToWait=0;
	}
	
	public int throwDaces() {
		//player consumes a round throwing daces
		/*
		 */
		int ris=Daces.INSTANCE.throww(this);
		this.setLastScore(ris);
		return ris;
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

	public int getRoundsToWait() {
		return roundsToWait;
	}

	public void setRoundsToWait(int roundsToWait) {
		this.roundsToWait = roundsToWait;
	}
	
	@Override
	public String toString() {
		return "Player "+this.cardinal;
	}
}
