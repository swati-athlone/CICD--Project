package edu.tus.winemanager.controllers;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired
	WineRepository wineRepo;

	@Autowired
	WineValidator wineValidator;

	private static Logger log = LoggerFactory.getLogger(WineService.class);

	@GetMapping("/wines")
	public List<Wine> findAllWines(){
		List<Wine> wines = wineRepo.findAll();
		return wines;
	}


	@PostMapping("/wines")
	public ResponseEntity createWine(@Valid @RequestBody Wine wine) {
		try {
			wineValidator.validateWine(wine);
			log.info("Validated wines");
			Wine savedWine=wineRepo.save(wine);
			log.info("Saved wines");
			return ResponseEntity.status(HttpStatus.CREATED).body(savedWine);
		}catch(WineException e) {
			ErrorMessage errorMessage=new ErrorMessage(e.getMessage());
			return ResponseEntity.badRequest().body(errorMessage);
		}
	}
	public void show(){
		log.info("checking webhook");
	}

}
