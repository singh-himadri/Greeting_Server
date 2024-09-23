import org.example.GreetingServerApplication;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GreetingHandleriT {

    private static Thread serverThread;
    private static final int PORT = 6666;

    @BeforeAll
    static void startServer() {
        serverThread = new Thread(() -> {
            String[] args = {"--port", String.valueOf(PORT), "--timeout", "30"};
            GreetingServerApplication.main(args);
        });
        serverThread.start();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @AfterAll
    static void stopServer() {

        serverThread.interrupt();
        try {
            serverThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Test
    void testClientServerInteraction() throws Exception {
        // Simulate a client connection
        try (Socket clientSocket = new Socket("localhost", PORT);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

            // Test initial server response
            assertEquals("200 server ready", in.readLine());

            // Send NAME command
            out.println("NAME John");
            assertEquals("201 NAME ok", in.readLine());

            // Send LOCATION command
            out.println("LOCATION Earth");
            assertEquals("201 LOCATION ok", in.readLine());

            // Send GREET command
            out.println("GREET");
            assertEquals("Hello John of Earth", in.readLine());

            // Send QUIT command
            out.println("QUIT");
            assertEquals("202 Bye", in.readLine());
        }
    }
}
