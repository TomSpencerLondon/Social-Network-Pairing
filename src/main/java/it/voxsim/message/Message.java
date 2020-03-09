package it.voxsim.message;

import java.util.Calendar;

public class Message implements Comparable<Message> {

	private String description;
	private Calendar time;
	private String user;
	private DeltaTimeTranslator deltaTimeTranslator;

	private Message(String user, String description, Calendar time, DeltaTimeTranslator deltaTimeTranslator) {
		this.user = user;
		this.description = description;
		this.time = time;
		this.deltaTimeTranslator = deltaTimeTranslator;
	}

	// TODO 7. Move description method from Message to multimessagePrinter
	// Message should not know how to format itself. It is a domain object
	public String description(String format, Calendar timeOfExecution) {
		return format.replace("%{user}", user).replace("%{description}", description).replace("%{time}",
				deltaTime(timeOfExecution));
	}

	// TODO 8. This should also be extracted to multimessagePrinter
	private String deltaTime(Calendar timeOfExecution) {
		return deltaTimeTranslator.translate(timeOfExecution, time);
	}

	// TODO 12. Extract compareTo to separate class (Sherlock)
	public int compareTo(Message anotherMessage) {
		return (int) (anotherMessage.time.getTimeInMillis() - time.getTimeInMillis());
	}

	// TODO 6. Wrap user to an object with one attribute (user name)
	// extract user allows us to decouple other points
	// User and followers -
	public static Message create(String user, String description, Calendar time) {
		DeltaTimeTranslator deltaTimeTranslator = new EnglishDeltaTimeTranslator();
		return new Message(user, description, time, deltaTimeTranslator);
	}
}