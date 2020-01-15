package virtualWorld.organisms.plants;

import virtualWorld.World;
import virtualWorld.organisms.Organisms;

public class Dandelion extends Plants
{
    public Dandelion(int posX,int posY)
    {
        this.positionX=posX;
        this.positionY=posY;
        this.age=0;
        this.force=0;
        this.initiative=0;
        this.speed=0;
        this.symbol='M';
        this.name="Dandelion";
    }

    @Override
    public Organisms newOrganism(int posX, int posY) {
        return new Dandelion(posX,posY);
    }

    @Override
    public void action(char board[][], World world, int n, int m, int direction)
    {
        for (int i=0;i<3;i++)
        {
            super.action(board,world,n,m,direction);
        }
    }

}
