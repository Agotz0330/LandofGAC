package edu.gac.mcs178.gack.domain;

import java.util.ArrayList;
import java.util.List;

import edu.gac.mcs178.gack.Utility;

public class Ghost extends AutoPerson{
	
	private Person target;
	private int turn_count;
	private Place soulJar;
	private Boolean hasPossessed;
	private Person victim;

	public Ghost(String name, Place place, int threshold, Person target, Place soulJar) {
		super(name, place, threshold);
		this.target = target;
		turn_count = 0;
		this.soulJar = soulJar;
		hasPossessed = false;
		this.victim = null;
	}
	
	@Override
	public void act() {
		if (hasPossessed == true) {
			turn_count += 1;
		}
		List<Person> others = otherPeopleAtSamePlace();
		//if (others.contains(target)) {
			//possess();
//			hasPossessed = true;
	//	}else {
		//	super.act();
		//}
		if (!others.isEmpty() & victim == null) {
			Person victim = others.get(Utility.randInt(others.size()));
			possess(victim);
			this.victim = victim;
		} else {
			super.act();
		if (turn_count == 3) {
			turnIntoSoul(this.victim);
			this.victim = null;
		}
	}
	}
	
	public void possess(Person victim){
		say("OooOoOoOoO, I have enacted my revenge on " + target + "!!!!!!");
	}
	
	public void turnIntoSoul(Person target) {
		List<Thing> personsPossessions = new ArrayList<Thing>(target.getPossessions());
		for (Thing thing : personsPossessions) {
			target.lose(thing);
		}
		target.say("What is happening to me!!!");
		target.moveTo(soulJar);
	}
}