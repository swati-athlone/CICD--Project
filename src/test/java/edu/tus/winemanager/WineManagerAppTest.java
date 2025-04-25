package edu.tus.winemanager;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class WineManagerAppTest {

    @Test
     void testMainMethod() {
        // You don't need to assert anything. Just make sure it runs.
        assertDoesNotThrow(() -> WineManagerApp.main(new String[]{}));
    }
}
