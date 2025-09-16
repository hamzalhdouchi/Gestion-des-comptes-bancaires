package Entity;

public class Versement extends Operation {

    private String source;

    public Versement(String source) {
        this.source = source;
    }
    public String getSource() {
        return source;
    }
    public void setSource(String source) {
        this.source = source;
    }

}

