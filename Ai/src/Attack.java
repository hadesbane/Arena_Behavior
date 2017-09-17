import java.util.Random;

public class Attack extends Routine{
	
	public Attack(){}
	
	@Override
	public void reset(){
		start();
	}
	
	@Override
	public void act(Fighter fighter, Arena arena){
		Random random = new Random();
		int def = random.nextInt(21)+fighter.getEnemy().getDex();
		int att = 0;
		if(fighter.getWeapon().isStr()){
			att = random.nextInt(21) + fighter.getStr();
		}
		else{
			att = random.nextInt(21) + fighter.getDex();
		}
		if(def <= att){
			fighter.getEnemy().takeDamage(random.nextInt((int)(fighter.getStr()*fighter.getWeapon().dmg())+1));
		}
		else{
			fail();
		}
	}

}
