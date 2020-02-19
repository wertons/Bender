public class Bender {
    int maxHeight;
    int maxWidth;

    Cell[][] map;

    Bender(String map) {

        //Test size
        int counter = 0;
        int finalCounter = 0;
        for (int i = 0; i < map.length(); i++) {
            if (map.charAt(i) == '\n') {
                counter = 0;
                maxHeight++;
            } else {
                counter ++;
            }
            if (counter > finalCounter){
                finalCounter++;
            }
        }
        maxWidth = finalCounter;
        maxHeight ++;

        map = map;
    }

    public static void main(String[] args) {

    }

    String run() {
        return "potat";
    }

    public String bestRun() {
        return "";
    }
}
