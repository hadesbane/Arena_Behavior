import java.util.HashSet;
import java.util.Set;

//factory to more easily create routines for the behavior tree
public class Routines {

	public Routine Sequence(Routine...routines){
		Sequence sequence = new Sequence();
		for(Routine routine : routines){
			sequence.addRoutine(routine);
		}
		return sequence;
	}
	
	public Routine Selector(Routine...routines){
		Selector selector = new Selector();
		for(Routine routine : routines){
			selector.addRoutine(routine);
		}
		return selector;
	}
	
	public Routine RandomSelector(Routine...routines){
		Set<Routine> set = new HashSet<Routine>();
		Selector selector = new Selector();
		for(Routine routine : routines){
			set.add(routine);
		}
		for(Routine routine : set){
			selector.addRoutine(routine);
		}
		return selector;
	}
	
	public Routine Wander(Arena arena){
		return new Wander(arena);
	}
	
	public Routine AttackGroup(){
			return this.helper(new Sequence(),0);
	}
	
	private Routine helper(Routine routine, int num){
		if(routine != null){
			if(routine.isBranchable()){
				BehaviorPath rout = ((BehaviorPath)routine);
				switch(num){
					case 0:
						rout.addRoutine(new IsFighterInSight());
						rout.addRoutine(new Selector());
						break;
					case 2:
						rout.addRoutine(new Sequence());
						rout.addRoutine(new Selector());
						break;
					case 3:
						rout.addRoutine(new Sequence());
						rout.addRoutine(new Attack());
						break;
					case 4:
						rout.addRoutine(new IsFighterInRange());
						rout.addRoutine(new FighterIsWeaker());
						break;
					case 5:
						rout.addRoutine(new FindSafePlace());
						rout.addRoutine(new Attack());
						break;
				}
				for(Routine curr : rout.children){
					curr = this.helper(curr, num+1);
				}
				return rout;
			}
		}
		return null;
	}
}