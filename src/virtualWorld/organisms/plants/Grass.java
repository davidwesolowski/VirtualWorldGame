package virtualWorld.organisms.plants;

import virtualWorld.organisms.Organisms;

public class Grass extends Plants
{
    public Grass(int posX,int posY)
    {
        this.positionX=posX;
        this.positionY=posY;
        this.age=0;
        this.force=0;
        this.initiative=0;
        this.speed=0;
        this.symbol='T';
        this.name="Grass";
    }

    @Override
    public Organisms newOrganism(int posX, int posY) {
        return new Grass(posX,posY);
    }
}
