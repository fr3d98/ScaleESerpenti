import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Tabella {
	
	private static Tabella INSTANCE=null;
	private static int N=100; //numero di caselle
	private static int nGiocatori=4; //numero giocatori
	private static int nSerpenti=7; //numero serpenti
	private static int nScale=7; //numero scale
	private static int nRighe=10;
	private static int nColonne=10;
	private static int nDadi=2;
	
	private static List<Player> players;
	
	
	
	private static Random dace;
	
	
	private Tabella() {
		players=new LinkedList<Player>();
		for (int i=1; i<=nGiocatori; i++) {
			Player g=new Player();
			g.setCardinal(i);
			g.setCurrPos(1);
			players.add(g);
		}
		dace=new Random();
	}
	
	
	public static synchronized Tabella getInstance() {
		if(INSTANCE!=null)return INSTANCE;
		else {
			INSTANCE=new Tabella();
			return INSTANCE;
		}
	}
	
	private int throwDaces() {
		int res=0;
		for(int i=0; i<nDadi; i++) res+=dace.nextInt(7)+1;
		return res;
		
	}
	
	private int singleThrow() {
		return dace.nextInt(7)+1;
	}
	
	
	public static void main(String[] args) {
		System.out.println("DEBUG");
		Tabella ses= Tabella.getInstance();
		System.out.println(ses.throwDaces());
		System.out.println(ses.singleThrow());

	}


	public int getN() {
		return N;
	}

	public void setN(int n) {
		N = n;
	}

	public int getnGiocatori() {
		return nGiocatori;
	}

	public void setnGiocatori(int nGiocatori) {
		this.nGiocatori = nGiocatori;
	}

	public int getnSerpenti() {
		return nSerpenti;
	}

	public void setnSerpenti(int nSerpenti) {
		this.nSerpenti = nSerpenti;
	}

	public int getnScale() {
		return nScale;
	}

	public void setnScale(int nScale) {
		this.nScale = nScale;
	}

	public int getnRighe() {
		return nRighe;
	}

	public void setnRighe(int nRighe) {
		this.nRighe = nRighe;
	}

	public int getnColonne() {
		return nColonne;
	}

	public void setnColonne(int nColonne) {
		this.nColonne = nColonne;
	}

	public int getnDadi() {
		return nDadi;
	}

	public void setnDadi(int nDadi) {
		this.nDadi = nDadi;
	}

	public static List<Player> getGiocatori() {
		return players;
	}

	public static void setGiocatori(List<Player> giocatori) {
		Tabella.players = giocatori;
	}

	public static Random getDado() {
		return dace;
	}

	public static void setDado(Random dado) {
		Tabella.dace = dado;
	}
	
	
	
	

}
