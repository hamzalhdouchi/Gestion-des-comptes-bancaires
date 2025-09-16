package Entity;

public class Retrait extends Operation {

    private String destination;

    public Retrait(String destination) {
        this.destination = destination;
    }

    public String  getDestination() {
        return destination;
    }
    public void setDestination(String destination) {
        this.destination = destination;
    }
}
