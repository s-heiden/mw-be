package at.fhtw.mw.be.model;

public class Workout {

    private String name;
    private String sets;

    /**
     * Custom constructor.
     */
    public Workout(String name, String sets) {
        this.name = name;
        this.sets = sets;
    }

    /**
     * Default Constructor.
     */
    public Workout() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSets() {
        return sets;
    }

    public void setSets(String sets) {
        this.sets = sets;
    }

    /**
     * Returns a JSON representation of this object.
     */
    public String toJson() {
        return "{name:\"" + name + "\",sets: " + sets + "}";
    }
    
    public Workout fromJson(String json){
        return null;
    }

}
