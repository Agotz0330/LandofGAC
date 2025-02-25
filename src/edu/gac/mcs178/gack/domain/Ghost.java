package edu.gac.mcs178.gack.domain;

import java.util.ArrayList;
import java.util.List;

import edu.gac.mcs178.gack.Utility;

public class Ghost extends AutoPerson{
	
	private int turn_count;
	private Place soulJar;
	private Boolean hasPossessed;
	private Person victim;

	public Ghost(String name, Place place, int threshold, Place soulJar) {
		super(name, place, threshold);
		turn_count = 0;
		this.soulJar = soulJar;
		hasPossessed = false;
		this.victim = null;
	}
	
	@Override
	public void act() {
		List<Person> others = otherPeopleAtSamePlace();
		//if (others.contains(target)) {
			//possess();
//			hasPossessed = true;
	//	}else {
		//	super.act();
		//}
		if (turn_count > 3) {
			turnIntoSoul(this.victim);
			this.victim = null;
		}
		if (!others.isEmpty() && this.victim == null) {
			Person victim = others.get(Utility.randInt(others.size()));
			possess(victim);
		} else {
			super.act();
		}
	}
	public void maybeAct() { //override
		if(hasPossessed) {
			this.turn_count += 1;
			this.moveTo(victim.getPlace());
		} else {
		super.maybeAct();
		}
	}
		
	public void possess(Person target){
		say("OooOoOoOoO, I have begun my revenge!!!!!!");
		this.victim = target;
		this.hasPossessed = true;
	}
	
	public void turnIntoSoul(Person target) {
		List<Thing> personsPossessions = new ArrayList<Thing>(target.getPossessions());
		for (Thing thing : personsPossessions) {
			target.lose(thing);
		}
		target.say("What is happening to me!!!");
		target.moveTo(soulJar);
		this.moveTo(soulJar);
	}
}