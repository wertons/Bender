public class Cell {
    String type;
    boolean visited = false;



    public void visit() {
        this.visited = true;
    }


    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }


    Cell(String type) {
        this.type = type;

    }
}
