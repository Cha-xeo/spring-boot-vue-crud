<template>
    <div v-if="currentGame" class="edit-form">
      <h4>Game</h4>
      <form>
        <div class="form-group">
          <label for="title">Title</label>
          <input type="text" class="form-control" id="title"
            v-model="currentGame.title"
          />
        </div>
        <div class="form-group">
          <label for="description">Description</label>
          <input type="text" class="form-control" id="description"
            v-model="currentGame.description"
          />
        </div>
  
        <div class="form-group">
          <label><strong>Status:</strong></label>
          {{ currentGame.published ? "Published" : "Pending" }}
        </div>
      </form>
  
      <button class="badge badge-primary mr-2"
        v-if="currentGame.published"
        @click="updatePublished(false)"
      >
        UnPublish
      </button>
      <button v-else class="badge badge-primary mr-2"
        @click="updatePublished(true)"
      >
        Publish
      </button>
  
      <button class="badge badge-danger mr-2"
        @click="deleteGame"
      >
        Delete
      </button>
  
      <button type="submit" class="badge badge-success"
        @click="updateGame"
      >
        Update
      </button>
      <p>{{ message }}</p>
    </div>
  
    <div v-else>
      <br />
      <p>Please click on a Game...</p>
    </div>
  </template>
  
  <script>
  import GameDataService from "../services/GameDataService";
  
  export default {
    name: "gameData",
    data() {
      return {
        currentGame: null,
        message: ''
      };
    },
    methods: {
      getGame(id) {
        GameDataService.get(id)
          .then(response => {
            this.currentGame = response.data;
            console.log(response.data);
          })
          .catch(e => {
            console.log(e);
          });
      },
  
      updatePublished(status) {
        var data = {
          id: this.currentGame.id,
          title: this.currentGame.title,
          description: this.currentGame.description,
          published: status
        };
  
        GameDataService.update(this.currentGame.id, data)
          .then(response => {
            console.log(response.data);
            this.currentGame.published = status;
            this.message = 'The status was updated successfully!';
          })
          .catch(e => {
            console.log(e);
          });
      },
  
      updateGame() {
        GameDataService.update(this.currentGame.id, this.currentGame)
          .then(response => {
            console.log(response.data);
            this.message = 'The game was updated successfully!';
          })
          .catch(e => {
            console.log(e);
          });
      },
  
      deleteGame() {
        GameDataService.delete(this.currentGame.id)
          .then(response => {
            console.log(response.data);
            this.$router.push({ name: "games" });
          })
          .catch(e => {
            console.log(e);
          });
      }
    },
    mounted() {
      this.message = '';
      this.getGame(this.$route.params.id);
    }
  };
  </script>
  
  <style>
  .edit-form {
    max-width: 300px;
    margin: auto;
  }
  </style>
  