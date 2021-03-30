package Dto;

public class Step {
    private String name;
    private String dtime;
    private int x;
    private int y;

    public Step(String name, String dtime, int x, int y) {
        this.name = name;
        this.dtime = dtime;
        this.x = x;
        this.y = y;
    }

    public Step() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDtime() {
        return dtime;
    }

    public void setDtime(String dtime) {
        this.dtime = dtime;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
