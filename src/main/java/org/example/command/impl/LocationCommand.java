package org.example.command.impl;

import org.example.SessionContext;
import org.example.command.Command;

import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.List;

public class LocationCommand implements Command {
    @Override
    public void execute (List<String> arguments, Reader in, PrintWriter out, SessionContext context){
        String location = String.join(" ", arguments);
        out.println("201 LOCATION ok");
    }
}
