package util;

import org.junit.jupiter.api.Test;
import org.example.GreetingCommand;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ParseUtilTest {

    @Test
    void testGreetingCommandCreation() {
        GreetingCommand command = GreetingCommand.builder()
                .name("NAME")
                .arguments(Arrays.asList("John", "Doe"))
                .build();

        assertEquals("NAME", command.getName());
        assertEquals(2, command.getArguments().size());
        assertEquals("John", command.getArguments().get(0));
        assertEquals("Doe", command.getArguments().get(1));
    }

}
