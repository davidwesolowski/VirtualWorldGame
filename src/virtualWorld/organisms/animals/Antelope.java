package virtualWorld.organisms.animals;

import virtualWorld.Options;
import virtualWorld.World;
import virtualWorld.organisms.Organisms;

import java.util.Random;

public class Antelope extends Animals
{
    public Antelope(int posX,int posY,int age)
    {
        this.positionX=posX;
        this.positionY=posY;
        this.age=age;
        this.force=4;
        this.initiative=4;
        this.speed=2;
        this.symbol='A';
        this.name="Antelope";
    }
    @Override
    public Organisms newOrganism(int posX, int posY) {
        return new Antelope(posX,posY,0);
    }

    @Override
    public boolean bounceAttack(Organisms organisms, World world)
    {
        Random randomize=new Random();
        int capabaleEscape=randomize.nextInt(100);
        if(capabaleEscape<=50)
        {
            if(getPositionY()>0 && world.getBoard()[getPositionY()-1][getPositionX()]==Options.FREEFIELD)
            {
               world.getBoard()[getPositionY()][getPositionX()]=Options.FREEFIELD;
               moveYUp();
               world.getBoard()[getPositionY()][getPositionX()]=Options.ANTELOPEFIELD;
            }
            else if (getPositionY()<world.getBoardHeight()-1 && world.getBoard()[getPositionY()+1][getPositionX()]==Options.FREEFIELD)
            {
                world.getBoard()[getPositionY()][getPositionX()]=Options.FREEFIELD;
                moveYDown();
                world.getBoard()[getPositionY()][getPositionX()]=Options.ANTELOPEFIELD;
            }
            else if (getPositionX()>0 && world.getBoard()[getPositionY()][getPositionX()-1]==Options.FREEFIELD)
            {
                world.getBoard()[getPositionY()][getPositionX()]=Options.FREEFIELD;
                moveXLeft();
                world.getBoard()[getPositionY()][getPositionX()]=Options.ANTELOPEFIELD;
            }
            else if (getPositionX()<world.getBoardWidth()-1 && world.getBoard()[getPositionY()][getPositionX()+1]==Options.FREEFIELD)
            {
                world.getBoard()[getPositionY()][getPositionX()]=Options.FREEFIELD;
                moveXRight();
                world.getBoard()[getPositionY()][getPositionX()]=Options.ANTELOPEFIELD;
            }
            return true;
        }
        else if(organisms.getForce()>=getForce())
            return false;
        else
            return true;
    }
}
