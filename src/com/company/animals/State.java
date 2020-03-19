package com.company.animals;

public enum State {
    DEAD(false),
    ALIVE(true);

    public boolean isAlive;

    State(boolean isAlive) {
        this.isAlive = isAlive;
    }
}
