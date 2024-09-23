package org.example.command;

import org.example.GreetingCommand;
import org.example.SessionContext;
import org.example.command.impl.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;


public class CommandInvoker {

    private Map<String , Command> commandMap = new HashMap<String, Command>();

    public CommandInvoker(){
        commandMap.put("QUIT", new QuitCommand());
        commandMap.put("GREET", new GreetCommand());
        commandMap.put("NAME", new NameCommand());
        commandMap.put("LOCATION", new LocationCommand());
        commandMap.put("HELP", new HelpCommand());
    }

    public void executeCommand(GreetingCommand parsedCommand, Reader in, PrintWriter out) throws IOException {
        String commandName = parsedCommand.getName();
        Command command = commandMap.getOrDefault(commandName, new DefaultCommand());
        command.execute(parsedCommand.getArguments(), in, out, new SessionContext());
    }
}
