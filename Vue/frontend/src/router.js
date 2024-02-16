import { createWebHistory, createRouter } from "vue-router";

const routes =  [
  {
    path: "/",
    alias: "/games",
    name: "games",
    component: () => import("./components/GamesList")
  },
  {
    path: "/games/:id",
    name: "game-details",
    component: () => import("./components/GameData")
  },
  {
    path: "/add",
    name: "add",
    component: () => import("./components/AddGame")
  }
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
