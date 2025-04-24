package edu.tus.winemanager;

import edu.tus.winemanager.dto.WineDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WineDtoTest {

    @Test
    void testWineDtoGettersAndSetters() {
        WineDto wine = new WineDto();

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
