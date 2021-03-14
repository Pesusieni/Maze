package Gui;

public class Maze {
    private int table[][];// maze size
    private int x;// player position
    private int y;// player position
    private int length;// maze length or x
    private int width;// maze width or y
    public boolean truth; // if the maze is created and usuable
    private long seed; // the seed of the maze
    private int endX; // where the end is the x coordinate
    private int endY; // where the end is the y coordiante

    // initing a maze thats full of walls
    public Maze(int k, int j) {
        table = new int[k][j];
        this.length = k;
        this.width = j;
        this.y = 1;
        this.x = 1;
        this.truth = false;
    }

    // get on of the dimensions of the maze
    public int getLength() {
        return this.length;
    }

    // get on of the dimensions of the maze

    public int getWidth() {
        return this.width;
    }

    // return the table so you can manipulate it
    public int[][] getTable() {
        return this.table;
    }

    // get a specific value from the maze
    public int getValue(int k, int j) {
        return this.table[k][j];
    }

    // get the position of the player in the maze
    public int[] position() {
        int[] tmp = { this.x, this.y };
        return tmp;
    }

    // get the x coordinat eof the player
    public int positionX() {
        return this.x;

    }

    // get the y coordinate of the player
    public int positionY() {
        return this.y;
    }

    // tries to carve a maze to rigth
    public int getRight() {
        if (this.subRight()) {
            this.goRight();
            if (this.subDown() && this.subRight() && this.subUp()
                    && this.subUpperRight() && this.subDownRight()) {
                this.goLeft();
                return 1;
            } else if (this.bridgeRight()) {
                this.goLeft();
                return 2;
            } else {

                this.goLeft();
                return 0;
            }
        } else {
            return 0;
        }
    }

    // moves the player right
    public void goRight() {
        this.x++;
    }

    // tries to carve a maze left
    public int getLeft() {
        if (this.subLeft()) {
            this.goLeft();
            if (this.subDown() && this.subLeft() && this.subUp()
                    && this.subUpperLeft() && this.subDownLeft()) {
                this.goRight();
                return 1;
            } else if (this.bridgeLeft()) {
                this.goRight();
                return 2;
            } else {
                this.goRight();
                return 0;
            }
        } else {
            return 0;
        }
    }

    // moves the player left
    public void goLeft() {
        this.x--;
    }

    // tries to carve the maze up
    public int getUp() {
        if (this.subUp()) {
            this.goUp();
            if (this.subUp() && this.subRight() && this.subLeft()
                    && this.subUpperRight() && this.subUpperLeft()) {
                this.goDown();
                return 1;
            } else if (this.bridgeUp()) {
                this.goDown();
                return 2;
            } else {
                this.goDown();
                return 0;
            }
        } else {
            return 0;
        }
    }

    // moves the player up
    public void goUp() {
        this.y--;
    }

    // tries to carve the maze down
    public int getDown() {
        if (this.subDown()) {
            this.goDown();
            if (this.subDown() && this.subRight() && this.subLeft()
                    && this.subDownRight() && this.subDownLeft()) {
                this.goUp();
                return 1;
            } else if (this.bridgeDown()) {
                this.goUp();
                return 2;
            } else {
                this.goUp();
                return 0;
            }
        } else {
            return 0;
        }

    }

    // moves the player down
    public void goDown() {
        this.y++;
    }

    // gets info if all nine adjacent blocks are walls
    public boolean getArea() {
        if (this.subDown() && this.subDownLeft() && this.subDownRight()
                && this.subLeft() && this.subRight() && this.subUp()
                && this.subUpperLeft() && this.subUpperRight()
                && this.table[this.x][this.y] == 0) {
            return true;
        } else
            return false;
    }

    // info if down is carving material for the maze
    public boolean subDown() {
        if ((this.y + 1 < this.width) && (this.table[this.x][this.y + 1]) == 0) {
            return true;
        } else
            return false;

    }

    // info if up is carving material for the maze
    public boolean subUp() {
        if ((this.y - 1 > -1) && (this.table[this.x][this.y - 1]) == 0) {
            return true;
        } else
            return false;

    }

    // info if left is carving material for the maze
    public boolean subLeft() {
        if ((this.x - 1 > -1) && (this.table[this.x - 1][this.y]) == 0) {
            return true;
        } else
            return false;

    }

    // info if right is carving material for the maze

    public boolean subRight() {
        if ((this.x + 1 < this.length) && (this.table[this.x + 1][this.y]) == 0) {
            return true;
        } else
            return false;

    }

    public boolean subUpperRight() {
        if ((this.x + 1 < this.length) && (this.y - 1 > -1)
                && (this.table[this.x + 1][this.y - 1]) == 0) {
            return true;

        } else
            return false;
    }

    public boolean subDownRight() {
        if ((this.x + 1 < this.length) && (this.y + 1 < this.width)
                && (this.table[this.x + 1][this.y + 1]) == 0) {
            return true;
        } else
            return false;
    }

    public boolean subUpperLeft() {
        if ((this.x - 1 > -1) && (this.y - 1 > -1)
                && (this.table[this.x - 1][this.y - 1]) == 0) {
            return true;
        } else
            return false;
    }

    public boolean subDownLeft() {
        if ((this.x - 1 > -1) && (this.y + 1 < this.width)
                && (this.table[this.x - 1][this.y + 1]) == 0) {
            return true;
        } else
            return false;
    }

    public void positionRandomize(int i) {
        // randomizes the end of the game
        if (i == 0) {
            this.x = 0;
            this.y = 1;
            this.table[this.x][this.y] = 1;
            while (this.subRight()) {
                this.goRight();
                this.table[this.x][this.y] = 1;
            }
        }
        if (i == 1) {
            this.x = this.length - 2;
            this.y = 0;
            this.table[this.x][this.y] = 1;
            while (this.subDown()) {
                this.goDown();
                this.table[this.x][this.y] = 1;
            }
        }
        if (i == 2) {
            this.x = this.length - 1;
            this.y = this.width - 2;
            this.table[this.x][this.y] = 1;
            while (this.subLeft()) {
                this.goLeft();
                this.table[this.x][this.y] = 1;
            }
        }
        if (i == 3) {
            this.x = 1;
            this.y = this.width - 1;
            this.table[this.x][this.y] = 1;
            while (this.subUp()) {
                this.goUp();
                this.table[this.x][this.y] = 1;
            }
        }
        this.endX = this.x;
        this.endY = this.y;
        // randomizes the player position somewhere close to the middle
        this.y = this.width / 2;
        this.x = this.length / 2;
        while (this.table[this.x][this.y] == 0) {
            this.x++;
        }
    }

    // tries to move the player up in the maze
    public boolean up() {
        if (!subUp()) {
            this.goUp();
            if (this.table[this.x][this.y] == 2) {
                this.goUp();

            }
            return true;
        } else
            return false;
    }

    // tries to move the player rigth in the maze
    public boolean right() {
        if (!subRight()) {
            this.goRight();
            if (this.table[this.x][this.y] == 2) {
                this.goRight();

            }
            return true;
        } else
            return false;
    }

    // tries to move the player down in the maze
    public boolean down() {
        if (!subDown()) {
            this.goDown();
            if (this.table[this.x][this.y] == 2) {
                this.goDown();

            }
            return true;
        } else
            return false;
    }

    // tries to move left the player
    public boolean left() {
        if (!subLeft()) {
            this.goLeft();
            if (this.table[this.x][this.y] == 2) {
                this.goLeft();
            }
            return true;
        } else
            return false;
    }

    // get seed for this game
    public long seed() {
        return this.seed;
    }

    // set seed for this maze
    public void seed(long seed) {
        this.seed = seed;
    }

    // sets the location of the player
    public void setLocation(int posx, int posy) {
        this.y = posy;
        this.x = posx;
    }

    private boolean bridgeRight() {
        if (this.x + 5 < this.length && this.table[this.x + 2][this.y] == 1
                && this.subRight() && this.subDownRight()
                && this.subUpperRight()) {
            int tmp = this.x;
            this.x = this.x + 4;
            if (this.getArea()) {
                this.x = tmp;
                return true;
            } else {
                this.x = tmp;
                return false;
            }
        }
        return false;
    }

    // cheacks if carving a brdige down is allowd in the maze
    private boolean bridgeDown() {
        if (this.y + 5 < this.width && this.table[this.x][this.y + 2] == 1
                && this.subDownRight() && this.subDownLeft() && this.subDown()) {
            int tmp = this.y;
            this.y = this.y + 4;
            if (this.getArea()) {
                this.y = tmp;
                return true;
            } else {
                this.y = tmp;
                return false;
            }
        }
        return false;
    }

    // cheacks if carving a bridge left is allowd in the maze
    private boolean bridgeLeft() {
        if (this.x - 5 > -1 && this.table[this.x - 2][this.y] == 1
                && this.subLeft() && this.subDownLeft() && this.subUpperLeft()) {
            int tmp = this.x;
            this.x = this.x - 4;
            if (this.getArea()) {
                this.x = tmp;
                return true;
            } else {
                this.x = tmp;
                return false;
            }
        }
        return false;
    }

    // cheacks if carving a bridge up is allowd
    private boolean bridgeUp() {
        if (this.y - 5 > -1 && this.table[this.x][this.y - 2] == 1
                && this.subUp() && this.subUpperRight() && this.subUpperLeft()) {
            int tmp = this.y;
            this.y = this.y - 4;
            if (this.getArea()) {
                this.y = tmp;
                return true;
            } else {
                this.y = tmp;
                return false;
            }
        }
        return false;
    }

    // allows the setting of the end postion of the maze
    public void setEnd(int posx, int posy) {
        this.endX = posx;
        this.endY = posy;
    }

    // a boolean to cheack if the game has ended
    public boolean gameOver() {
        if (this.endX == this.x && this.endY == this.y) {
            return true;
        } else
            return false;
    }
}
