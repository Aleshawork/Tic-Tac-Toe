package Dto;

import java.util.ArrayList;

public class MapDto {

    private ArrayList<String> mapOfGame;

    public void outOnScreenGameMap(){
        System.out.println("______");
        for(String el: mapOfGame){
            System.out.println(String.format("|%c|%c|%c|",el.charAt(0),el.charAt(1),el.charAt(2)));

        }
        System.out.println("------");
    }

    public MapDto(ArrayList<String> mapOfGame) {
        this.mapOfGame = mapOfGame;
    }

    public ArrayList<String> getMapOfGame() {
        return mapOfGame;
    }

    public void setMapOfGame(ArrayList<String> mapOfGame) {
        this.mapOfGame = mapOfGame;
    }
}
