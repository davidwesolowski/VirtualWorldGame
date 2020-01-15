package virtualWorld.organisms.animals;

import virtualWorld.World;
import virtualWorld.organisms.Organisms;

import java.util.Random;

public class Turtle extends Animals
{
    public Turtle(int posX,int posY, int age)
    {
        this.positionX=posX;
        this.positionY=posY;
        this.age=age;
        this.force=2;
        this.initiative=1;
        this.speed=1;
        this.symbol='Z';
        this.name="Turtle";
    }
    @Override
    public Organisms newOrganism(int posX, int posY)
    {
        return new Turtle(posX,posY,0);
    }

    @Override
    public void action(char board[][], World world, int n, int m, int direction)
    {
        Random randomize=new Random();
        int probability=randomize.nextInt(100)+1;
        if(probability<25)
        {
            super.action(board,world,n,m,direction);
        }
    }

    @Override
    public boolean bounceAttack(Organisms organisms,World world)
    {
        if(organisms.getForce()<5)
            return true;
        return false;
    }

}
