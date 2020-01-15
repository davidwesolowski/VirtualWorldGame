package virtualWorld.organisms.animals;

import virtualWorld.Options;
import virtualWorld.World;
import virtualWorld.organisms.Organisms;
import virtualWorld.organisms.plants.DeadlyNightshade;

import java.util.Random;

public class Human extends Animals
{
    public Human(int posX, int posY,int age)
    {
        this.positionX=posX;
        this.positionY=posY;
        this.age=age;
        this.force=5;
        this.initiative=4;
        this.speed=1;
        this.symbol='C';
        this.name="Human";
    }

    @Override
    public Organisms newOrganism(int posX, int posY) {
        return new Human(posX,posY,0);
    }

    @Override
    public void action(char[][] board, World world, int n, int m, int direction)
    {
        if(direction==Options.RIGHT_KEY && getPositionX()<m-speed)
        {
            if(world.getSkillSign()==Options.SUPERSKILL)
                collision(board,world,Options.RIGHT_KEY);
            else if(super.collision(board,world,Options.RIGHT_KEY));
            else if(board[getPositionY()][getPositionX()+1]==Options.FREEFIELD)
                move(board,Options.RIGHT_KEY);
        }
        else if (direction==Options.LEFT_KEY && getPositionX()-speed+1>0)
        {
            if(world.getSkillSign()==Options.SUPERSKILL)
                collision(board,world,Options.LEFT_KEY);
            else if(super.collision(board,world,Options.LEFT_KEY));
            else if(board[getPositionY()][getPositionX()-1]==Options.FREEFIELD)
                move(board,Options.LEFT_KEY);
        }
        else if (direction==Options.UP_KEY && getPositionY()-speed+1>0)
        {
            if(world.getSkillSign()==Options.SUPERSKILL)
                collision(board,world,Options.UP_KEY);
            else if(super.collision(board,world,Options.UP_KEY));
            else if(board[getPositionY()-1][getPositionX()]==Options.FREEFIELD)
                move(board,Options.UP_KEY);
        }
        else if (direction==Options.DOWN_KEY && getPositionY()<n-speed)
        {
            if(world.getSkillSign()==Options.SUPERSKILL)
                collision(board,world,Options.DOWN_KEY);
            else if(super.collision(board,world,Options.DOWN_KEY));
            else if(board[getPositionY()+1][getPositionX()]==Options.FREEFIELD)
                move(board,Options.DOWN_KEY);
        }
        if(world.getCounter()<10 && world.getSkillSign()==0)
        {
            world.setCounter(world.getCounter()+1);
            if(world.getCounter()==10)
            {
                world.setIfSkill(true);
                world.setCounter(0);
                world.getSuperSkillField().setText("Super skill: OFF");
            }
        }
        else if(world.getSkillSign()==Options.SUPERSKILL && world.getCounter()==5)
        {
            world.setSkillSign(0);
            setSpeed(1);
            world.getSuperSkillField().setText("Not able to use super skill");
        }
    }

    @Override
    public boolean collision(char[][] board, World world, int option)
    {
        Random randomize=new Random();
        int chance=randomize.nextInt(100);
        if(world.getCounter()<=5)
        {
            if(option==Options.RIGHT_KEY)
            {
                if(world.getCounter()>3 && chance>50)
                {
                    if(board[getPositionY()][getPositionX()+1]==Options.FREEFIELD)
                        move(board,Options.RIGHT_KEY);
                }
                else
                {
                    if(getPositionX()<world.getBoardWidth()-speed && board[getPositionY()][getPositionX()+speed]==Options.FREEFIELD)
                            move(board,Options.RIGHT_KEY);
                    else
                        super.collision(board,world,Options.RIGHT_KEY);
                }
            }
            else if (option==Options.LEFT_KEY)
            {
                if(world.getCounter()>3 && chance>50)
                {
                    if(board[getPositionY()][getPositionX()-1]==Options.FREEFIELD)
                        move(board,Options.LEFT_KEY);
                }
                else
                {
                    if(getPositionX()>speed && board[getPositionY()][getPositionX()-speed]==Options.FREEFIELD)
                        move(board,Options.LEFT_KEY);
                    else
                        super.collision(board,world,Options.LEFT_KEY);
                }
            }
            else if (option==Options.UP_KEY)
            {
                if(world.getCounter()>3 && chance>50)
                {
                    if(board[getPositionY()-1][getPositionX()]==Options.FREEFIELD)
                        move(board,Options.UP_KEY);
                }
                else
                {
                    if(getPositionY()>speed && board[getPositionY()-speed][getPositionX()]==Options.FREEFIELD)
                        move(board,Options.UP_KEY);
                    else
                        super.collision(board,world,Options.UP_KEY);
                }
            }
            else if (option==Options.DOWN_KEY)
            {
                if(world.getCounter()>3 && chance>50)
                {
                    if(board[getPositionY()+1][getPositionX()]==Options.FREEFIELD)
                        move(board,Options.DOWN_KEY);
                }
                else
                {
                    if(getPositionY()<world.getBoardHeight()-speed && board[getPositionY()+speed][getPositionX()]==Options.FREEFIELD)
                        move(board,Options.DOWN_KEY);
                    else
                        super.collision(board,world,Options.DOWN_KEY);
                }
            }
            world.setCounter(world.getCounter()+1);
        }
        return false;
    }
}
