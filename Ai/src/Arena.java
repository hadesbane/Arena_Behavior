import java.util.ArrayList;
import java.util.List;

public class Arena {
	private final int width;
	private final int length;
	
	private List<Fighter> fighters = new ArrayList<Fighter>();
	
	public Arena(int width, int length) {
		this.width = width;
		this.length = length;
	}
	
	public int getWidth(){
		return this.width;
	}
	
	public int getLength(){
		return this.length;
	}
	
	public void addFighter(Fighter fighter){
		if (isTileWalkable(fighter.getX(), fighter.getY())){
			fighters.add(fighter);
			fighter.setArena(this);
		}
	}
	
	public boolean isTileWalkable(int x, int y){
		for (Fighter fighter : fighters){
			if(fighter.getX()== x && fighter.getY() == y){
				return false;
			}
		}
		return true;
	}
	
	public List<Fighter> getFighters(){
		return fighters;
	}
}
