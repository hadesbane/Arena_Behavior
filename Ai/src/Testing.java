import java.io.FileNotFoundException;

public class Testing {
	
	public static void main(String[] args) throws FileNotFoundException{
		Arena arena = new Arena(10,10);
		FighterFactory fact = new FighterFactory();
		
		
		Fighter fighter = fact.makeFighter(arena);
		
		arena.addFighter(fighter);
	
		Routine routine = new Repeat();
		fighter.setRoutine(routine);
		System.out.println(fighter);
		
		int fighters = arena.getFighters().size();
		while(fighters > 1){
			for(Fighter curr : arena.getFighters()){
				curr.update();
				System.out.println(curr);
				if(!curr.isAlive()){
					arena.toDeadPool(curr);
					fighters--;
				}
			}
		}
	}
}
