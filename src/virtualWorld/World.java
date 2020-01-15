package virtualWorld;


import virtualWorld.organisms.Organisms;
import virtualWorld.organisms.animals.*;
import virtualWorld.organisms.plants.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;


public class World implements ActionListener,KeyListener
{
    private char board[][];
    private JButton buttonBoard[][];
    private int boardHeight;
    private int boardWidth;
    private int round;
    private JFrame window;
    private JPanel panelButton;
    private JButton newRoundButton;
    private JButton saveToFileButton;
    private JButton uploadFromFileButton;
    private JButton superSkillButton;
    private JTextField roundField;
    private JTextField superSkillField;
    private JTextPane commentField;
    private ArrayList<Organisms> tableOfOrganisms;
    private int humanDirection;
    private int counter;
    private int skillSign;
    private boolean ifSkill;
    private String comments;


    public World(int width, int height)
    {
        boardWidth=width;
        boardHeight=height;
        round=0;
        counter=0;
        skillSign=0;
        humanDirection=0;
        comments=" ";
        ifSkill=true;
        window=createWindow();
        panelButton=new JPanel();
        tableOfOrganisms=new ArrayList<>();
        newRoundButton=new JButton("New round");
        saveToFileButton=new JButton("Save to file");
        uploadFromFileButton=new JButton("Upload from file");
        roundField=new JTextField();
        commentField=new JTextPane();
        superSkillField=new JTextField();
        superSkillButton=new JButton("Super skill");
        panelButton.setLayout(null);

        newRoundButton.setBounds(750,50,150,30);
        newRoundButton.addActionListener(this);
        newRoundButton.setFocusable(false);

        saveToFileButton.setBounds(750,100,150,30);
        saveToFileButton.addActionListener(this);
        saveToFileButton.setFocusable(false);

        uploadFromFileButton.setBounds(750,150,150,30);
        uploadFromFileButton.addActionListener(this);
        uploadFromFileButton.setFocusable(false);

        roundField.setBounds(750,10,150,30);
        roundField.setText(Integer.toString(getRound()));
        roundField.setToolTipText("Round");
        roundField.setFocusable(false);

        commentField.setBounds(0,700,800,100);
        commentField.setToolTipText("Comments");
        commentField.setFocusable(false);

        superSkillButton.setBounds(750,200,150,30);
        superSkillButton.addActionListener(this);
        superSkillButton.setFocusable(false);

        superSkillField.setBounds(750,250,150,30);
        superSkillField.setText("Superskill: OFF");
        superSkillField.setFocusable(false);

        panelButton.add(newRoundButton);
        panelButton.add(saveToFileButton);
        panelButton.add(uploadFromFileButton);
        panelButton.add(roundField);
        panelButton.add(commentField);
        panelButton.add(superSkillButton);
        panelButton.add(superSkillField);

        createBoard();
    }

    public char[][] getBoard()
    {
        return board;
    }

    public int getBoardHeight()
    {
        return boardHeight;
    }

    public void setCounter(int n)
    {
        this.counter=n;
    }

    public int getCounter()
    {
        return counter;
    }

    public int getBoardWidth()
    {
        return boardWidth;
    }

    public int getRound()
    {
        return round;
    }

    public void goRound()
    {
        comments=" ";
        round++;
    }

    public void setRound(int r)
    {
        round=r;
    }

    public void setBoardHeight(int h)
    {
        boardHeight=h;
    }

    public void setBoardWidth(int w)
    {
        boardWidth=w;
    }

    public int getSkillSign()
    {
        return skillSign;
    }

    public void setSkillSign(int n)
    {
        this.skillSign=n;
    }

    public void setIfSkill(boolean value)
    {
        this.ifSkill=value;
    }

    public boolean getIfSkill()
    {
        return ifSkill;
    }

    public JFrame createWindow() {
        JFrame window=new JFrame();
        window.setSize(1000,800) ;
        window.setLocationRelativeTo(null);
        window.setTitle("Dawid Wesołowski");
        window.setResizable(false);
        window.addKeyListener(this);
        window.setFocusable(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        return window;
    }

    public JTextField getSuperSkillField()
    {
        return superSkillField;
    }

    public void createBoard()
    {
        board=new char[boardHeight][boardWidth];
        buttonBoard=new JButton[boardHeight][boardWidth];
        Image img = new ImageIcon(this.getClass().getResource("icons/freeField.png")).getImage();

        for (int i=0;i<board.length;i++)
        {
            for (int j=0;j<board[0].length;j++)
            {
                board[i][j]=Options.FREEFIELD;
                buttonBoard[i][j]=new JButton();
                buttonBoard[i][j].setIcon(new ImageIcon(img));
                buttonBoard[i][j].setBounds((j*Options.IMAGESIZE)+10,(i*Options.IMAGESIZE)+10,Options.IMAGESIZE,Options.IMAGESIZE);
                window.add(buttonBoard[i][j]);
            }
        }

        window.add(panelButton);
        window.setVisible(true);
    }

    public void removeButtonBoard()
    {
        for (int i=0;i<board.length;i++)
        {
            for (int j=0;j<board[0].length;j++)
            {
                window.remove(buttonBoard[i][j]);
            }
        }
    }

    public void displayBoard()
    {
        Image imgFreeField = new ImageIcon(this.getClass().getResource("icons/freeField.png")).getImage();
        Image imgHuman = new ImageIcon(this.getClass().getResource("icons/human.png")).getImage();
        Image imgWolf = new ImageIcon(this.getClass().getResource("icons/wolf.png")).getImage();
        Image imgSheep = new ImageIcon(this.getClass().getResource("icons/sheep.png")).getImage();
        Image imgFox = new ImageIcon(this.getClass().getResource("icons/fox.png")).getImage();
        Image imgAntelope = new ImageIcon(this.getClass().getResource("icons/antelope.png")).getImage();
        Image imgTurtle = new ImageIcon(this.getClass().getResource("icons/turtle.png")).getImage();
        Image imgGuarana = new ImageIcon(this.getClass().getResource("icons/guarana.png")).getImage();
        Image imgGrass = new ImageIcon(this.getClass().getResource("icons/grass.png")).getImage();
        Image imgDeadlynight = new ImageIcon(this.getClass().getResource("icons/deadlynight.png")).getImage();
        Image imgDandelion = new ImageIcon(this.getClass().getResource("icons/dandelion.png")).getImage();
        Image imgSosnowsky = new ImageIcon(this.getClass().getResource("icons/sosnowsky.png")).getImage();

        for (int i=0;i<boardHeight;i++)
        {
            for (int j=0;j<boardWidth;j++)
            {
                buttonBoard[i][j].removeActionListener(this);
                buttonBoard[i][j].setFocusable(false);
                switch(board[i][j])
                {
                    case Options.FREEFIELD:
                        buttonBoard[i][j].setIcon(new ImageIcon(imgFreeField));
                        buttonBoard[i][j].addActionListener(this);
                        break;
                    case Options.HUMANFIELD:
                        buttonBoard[i][j].setIcon(new ImageIcon(imgHuman));
                        break;
                    case Options.WOLFFIELD:
                        buttonBoard[i][j].setIcon(new ImageIcon(imgWolf));
                        break;
                    case Options.FOXFIELD:
                        buttonBoard[i][j].setIcon(new ImageIcon(imgFox));
                        break;
                    case Options.ANTELOPEFIELD:
                        buttonBoard[i][j].setIcon(new ImageIcon(imgAntelope));
                        break;
                    case Options.TURTLEFIELD:
                        buttonBoard[i][j].setIcon(new ImageIcon(imgTurtle));
                        break;
                    case Options.SHEEPFIELD:
                        buttonBoard[i][j].setIcon(new ImageIcon(imgSheep));
                        break;
                    case Options.GRASSFIELD:
                        buttonBoard[i][j].setIcon(new ImageIcon(imgGrass));
                        break;
                    case Options.DANDELIONFIELD:
                        buttonBoard[i][j].setIcon(new ImageIcon(imgDandelion));
                        break;
                    case Options.GUARANAFIELD:
                        buttonBoard[i][j].setIcon(new ImageIcon(imgGuarana));
                        break;
                    case Options.SOSNOWSKYFIELD:
                        buttonBoard[i][j].setIcon(new ImageIcon(imgSosnowsky));
                        break;
                    case Options.DEADLYNIGHTSHADEFIELD:
                        buttonBoard[i][j].setIcon(new ImageIcon(imgDeadlynight));
                        break;
                }
            }
        }
    }

    public ArrayList<Organisms>getOrganisms()
    {
        return tableOfOrganisms;
    }

    public void pushOrganism(Organisms organism)
    {
        getOrganisms().add(organism);
        board[organism.getPositionY()][organism.getPositionX()]=organism.getSymbol();
    }

    public void popOrganism(int i)
    {
        board[getOrganisms().get(i).getPositionY()][getOrganisms().get(i).getPositionX()]=Options.FREEFIELD;
        getOrganisms().remove(i);
    }

    public void sortOrganisms()
    {
        Collections.sort(tableOfOrganisms, new Comparator<Organisms>() {
            @Override
            public int compare(Organisms o1, Organisms o2) {
                int result=Integer.valueOf(o2.getInitiative()).compareTo(Integer.valueOf(o1.getInitiative()));
                if(result==0)
                {
                    result=(Integer.valueOf(o2.getAge())<Integer.valueOf(o1.getAge())?-1:(Integer.valueOf(o2.getAge())==Integer.valueOf(o1.getAge())?0:1));
                }
                return result;
            }
        });
    }

    public void fillInSign()
    {
        for(int i=0;i<tableOfOrganisms.size();i++)
        {
            board[tableOfOrganisms.get(i).getPositionY()][tableOfOrganisms.get(i).getPositionX()]=tableOfOrganisms.get(i).getSymbol();
        }
    }

    @Override
    public void keyTyped(KeyEvent e)
    {
        //not using this method
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        int keyCode=e.getKeyCode();
        if(keyCode==KeyEvent.VK_RIGHT)
        {
            setHumanDirection(KeyEvent.VK_RIGHT);
        }
        else if (keyCode==KeyEvent.VK_LEFT)
        {
            setHumanDirection(KeyEvent.VK_LEFT);
        }
        else if (keyCode==KeyEvent.VK_UP)
        {
            setHumanDirection(KeyEvent.VK_UP);
        }
        else if (keyCode==KeyEvent.VK_DOWN)
        {
            setHumanDirection(KeyEvent.VK_DOWN);
        }
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        //not using this method
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==newRoundButton)
        {
            commentField.setText(" ");
            sortOrganisms();
            for(int i=0;i<tableOfOrganisms.size();i++)
            {
                if (tableOfOrganisms.get(i).getName()=="Human")
                {
                    if(getSkillSign()==Options.SUPERSKILL)
                        tableOfOrganisms.get(i).setSpeed(2);
                    tableOfOrganisms.get(i).action(board,this,boardHeight,boardWidth,humanDirection);
                    setHumanDirection(0);
                }
                else
                    tableOfOrganisms.get(i).action(board,this,boardHeight,boardWidth,0);
            }
            for (int i=0;i<tableOfOrganisms.size();i++)
            {
                tableOfOrganisms.get(i).incrementLife();
            }
            goRound();
            roundField.setText(Integer.toString(getRound()));
            displayBoard();
        }
        else if (e.getSource()==saveToFileButton)
        {
            try
            {
                saveToFile();
            }
            catch (FileNotFoundException e1)
            {
                e1.printStackTrace();
            }
        }
        else if (e.getSource()==uploadFromFileButton)
        {
            tableOfOrganisms.clear();
            try
            {
                updateFromFile();
            }
            catch (FileNotFoundException e1)
            {
                e1.printStackTrace();
            }
            removeButtonBoard();
            createBoard();
            fillInSign();
            roundField.setText(Integer.toString(getRound()));
            superSkillField.setText("Super skill: OFF");
            window.revalidate();
            window.repaint();
            displayBoard();
        }
        else if (e.getSource()==superSkillButton)
        {
            if(getIfSkill())
            {
                setSkillSign('s');
                setCounter(0);
                setIfSkill(false);
                superSkillField.setText("Superskill: ON");
            }
        }
        for(int i=0;i<boardHeight;i++)
        {
            for(int j=0;j<boardWidth;j++)
            {
                if(board[i][j]==Options.FREEFIELD && e.getSource()==buttonBoard[i][j])
                {
                    Random randomize=new Random();
                    int randomNumber=randomize.nextInt(tableOfOrganisms.size());
                    while(getOrganisms().get(randomNumber).getSymbol()==Options.HUMANFIELD)
                        randomNumber=randomize.nextInt(tableOfOrganisms.size());
                    addNewOrganism(getOrganisms().get(randomNumber).getSymbol(),j,i);
                    displayBoard();
                }
            }
        }
    }

    public void addNewOrganism(char symbol, int x, int y)
    {
        switch (symbol)
        {
            case Options.WOLFFIELD:
                pushOrganism(new Wolf(x,y,0));
                break;
            case Options.FOXFIELD:
                pushOrganism(new Fox(x,y,0));
                break;
            case Options.ANTELOPEFIELD:
                pushOrganism(new Antelope(x,y,0));
                break;
            case Options.TURTLEFIELD:
                pushOrganism(new Turtle(x,y,0));
                break;
            case Options.SHEEPFIELD:
                pushOrganism(new Sheep(x,y,0));
                break;
            case Options.GRASSFIELD:
                pushOrganism(new Grass(x,y));
                break;
            case Options.DANDELIONFIELD:
                pushOrganism(new Dandelion(x,y));
                break;
            case Options.GUARANAFIELD:
                pushOrganism(new Guarana(x,y));
                break;
            case Options.SOSNOWSKYFIELD:
                pushOrganism(new SosnowskyHogweed(x,y));
                break;
            case Options.DEADLYNIGHTSHADEFIELD:
                pushOrganism(new DeadlyNightshade(x,y));
                break;
        }

    }

    public void saveToFile() throws FileNotFoundException
    {
        PrintWriter write=new PrintWriter("game.txt");
        write.println(getBoardHeight());
        write.println(getBoardWidth());
        write.println(getRound());
        for(int i=0;i<getOrganisms().size();i++)
        {
            write.println(getOrganisms().get(i).getSymbol());
            write.println(getOrganisms().get(i).getPositionX());
            write.println(getOrganisms().get(i).getPositionY());
            write.println(getOrganisms().get(i).getAge());
        }
        write.close();
    }

    public void updateFromFile() throws FileNotFoundException
    {
        int w=0,h=0,r=0,posX=0,posY=0,age=0;
        char s=' ';
        int lineNumber=1;
        int inlineCounter=0;
        File file=new File("game.txt");
        Scanner in=new Scanner(file);
        while(lineNumber<=3 && in.hasNext())
        {
            switch(lineNumber)
            {
                case 1:
                    h=Integer.parseInt(in.nextLine());
                    setBoardHeight(h);
                    break;
                case 2:
                    w=Integer.parseInt(in.nextLine());
                    setBoardWidth(w);
                    break;
                case 3:
                    r=Integer.parseInt(in.nextLine());
                    setRound(r);
                    break;
            }
            lineNumber++;
        }
        while(in.hasNext())
        {
            switch(lineNumber)
            {
                case 4:
                    s=in.nextLine().charAt(0);
                    inlineCounter++;
                    break;
                case 5:
                    posX=Integer.parseInt(in.nextLine());
                    inlineCounter++;
                    break;
                case 6:
                    posY=Integer.parseInt(in.nextLine());
                    inlineCounter++;
                    break;
                case 7:
                    age=Integer.parseInt(in.nextLine());
                    inlineCounter++;
                    break;
            }
            if(lineNumber==7)
                lineNumber=3;
            if(inlineCounter==4)
            {
                switch(s)
                {
                    case Options.WOLFFIELD:
                        getOrganisms().add(new Wolf(posX,posY,age));
                        break;
                    case Options.SHEEPFIELD:
                        getOrganisms().add(new Sheep(posX,posY,age));
                        break;
                    case Options.HUMANFIELD:
                        getOrganisms().add(new Human(posX,posY,age));
                        break;
                    case Options.FOXFIELD:
                        getOrganisms().add(new Fox(posX,posY,age));
                        break;
                    case Options.ANTELOPEFIELD:
                        getOrganisms().add(new Antelope(posX,posY,age));
                        break;
                    case Options.TURTLEFIELD:
                        getOrganisms().add(new Turtle(posX,posY,age));
                        break;
                    case Options.GRASSFIELD:
                        getOrganisms().add(new Grass(posX,posY));
                        break;
                    case Options.DANDELIONFIELD:
                        getOrganisms().add(new Dandelion(posX,posY));
                        break;
                    case Options.DEADLYNIGHTSHADEFIELD:
                        getOrganisms().add(new DeadlyNightshade(posX,posY));
                        break;
                    case Options.SOSNOWSKYFIELD:
                        getOrganisms().add(new SosnowskyHogweed(posX,posY));
                        break;
                    case Options.GUARANAFIELD:
                        getOrganisms().add(new Guarana(posX,posY));
                        break;
                }
                inlineCounter=0;
            }
            lineNumber++;
        }
    }

    public void setHumanDirection(int humanDirection)
    {
        this.humanDirection = humanDirection;
    }

    public void commentator(Organisms organismFirst, Organisms organismSecond, int option)
    {
        if (option==Options.FIGHTING)
        {
            if(organismFirst instanceof Plants)
                comments+=organismFirst.getName()+" is eaten by "+ organismSecond.getName()+". ";
            else
                comments+=organismFirst.getName()+" is defeated by "+ organismSecond.getName()+". ";
            commentField.setText(comments);
        }
    }
    public void commentator(Organisms organismFirst, int option)
    {
        if(option==Options.BREEDING)
        {
            comments += organismFirst.getName()+" has bred. ";
            commentField.setText(comments);
        }
    }


}
