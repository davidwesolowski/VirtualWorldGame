package virtualWorld.organisms.animals;

import virtualWorld.organisms.Organisms;

public class Sheep extends Animals
{
    public Sheep(int posX,int posY, int age)
    {
        this.positionX=posX;
        this.positionY=posY;
        this.age=age;
        this.force=4;
        this.initiative=4;
        this.speed=1;
        this.symbol='O';
        this.name="Sheep";
    }

    @Override
    public Organisms newOrganism(int posX, int posY)
    {
        return new Sheep(posX,posY,0);
    }
}
