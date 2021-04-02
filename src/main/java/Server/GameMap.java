package Server;

import java.util.ArrayList;

public class GameMap {
    private final int size=3;
    private String map;
    private ArrayList<String> mapOfGame;

    public ArrayList<String> getMapOfGame() {
        return mapOfGame;
    }

    public GameMap(){
        mapOfGame= new ArrayList<String>();
        for(int i=0;i<size;i++){
            this.mapOfGame.add("000");
        }
    }



    private boolean checkRow(String str){
        char[] charArray = str.toCharArray();
        for(int i=0;i<charArray.length-1;i++){
            if (charArray[i] != charArray[i+1] || charArray[i]=='0') {
                return false;
            }
        }

        return true;
    }




    public boolean endOfGame(){
        for(int i=0;i<size;i++){

            // провкеряем строки
            if(checkRow(mapOfGame.get(i))){

                // todo: выявление победителя
                return true;
            }


            // проверям колонки
            // todo: написать для возможности заменить size  поля

            StringBuffer strColumn = new StringBuffer();
            strColumn.append(mapOfGame.get(0).charAt(i));
            strColumn.append(mapOfGame.get(1).charAt(i));
            strColumn.append(mapOfGame.get(2).charAt(i));

            if(checkRow(strColumn.toString())) {
                return true;
            }

        }
        // проверяем диагональ
        StringBuffer str2 = new StringBuffer();
        str2.append(mapOfGame.get(0).charAt(0));
        str2.append(mapOfGame.get(1).charAt(1));
        str2.append(mapOfGame.get(2).charAt(2));
        StringBuffer str3 = new StringBuffer();
        str3.append(mapOfGame.get(0).charAt(2));
        str3.append(mapOfGame.get(1).charAt(1));
        str3.append(mapOfGame.get(2).charAt(0));

        if( checkRow(str2.toString()) || checkRow(str3.toString()))
        {
            return true;
        }
        else return  false;

    }

    public void setPozition(int x, int y, int number){
       StringBuffer str = new StringBuffer(mapOfGame.get(x-1));
       str.setCharAt(y-1,(char)(number+'0'));
       mapOfGame.set(x-1,str.toString());
    }

    public void outOnScreenGameMap(){
        for(int i=0;i<size;i++){
            System.out.println(mapOfGame.get(i));
        }
    }
}
