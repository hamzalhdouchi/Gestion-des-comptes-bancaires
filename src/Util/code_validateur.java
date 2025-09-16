package Util;

import java.util.Date;

public class code_validateur {


    private String pattern;
    private Date ValidDate;


    public code_validateur(String pattern, Date ValidDate) {
        this.pattern = pattern;
        this.ValidDate = ValidDate;
    }


    public String getPattern() {
        return pattern;
    }


    public void setPattern(String pattern) {
        this.pattern = pattern;
    }


    public Date getValidDate() {
        return ValidDate;
    }


    public void setValidDate(Date ValidDate) {
        this.ValidDate = ValidDate;
    }


    public String checkPatern(){

    }
}
