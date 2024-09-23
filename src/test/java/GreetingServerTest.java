import org.example.GreetingServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class GreetingServerTest {
    private GreetingServer server;
    private Thread serverThread;

    @BeforeEach
    void setup() {
        server = new GreetingServer(6666, 45);
        serverThread = new Thread(() -> {
            try {
                server.start();
            } catch (Exception e) {
            }
        });
        serverThread.start();
    }

    @AfterEach
    void teardown() throws IOException {
        serverThread.interrupt();
    }

    @Test
    void testServerAcceptsConnection() throws Exception {

        Thread.sleep(1000);

        try (Socket clientSocket = new Socket("localhost", 6666)) {
            assertNotNull(clientSocket);
        }
    }
}
