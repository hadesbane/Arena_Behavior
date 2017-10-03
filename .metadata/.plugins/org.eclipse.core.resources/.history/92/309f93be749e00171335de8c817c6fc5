//Does the actions that are added to it in the order they are added.
//Only moves to the next when the one before succeeds, succeeds when all do
public class Sequence extends BehaviorPath{
	
	public Sequence(){
		super();
	}
	
	@Override
	public void act(Fighter fighter, Arena arena){
		if(this.helper(this.children.removeFirst(), fighter, arena, 0)){
			succeed();
		}
		else{
			fail();
		}
	}
	
	
	private boolean helper(Routine curr, Fighter fighter, Arena arena, int tries){
		if (curr != null){
			if(curr.state != null){
				if(curr.isSuccess())
					this.graveyard.addFirst(curr);
					if(this.children.isEmpty()){
						this.recycle();
						return true;
					}
					return this.helper(this.children.removeFirst(), fighter, arena, tries);
				}
				if(curr.isRunning()){
					curr.act(fighter, arena);
					return this.helper(curr, fighter, arena, tries);
				}
				if(curr.isFailure()){
					if(fighter.isAlive() && tries < 5){
						curr.reset();
						return this.helper(curr, fighter, arena, tries+1);
					}
					else if(fighter.isAlive() && !this.children.isEmpty()){
						this.graveyard.addFirst(curr);
						return this.helper(this.children.removeFirst(), fighter, arena, 0);
					}
					else{
						this.recycle();
						return false;
					}
				}
			else{
				curr.start();
				return this.helper(curr, fighter, arena, tries);
			}
		}
		return false;
	}
}
