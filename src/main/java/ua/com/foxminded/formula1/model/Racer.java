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

    public String getTeam() {
        return this.team;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Racer)) {
            return false;
        }
        Racer another = (Racer) obj;
        return this.abbreviation.equals(another.abbreviation) && this.name.equals(another.name)
                && this.team.equals(another.team);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + this.abbreviation.hashCode();
        hash = 89 * hash + this.name.hashCode();
        hash = 89 * hash + this.team.hashCode();
        return hash;
    }

    @Override
    public String toString() {
        return this.abbreviation + ": " + this.name + ", " + this.team + " team";
    }
}
