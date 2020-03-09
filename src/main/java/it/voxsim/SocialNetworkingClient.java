package it.voxsim;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import it.voxsim.command.Command;
import it.voxsim.command.CommandDispatcher;

public class SocialNetworkingClient {

	private CommandDispatcher commandFactory;

	public SocialNetworkingClient(CommandDispatcher commandDispatcher) {
		this.commandFactory = commandDispatcher;
	}

	public String process(String commandToInterpret, Calendar timeOfExecution) {
		// TODO 2. Extract this low level processing of the string to a separate class -
		// ie a Processor class - this could be a separate method for now
		Pattern pattern = Pattern.compile("(\\w+)(\\s(->|follows|wall)(\\s(.*))*)*");
		Matcher matcher = pattern.matcher(commandToInterpret);
		matcher.matches();

		String username = matcher.group(1);
		String action = matcher.group(3);
		String arguments = matcher.group(5);

		Command command = commandFactory.dispatch(action);
		return command.execute(username, arguments, timeOfExecution);
	}
}
