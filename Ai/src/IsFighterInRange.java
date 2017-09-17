
public class IsFighterInRange extends Routine{

	@Override
	public void reset(){
		start();
	}
	
	@Override
	public void act(Fighter fighter, Arena arena){
		for(Fighter enemy: arena.getFighters()){
			if(!fighter.getName().equals(enemy)){
				if(isInRange(fighter, enemy)){
					fighter.setEnemy(enemy);
					succeed();
					break;
				}
			}
		}
	}
	
	private boolean isInRange(Fighter fighter, Fighter enemy){
		if(fighter.getWeapon().isMelee()){
			return (Math.abs(fighter.getX() - enemy.getX()) == 1 || 
					Math.abs(fighter.getY() - enemy.getY()) == 1);
		}
		else{
			int range = fighter.getRange();
			return (Math.abs(fighter.getX() - enemy.getX()) <= range 
				|| Math.abs(fighter.getY() - enemy.getY()) <= range);
		}
		
		
	}
}
