package com.musatov.waterworld;

class Entity {
    int lifetime;
    boolean life;
    float today;
    final public String name = "";
    Entity(int lifetime, float today){
        this.lifetime = lifetime;
        life = false;
        this.today = today;
    }
    Entity(){
        lifetime = 0;
        life = false;
        today = 0;
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

    public String getName() {
        return name;
    }

    public float getToday() {
        return today;
    }

    public void setToday(float today) {
        this.today = today;
    }
}
