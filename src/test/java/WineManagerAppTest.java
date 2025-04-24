import edu.tus.winemanager.WineManagerApp;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class WineManagerAppTest {

    @Test
    public void testMainMethod() {
        // You don't need to assert anything. Just make sure it runs.
        assertDoesNotThrow(() -> WineManagerApp.main(new String[]{}));
    }
}
