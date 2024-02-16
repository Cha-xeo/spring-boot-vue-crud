package com.data.model;

import jakarta.persistence.*; // for Spring Boot 3

@Entity
@Table(name = "Game")
public class Game {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "title")
	private String title;

	@Column(name = "description")
	private String description;

	@Column(name = "published")
	private boolean published;


	public Game() {

	}

	public Game(String title, String description, boolean published){
		this.title = title;
		this.description = description;
		this.published = published;
	}

	public Game(Game game){
		this.title = game.title;
		this.description = game.description;
		this.published = game.published;
	}

	public void setGame(Game game){
		this.title = game.title;
		this.description = game.description;
		this.published = game.published;
	}

	public long getId(){
		return id;
	}

	public String getTitle(){
		return title;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getDescription(){
		return description;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public boolean isPublished(){
		return published;
	}

	public void setPublished(boolean published){
		this.published = published;
	}

	@Override
	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append("Game [id=").append(id).append(", title=").append(title).append(", desc").append(description).append(", published").append(published);
		return builder.toString();
	}
}
