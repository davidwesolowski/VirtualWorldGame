package virtualWorld.organisms.animals;

import virtualWorld.Options;
import virtualWorld.World;
import virtualWorld.organisms.Organisms;

import java.util.Random;

public class Fox extends Animals
{
    public Fox(int posX,int posY,int age)
    {
        this.positionX=posX;
        this.positionY=posY;
        this.age=age;
        this.force=3;
        this.speed=1;
        this.initiative=7;
        this.symbol='L';
        this.name="Fox";
    }

    @Override
    public Organisms newOrganism(int posX, int posY) {
        return new Fox(posX,posY,0);
    }

    @Override
    public void action(char board[][], World world, int n, int m, int direction)
    {
        int i;
        Random randomize=new Random();
        int option=randomize.nextInt(4);

        if (option==Options.RIGHT && getPositionX()<m-1)
        {
            i=identityOrganism(getPositionX()+1,getPositionY(),world);
            if(i>=0 && getForce()<world.getOrganisms().get(i).getForce());
            else if (collision(board,world,Options.RIGHT));
            else if(board[getPositionY()][getPositionX()+1]== Options.FREEFIELD)
                move(board,Options.RIGHT);
        }
        else if (option==Options.LEFT && getPositionX()>0)
        {
            i=identityOrganism(getPositionX()-1,getPositionY(),world);
            if(i>=0 && getForce()<world.getOrganisms().get(i).getForce());
            else if (collision(board,world,Options.LEFT));
            else if(board[getPositionY()][getPositionX()-1]==Options.FREEFIELD)
                move(board,Options.LEFT);
        }
        else if (option==Options.UP && getPositionY()>0)
        {
            i=identityOrganism(getPositionX(),getPositionY()-1,world);
            if(i>=0 && getForce()<world.getOrganisms().get(i).getForce());
            else if (collision(board,world,Options.UP));
            else if(board[getPositionY()-1][getPositionX()]==Options.FREEFIELD)
                move(board,Options.UP);
        }
        else if (option==Options.DOWN && getPositionY()<n-1)
        {
            i=identityOrganism(getPositionX(),getPositionY()+1,world);
            if(i>=0 && getForce()<world.getOrganisms().get(i).getForce());
            else if (collision(board,world,Options.DOWN));
            else if(board[getPositionY()+1][getPositionX()]==Options.FREEFIELD)
                move(board,Options.DOWN);
        }
    }
}
