package virtualWorld.organisms.plants;

import virtualWorld.Options;
import virtualWorld.World;
import virtualWorld.organisms.NewOrganismInterface;
import virtualWorld.organisms.Organisms;

import java.util.Random;

public abstract class Plants extends Organisms implements NewOrganismInterface
{
    @Override
    public void action(char board[][], World world, int n, int m, int direction)
    {
        Random randomize=new Random();
        int option=randomize.nextInt(4);
        int newPlant=randomize.nextInt(100);
        int i=identityOrganism(getPositionX(),getPositionY(),world);
        if(newPlant<2)        {
            if(option==Options.RIGHT && getPositionX()<m-1)
            {
                if(board[getPositionY()][getPositionX()+1]==Options.FREEFIELD)
                {
                    world.pushOrganism(newOrganism(getPositionX()+1,getPositionY()));
                    world.commentator(world.getOrganisms().get(i),Options.BREEDING);
                }
            }
            else if (option==Options.LEFT && getPositionX()>0)
            {
                if(board[getPositionY()][getPositionX()-1]==Options.FREEFIELD)
                {
                    world.pushOrganism(newOrganism(getPositionX() - 1, getPositionY()));
                    world.commentator(world.getOrganisms().get(i),Options.BREEDING);
                }
            }
            else if (option==Options.UP && getPositionY()>0)
            {
                if(board[getPositionY()-1][getPositionX()]==Options.FREEFIELD)
                {
                    world.pushOrganism(newOrganism(getPositionX(), getPositionY() - 1));
                    world.commentator(world.getOrganisms().get(i),Options.BREEDING);
                }
            }
            else if (option==Options.DOWN && getPositionY()<n-1)
            {
                if(board[getPositionY()+1][getPositionX()]==Options.FREEFIELD)
                {
                    world.pushOrganism(newOrganism(getPositionX(),getPositionY()+1));
                    world.commentator(world.getOrganisms().get(i),Options.BREEDING);
                }
            }
        }
    }

    @Override
    public boolean collision(char board[][], World world, int option)
    {
        return true;
    }

    @Override
    public boolean bounceAttack(Organisms organisms,World world)
    {
        return false;
    }
}
