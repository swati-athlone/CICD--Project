package edu.tus.winemanager.validation;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;
import edu.tus.winemanager.dao.WineRepository;
import edu.tus.winemanager.dto.*;
import edu.tus.winemanager.exception.WineValidationException;

@Component
public class WineValidator {
	private WineRepository wineRepo;

	public void validateWine(WineDto wine) throws WineValidationException {
		checkEmptyFields(wine);
		checkForVintage(wine);
		checkMoreThanThreeWinesFromCountry(wine);
		checkForGrapes(wine);
	}


	private void checkEmptyFields(WineDto wine) throws WineValidationException {
		if ((wine.getName().isEmpty()) || (wine.getCountry().isEmpty()) || (wine.getYear() == 0)
				|| (wine.getGrapes().isEmpty())) {
			throw new WineValidationException(ErrorMessages.EMPTY_FIELDS.getMsg());
		}
	}
	//only allow three wines from a country
	private void checkMoreThanThreeWinesFromCountry(WineDto wine) throws WineValidationException {
		List<Wine> winesFromCountry = wineRepo.findByCountry(wine.getCountry());
		if (winesFromCountry.size()>=3) {
			throw new WineValidationException(ErrorMessages.INVALID_COUNTRY.getMsg());
		}
	}
	//if wine with name and year already exists
	private void checkForVintage(WineDto wine) throws WineValidationException {
		Optional<Wine> savedWine = wineRepo.findByNameAndYear(wine.getName(), wine.getYear());
		if (savedWine.isPresent()){
			throw new WineValidationException(ErrorMessages.ALREADY_EXISTS.getMsg());
		}
	}

	private void checkForGrapes(WineDto wine) throws WineValidationException {
		if ((wine.getGrapes().toUpperCase()).equals("MERLOT"))
			throw new WineValidationException(ErrorMessages.BAD_GRAPES.getMsg());
	}
}
