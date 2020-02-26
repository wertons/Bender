import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class Bender {
    Cell start;
    Cell goal;
    Cell current;
    Cell[][] map;

    boolean invert = false;
    char[] directions = new char[]{
            'S',
            'E',
            'N',
            'W'
    };
    char direction = directions[0];
    String path = "";
    String directionalPath = "S";

    List<Cell> teleList = new ArrayList<Cell>();

    List<Cell> openList = new LinkedList<Cell>();
    List<Cell> closedList = new LinkedList<Cell>();

    public Bender(String mapa) {
        String[] split = mapa.split("\n");

        int max = 0;
        for (String s : split) {
            if (s.length() > max) {
                max = s.length();
            }
        }

        map = new Cell[split.length][max];

        for (int i = 0; i < split.length; i++) {
            for (int j = 0; j < split[i].length(); j++) {

                map[i][j] = new Cell(split[i].charAt(j), i, j);

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
        current = start;
        while (current != goal) {
            if (checkWall()) {
                move();

                if (current.type.equals("teleport")) {
                    teleport();
                }
                if (current.type.equals("invert")) {
                    invert();
                }


            } else {
                turn();

            }
            if (infinite()) {
                return null;
            }


        }
        return path;
    }

    public void turn() {
        for (char c : directions) {
            if (checkWall()) {
                break;
            }
            direction = c;
        }
    }

    public void move() {
        int x = current.x;
        int y = current.y;

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
        path += this.direction;
        try {
            if (directionalPath.charAt(directionalPath.length() - 1) != direction) {
                directionalPath += direction;
            }
        } catch (Exception ignore) {

        }
    }

    public boolean checkWall() {
        int x = current.x;
        int y = current.y;
        Cell objetivo = new Cell();

        try {


            switch (this.direction) {
                case 'S':
                    objetivo = map[x + 1][y];
                    break;
                case 'E':
                    objetivo = map[x][y + 1];
                    break;
                case 'N':
                    objetivo = map[x - 1][y];
                    break;
                case 'W':
                    objetivo = map[x][y - 1];
                    break;

            }
        } catch (Exception ignore) {

        }
        return !objetivo.type.equals("wall");


    }

    public void teleport() {
        Cell tmpStrg = current;
        for (int i = 0; i < teleList.size(); i++) {
            if (teleList.get(i) == tmpStrg) {
                teleList.remove(i);
            }
        }

        Cell baseCell = teleList.get(0);
        int base = (Math.abs(teleList.get(0).x - current.x)) + (Math.abs(teleList.get(0).y - current.y));
        int idx = 0;
        for (int c = 1; c < teleList.size(); c++) {
            int tmpBase = (Math.abs(teleList.get(c).x - current.x)) + (Math.abs(teleList.get(c).y - current.y));
            if (tmpBase < base) {
                idx = c;
                base = tmpBase;
                baseCell = teleList.get(0);
            } else if (tmpBase == base) {
                if (preferableTeleport(tmpStrg, baseCell, teleList.get(c))) {
                    idx = c;
                    baseCell = teleList.get(c);
                }
            }
        }
        current = teleList.get(idx);
        teleList.add(tmpStrg);


    }

    public boolean preferableTeleport(Cell reference, Cell cell1, Cell cell2) {
        double angle1 = Math.toDegrees(Math.atan2((cell1.y - reference.y), (cell1.x - reference.x)));
        angle1 -= 180;
        double angle2 = Math.toDegrees(Math.atan2((cell2.y - reference.y), (cell2.x - reference.x)));
        angle2 -= 180;
        return Math.abs(angle1) > Math.abs(angle2);
    }

    public void invert() {
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
        current.visits++;
        return current.visits >= 8;

    }

    public int bestRun() {
        setHeuristic();
        return map[start.x][start.y].heuristic;
    }

    public void setHeuristic() {
        List<Cell> assigned = new LinkedList<Cell>();
        List<Cell> tmp = new LinkedList<Cell>();
        map[goal.x][goal.y].heuristic = 0;
        assigned.add(map[goal.x][goal.y]);
        boolean found = false;
        while (!found) {
            for (Cell cell : assigned
            ) {
                List<Cell> neighbors = assignNeighbors(cell);
                for (Cell neighbor : neighbors) {
                    if (neighbor.type.equals("start")) {
                        found = true;
                        break;
                    }
                    tmp.add(neighbor);
                }


            }
            assigned = tmp;
            tmp = new LinkedList<Cell>();
        }

    }


    public List<Cell> assignNeighbors(Cell cell) {
        List<Cell> neighbors = new LinkedList<>();
        for (int i = 0; i < 4; i++) {
            switch (i) {
                case 0:
                    if (map[cell.x + 1][cell.y].type == "teleport") {
                        Cell tmp = closestTeleport(map[cell.x + 1][cell.y]);
                        map[tmp.x][tmp.y].heuristic = cell.heuristic + 1;
                        neighbors.add(closestTeleport(map[cell.x + 1][cell.y]));
                    }

                    if (map[cell.x + 1][cell.y].heuristic == null && !map[cell.x + 1][cell.y].type.equals("wall")) {
                        map[cell.x + 1][cell.y].heuristic = cell.heuristic + 1;
                        neighbors.add(map[cell.x + 1][cell.y]);
                    }
                    break;
                case 1:
                    if (map[cell.x - 1][cell.y].type == "teleport") {
                        Cell tmp = closestTeleport(map[cell.x - 1][cell.y]);
                        map[tmp.x][tmp.y].heuristic = cell.heuristic + 1;
                        neighbors.add(closestTeleport(map[cell.x - 1][cell.y]));
                    }
                    if (map[cell.x - 1][cell.y].heuristic == null && !map[cell.x - 1][cell.y].type.equals("wall")) {
                        map[cell.x - 1][cell.y].heuristic = cell.heuristic + 1;
                        neighbors.add(map[cell.x - 1][cell.y]);
                    }
                    break;
                case 2:
                    if (map[cell.x][cell.y + 1].type == "teleport") {
                        Cell tmp = closestTeleport(map[cell.x][cell.y + 1]);
                        map[tmp.x][tmp.y].heuristic = cell.heuristic + 1;
                        neighbors.add(closestTeleport(map[cell.x][cell.y + 1]));
                    }

                    if (map[cell.x][cell.y + 1].heuristic == null && !map[cell.x][cell.y + 1].type.equals("wall")) {
                        map[cell.x][cell.y + 1].heuristic = cell.heuristic + 1;
                        neighbors.add(map[cell.x][cell.y + 1]);

                    }
                    break;
                case 3:
                    if (map[cell.x][cell.y - 1].type == "teleport") {
                        Cell tmp = closestTeleport(map[cell.x][cell.y - 1]);
                        map[tmp.x][tmp.y].heuristic = cell.heuristic + 1;
                        neighbors.add(closestTeleport(map[cell.x][cell.y - 1]));
                    }
                    if (map[cell.x][cell.y - 1].heuristic == null && !map[cell.x][cell.y - 1].type.equals("wall")) {
                        map[cell.x][cell.y - 1].heuristic = cell.heuristic + 1;
                        neighbors.add(map[cell.x][cell.y - 1]);

                    }
                    break;
            }
        }
        return neighbors;
    }

    public Cell closestTeleport(Cell cell) {

        Cell tmpStrg = cell;
        for (int i = 0; i < teleList.size(); i++) {
            if (teleList.get(i).x == tmpStrg.x && teleList.get(i).y == tmpStrg.y) {
                teleList.remove(i);
            }
        }

        Cell baseCell = teleList.get(0);
        int base = (Math.abs(teleList.get(0).x - tmpStrg.x)) + (Math.abs(teleList.get(0).y - tmpStrg.y));
        int idx = 0;
        for (int c = 1; c < teleList.size(); c++) {
            int tmpBase = (Math.abs(teleList.get(c).x - tmpStrg.x)) + (Math.abs(teleList.get(c).y - tmpStrg.y));
            if (tmpBase < base) {
                idx = c;
                base = tmpBase;
                baseCell = teleList.get(0);
            } else if (tmpBase == base) {
                if (preferableTeleport(tmpStrg, baseCell, teleList.get(c))) {
                    idx = c;
                    baseCell = teleList.get(c);
                }
            }
        }
        teleList.add(tmpStrg);

        return teleList.get(idx);

    }

}

class Cell {
    String type;
    int x;
    int y;
    Integer heuristic = null;
    int visits;

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