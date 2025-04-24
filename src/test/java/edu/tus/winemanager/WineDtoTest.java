package edu.tus.winemanager;

import edu.tus.winemanager.dto.WineDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WineDtoTest {

    @Test
    void testWineDtoGettersAndSetters() {
        WineDto wineDto = new WineDto();

        // Test Name
        wineDto.setName("Merlot");
        assertEquals("Merlot", wineDto.getName());

        // Test Year
        wineDto.setYear(2022);
        assertEquals(2022, wineDto.getYear());

        // Test Grapes
        wineDto.setGrapes("Cabernet");
        assertEquals("Cabernet", wineDto.getGrapes());

        // Test Country
        wineDto.setCountry("France");
        assertEquals("France", wineDto.getCountry());
    }
}
