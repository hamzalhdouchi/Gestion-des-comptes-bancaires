package Entity;

import java.util.Date;

public class Versement extends Operation {

    private String source;

    public Versement( String compteId,  Date date ,double montant,String source ) {
        super(compteId,date, montant);
        this.source = source;
    }
    
    public String getSource() {
        return source;
    }
    public void setSource(String source) {
        this.source = source;
    }

    public String getType() {
        return "versement";
    }
    

}

