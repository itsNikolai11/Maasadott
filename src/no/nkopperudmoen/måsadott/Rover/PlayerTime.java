package no.nkopperudmoen.måsadott.Rover;

public class PlayerTime {
    private int min;

    public PlayerTime() {

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
