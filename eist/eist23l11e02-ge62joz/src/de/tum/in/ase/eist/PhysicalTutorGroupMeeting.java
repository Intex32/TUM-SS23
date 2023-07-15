package de.tum.in.ase.eist;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class PhysicalTutorGroupMeeting extends TutorGroupMeeting {

	private final String room;

	public PhysicalTutorGroupMeeting(TimeSlot timeSlot, TutorGroup tutorGroup, Skill skillToPractice, String room) {
		super(timeSlot, tutorGroup, skillToPractice);
		this.room = room;
	}

	public String getRoom() {
		return room;
	}

	@Override
	public void practice() {
		Student tutor = getTutorGroup().getTutor();
		tutor.say("Welcome to the physical tutor meeting");
		tutor.say("Thank you for coming to " + room + " today.");
		tutor.say("We start with the homework presentation");

		homework();

		tutor.say("Next is the group work");
		skill();

		tutor.say("Let's have a look at the new homework");
		tutor.say("Thank you that you have participated in " + room + " today.");
		tutor.say("See you next time!");
	}

}
