package com.musatov.waterworld;

class Penguin extends Entity{
    final public String name = "Penguin";
    boolean life;
    float today;
    Penguin(int lifetime, float today) {
        super(lifetime, today);
        life = true;

    }

    @Override
    public boolean isLife() {
        return super.isLife();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public float getToday() {
        return today;
    }

    @Override
    public void setToday(float today) {
        this.today = today;
    }
}
