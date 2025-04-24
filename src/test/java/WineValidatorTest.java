import edu.tus.winemanager.dao.WineRepository;
import edu.tus.winemanager.dto.Wine;
import edu.tus.winemanager.exception.WineValidationException;
import edu.tus.winemanager.validation.WineValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class WineValidatorTest {

    @Mock
    private WineRepository wineRepo;

    @InjectMocks
    private WineValidator wineValidator;

    private Wine validWine;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        validWine = new Wine();
        validWine.setName("Cabernet");
        validWine.setCountry("France");
        validWine.setYear(2020);
        validWine.setGrapes("Shiraz");
    }

    @Test
    void testValidWine() {
        when(wineRepo.findByCountry(anyString())).thenReturn(Collections.emptyList());
        when(wineRepo.findByNameAndYear(anyString(), anyInt())).thenReturn(Optional.empty());

        assertDoesNotThrow(() -> wineValidator.validateWine(validWine));
    }

    @Test
    void testEmptyFields() {
        Wine wine = new Wine();
        wine.setName("");
        wine.setCountry("France");
        wine.setYear(2020);
        wine.setGrapes("Shiraz");

        WineValidationException e = assertThrows(WineValidationException.class, () -> wineValidator.validateWine(wine));
        assertEquals("One or more empty fields", e.getMessage()); // adjust this message to match your actual one
    }

    @Test
    void testMoreThanThreeWinesFromCountry() {
        when(wineRepo.findByCountry("France")).thenReturn(Arrays.asList(new Wine(), new Wine(), new Wine()));

        WineValidationException e = assertThrows(WineValidationException.class, () -> wineValidator.validateWine(validWine));
        assertEquals("Not accepting more wines from that country", e.getMessage()); // adjust message
    }

    @Test
    void testVintageAlreadyExists() {
        when(wineRepo.findByCountry(anyString())).thenReturn(Collections.emptyList());
        when(wineRepo.findByNameAndYear("Cabernet", 2020)).thenReturn(Optional.of(new Wine()));

        WineValidationException e = assertThrows(WineValidationException.class, () -> wineValidator.validateWine(validWine));
        assertEquals("Wine with given name and year already exists", e.getMessage()); // adjust message
    }

    @Test
    void testBadGrapesThrowsException() {
        validWine.setGrapes("Merlot");
        when(wineRepo.findByCountry(anyString())).thenReturn(Collections.emptyList());
        when(wineRepo.findByNameAndYear(anyString(), anyInt())).thenReturn(Optional.empty());

        WineValidationException e = assertThrows(WineValidationException.class, () -> wineValidator.validateWine(validWine));
        assertEquals("Type of grape not acceptable", e.getMessage()); // adjust message
    }
}
