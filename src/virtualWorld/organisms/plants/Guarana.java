package virtualWorld.organisms.plants;

import virtualWorld.World;
import virtualWorld.organisms.Organisms;

public class Guarana extends Plants
{
    public Guarana(int posX, int posY)
    {
        this.positionX=posX;
        this.positionY=posY;
        this.age=0;
        this.force=0;
        this.initiative=0;
        this.speed=0;
        this.symbol='G';
        this.name="Guarana";
    }

    @Override
    public Organisms newOrganism(int posX, int posY) {
        return new Guarana(posX,posY);
    }

    @Override
    public boolean bounceAttack(Organisms organisms, World world)
    {
        organisms.setForce();
        return false;
    }
}
