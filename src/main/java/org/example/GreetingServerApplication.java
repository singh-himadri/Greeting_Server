package org.example;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GreetingServerApplication {
    @Parameter(names = {"--port", "-p"}, description = "the port to listen on")
    private int port = 6666;

    @Parameter(names = {"--timeout", "-t"}, description = "idle timeout value for each connection.")
    private int timeout = 45;

    public static void main(String[] args) {
        GreetingServerApplication app = new GreetingServerApplication();
        JCommander commander = JCommander.newBuilder()
                .addObject(app).build();
        commander.parse(args);
        app.run();
    }

    private void run() {
//        log.info("Starting server on port {} with timeout {}", port, timeout);
        GreetingServer greetingServer = new GreetingServer(port, timeout);
        greetingServer.start();
    }
}