
public enum Weapon {
	
	RAPIER(false, true, 6, 1.2, 3),
	BROADSWORD(true, true, 5, 1.5, 1),
	SHORTBOW(false, false, 3, 1.3, 1),
	LONGBOW(false, false, 8, 1.6, 1),
	SPEAR(true, true, 4, 1.3, 1),
	DAGGER(true, true, 2, 1.1, 1),
	FIST(true, true, -20, 1, 2);
	
	private boolean strBase;
	private boolean melee;
	private int req; //Stats needed to wield
	private double dmgMult;
	private int apt; //Attacks per turn
	
	private Weapon(boolean based, boolean melee, int reqs, double mult, int apt){
		this.strBase = based;
		this.melee = melee;
		this.req = reqs;
		this.dmgMult = mult;
		this.apt = apt;
	}
	
	public Weapon getWeapon(int id){
		int i = 0;
		for(Weapon curr : Weapon.values()){
			i++;
			if(i == id){
				return curr;
			}
		}
		throw new IndexOutOfBoundsException("This is not in the list of weapons");
	}
	
	public double dmg(){
		return this.dmgMult;
	}
	
	public boolean isStr(){
		return this.strBase;
	}
	
	public boolean isMelee(){
		return this.melee;
	}
}
