import java.util.HashSet;
import java.util.Random;
import java.util.Set;

//factory to more easily create routines for the behavior tree
public class Routines {

	public Sequence sequence(Routine...routines){
		Sequence sequence = new Sequence();
		for(Routine routine : routines){
			sequence.addRoutine(routine);
		}
		return sequence;
	}
	
	public Selector selector(Routine...routines){
		Selector selector = new Selector();
		for(Routine routine : routines){
			selector.addRoutine(routine);
		}
		return selector;
	}
	
	public Selector randomSelector(Routine...routines){
		Set<Integer> set = new HashSet<Integer>();
		Selector selector = new Selector();
		Random random = new Random();
		while(set.size() < routines.length){
			int curr = random.nextInt(routines.length);
			while(!set.contains(curr)){
				curr = random.nextInt(routines.length);
			}
			selector.addRoutine(routines[curr]);
			set.add(curr);
		}
		return selector;
	}
	
	public Wander Wander(){
		return new Wander();
	}
	
	public Routine attackGroup(){
			return this.attackHelper(new Sequence(),0);
	}
	
	private Routine attackHelper(Routine routine, int num){
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
					curr = this.attackHelper(curr, num+1);
				}
				return rout;
			}
		}
		return null;
	}
	
	public Routine Brain(){
		Repeat brain = new Repeat();
		Selector styles = (Selector) this.selector(this.attackGroup(), this.Wander());
		brain.setRoutine(styles);
		return brain;
	}
}
