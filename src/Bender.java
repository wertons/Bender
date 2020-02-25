import java.util.ArrayList;
import java.util.List;

class Bender {
    public static void main(String[] args) {
        double angle1 = Math.toDegrees(Math.atan2((-1 - 0), (-1 - 0)));
        angle1 = angle1 * -1;
        System.out.println(angle1);
    }


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

    List<Cell> teleporters = new ArrayList<Cell>();

    public Bender(String mapa) {
        String[] split = mapa.split("\n");

        int max = 0;
        for (int i = 0; i < split.length; i++) {
            if (split[i].length() > max) {
                max = split[i].length();
            }
        }

        map = new Cell[split.length][max];

        for (int i = 0; i < split.length; i++) {
            for (int j = 0; j < split[i].length(); j++) {

                map[i][j] = new Cell(split[i].charAt(j), i, j);

                if (map[i][j].type == "start") {
                    start = map[i][j];
                }

                if (map[i][j].type == "goal") {
                    goal = map[i][j];
                }
                if (map[i][j].type == "teleport") {
                    teleporters.add(map[i][j]);
                }

            }
        }

    }

    public String run() {
        current = start;
        while (current != goal) {
            if (!checkWall()) {
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
        for (int i = 0; i < directions.length; i++) {
            if (!checkWall()) {
                break;
            }
            direction = directions[i];
        }
    }

    public void move() {
        int x = current.xPosition;
        int y = current.yPosition;

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
        int x = current.xPosition;
        int y = current.yPosition;
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
        if (objetivo.type.equals("wall")) {
            return true;
        } else {
            return false;
        }


    }

    public void teleport() {
        Cell tmpStrg = current;
        for (int i = 0; i < teleporters.size(); i++) {
            if (teleporters.get(i) == tmpStrg) {
                teleporters.remove(i);
            }
        }

        Cell baseCell = teleporters.get(0);
        int base = (Math.abs(teleporters.get(0).xPosition - current.xPosition)) + (Math.abs(teleporters.get(0).yPosition - current.yPosition));
        int idx = 0;
        for (int c = 1; c < teleporters.size(); c++) {
            int tmpBase = (Math.abs(teleporters.get(c).xPosition - current.xPosition)) + (Math.abs(teleporters.get(c).yPosition - current.yPosition));
            if (tmpBase < base) {
                idx = c;
                base = tmpBase;
                baseCell = teleporters.get(0);
            } else if (tmpBase == base) {
                tmpStrg = tmpStrg;
                if (checkClockwise(tmpStrg, baseCell, teleporters.get(c))) {
                    idx = c;
                    baseCell = teleporters.get(c);
                }
            }
        }
        current = teleporters.get(idx);
        teleporters.add(tmpStrg);


    }

    public boolean checkClockwise(Cell reference, Cell cell1, Cell cell2) {
        double angle1 = Math.toDegrees(Math.atan2((cell1.yPosition - reference.yPosition), (cell1.xPosition - reference.xPosition)));
        angle1 -= 135;
        double angle2 = Math.toDegrees(Math.atan2((cell2.yPosition - reference.yPosition), (cell2.xPosition - reference.xPosition)));
        angle2 -= 135;
        if (Math.abs(angle1) > Math.abs(angle2)) {
            return true;
        } else {
            return false;
        }
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
        String findStr = "SNS";
        int lastIndex = 0;
        int count = 0;

        while (lastIndex != -1) {

            lastIndex = directionalPath.indexOf(findStr, lastIndex);

            if (lastIndex != -1) {
                count++;
                lastIndex += findStr.length();
            }
        }
        if (count > 50) {
            return true;
        } else {
            return false;
        }
    }

    public int bestRun() {
        return 1;
    }
}

class Cell {
    String type;
    int xPosition;
    int yPosition;


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

        this.xPosition = x;
        this.yPosition = y;


    }

}