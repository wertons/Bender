import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class Bender {
    //We initiate the global variables
    Cell start; //The initial position
    Cell goal; //The target position
    Cell current; //The current position
    Cell[][] map; //All the possible positions

    boolean invert = false; //The current direction priority
    char[] directions = new char[]{
            'S',
            'E',
            'N',
            'W'
    }; //The default direction priority
    char direction = directions[0]; //The current direction, by default South
    String path = ""; //Here we store the direction in which we take every movement

    List<Cell> teleList = new ArrayList<Cell>(); //List of the teleporters


    public Bender(String mapa) {
        String[] split = mapa.split("\n"); //We store the map as a array of rows, we define the rows by the line jumps('\n')

        //We have the rows, now to make the map we need the columns
        int max = 0;
        for (String s : split) {
            if (s.length() > max) {
                max = s.length();//The longest column
            }
        }

        map = new Cell[split.length][max];//The map is a empty bi-dimensional array

        //Create a new cell for each position on the map and store it in its respective position
        for (int i = 0; i < split.length; i++) {
            for (int j = 0; j < split[i].length(); j++) {

                map[i][j] = new Cell(split[i].charAt(j), i, j);

                //Store the start, goal and teleport cells
                if (map[i][j].type.equals("start")) {
                    start = map[i][j];
                }
                if (map[i][j].type.equals("goal")) {
                    goal = map[i][j];
                }
                if (map[i][j].type.equals("teleport")) {
                    teleList.add(map[i][j]);
                }

            }
        }

    }

    public String run() {
        //Establish the current cell at the starting point
        current = start;
        //Check if we are at the goal, if not...
        while (current != goal) {
            //look forward, if not a wall...
            if (checkWall()) {
                //move forward
                move();
                //If we moved into a teleporter...
                if (current.type.equals("teleport")) {
                    //teleport
                    teleport();
                }
                //If we moved into a invert cell
                if (current.type.equals("invert")) {
                    //invert
                    invert();
                }

            } else {
                //If we see a wall turn around until we don't see one
                turn();
            }
            //Check if we are in a infinite loop
            if (infinite()) {
                return null;
            }
        }
        //Return the path to the goal
        return path;
    }

    public void turn() {
        //In each pre-defined direction...
        for (char c : directions) {
            //look forward for a wall
            if (checkWall()) {
                //If there is no wall we're done
                break;
            }
            //If there is a wall try the next direction
            direction = c;
        }
    }

    public void move() {
        //Store the instance of x and y in order to make less calls to them
        int x = current.x;
        int y = current.y;

        //Move one cell in the pre established direction
        switch (this.direction) {
            case 'S':
                current = map[x + 1][y];
                break;
            case 'E':
                current = map[x][y + 1];
                break;
            case 'N':
                current = map[x - 1][y];
                break;
            case 'W':
                current = map[x][y - 1];
                break;
        }
        //Store the movement in the path
        path += this.direction;
    }

    public boolean checkWall() {
        //Store the instance of x and y in order to make less calls to them
        int x = current.x;
        int y = current.y;
        Cell target = new Cell();

        //Try to get the next cell in the pre established direction
        try {
            switch (this.direction) {
                case 'S':
                    target = map[x + 1][y];
                    break;
                case 'E':
                    target = map[x][y + 1];
                    break;
                case 'N':
                    target = map[x - 1][y];
                    break;
                case 'W':
                    target = map[x][y - 1];
                    break;
            }
        } catch (Exception ignore) {
            //We have to try it in the case that there is no cell in the direction we are looking at
        }

        //Return true if the next cell over is not a wall, false if it is
        return !target.type.equals("wall");


    }

    public Cell teleport() {
        //Remove the current teleport from the list
        for (int i = 0; i < teleList.size(); i++) {
            if (teleList.get(i) == current) {
                teleList.remove(i);
            }
        }

        Cell baseCell = teleList.get(0);
        //Store the instance of x and y in order to make less calls to them
        int x = current.x;
        int y = current.y;

        //Set the default closest teleport as the first of the list
        int base = (Math.abs(baseCell.x - x)) + (Math.abs(baseCell.y - y));
        int idx = 0;
        //For each teleporter in the list...
        for (int c = 1; c < teleList.size(); c++) {
            //we instance it for a more efficient run-time
            Cell tmpCell = teleList.get(c);
            //we then get the distance from the current teleporter to the current cell
            int tmpBase = (Math.abs(tmpCell.x - x)) + (Math.abs(tmpCell.y - y));
            //If the current teleporter is closer than the base one we set the new one as the base one
            if (tmpBase < base) {
                idx = c;
                base = tmpBase;
                baseCell = teleList.get(0);
            } else if (tmpBase == base) { //If the distance is the same we have to obtain the one with the shortest angle
                if (preferableTeleport(current, baseCell, tmpCell)) {
                    //If the new one has a shorter angle we set it as the new base
                    idx = c;
                    baseCell = teleList.get(c);
                }
            }
        }
        //We change our position to the closest teleport
        current = teleList.get(idx);
        //We add the original teleport back to the list for potential future teleportations
        teleList.add(current);
        return teleList.get(idx);


    }

    public boolean preferableTeleport(Cell reference, Cell cell1, Cell cell2) {
        //Get the tangent of two points
        double angle1 = Math.toDegrees(Math.atan2((cell1.y - reference.y), (cell1.x - reference.x)));
        angle1 -= 180;
        double angle2 = Math.toDegrees(Math.atan2((cell2.y - reference.y), (cell2.x - reference.x)));
        angle2 -= 180;

        //Return true if the first angle is bigger than the second one
        return Math.abs(angle1) > Math.abs(angle2);
    }

    public void invert() {
        //Switch the direction priorities
        if (!invert) {
            directions = new char[]{
                    'N',
                    'W',
                    'S',
                    'E'
            };
            invert = true;
        } else {
            directions = new char[]{
                    'S',
                    'E',
                    'N',
                    'W'
            };
            invert = false;
        }
    }

    public boolean infinite() {
        //As there are 8 possible types of movements in our logic we can define that in the case that we pass by the
        // same cell more than 8 times we have entered a infinite loop
        current.visits++;
        return current.visits >= 8;
    }

    public int bestRun() {
        //We set the heuristic of each cell
        setHeuristic();
        //We return the heuristic of the starting point as it is the minimum distance to the goal
        return map[start.x][start.y].heuristic;
    }

    public void setHeuristic() {
        //We create two temporary lists in order to assign the heuristic value to each cell
        List<Cell> openList = new LinkedList<Cell>();
        List<Cell> closedList = new LinkedList<Cell>();
        //We set the value of the goal as 0
        map[goal.x][goal.y].heuristic = 0;
        //We add the goal to the open list
        openList.add(map[goal.x][goal.y]);
        boolean found = false; //We use this boolean to determine when to end the loop
        //Until we find the starting point...
        while (!found) {
            //for each cell in the open list...
            for (Cell cell : openList
            ) {
                //get it's neighbors...
                List<Cell> neighbors = assignNeighbors(cell);
                //for each neighbour...
                for (Cell neighbour : neighbors) {
                    //if they are the starting point end the loop
                    if (neighbour.type.equals("start")) {
                        found = true;
                        break;
                    }
                    //add them to the closed list
                    closedList.add(neighbour);
                }
            }
            //The open list becomes a copy of the closed list
            openList = closedList;
            //We reset the closed list
            closedList = new LinkedList<Cell>();
        }

    }

    public List<Cell> assignNeighbors(Cell cell) {
        //We create the neighbors to the current cell
        List<Cell> neighbors = new LinkedList<>();
        for (int i = 0; i < 4; i++) {
            switch (i) {
                case 0:
                    current = map[cell.x + 1][cell.y];
                    break;
                case 1:
                    current = map[cell.x - 1][cell.y];
                    break;
                case 2:
                    current = map[cell.x][cell.y + 1];
                    break;
                case 3:
                    current = map[cell.x][cell.y - 1];
                    break;
            }
            //If the neighbour is a teleporter we add it's exit instead as the neighbour
            if (current.type.equals("teleport")) {
                Cell tmp = teleport();
                map[tmp.x][tmp.y].heuristic = cell.heuristic + 1;
                neighbors.add(tmp);
                break;
            }
            //If the neighbour is a walkable cell we assign it the heuristic and add it as a neighbour
            if (current.heuristic == null && !current.type.equals("wall")) {
                current.heuristic = cell.heuristic + 1;
                neighbors.add(current);
            }

        }
        return neighbors;
    }


}

class Cell {
    //This are the attributes of each cell, although not all of them are necessary
    String type; //Defines the function of the cell. Required
    int x; //Defines the column of the map in which the cell can be found. Required
    int y; //Defines the row of the map in which the cell can be found. Required
    Integer heuristic = null; //Defines the distance from the cell to the goal
    int visits; //Counts the number of instances in which the path has gone through the cell

    public Cell() {

    }

    public Cell(char type, int x, int y) {
        String tmpStr = "";
        switch (type) {
            case '#':
                tmpStr = "wall";
                break;
            case 'X':
                tmpStr = "start";
                break;
            case '$':
                tmpStr = "goal";
                break;
            case 'T':
                tmpStr = "teleport";
                break;
            case 'I':
                tmpStr = "invert";
                break;
            default:
                tmpStr = "void";
                break;
        }
        this.type = tmpStr;

        this.x = x;
        this.y = y;


    }

}