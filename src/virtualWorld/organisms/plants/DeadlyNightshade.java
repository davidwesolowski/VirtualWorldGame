package virtualWorld.organisms.plants;

import virtualWorld.World;
import virtualWorld.organisms.Organisms;

public class DeadlyNightshade extends Plants
{
    public DeadlyNightshade(int posX,int posY)
    {
        this.positionX=posX;
        this.positionY=posY;
        this.age=0;
        this.force=99;
        this.initiative=0;
        this.speed=0;
        this.symbol='J';
        this.name="Deadly nightshade";
    }
    @Override
    public Organisms newOrganism(int posX, int posY) {
        return new DeadlyNightshade(posX,posY);
    }

    @Override
    public boolean bounceAttack(Organisms organisms, World world)
    {
        return true;
    }
}
