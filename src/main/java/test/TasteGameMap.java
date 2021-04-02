package test;


import Server.GameMap;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class TasteGameMap {
    private ArrayList<String> first = new ArrayList<String>();
    private ArrayList<String> second = new ArrayList<String>();
    private GameMap gameMap;



    @Test
    public void testSetPozition(){
        gameMap= new GameMap();
        Assert.assertEquals(gameMap.getMapOfGame().get(1),"000");
        gameMap.setPozition(1,1,2);
        Assert.assertEquals(gameMap.getMapOfGame().get(0),"200");
    }

    @Test
    public void testCheckRows(){
        gameMap = new GameMap();

        // test by row
        Assert.assertEquals(gameMap.endOfGame(),false);
        gameMap.setPozition(1,1,2);
        gameMap.setPozition(1,2,2);
        gameMap.setPozition(1,3,2);

        Assert.assertEquals(gameMap.endOfGame(),true);

        gameMap.setPozition(1,1,0);
        Assert.assertEquals(gameMap.endOfGame(),false);


        gameMap.setPozition(2,1,1);
        gameMap.setPozition(2,2,1);
        gameMap.setPozition(2,3,1);
        Assert.assertEquals(gameMap.endOfGame(),true);
        gameMap.setPozition(2,2,0);
        Assert.assertEquals(gameMap.endOfGame(),false);

        //test by column
        gameMap.setPozition(1,2,2);
        gameMap.setPozition(2,2,2);
        gameMap.setPozition(3,2,2);
        gameMap.outOnScreenGameMap();
        Assert.assertEquals(gameMap.endOfGame(),true);

        //test diagonal
        gameMap.setPozition(2,1,0);
        Assert.assertEquals(gameMap.endOfGame(),true);


    }


}
