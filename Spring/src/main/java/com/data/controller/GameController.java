package com.data.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import com.data.model.Game;
import com.data.repository.GameRepository;

@CrossOrigin(origins = {"http://localhost:8081", "http://localhost:8090"})
@RestController
@RequestMapping("/api")
public class GameController {

  @Autowired
  GameRepository gameRepository;


  @GetMapping("/games")
  public ResponseEntity<List<Game>> getAllGame(@RequestParam(required = false) String title) {
    List<Game> games = new ArrayList<Game>();
    try {
      if (title == null){
        gameRepository.findAll().forEach(games::add);
      }else{
        gameRepository.findByTitleContaining(title).forEach(games::add);
      }

      if (games.isEmpty()){
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
      return new ResponseEntity<>(games, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }  
    
  }
  
  @GetMapping("/games/{id}")
  public ResponseEntity<Game> getGameById(@PathVariable("id") long id) {
    Optional<Game> gameData = gameRepository.findById(id);
    if (gameData.isPresent()){
      return new ResponseEntity<>(gameData.get(), HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
  
  @PostMapping("/games")
  public ResponseEntity<Game> createGame(@RequestBody Game game) {
    try {
//      Game _game = );      
      return new ResponseEntity<>(gameRepository.save(new Game(game)), HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  
  @PutMapping("/games/{id}")
  public ResponseEntity<Game> updateGame(@PathVariable("id") long id, @RequestBody Game game) {
    Optional<Game> gameData = gameRepository.findById(id);
    if (gameData.isPresent()){
      Game _game = gameData.get();
      _game.setGame(game);
      return new ResponseEntity<>(gameRepository.save(_game), HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @DeleteMapping("/games/{id}")
  public ResponseEntity<HttpStatus> deleteGame(@PathVariable("id") long id) {
    try{
      gameRepository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e){
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  
  @DeleteMapping("/games")
  public ResponseEntity<HttpStatus> deleteAllGame() {
    try{
      gameRepository.deleteAll();
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e){
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/games/published")
  public ResponseEntity<List<Game>> findByPublished(@RequestBody boolean published) {
    try {
      List <Game> games = gameRepository.findByPublished(published);
      if (games.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
      return new ResponseEntity<>(games, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
