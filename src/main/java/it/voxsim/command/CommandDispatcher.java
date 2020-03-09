package it.voxsim.command;

import java.util.HashMap;
import java.util.Map;

import it.voxsim.command.Command;
import it.voxsim.command.FollowCommand;
import it.voxsim.command.PostCommand;
import it.voxsim.command.ReadCommand;
import it.voxsim.command.WallCommand;
import it.voxsim.repository.LinkRepository;
import it.voxsim.repository.MessageRepository;

public class CommandDispatcher {
	private Map<String, Command> commandDispatcher;


	// TODO 10. Create An abstraction level between the Command, ReadCommand and the WallCommand
	// - this could be called PrintableCommand
	// The PostCommand returns an empty string
	// Mixture of Command and Factory Pattern

	// The problem here is that we just pass the values from the regex rather than providing an interface
	// Use an enum or string for every command
	// Command tokens for all core logic and parsers to map user input and commands

	public CommandDispatcher(MessageRepository messageRepository, LinkRepository linkRepository) {
		commandDispatcher = new HashMap<String, Command>();
		commandDispatcher.put(null, ReadCommand.create(messageRepository));
		commandDispatcher.put("->", new PostCommand(messageRepository, linkRepository));
		commandDispatcher.put("follows", new FollowCommand(linkRepository));
		commandDispatcher.put("wall", WallCommand.create(messageRepository, linkRepository));
	}

	public Command dispatch(String action) {
		return commandDispatcher.get(action);
	}
}