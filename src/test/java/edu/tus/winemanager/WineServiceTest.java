package edu.tus.winemanager;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import edu.tus.winemanager.controllers.WineService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import edu.tus.winemanager.dao.WineRepository;
import edu.tus.winemanager.dto.Wine;
import edu.tus.winemanager.exception.WineValidationException;
import edu.tus.winemanager.validation.WineValidator;

@ExtendWith(MockitoExtension.class)
public class WineServiceTest {

    @Mock
    private WineRepository wineRepo;

    @Mock
    private WineValidator wineValidator;

    @InjectMocks
    private WineService wineService;

    private Wine wine1;
    private Wine wine2;

    @BeforeEach
    void setUp() {
        wine1 = new Wine();
        wine1.setId(1L);
        wine1.setName("Cabernet");
        wine1.setYear(2015);
        wine1.setGrapes("Cabernet Sauvignon");
        wine1.setCountry("France");

        wine2 = new Wine();
        wine2.setId(2L);
        wine2.setName("Chardonnay");
        wine2.setYear(2018);
        wine2.setGrapes("Chardonnay");
        wine2.setCountry("USA");
    }

    @Test
    void testFindAllWines() {
        when(wineRepo.findAll()).thenReturn(Arrays.asList(wine1, wine2));
        List<Wine> wines = wineService.findAllWines();
        assertEquals(2, wines.size());
        verify(wineRepo, times(1)).findAll();
    }

    @Test
    void testCreateWine_Success() throws WineValidationException {
        doNothing().when(wineValidator).validateWine(wine1);
        when(wineRepo.save(wine1)).thenReturn(wine1);
        ResponseEntity response = wineService.createWine(wine1);
        assertEquals(201, response.getStatusCodeValue());
        verify(wineRepo, times(1)).save(wine1);
    }

    @Test
    void testCreateWine_ValidationFailure() throws WineValidationException {
        doThrow(new WineValidationException("Wine validation failed"))
                .when(wineValidator).validateWine(wine1);
        ResponseEntity response = wineService.createWine(wine1);
        assertEquals(400, response.getStatusCodeValue());
        verify(wineRepo, never()).save(any(Wine.class));
    }

    @Test
    void testShow(){
        assertEquals("checking webhook working or no", wineService.show());
    }
}

