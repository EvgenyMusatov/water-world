package com.musatov.waterworld;

class Entity {
    int lifetime;
    boolean life;
    Entity(int lifetime){
        this.lifetime = lifetime;
        life = true;
    }

    public int getLifetime() {
        return lifetime;
    }

    public void setLifetime(int lifetime) {
        this.lifetime = lifetime;
    }

    public boolean isLife() {
        return life;
    }
}
