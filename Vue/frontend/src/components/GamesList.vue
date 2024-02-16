<template>
    <div class="list row">
      <div class="col-md-8">
        <div class="input-group mb-3">
          <input type="text" class="form-control" placeholder="Search by title"
            v-model="title"/>
          <div class="input-group-append">
            <button class="btn btn-outline-secondary" type="button"
              @click="searchTitle"
            >
              Search
            </button>
          </div>
        </div>
      </div>
      <div class="col-md-6">
        <h4>Games List</h4>
        <ul class="list-group">
          <li class="list-group-item"
            :class="{ active: index == currentIndex }"
            v-for="(game, index) in games"
            :key="index"
            @click="setActiveGame(game, index)"
          >
            {{ game.title }}
          </li>
        </ul>
  
        <button class="m-3 btn btn-sm btn-danger" @click="removeAllGames">
          Remove All
        </button>
      </div>
      <div class="col-md-6">
        <div v-if="currentGame">
          <h4>Game</h4>
          <div>
            <label><strong>Title:</strong></label> {{ currentGame.title }}
          </div>
          <div>
            <label><strong>Description:</strong></label> {{ currentGame.description }}
          </div>
          <div>
            <label><strong>Status:</strong></label> {{ currentGame.published ? "Published" : "Pending" }}
          </div>
  
          <router-link :to="'/games/' + currentGame.id" class="badge badge-warning">Edit</router-link>
        </div>
        <div v-else>
          <br />
          <p>Please click on a Game...</p>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  import GameDataService from "../services/GameDataService";
  
  export default {
    name: "games-list",
    data() {
      return {
        games: [],
        currentGame: null,
        currentIndex: -1,
        title: ""
      };
    },
    methods: {
      retrieveGames() {
        GameDataService.getAll()
          .then(response => {
            this.games = response.data;
            console.log(response.data);
          })
          .catch(e => {
            console.log(e);
          });
      },
  
      refreshList() {
        this.retrieveGames();
        this.currentGame = null;
        this.currentIndex = -1;
      },
  
      setActiveGame(game, index) {
        this.currentGame = game;
        this.currentIndex = game ? index : -1;
      },
  
      removeAllGames() {
        GameDataService.deleteAll()
          .then(response => {
            console.log(response.data);
            this.refreshList();
          })
          .catch(e => {
            console.log(e);
          });
      },
      
      searchTitle() {
        GameDataService.findByTitle(this.title)
          .then(response => {
            this.games = response.data;
            this.setActiveGame(null);
            console.log(response.data);
          })
          .catch(e => {
            console.log(e);
          });
      }
    },
    mounted() {
      this.retrieveGames();
    }
  };
  </script>
  
  <style>
  .list {
    text-align: left;
    max-width: 750px;
    margin: auto;
  }
  </style>
  