//Does the actions that are added to it in the order they are added.
//Moves either when the current routine succeeds or the 
public class Sequence extends BehaviorPath{
	private int tries;
	
	public Sequence(){
		super();
		this.tries = 0;
	}
	
	@Override
	public void act(Fighter fighter, Arena arena){
		if(!this.children.isEmpty()){
			Routine curr = this.children.peek();
			if(curr.state == null){
				curr.start();
			}
			if(curr.isRunning()){
				curr.act(fighter, arena);
			}
			else if(curr.isSuccess()){
				this.graveyard.push(curr);
				if(this.children.isEmpty()){
					this.recycle();
					this.succeed();
				}
			}
			else if(curr.isFailure()){
				if(fighter.isAlive() && tries < 5){
					curr.reset();
				}
				else if(fighter.isAlive() && !this.graveyard.isEmpty()){
					this.children.push(this.graveyard.pop());
				}
				else if(fighter.isAlive()){
					curr.reset();
				}
				else{
					this.fail();
				}
			}
		}
		this.fail();
	}
}
