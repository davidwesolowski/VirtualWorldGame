package virtualWorld.organisms.animals;

import virtualWorld.organisms.Organisms;

public class Wolf extends Animals
{
    public Wolf(int posX, int posY,int age)
    {
        this.positionX=posX;
        this.positionY=posY;
        this.age=age;
        this.force=9;
        this.initiative=5;
        this.speed=1;
        this.symbol='W';
        this.name="Wolf";
    }

    @Override
    public Organisms newOrganism(int posX, int posY)
    {
        return new Wolf(posX,posY,0);
    }
}
