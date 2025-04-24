package edu.tus.winemanager;

import edu.tus.winemanager.dto.Wine;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

 class WineTest {

    @Test
    void testWineGettersAndSetters() {
        Wine wine = new Wine();

        // Test ID
        wine.setId(1L);
        assertEquals(1L, wine.getId());

        // Test Name
        wine.setName("Merlot");
        assertEquals("Merlot", wine.getName());

        // Test Year
        wine.setYear(2022);
        assertEquals(2022, wine.getYear());

        // Test Grapes
        wine.setGrapes("Cabernet");
        assertEquals("Cabernet", wine.getGrapes());

        // Test Country
        wine.setCountry("France");
        assertEquals("France", wine.getCountry());
    }
}
