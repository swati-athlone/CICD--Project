package edu.tus.winemanager.controllers;

import java.util.List;

import javax.validation.Valid;

import edu.tus.winemanager.dto.WineDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import edu.tus.winemanager.dao.WineRepository;
import edu.tus.winemanager.dto.Wine;
import edu.tus.winemanager.exception.WineException;
import edu.tus.winemanager.validation.ErrorMessage;
import edu.tus.winemanager.validation.WineValidator;


@RestController
@Service
public class WineService {

	private final WineRepository wineRepo;
	private final WineValidator wineValidator;
	private static final Logger log = LoggerFactory.getLogger(WineService.class);

	public WineService(WineRepository wineRepo, WineValidator wineValidator) {
		this.wineRepo = wineRepo;
		this.wineValidator = wineValidator;
	}

	@GetMapping("/wines")
	public List<Wine> findAllWines(){
		return wineRepo.findAll();
	}


	@PostMapping("/wines")
	public ResponseEntity<WineDto> createWine(@Valid @RequestBody WineDto wineDto) {
		try {
			// Validate using DTO
			wineValidator.validateWine(wineDto);
			log.info("Validated wine data");

			// Map DTO to entity
			Wine wine = new Wine();
			wine.setName(wineDto.getName());
			wine.setYear(wineDto.getYear());
			wine.setGrapes(wineDto.getGrapes());
			wine.setCountry(wineDto.getCountry());

			// Save entity
			Wine savedWine = wineRepo.save(wine);
			log.info("Saved wine in Database");

			WineDto wineDto1 = new WineDto();
			wineDto1.setName(savedWine.getName());
			wineDto1.setYear(savedWine.getYear());
			wineDto1.setGrapes(savedWine.getGrapes());
			wineDto1.setCountry(savedWine.getCountry());


			return ResponseEntity.status(HttpStatus.CREATED).body(wineDto1);

		} catch (WineException e) {
			return ResponseEntity.badRequest().body(null);
		}
	}

	@GetMapping("/show")
	public String show(){
		log.info("checking webhook if it is working or no");
		return "checking webhook working or no";
	}

}
