package Dto;

import com.google.gson.annotations.SerializedName;

public class Step {

    @SerializedName("name")
    private String name;

    @SerializedName("dtime")
    private String dtime;

    @SerializedName("x")
    private int x;

    @SerializedName("y")
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

    @Override
    public String toString() {
        return String.format("name:%s  dtime:%s  x:%d  y:%d", name, dtime,x,y);
    }
}
