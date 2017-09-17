
public class FighterIsWeaker extends Routine{
	
	public FighterIsWeaker(){}
	
	@Override
	public void reset() {
		start();
	}

	@Override
	//Checks the weapons used by both fighters and then proceeds to determine if the fighter given will die first or not
	//This is checked by damage done and health.
	public void act(Fighter fighter, Arena arena) {	
		Fighter enemy = fighter.getEnemy();
		if(enemy != null){
			if(Math.abs(fighter.getX() - enemy.getX()) > 1 && Math.abs(fighter.getY() - enemy.getY()) > 1){
				if(!fighter.getWeapon().isMelee() && !enemy.getWeapon().isMelee()){
					if(fighter.getRange() > enemy.getRange()){
						if(!this.diesFirst(fighter, fighter.getRange()- enemy.getRange(), enemy, 0)){
							succeed();
						}
						else{
							fail();
						}
					}
					if(!this.diesFirst(fighter, 0, enemy, 0)){
						succeed();
					}
					else{
						fail();
					}
				}
				if(!fighter.getWeapon().isMelee()){
					int closer = this.findCloser(fighter, enemy);
					if(!this.diesFirst(fighter, closer, enemy, 0)){
						succeed();
					}
					else{
						fail();
					}
				}
				else if(!enemy.getWeapon().isMelee()){
					int closer2 = this.findCloser(fighter, enemy);
					if(!this.diesFirst(fighter, 0, enemy, closer2)){
						succeed();
					}
					else{
						fail();
					}
				}
				else{
					if(!this.diesFirst(fighter, 0, enemy, 0)){
						succeed();
					}
					else{
						fail();
					}
				}
			}
			else{
				if(!this.diesFirst(fighter, 0 , enemy, 0)){
					succeed();
				}
				else{
					fail();
				}
			}
		}
		else{
			fail();
			System.out.println("There is no enemy to test who is stronger");
		}
	}
	
	//Finds the closest int needed to be within melee range of the enemy
	private int findCloser(Fighter fighter, Fighter enemy){
		int x = Math.abs(fighter.getX() - enemy.getX())-1;
		int y = Math.abs(fighter.getY() - enemy.getY())-1;
		return (x < y) ? x : y; 
	}
	
	//tells whether the fighter that the routine is based on is going to die first
	private boolean diesFirst(Fighter fighter, int adv1, Fighter enemy, int adv2){
		int fightSum = 0;
		int enemySum = 0;
		int fDMG = fighter.getDmg();
		int eDMG = enemy.getDmg();
		for(int i = adv1; i == 0; i--){
			fightSum += fDMG;
		}
		for(int i = adv2; i == 0; i--){
			enemySum += eDMG;
		}
		while(fighter.getHP() > enemySum && enemy.getHP() > fightSum){
			fightSum += fDMG;
			enemySum += eDMG;
		}
		return(fighter.getHP() < enemySum);
	}
}
