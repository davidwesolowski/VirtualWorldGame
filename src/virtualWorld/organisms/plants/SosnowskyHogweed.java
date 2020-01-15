package virtualWorld.organisms.plants;

import virtualWorld.World;
import virtualWorld.organisms.Organisms;

public class SosnowskyHogweed extends Plants
{
    public SosnowskyHogweed(int posX,int posY)
    {
        this.positionX=posX;
        this.positionY=posY;
        this.age=0;
        this.force=10;
        this.initiative=0;
        this.speed=0;
        this.symbol='B';
        this.name="Sosnowsky's hogweed";
    }
    @Override
    public Organisms newOrganism(int posX, int posY) {
        return new SosnowskyHogweed(posX,posY);
    }

    @Override
    public boolean bounceAttack(Organisms organisms, World world)
    {
        return true;
    }
}
