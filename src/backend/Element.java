package backend;
import java.util.Set;

interface Element{
	
	int action(Player p, StringBuilder sb);
	
	void locateElement(Set<Integer> set, int ... pos);
	
	int getActionPoint();

}
