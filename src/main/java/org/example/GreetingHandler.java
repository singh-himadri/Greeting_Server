package org.example;

import org.example.command.CommandInvoker;
import org.example.util.ParseUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import static java.lang.System.out;

public class GreetingHandler implements Runnable{
    private Socket clientSocket;
    private int timeout;
    private CommandInvoker invoker;

    public GreetingHandler(Socket clientSocket, int timeout) {
        this.clientSocket = clientSocket;
        this.timeout = timeout;
        this.invoker = new CommandInvoker();
    }

    @Override
    public void run() {
        try(
                BufferedReader in =  new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        ) {
            try {
                SessionContext context = new SessionContext();
                clientSocket.setSoTimeout(timeout * 1000);
                out.println("200 server ready");
                String line;
                String name = "";
                String location = "";

                while ((line = in.readLine()) != null) {
                    this.invoker.executeCommand(ParseUtil.parseInput(line), in, out, context);
//                GreetingCommand cmd = ParseUtil.parseInput(line);
//                switch (cmd.getName().toUpperCase()) {
//                    case "NAME":
//                        name = String.join(" ", cmd.getArguments());
//                        out.println("201 NAME ok");
//                        break;
//                    case "LOCATION":
//                        location = String.join(" ", cmd.getArguments());
//                        out.println("201 LOCATION ok");
//                        break;
//                    case "GREET":
//                        out.printf("Hello %s of %s%n", name, location);
//                        break;
//                    case "QUIT":
//                        out.println("202 Bye");
//                        return;
//                    default:
//                        out.println("400 Bad Request");
//                        break;
//                }
                    //parse the input
//                GreetingCommand cmd = parseInput(line);
                    //handle the command
                    //for each command write the response code and message back to the client
                }
            } finally {
                out.println("202 Bye");
            }

        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }

}
