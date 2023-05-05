package gad.maze;

import java.util.HashSet;

enum Direction {UP, RIGHT, DOWN, LEFT}

record State (Direction dir, int x, int y) {

}

public class Walker {

    private final boolean[][] maze;
    private final Result result;

    private int posX = 1;
    private int posY = 0;
    private Direction dir = Direction.DOWN;
    private HashSet<State> previousStates = new HashSet<State>();

    public Walker(boolean[][] maze, Result result) {
        this.maze = maze;
        this.result = result;
    }

    private int getDxInDirection(Direction dir) {
        return switch (dir) {
            case LEFT -> -1;
            case RIGHT -> 1;
            case UP, DOWN -> 0;
        };
    }

    private int getDyInDirection(Direction dir) {
        return switch (dir) {
            case UP -> -1;
            case DOWN -> 1;
            case LEFT, RIGHT -> 0;
        };
    }

    private Direction getRelDir(int n) {
        int index = (dir.ordinal() + n) % Direction.values().length;
        if (index < 0)
            index = Direction.values().length + index;
        return Direction.values()[index];
    }

    public boolean walk() {
        result.addLocation(posX, posY);

        for (int i = 0; i < 999999; i++) {
            { // checking the right side
                Direction rightDir = getRelDir(1);
                int newX = posX + getDxInDirection(rightDir);
                int newY = posY + getDyInDirection(rightDir);
                boolean wallOnTheRight = wallAt(newX, newY);
                if (!wallOnTheRight) {
                    dir = rightDir; // turn right
                    move(newX, newY);
                    continue;
                }
            }

            { // checking ahead (forwards)
                int newX = posX + getDxInDirection(dir);
                int newY = posY + getDyInDirection(dir);
                boolean wallAhead = wallAt(newX, newY);
                if (!wallAhead) { // move forward
                    move(newX, newY);
                } else {
                    dir = getRelDir(-1); // turn left
                }
            }

            State currentState = new State(dir, posX, posY);

            // exit conditions
            if(previousStates.contains(currentState))
                return false;
            if (posX == 1 && posY == 0 && i > 0)
                return false;
            if (posX == maze.length - 1 && posY == maze[0].length - 2)
                return true;

            previousStates.add(currentState);
        }

        return false;
    }

    private boolean wallAt(int x, int y) {
        if (x < 0 || y < 0 || x >= maze.length || y >= maze[0].length)
            return true;
        return maze[x][y];
    }

    private void move(int newX, int newY) {
        posX = newX;
        posY = newY;
        result.addLocation(posX, posY);
    }

    public static void main(String[] args) {
        boolean[][] maze = Maze.generateStandardMaze(89, 89);
        StudentResult result = new StudentResult();
        Walker walker = new Walker(maze, result);
        System.out.println(walker.walk());
        Maze.draw(maze, result);
    }
}
