package edu.tus.winemanager;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class WineManagerAppTest {

    @Test
     void testMainMethod() {
        // You don't need to assert anything. Just make sure it runs.
        assertDoesNotThrow(() -> WineManagerApp.main(new String[]{}));
    }
}
