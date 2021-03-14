package Gui;

import java.util.Stack;
import java.util.Random;

public class Mazealgo {
    private long seed;
    private Random random;

    // used to load a maze with a seed
    public Mazealgo(long seed) {
        this.seed = seed;
        this.random = new Random(seed);
    }

    // used to craete a random maze
    public Mazealgo() {
        Random number = new Random();
        long i = number.nextLong();
        this.random = new Random(i);
        this.seed = i;
    }

    public boolean direction(Maze maze, Stack<Integer> stack) {
        // used in the creation of the maze it carves the maze by choosing a
        // random direction carving that places unless it is allready carved
        boolean zero = true;
        boolean one = true;
        boolean two = true;
        boolean three = true;
        while (one || two || zero || three) {
            int direction = random.nextInt(4);
            if (direction == 0 && zero) {
                if (maze.getRight() == 1) {
                    maze.goRight();
                    maze.getTable()[maze.positionX()][maze.positionY()] = 1;
                    stack.push(0);
                    return true;
                } else if (maze.getRight() == 2) {
                    maze.goRight();
                    maze.goRight();
                    maze.goRight();
                    maze.goRight();
                    stack.push(0);
                    stack.push(0);
                    stack.push(0);
                    stack.push(0);
                    maze.getTable()[maze.positionX()][maze.positionY()] = 1;
                    maze.getTable()[maze.positionX() - 1][maze.positionY()] = 2;
                    maze.getTable()[maze.positionX() - 2][maze.positionY()] = 1;
                    maze.getTable()[maze.positionX() - 3][maze.positionY()] = 1;
                } else
                    zero = false;
            } else if (direction == 1 && one) {
                if (maze.getDown() == 1) {
                    maze.goDown();
                    maze.getTable()[maze.positionX()][maze.positionY()] = 1;
                    stack.push(1);
                    return true;
                } else if (maze.getDown() == 2) {

                    maze.goDown();
                    maze.goDown();
                    maze.goDown();
                    maze.goDown();
                    stack.push(1);
                    stack.push(1);
                    stack.push(1);
                    stack.push(1);
                    maze.getTable()[maze.positionX()][maze.positionY()] = 1;
                    maze.getTable()[maze.positionX()][maze.positionY() - 1] = 2;
                    maze.getTable()[maze.positionX()][maze.positionY() - 2] = 1;
                    maze.getTable()[maze.positionX()][maze.positionY() - 3] = 1;
                } else
                    one = false;
            } else if (direction == 2 && two) {
                if (maze.getLeft() == 1) {
                    maze.goLeft();
                    maze.getTable()[maze.positionX()][maze.positionY()] = 1;
                    stack.push(2);
                    return true;
                } else if (maze.getLeft() == 2) {

                    maze.goLeft();
                    maze.goLeft();
                    maze.goLeft();
                    maze.goLeft();
                    stack.push(2);
                    stack.push(2);
                    stack.push(2);
                    stack.push(2);
                    maze.getTable()[maze.positionX()][maze.positionY()] = 1;
                    maze.getTable()[maze.positionX() + 1][maze.positionY()] = 2;
                    maze.getTable()[maze.positionX() + 2][maze.positionY()] = 1;
                    maze.getTable()[maze.positionX() + 3][maze.positionY()] = 1;

                } else
                    two = false;
            } else if (direction == 3 && three) {
                if (maze.getUp() == 1) {
                    maze.goUp();
                    maze.getTable()[maze.positionX()][maze.positionY()] = 1;
                    stack.push(3);
                    return true;
                } else if (maze.getUp() == 2) {
                    maze.goUp();
                    maze.goUp();
                    maze.goUp();
                    maze.goUp();
                    stack.push(3);
                    stack.push(3);
                    stack.push(3);
                    stack.push(3);
                    maze.getTable()[maze.positionX()][maze.positionY()] = 1;
                    maze.getTable()[maze.positionX()][maze.positionY() + 1] = 2;
                    maze.getTable()[maze.positionX()][maze.positionY() + 2] = 1;
                    maze.getTable()[maze.positionX()][maze.positionY() + 3] = 1;
                } else

                    three = false;
            }

        }
        return false;
    }

    public Maze make(Maze maze) {
        // creates the maze using a stack for visited places
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(0);
        maze.getTable()[maze.positionX()][maze.positionY()] = 1;
        while (!stack.isEmpty()) {
            while (direction(maze, stack)) {
            }

            int direction = stack.pop();
            if (direction == 0) {
                maze.goLeft();

            } else if (direction == 1) {
                maze.goUp();

            } else if (direction == 2) {
                maze.goRight();
            } else if (direction == 3) {
                maze.goDown();

            }
        }
        Random random = new Random(seed);
        // creates a exit and randomzies the position of the player sligthly
        maze.positionRandomize(random.nextInt(4));
        return maze;
    }

    public long seed() {
        return this.seed;
    }

    public Stack<Integer> dfs(Maze tmp) {
        // DFS uses Stack data structure
        
        int tmpx = tmp.positionX();
        int tmpy = tmp.positionY();
        Stack<Integer> s = new Stack<Integer>();
        while (!tmp.gameOver()) {
            choosedirection(s, tmp);
            while (!s.isEmpty() && !tmp.gameOver()) {
                if (s.peek() == 0) {
                    if (tmp.right()) {
                        s.push(1);
                    } else if (tmp.up()) {
                        s.push(0);
                    } else if (tmp.left()) {
                        s.push(3);
                    } else {
                        this.stackpop(s, tmp);
                    }
                } else if (s.peek() == 1) {
                    if (tmp.down()) {
                        s.push(2);
                    } else if (tmp.right()) {
                        s.push(1);
                    } else if (tmp.up()) {
                        s.push(0);
                    } else {
                        this.stackpop(s, tmp);
                    }
                } else if (s.peek() == 2) {
                    if (tmp.left()) {
                        s.push(3);
                    } else if (tmp.down()) {
                        s.push(2);
                    } else if (tmp.right()) {
                        s.push(1);
                    } else {
                        this.stackpop(s, tmp);
                    }
                } else if (s.peek() == 3) {
                    if (tmp.up()) {
                        s.push(0);
                    } else if (tmp.left()) {
                        s.push(3);
                    } else if (tmp.down()) {
                        s.push(2);
                    } else {
                        this.stackpop(s, tmp);
                    }

                }

            }
        }

        Stack<Integer> stack = new Stack<Integer>();
        while (!s.isEmpty()) {
            stack.push(s.pop());
        }

        tmp.setLocation(tmpx, tmpy);
        return stack;

    }

    private void choosedirection(Stack<Integer> stack, Maze maze) {
        if (maze.up()) {
            stack.push(0);
        } else if (maze.right()) {
            stack.push(1);
        } else if (maze.down()) {
            stack.push(2);
        } else if (maze.left()) {
            stack.push(3);
        }

    }

    private void stackpop(Stack<Integer> s, Maze tmp) {
        tmp.getTable()[tmp.positionX()][tmp.positionY()] = 0;
        int direction = s.pop();
        if (direction == 0) {
            tmp.down();
            if (tmp.getTable()[tmp.positionX()][tmp.positionY() - 1] == 2) {
                tmp.getTable()[tmp.positionX()][tmp.positionY() ] = 0;
                stackpop(s,tmp);
            }

        } else if (direction == 1) {
            tmp.left();
            if (tmp.getTable()[tmp.positionX()+1][tmp.positionY()] == 2) {
                tmp.getTable()[tmp.positionX()][tmp.positionY()] = 0;
                stackpop(s,tmp);
            }

        } else if (direction == 2) {
            tmp.up();
            if (tmp.getTable()[tmp.positionX()][tmp.positionY() + 1] == 2) {
                tmp.getTable()[tmp.positionX()][tmp.positionY() ] = 0;
                stackpop(s,tmp);
            }
        } else if (direction == 3) {
            tmp.right();
            if (tmp.getTable()[tmp.positionX()-1][tmp.positionY() ] == 2) {
                tmp.getTable()[tmp.positionX()][tmp.positionY() ] = 0;
                stackpop(s,tmp);
            }

        }

    }

}
