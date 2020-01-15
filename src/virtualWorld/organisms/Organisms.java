package virtualWorld.organisms;
import virtualWorld.World;

import java.lang.String;

public abstract class Organisms implements OrganismInterface
{
    protected int force;
    protected int initiative;
    protected int positionX;
    protected int positionY;
    protected int age;
    protected int speed;
    protected char symbol;
    protected String name;
    protected World world;

    public final int getForce()
    {
        return force;
    }
    public final int getInitiative(){ return initiative; }
    public final int getPositionX()
    {
        return positionX;
    }
    public final int getPositionY()
    {
        return positionY;
    }
    public final int getAge()
    {
        return age;
    }
    public final String getName()
    {
        return name;
    }
    public final char getSymbol(){ return symbol; }
    public void setForce()
    {
        this.force+=3;
    }
    public void setSpeed(int s)
    {
        this.speed=s;
    }
    public void incrementLife()
    {
        age++;
    }
    public void moveXRight()
    {
        positionX++;
    }
    public void moveXLeft()
    {
        positionX--;
    }
    public void moveYUp()
    {
        positionY--;
    }
    public void moveYDown()
    {
        positionY++;
    }
    public int identityOrganism(int posX,int posY,World world)
    {
        for (int i=0;i<world.getOrganisms().size();i++)
        {
            if(posX==world.getOrganisms().get(i).getPositionX() &&
               posY==world.getOrganisms().get(i).getPositionY())
                return i;
        }
        return -1;
    }
}
