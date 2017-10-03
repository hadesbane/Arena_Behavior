//The set means for the fighters to move
public class MoveTo extends Routine {
	
	final protected int destX;
	final protected int destY;
	
	public MoveTo (int x, int y){
		super(); //calls the super (routine)'s creation for the basics 
		this.destX = x;
		this.destY = y;
		
		/*
		 * To test out the moving and wander capabilities
		 * System.out.println("Going to ("+x+","+y+")");
		 * */
	}
	
	public void reset(){
		start();
	}
	
	@Override
	public void act(Fighter fighter, Arena arena){
		if(isRunning()){
			if(!fighter.isAlive()){
				fail();
				return;
			}
			if(!isAtGoal(fighter)){
				moveFighter(fighter);
			}
		}
	}
	
	 private void moveFighter(Fighter fighter){
		 int currY = fighter.getY();
		 int currX = fighter.getX();
		 if(this.destY != currY){
			 if(this.destY > currY){
				 fighter.setY(currY + 1);
			 }
			 else{
				 fighter.setY(currY -1);
			 }
		 }
		 if(this.destX != currX){
			 if(this.destX > currX){
				 fighter.setX(currX + 1);
			 }
			 else{
				 fighter.setX(currX - 1);
			 }
		 }
		 if(isAtGoal(fighter)){
			 succeed();
		 }
	 }
	 
	 private boolean isAtGoal(Fighter fighter){
		 return this.destX == fighter.getX() && this.destY == fighter.getY();
	 }
}
