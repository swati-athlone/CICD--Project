package edu.tus.winemanager;

import edu.tus.winemanager.validation.ErrorMessage;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ErrorMessageTest {

    @Test
    void testConstructorAndGetter() {
        String message = "Something went wrong!";
        ErrorMessage errorMessage = new ErrorMessage(message);
        assertEquals(message, errorMessage.geteMessage());
    }

    @Test
    void testErrorGettersAndSetters() {
        ErrorMessage errorMessage = new ErrorMessage("Test");
        assertEquals("Test", errorMessage.geteMessage());
    }
}
