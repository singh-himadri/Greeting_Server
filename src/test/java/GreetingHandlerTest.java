import org.example.GreetingHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.*;
import java.net.Socket;

import static org.mockito.Mockito.*;

class GreetingHandlerTest {
    private Socket mockSocket;
    private GreetingHandler handler;
    private BufferedReader mockReader;
    private PrintWriter mockWriter;

    @BeforeEach
    void setup() throws IOException {
        mockSocket = mock(Socket.class);
        mockReader = mock(BufferedReader.class);
        mockWriter = new PrintWriter(new StringWriter(), true);

        when(mockSocket.getInputStream()).thenReturn(new ByteArrayInputStream("NAME John\nQUIT\n".getBytes()));
        when(mockSocket.getOutputStream()).thenReturn(new ByteArrayOutputStream());

        handler = new GreetingHandler(mockSocket, 60);
    }

    @Test
    void testHandleNameCommand() throws Exception {
        when(mockReader.readLine()).thenReturn("NAME John", "QUIT");

        handler.run();

        // Verify the expected interactions
        verify(mockSocket, times(1)).getInputStream();
        verify(mockSocket, times(1)).getOutputStream();
    }
}
