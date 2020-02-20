public class BenderSupperior {
    int maxWidth;
    int maxHeight;

    int roboX;
    int roboY;

    int goalX;
    int goalY;

    String path = "";
    Cell[][] mapStruct;


    BenderSupperior(String map) {

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

        counter = 0;
        map = map.replace("\n", "");
        for (int i = 0; i < maxHeight; i++) {
            for (int j = 0; j < maxWidth; j++) {
                char currentCell = map.charAt(counter);
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
                }
                mapStruct[i][j] = tmpCell;

                counter++;
            }

        }

    }

    public static void main(String[] args) {

    }

    String run() {

        while (this.roboX != this.goalX || this.roboY != this.goalY) {
            mapStruct[roboX][roboY].visit();

            int xDiff = roboX - goalX;
            int yDiff = roboY - goalY;
            if (xDiff < 0) {
                if (checkNextCell(mapStruct, roboX, roboY, roboX + 1, roboY)) {
                    path += "S";
                    roboX++;
                }
            } else if (yDiff < 0) {
                if (checkNextCell(mapStruct, roboX, roboY, roboX - 1, roboY)) {
                    path += "E";
                    roboY++;
                }
            } else if (xDiff > 0) {
                if (checkNextCell(mapStruct, roboX, roboY, roboX - 1, roboY)) {
                    path += "N";
                    roboX--;
                }
            } else if (yDiff > 0) {
                if (checkNextCell(mapStruct, roboX, roboY, roboX + 1, roboY)) {
                    path += "W";
                    roboY--;
                }
            }


        }
        return path;
    }

    public boolean checkNextCell(Cell[][] map, int xOg, int yOg, int xGoal, int yGoal) {
        if (map[xGoal][yGoal].getType() == "wall") {
            return false;
        } else {
            return true;
        }
    }

    public String bestRun() {
        return "";
    }
}
