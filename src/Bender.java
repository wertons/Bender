import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class Bender {
    int maxWidth;
    int maxHeight;

    int roboX;
    int roboY;

    int goalX;
    int goalY;


    char orientation = 'S';
    char[] possOrien = new char[]{'S', 'E', 'N', 'W'};

    boolean inverted = false;

    String path = "";
    Cell[][] mapStruct;

    List<int[]> teleporters = new ArrayList<int[]>();
    String turns = " ";


    public static void main(String[] args) {

    }

    Bender(String map) {

        //Check the dimensions of the map
        int counter = 0;
        int finalCounter = 0;
        for (int i = 0; i < map.length(); i++) {
            if (map.charAt(i) == '\n') {
                counter = 0;
                maxHeight++;
            } else {
                counter++;
            }
            if (counter > finalCounter) {
                finalCounter++;
            }


        }
        maxWidth = finalCounter;
        maxHeight++;

        mapStruct = new Cell[maxHeight][maxWidth];
        String[] mapTMP = map.split("\n");

        for (int i = 0; i < mapTMP.length; i++) {
            for (int j = 0; j < mapTMP[i].length(); j++) {
                char currentCell = mapTMP[i].charAt(j);
                Cell tmpCell = new Cell("");
                switch (currentCell) {
                    case ' ':
                        tmpCell = new Cell("void");
                        break;
                    case '#':
                        tmpCell = new Cell("wall");

                        break;
                    case 'X':
                        this.roboX = i;
                        this.roboY = j;
                        tmpCell = new Cell("start");

                        break;
                    case '$':
                        this.goalX = i;
                        this.goalY = j;
                        tmpCell = new Cell("goal");

                        break;
                    case 'T':
                        teleporters.add(new int[]{i, j});
                        tmpCell = new Cell("teleport");

                        break;
                    case 'I':
                        tmpCell = new Cell("invert");
                }
                mapStruct[i][j] = tmpCell;

                counter++;
            }

        }
    }

    String run() {
        while (!getCell().equals("goal")) {
            mapStruct[roboX][roboY].visit();
            if (getCell().equals("teleport")) {
                teleport();

            }

            if (getCell().equals("invert")) {
                invert();
            }

            if (!lookForward().equals("wall")) {
                this.move();
                path += orientation;
            } else {
                for (char c : possOrien) {
                    orientation = c;
                    if (!lookForward().equals("wall")) break;
                }

                if (checkInfinite()) {
                    return null;
                }

            }
        }
        return path;
    }

    public String getCell() {
        return this.mapStruct[roboX][roboY].getType();
    }

    public void move() {
        switch (orientation) {
            case 'S':
                this.roboX++;
                break;
            case 'N':
                this.roboX--;
                break;
            case 'E':
                this.roboY++;
                break;
            case 'W':
                this.roboY--;
                break;
        }


    }

    public String lookForward() {
        switch (orientation) {
            case 'S':
                return this.mapStruct[roboX + 1][roboY].getType();
            case 'N':
                return this.mapStruct[roboX - 1][roboY].getType();
            case 'E':
                return this.mapStruct[roboX][roboY + 1].getType();
            case 'W':
                return this.mapStruct[roboX][roboY - 1].getType();
        }
        return null;
    }

    public void teleport() {
        int currentTele = 0;
        int currX;
        int currY;
        int closestX = -111111111;
        int closestY = -111111111;


        for (int i = 0; i < teleporters.size(); i++) {
            if (teleporters.get(i)[0] == roboX && teleporters.get(i)[1] == roboY) {
                currentTele = i;
            }
        }

        for (int i = 0; i < teleporters.size(); i++) {
            if (i != currentTele) {
                currX = teleporters.get(i)[0];
                currY = teleporters.get(i)[1];
                if (Math.abs(roboX - currX) + Math.abs(roboY - currY) < Math.abs(roboX - closestX) + Math.abs(roboX - closestY)) {
                    closestX = currX;
                    closestY = currY;
                }

            }
        }

        roboX = closestX;
        roboY = closestY;

    }

    public void invert() {
        if (!inverted) {
            mapStruct[roboX][roboY].setType("void");
            possOrien = new char[]{'N', 'W', 'S', 'E'};
            inverted = true;
        } else {
            mapStruct[roboX][roboY].setType("void");

            possOrien = new char[]{'S', 'E', 'N', 'W'};
            inverted = false;
        }
    }

    public boolean checkInfinite() {
        if (turns.charAt(turns.length() - 1) != orientation) {
            turns += orientation;

        }
        try {
            return turns.substring(turns.length() - 4, turns.length() - 1).equals("NSN");
        } catch (Exception e) {
            return false;
        }


    }


    char prefDir;
    int[] currGoal = new int[2];

    public String bestRun() {
        while (!getCell().equals("goal")){
            currGoal[0] = goalX;
            currGoal[1] = goalY;

            calcPath();
            if (!lookForward().equals("wall")){
                move();
                path += orientation;
            }
        }

        return path;
    }

    public void calcPath(){
        int xGoal = roboX - currGoal[0];
        int yGoal = roboY - currGoal[1];

        if (xGoal > yGoal){
            if (xGoal > 0){
                orientation = 'S';
            } else {
                orientation = 'N';

            }
        } else {
            if (yGoal > 0){
                orientation = 'E';

            } else {
                orientation = 'W';

            }
        }

    }

}
