package virtualWorld.organisms.animals;

import virtualWorld.Options;
import virtualWorld.World;
import virtualWorld.organisms.NewOrganismInterface;
import virtualWorld.organisms.Organisms;
import virtualWorld.organisms.plants.Plants;

import java.util.Random;

public abstract class Animals extends Organisms implements NewOrganismInterface
{
    public void move(char board[][], int option)
    {
        if (option==Options.RIGHT || option==Options.RIGHT_KEY)
        {
            board[getPositionY()][getPositionX()]=Options.FREEFIELD;
            for(int i=0;i<speed;i++)
            {
                moveXRight();
            }
            board[getPositionY()][getPositionX()]=getSymbol();
        }
        else if (option==Options.LEFT || option==Options.LEFT_KEY)
        {
            board[getPositionY()][getPositionX()]=Options.FREEFIELD;
            for(int i=0;i<speed;i++)
            {
                moveXLeft();
            }
            board[getPositionY()][getPositionX()]=getSymbol();
        }
        else if (option==Options.UP || option==Options.UP_KEY)
        {
            board[getPositionY()][getPositionX()]=Options.FREEFIELD;
            for(int i=0;i<speed;i++)
            {
                moveYUp();
            }
            board[getPositionY()][getPositionX()]=getSymbol();
        }
        else if (option==Options.DOWN || option==Options.DOWN_KEY)
        {
            board[getPositionY()][getPositionX()]=Options.FREEFIELD;
            for(int i=0;i<speed;i++)
            {
                moveYDown();
            }
            board[getPositionY()][getPositionX()]=getSymbol();
        }
    }
    @Override
    public void action(char board[][], World world, int n, int m, int direction)
    {
        Random randomize=new Random();
        int option=randomize.nextInt(4);
        if (option==Options.RIGHT && getPositionX()<m-speed)
        {
            if(collision(board,world,Options.RIGHT));
            else if(board[getPositionY()][getPositionX()+speed]==Options.FREEFIELD)
                move(board,Options.RIGHT);
        }
        else if (option==Options.LEFT && getPositionX()-speed+1>0)
        {
            if(collision(board,world,Options.LEFT));
            else if(board[getPositionY()][getPositionX()-speed]==Options.FREEFIELD)
                move(board,Options.LEFT);
        }
        else if (option==Options.UP && getPositionY()-speed+1>0)
        {
            if(collision(board,world,Options.UP));
            else if(board[getPositionY()-speed][getPositionX()]==Options.FREEFIELD)
                move(board,Options.UP);
        }
        else if (option==Options.DOWN && getPositionY()<n-speed)
        {
            if(collision(board,world, Options.DOWN));
            else if(board[getPositionY()+speed][getPositionX()]==Options.FREEFIELD)
                move(board,Options.DOWN);
        }
    }

    public void fight(char board[][],World world, int i,int option)
    {
        int indexAttacker=identityOrganism(getPositionX(),getPositionY(),world);
        Organisms organismFirst=world.getOrganisms().get(i);
        Organisms organismsSecond=world.getOrganisms().get(indexAttacker);

        if(world.getOrganisms().get(i) instanceof Animals)
        {
            if(!(world.getOrganisms().get(i).bounceAttack(world.getOrganisms().get(indexAttacker),world)))
            {
                world.popOrganism(i);
                move(board,option);
                world.commentator(organismFirst,organismsSecond, Options.FIGHTING);
            }
            else if(this.bounceAttack(world.getOrganisms().get(i),world))
            {
                world.popOrganism(indexAttacker);
                world.commentator(organismsSecond,organismFirst, Options.FIGHTING);
            }
        }
        else if (world.getOrganisms().get(i) instanceof Plants)
        {
            if(world.getOrganisms().get(i).bounceAttack(world.getOrganisms().get(indexAttacker),world))
            {
                world.popOrganism(i);
                world.popOrganism(indexAttacker);
                world.commentator(organismFirst,organismsSecond,Options.FIGHTING);
            }
            else
            {
                world.popOrganism(i);
                move(board,option);
                world.commentator(organismFirst,organismsSecond,Options.FIGHTING);
            }
        }
    }

    @Override
    public boolean bounceAttack(Organisms organisms, World world)
    {
        if(organisms.getForce()>=getForce())
            return false;
        return true;
    }

    public boolean isSameOrganism(char board[][],int x, int y)
    {
        if(board[y][x]==getSymbol())
            return true;
        return false;
    }

    @Override
    public boolean collision(char board[][], World world, int option)
    {
        if(option==Options.RIGHT || option==Options.RIGHT_KEY)
        {
            if(getPositionX()<world.getBoardWidth()-speed && isSameOrganism(board,getPositionX()+speed,getPositionY()))
            {
                if(getPositionY()>1 && board[getPositionY()-1][getPositionX()]==Options.FREEFIELD)
                {
                    world.pushOrganism(newOrganism(getPositionX(),getPositionY()-1));
                    return true;
                }
                else if (getPositionY()<world.getBoardHeight()-1 && board[getPositionY()+1][getPositionX()]==Options.FREEFIELD)
                {
                    world.pushOrganism(newOrganism(getPositionX(),getPositionY()+1));
                    return true;
                }
                else if (getPositionX()>1 && board[getPositionY()][getPositionX()-1]==Options.FREEFIELD)
                {
                    world.pushOrganism(newOrganism(getPositionX()-1,getPositionY()));
                    return true;
                }
                else if (getPositionX()<world.getBoardWidth()-1 && getPositionY()<world.getBoardHeight()-1 && board[getPositionY()+1][getPositionX()+1]==Options.FREEFIELD)
                {
                    world.pushOrganism(newOrganism(getPositionX()+1,getPositionY()+1));
                    return true;
                }
                else if (getPositionX()<world.getBoardWidth()-2 && board[getPositionY()][getPositionX()+2]==Options.FREEFIELD)
                {
                    world.pushOrganism(newOrganism(getPositionX()+2,getPositionY()));
                    return true;
                }
                else if (getPositionY()>1 && getPositionX()<world.getBoardWidth()-1 && board[getPositionY()-1][getPositionX()+1]==Options.FREEFIELD)
                {
                    world.pushOrganism(newOrganism(getPositionX()+1,getPositionY()-1));
                    return true;
                }
            }
            else
            {
                int j=identityOrganism(getPositionX()+speed,getPositionY(), world);
                if(j>=0)
                {
                    fight(board,world,j,Options.RIGHT);
                    return true;
                }
            }
        }
        else if(option==Options.LEFT || option==Options.LEFT_KEY)
        {
            if(getPositionX()>speed-1 && isSameOrganism(board,getPositionX()-speed,getPositionY()))
            {
                if(getPositionY()>1 && board[getPositionY()-1][getPositionX()]==Options.FREEFIELD)
                {
                    world.pushOrganism(newOrganism(getPositionX(),getPositionY()-1));
                    return true;
                }
                else if (getPositionY()<world.getBoardHeight()-1 && board[getPositionY()+1][getPositionX()]==Options.FREEFIELD)
                {
                    world.pushOrganism(newOrganism(getPositionX(),getPositionY()+1));
                    return true;
                }
                else if (getPositionX()<world.getBoardWidth()-1 && board[getPositionY()][getPositionX()+1]==Options.FREEFIELD)
                {
                    world.pushOrganism(newOrganism(getPositionX()+1,getPositionY()));
                    return true;
                }
                else if (getPositionY()<world.getBoardHeight()-1 && getPositionX()>1 && board[getPositionY()+1][getPositionX()-1]==Options.FREEFIELD)
                {
                    world.pushOrganism(newOrganism(getPositionX()-1,getPositionY()+1));
                    return true;
                }
                else if (getPositionX()>2 && board[getPositionY()][getPositionX()-2]==Options.FREEFIELD)
                {
                    world.pushOrganism(newOrganism(getPositionX()-2,getPositionY()));
                    return true;
                }
                else if (getPositionY()>1&& getPositionX()>1 && board[getPositionY()-1][getPositionX()-1]==Options.FREEFIELD)
                {
                    world.pushOrganism(newOrganism(getPositionX()-1,getPositionY()-1));
                    return true;
                }
            }
            else
            {
                int j=identityOrganism(getPositionX()-speed,getPositionY(), world);
                if(j>=0)
                {
                    fight(board,world,j,Options.LEFT);
                    return true;
                }
            }
        }
        else if (option==Options.UP || option==Options.UP_KEY)
        {
            if(getPositionY()>speed -1 && isSameOrganism(board,getPositionX(),getPositionY()-speed))
            {
                if(getPositionY()>2 && board[getPositionY()-2][getPositionX()]==Options.FREEFIELD)
                {
                    world.pushOrganism(newOrganism(getPositionX(),getPositionY()-2));
                    return true;
                }
                else if (getPositionY()<world.getBoardHeight()-1 && board[getPositionY()+1][getPositionX()]==Options.FREEFIELD)
                {
                    world.pushOrganism(newOrganism(getPositionX(),getPositionY()+1));
                    return true;
                }
                else if (getPositionX()>1 && board[getPositionY()][getPositionX()-1]==Options.FREEFIELD)
                {
                    world.pushOrganism(newOrganism(getPositionX()-1,getPositionY()));
                    return true;
                }
                else if (getPositionY()>1 && getPositionX()<world.getBoardWidth()-1 && board[getPositionY()-1][getPositionX()+1]==Options.FREEFIELD)
                {
                    world.pushOrganism(newOrganism(getPositionX()+1,getPositionY()-1));
                    return true;
                }
                else if (getPositionX()<world.getBoardWidth()-1 && board[getPositionY()][getPositionX()+1]==Options.FREEFIELD)
                {
                    world.pushOrganism(newOrganism(getPositionX()+1,getPositionY()));
                    return true;
                }
                else if (getPositionY()>1 && getPositionX()>1 && board[getPositionY()-1][getPositionX()-1]==Options.FREEFIELD)
                {
                    world.pushOrganism(newOrganism(getPositionX()-1,getPositionY()-1));
                    return true;
                }
            }
            else
            {
                int j=identityOrganism(getPositionX(),getPositionY()-speed, world);
                if(j>=0)
                {
                    fight(board,world,j,Options.UP);
                    return true;
                }
            }
        }
        else if (option==Options.DOWN || option==Options.DOWN_KEY)
        {
            if(getPositionY()<world.getBoardHeight()-speed && isSameOrganism(board,getPositionX(),getPositionY()+speed))
            {
                if(getPositionY()>1 && board[getPositionY()-1][getPositionX()]==Options.FREEFIELD)
                {
                    world.pushOrganism(newOrganism(getPositionX(),getPositionY()-1));
                    return true;
                }
                else if (getPositionY()<world.getBoardHeight()-2 && board[getPositionY()+2][getPositionX()]==Options.FREEFIELD)
                {
                    world.pushOrganism(newOrganism(getPositionX(),getPositionY()+2));
                    return true;
                }
                else if (getPositionX()>1 && board[getPositionY()][getPositionX()-1]==Options.FREEFIELD)
                {
                    world.pushOrganism(newOrganism(getPositionX()-1,getPositionY()));
                    return true;
                }
                else if (getPositionY()<world.getBoardHeight()-1 && getPositionX()>1 && board[getPositionY()+1][getPositionX()-1]==Options.FREEFIELD)
                {
                    world.pushOrganism(newOrganism(getPositionX()-1,getPositionY()+1));
                    return true;
                }
                else if (getPositionX()<world.getBoardWidth()-1 && board[getPositionY()][getPositionX()+1]==Options.FREEFIELD)
                {
                    world.pushOrganism(newOrganism(getPositionX()+1,getPositionY()));
                    return true;
                }
                else if (getPositionY()<world.getBoardHeight()-1 && getPositionX()<world.getBoardWidth()-1 && board[getPositionY()+1][getPositionX()+1]==Options.FREEFIELD)
                {
                    world.pushOrganism(newOrganism(getPositionX()+1,getPositionY()+1));
                    return true;
                }
            }
            else
            {
                int j=identityOrganism(getPositionX(),getPositionY()+speed, world);
                if(j>=0)
                {
                    fight(board,world,j,Options.DOWN);
                    return true;
                }
            }
        }
        return false;
    }
}
