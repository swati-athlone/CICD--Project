import edu.tus.winemanager.exception.WineNotFoundException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WineNotFoundExceptionTest {

    @Test
    public void testWineNotFoundExceptionMessage() {
        String message = "Wine not found with id 123";
        WineNotFoundException exception = new WineNotFoundException(message);

        assertEquals(message, exception.getMessage());
    }
}
