package edu.tus.winemanager.controllers;

import java.util.List;

import javax.validation.Valid;

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
	public ResponseEntity createWine(@Valid @RequestBody Wine wine) {
		try {
			wineValidator.validateWine(wine);
			log.info("Validated wines in Database");
			Wine savedWine=wineRepo.save(wine);
			log.info("Saved wines in Database");
			return ResponseEntity.status(HttpStatus.CREATED).body(savedWine);
		}catch(WineException e) {
			ErrorMessage errorMessage=new ErrorMessage(e.getMessage());
			return ResponseEntity.badRequest().body(errorMessage);
		}
	}

	@GetMapping("/show")
	public String show(){
		log.info("checking webhook if it is working or no!!!");
		return "checking webhook working or no";
	}

}
