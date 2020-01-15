package virtualWorld.organisms;

import virtualWorld.World;

public interface OrganismInterface
{
    void action(char board[][], World world, int n, int m, int direction);
    boolean collision(char board[][], World world, int option);
    boolean bounceAttack(Organisms organisms,World world);
}
