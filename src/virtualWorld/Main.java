package virtualWorld;

import virtualWorld.organisms.animals.*;
import virtualWorld.organisms.plants.*;


public class Main
{
    public static void main(String[] args)
    {
        World game=new World(12,10);
        game.pushOrganism(new Wolf(0,0,0));
        game.pushOrganism(new Human(4,1,0));
        game.pushOrganism(new Fox(9,6,0));
        game.pushOrganism(new DeadlyNightshade(3,5));
        game.pushOrganism(new SosnowskyHogweed(4,4));
        game.pushOrganism(new DeadlyNightshade(4,6));
        game.pushOrganism(new Grass(7,5));
        game.pushOrganism(new Dandelion(11,3));
        game.pushOrganism(new Sheep(0,6,0));
        game.pushOrganism(new Turtle(1,9,0));
        game.displayBoard();
    }
}
