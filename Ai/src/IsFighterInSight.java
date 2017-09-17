
public class IsFighterInSight extends Routine{
	
	public IsFighterInSight(){}
	
	@Override
	public void reset(){
		start();
	}
	
	@Override
	public void act(Fighter fighter, Arena arena){
		for(Fighter enemy : arena.getFighters()){
			if(isInSight(fighter, enemy)){
				succeed();
				break;
			}
		}
	}
	
	private boolean isInSight(Fighter fighter, Fighter enemy){
		int sight = fighter.getSight();
		return(Math.abs(fighter.getX() - enemy.getX()) <= sight||
			   Math.abs(fighter.getY() - enemy.getY()) <= sight);
	}
}
