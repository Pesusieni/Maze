package Gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Stack;
public class Gui extends JFrame implements ActionListener, KeyListener,
        FocusListener {
    private JButton Play;
    private JButton Instructions;
    private JButton Exit;
    private JButton Back;
    private JButton Easy;
    private JLabel Instruct;
    private Mazealgo algo;
    private Maze maze;
    private JPanel position;
    private SaveLoad LS;

    public Gui() {
        this.setSize(1200, 800);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.ButtonSetUp();
        this.setResizable(false);
        LS = new SaveLoad();
    }

    public void ButtonSetUp() {

        this.setLayout(new BorderLayout());
        Instructions = new JButton("Instructions");
        Instructions.setSize(100, 50);
        this.add(Instructions);
        Instructions.setActionCommand("HELP");
        Instructions.addActionListener(this);
        Instructions.setLocation(650, 339);
        Instructions.setVisible(true);

        Exit = new JButton("Exit");
        Exit.setSize(100, 50);
        this.add(Exit);
        Exit.setActionCommand("EXIT");
        Exit.addActionListener(this);
        Exit.setLocation(550, 339);
        Exit.setVisible(true);

        Play = new JButton("Play");
        this.add(Play);
        Play.setActionCommand("PLAY");
        Play.addActionListener(this);
        Play.setLocation(450, 339);
        Play.setSize(100, 50);
        Play.setVisible(true);
        JButton Lol = new JButton("haha");
        this.add(Lol);
        Lol.setVisible(false);
    }

    public void setUpInstructions() {
        Back = new JButton("Back");
        Back.setActionCommand("BACK");
        Back.addActionListener(this);
        Back.setSize(100, 50);
        this.add(Back);
        Back.setVisible(true);
        Back.setLocation(1000, 650);
        Instruct = new JLabel(
                "The game is a maze and you are in it, get out of it");
        Instruct.setSize(500, 100);
        this.add(Instruct);
        Instruct.setVisible(true);
        Instruct.setLocation(350, 339);

    }

    public void setUpplay() {
        Easy = new JButton("Easy");
        Easy.setActionCommand("EASY");
        Easy.addActionListener(this);
        Easy.setSize(100, 50);
        this.add(Easy, -1);
        Easy.setVisible(true);
        Easy.setLocation(450, 339);

        JButton Medium = new JButton("Medium");
        Medium.setActionCommand("MEDIUM");
        Medium.addActionListener(this);
        Medium.setSize(100, 50);
        this.add(Medium, -1);
        Medium.setLocation(550, 339);
        if (maze != null) {
            JButton Continue = new JButton("Continue");
            Continue.setActionCommand("CONTINUE");
            Continue.addActionListener(this);
            Continue.setSize(100, 50);
            this.add(Continue, -1);
            Continue.setLocation(500, 389);
        }
        Back = new JButton("Back");
        this.add(Back, -1);
        Back.setVisible(true);
        Back.setSize(100, 50);
        Back.setLocation(1000, 650);
        Back.setActionCommand("BACK1");
        Back.addActionListener(this);

        JButton Hard = new JButton("Hard");
        this.add(Hard, -1);
        Hard.setVisible(true);
        Hard.setSize(100, 50);
        Hard.setLocation(650, 339);
        Hard.setActionCommand("HARD");
        Hard.addActionListener(this);

        JButton Load = new JButton("Load");
        Load.setActionCommand("LOAD");
        Load.addActionListener(this);
        Load.setSize(100, 50);
        this.add(Load, -1);
        Load.setLocation(600, 389);

    }

    public void removeGui() {
        this.getContentPane().removeAll();
        this.repaint();
    }

    public void drawhard(Maze maze) {
        for (int i = 0; i < maze.getLength(); i++) {
            for (int j = 0; j < maze.getWidth(); j++) {
                if (((maze.positionX() + 5 > i && maze.positionX() - 5 < i)
                       && (maze.positionY() + 5> j && maze.positionY() - 5 < j))
                        && (maze.getValue(i, j) == 0)) {

                    JPanel addable = new JPanel();
                    this.getContentPane().add(addable, -1);
                    addable.setVisible(true);
                    addable.setSize(12, 12);
                    addable.setLocation((i * 10) + 50, (j * 10) + 50);
                    addable.setBackground(Color.BLACK);
                }
            }
        }
    }

    public void draw(Maze maze) {
        for (int i = 0; i < maze.getLength(); i++) {
            for (int j = 0; j < maze.getWidth(); j++) {
                if (maze.getTable()[i][j] == 0) {
                    JPanel addable = new JPanel();
                    this.getContentPane().add(addable, -1);
                    addable.setVisible(true);
                    addable.setSize(12, 12);
                    addable.setLocation((i * 10) + 50, (j * 10) + 50);
                    addable.setBackground(Color.BLACK);

                }
                if (maze.getTable()[i][j] == 2) {
                    JPanel addable = new JPanel();
                    this.getContentPane().add(addable, -1);
                    addable.setVisible(true);
                    addable.setSize(12, 12);
                    addable.setLocation((i * 10) + 50, (j * 10) + 50);
                    addable.setBackground(Color.BLUE);

                }
            }
        }

        this.repaint();
    }

    public void Gamesetup(Maze maze) {

        position = new JPanel();
        this.add(position, -1);
        position.setVisible(true);
        position.setSize(6, 6);

        Back = new JButton("Back");
        this.add(Back, -1);
        Back.setVisible(true);
        Back.setSize(100, 50);
        Back.setLocation(400, 708);
        Back.setActionCommand("Back2");
        Back.addActionListener(this);

        JButton Save = new JButton("Save");
        this.add(Save, -1);
        Save.setVisible(true);
        Save.setSize(100, 50);
        Save.setLocation(500, 708);
        Save.setActionCommand("SAVE");
        Save.addActionListener(this);
        
        JButton Show = new JButton("Way Out");
        this.add(Show, -1);
        Show.setVisible(true);
        Show.setSize(100, 50);
        Show.setLocation(600, 708);
        Show.setActionCommand("SHOW");
        Show.addActionListener(this);
        
        

        position.setLocation((maze.positionX() * 10) + 53,
                (maze.positionY() * 10) + 53);
        position.setBackground(Color.RED);

    }

    public void keyTyped(KeyEvent ke) {
    }

    public void keyPressed(KeyEvent evt) {
        // Called when the user has pressed a key, which can be
        // a special key such as an arrow key. If the key pressed
        // was one of the arrow keys, move the square (but make sure
        // that it doesn't move off the edge, allowing for a
        // 3-pixel border all around the applet).
        int key = evt.getKeyCode(); // keyboard code for the key that was
                                    // pressed

        if (key == KeyEvent.VK_LEFT) {
            maze.left();
        } else if (key == KeyEvent.VK_RIGHT) {
            maze.right();
        } else if (key == KeyEvent.VK_UP) {
            maze.up();
        } else if (key == KeyEvent.VK_DOWN) {
            maze.down();
        }
        if(maze.truth){
            this.drawhard(maze);
        }
        position.setLocation((maze.positionX() * 10) + 53,
                (maze.positionY() * 10) + 53);

        if (maze.gameOver()) {
            this.removeGui();
            this.removeKeyListener(this);
            this.removeFocusListener(this);
            this.ButtonSetUp();
        }
        this.repaint();

    } // end keyPressed()

    public void keyReleased(KeyEvent evt) {
        // empty method, required by the KeyListener Interface
    }

    public void actionPerformed(ActionEvent evt) {
        String cmd = evt.getActionCommand();
        if (cmd == "HELP") {
            this.removeGui();
            this.setUpInstructions();

        } else if (cmd == "BACK") {
            this.removeGui();
            this.ButtonSetUp();

        } else if (cmd == "BACK1") {
            this.removeGui();
            this.ButtonSetUp();
        } else if (cmd == "PLAY") {
            this.removeGui();
            this.setUpplay();
        } else if (cmd == "EXIT") {
            System.exit(0);
        } else if (cmd == "EASY") {
            this.removeGui();
            algo = new Mazealgo();
            maze = algo.make(new Maze(55, 55));
            this.Gamesetup(maze);
            maze.seed(algo.seed());
            this.addFocusListener(this);
            this.addKeyListener(this);
            this.requestFocus();
            this.draw(maze);
        } else if (cmd == "MEDIUM") {
            this.removeGui();
            algo = new Mazealgo();
            maze = algo.make(new Maze(110, 65));
            this.Gamesetup(maze);
            maze.seed(algo.seed());
            this.addFocusListener(this);
            this.addKeyListener(this);
            this.requestFocus();
            this.draw(maze);
        } else if (cmd == "Back2") {
            this.removeGui();
            this.repaint();
            this.ButtonSetUp();
            this.removeKeyListener(this);
            this.removeFocusListener(this);

        } else if (cmd == "CONTINUE") {
            this.removeGui();
            this.Gamesetup(maze);
            this.addFocusListener(this);
            this.addKeyListener(this);
            this.requestFocus();
            this.draw(maze);
        } else if (cmd == "SAVE") {
            LS.save(maze);
            this.requestFocus();

        } else if (cmd == "LOAD") {
            this.removeGui();
            maze = LS.load();
            this.Gamesetup(maze);
            this.addFocusListener(this);
            this.addKeyListener(this);
            this.requestFocus();
            this.draw(maze);
        } else if (cmd == "HARD") {
            this.removeGui();
            algo = new Mazealgo();
            maze = algo.make(new Maze(110, 65));
            this.Gamesetup(maze);
            this.addFocusListener(this);
            this.addKeyListener(this);
            this.requestFocus();
            maze.truth = true;
            this.drawhard(maze);
        }
        else if(cmd == "SHOW"){
            this.drawEnd(algo.dfs(maze));
            
        }

    }
    public void drawEnd(Stack<Integer> stack){
        int tmpx = maze.positionX();
        int tmpy = maze.positionY();
        //this.removeGui();
        //this.draw(maze);
        System.out.print(stack.size());
        System.out.print(maze.gameOver());
        while (!stack.isEmpty()){
            int direction = stack.pop();

            System.out.print(direction);
            if (direction ==0){
                maze.up();
            }else if (direction ==1){
                maze.right();
            }else if (direction ==2){
                maze.down();
            }else if (direction ==3){
                maze.left();
            }
            JPanel addable = new JPanel();
            this.add(addable);
            addable.setLocation((maze.positionX() * 10) + 50, (maze.positionY() * 10) + 50);
            addable.setVisible(true);
            addable.setSize(10, 10);
            addable.setBackground(Color.GREEN);
           
        } this.repaint();
        maze.setLocation(tmpx, tmpy);
        
    }

    @Override
    public void focusGained(FocusEvent e) {

    }

    @Override
    public void focusLost(FocusEvent e) {
        // TODO Auto-generated method stub

    }

}
