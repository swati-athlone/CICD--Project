package edu.tus.winemanager;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import edu.tus.winemanager.controllers.WineService;
import edu.tus.winemanager.dto.WineDto;
import edu.tus.winemanager.exception.WineException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import edu.tus.winemanager.dao.WineRepository;
import edu.tus.winemanager.dto.Wine;
import edu.tus.winemanager.exception.WineValidationException;
import edu.tus.winemanager.validation.WineValidator;

class WineServiceTest {

    @InjectMocks
    private WineService wineService;

    @Mock
    private WineRepository wineRepo;

    @Mock
    private WineValidator wineValidator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAllWines_ReturnsWineList() {
        Wine wine1 = new Wine("Red Delight", 2020, "Cabernet", "France");
        Wine wine2 = new Wine("White Charm", 2021, "Chardonnay", "Italy");

        when(wineRepo.findAll()).thenReturn(Arrays.asList(wine1, wine2));

        List<Wine> result = wineService.findAllWines();

        assertEquals(2, result.size());
        verify(wineRepo, times(1)).findAll();
    }

    @Test
    void testCreateWine_Success() throws WineException {
        WineDto wineDto = new WineDto();
        wineDto.setName("Rose Light");
        wineDto.setYear(2022);
        wineDto.setGrapes("Syrah");
        wineDto.setCountry("Spain");

        Wine wine = new Wine();
        wine.setName("Rose Light");
        wine.setYear(2022);
        wine.setGrapes("Syrah");
        wine.setCountry("Spain");

        when(wineRepo.save(any(Wine.class))).thenReturn(wine);

        ResponseEntity<WineDto> response = wineService.createWine(wineDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Rose Light", response.getBody().getName());

        verify(wineValidator).validateWine(wineDto);
        verify(wineRepo).save(any(Wine.class));
    }

    @Test
    void testCreateWine_ValidationFails() throws WineException {
        WineDto wineDto = new WineDto();
        doThrow(new WineException("Invalid")).when(wineValidator).validateWine(wineDto);

        ResponseEntity<WineDto> response = wineService.createWine(wineDto);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());

        verify(wineValidator).validateWine(wineDto);
        verify(wineRepo, never()).save(any(Wine.class));
    }

    @Test
    void testShow_ReturnsMessage() {
        String result = wineService.show();
        assertEquals("checking webhook working or no", result);
    }
}

