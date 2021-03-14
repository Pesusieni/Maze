package Gui;

import java.io.*;

public class SaveLoad {

    public void save(Maze maze) {
        try {
            // try making a file
            // only one save is allowed and creates a txt for it
            FileWriter fstream = new FileWriter("game.txt");
            BufferedWriter out = new BufferedWriter(fstream);
            out.write("Size," + maze.getLength() + "," + maze.getWidth() + "\n");
            out.write("Seed," + Long.toString(maze.seed()) + "\n");
            out.write("Player," + maze.positionX() + "," + maze.positionY()
                    + "\n");
            // Close the output stream
            out.close();
        } catch (Exception e) {
            // Catch exception
            System.err.println("Error: " + e.getMessage());
        }
    }

    public Maze load() {
        try {
            // tries to load one game from the file
            FileInputStream stream = new FileInputStream("game.txt");
            // Get the object of DataInputStream
            DataInputStream data = new DataInputStream(stream);
            BufferedReader br = new BufferedReader(new InputStreamReader(data));
            String strLine;
            // Read File Line By Line
            Maze maze = null;
            while ((strLine = br.readLine()) != null) {
                String size[] = strLine.split(",");

                if (size[0].equalsIgnoreCase("size")) {
                    maze = new Maze(Integer.parseInt(size[1]),
                            Integer.parseInt(size[2]));
                }

                if (size[0].equalsIgnoreCase("player")) {
                    maze.setLocation(Integer.parseInt(size[1]),
                            Integer.parseInt(size[2]));
                }
                if (size[0].equalsIgnoreCase("seed")) {
                    Mazealgo algo = new Mazealgo(Long.parseLong(size[1]));
                    algo.make(maze);
                }
            }
            // Close the input stream
            data.close();

            return maze;

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            return null;
        }
    }
}
