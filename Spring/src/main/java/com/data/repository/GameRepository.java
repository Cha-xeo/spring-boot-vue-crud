package com.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.data.model.Game;

public interface GameRepository extends JpaRepository<Game, Long> {
  List<Game> findByPublished(boolean published);
  List<Game> findByTitleContaining(String title);
}