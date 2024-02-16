<template>
    <div class="submit-form">
      <div v-if="!submitted">
        <div class="form-group">
          <label for="title">Title</label>
          <input
            type="text"
            class="form-control"
            id="title"
            required
            v-model="game.title"
            name="title"
          />
        </div>
  
        <div class="form-group">
          <label for="description">Description</label>
          <input
            class="form-control"
            id="description"
            required
            v-model="game.description"
            name="description"
          />
        </div>
  
        <button @click="newGame" class="btn btn-success">Submit</button>
      </div>
  
      <div v-else>
        <h4>You submitted successfully!</h4>
        <button class="btn btn-success" @click="saveGame">Add</button>
      </div>
    </div>
  </template>
  
  <script>
import axios from "axios";
import GameDataService from "../services/GameDataService";
  
  export default {
    name: "add-game",
    data() {
      return {
        game: {
          id: null,
          title: "",
          description: "",
          published: false
        },
        submitted: false
      };
    },
    methods: {
      saveGame() {
        var data = {
          title: this.game.title,
          description: this.game.description
        };
  
        GameDataService.create(data)
          .then(response => {
            this.game.id = response.data.id;
            console.log(response.data);
            this.submitted = true;
          })
          .catch(e => {
            console.log(e);
          });
      },
      
      newGame() {
        const newGame = {
            title: this.game.title,
            description: this.game.description,
            published: this.game.published,
        }
        this.submitted = false;
        this.game = {};
        axios.post('http://localhost:8080/api/games', newGame)
        .then(response => 
        {
            this.$refs.
            this.submitted = true;
            GameDataService.create(response.data)
            
        })
        .catch(error => {
            this.errorMessage = error.message;
            console.error("There was an error!", error);
        });
      }
    }
  };
  </script>
  
  <style>
  .submit-form {
    max-width: 300px;
    margin: auto;
  }
  </style>
  