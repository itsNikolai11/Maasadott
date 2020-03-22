package no.nkopperudmoen.måsadott.Rover;

import java.io.Serializable;

public class PlayerTime implements Serializable {
    private int min;

    public PlayerTime() {
        min = 0;
    }

    public int getMin() {
        return min;
    }

    public void setTime(int min) {
        this.min = min;
    }

    public void increaseTime() {
        this.min += 5;
    }
}
