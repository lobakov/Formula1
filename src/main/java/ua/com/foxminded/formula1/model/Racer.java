package ua.com.foxminded.formula1.model;

public class Racer {

    private String name;
    private String team;
    private String abbreviation;
    
    public Racer(String abbreviation, String name, String team) {
        this.abbreviation = abbreviation;
        this.name = name;
        this.team = team;        
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getAbbreviation() {
        return this.abbreviation;
    }
    
    public void setTeam(String team) {
        this.team = team;
    }

    public String getTeam() {
        return this.team;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return this.abbreviation + ": " + this.name + ", " + this.team + " team";
    }
}
