package at.fhtw.mw.be.model;

import java.sql.ResultSet;
import java.sql.SQLException;

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

    /**
     * Returns the name of this Workout.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of this Workout.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the JSON string which represents the sets of this Workout.
     */
    public String getSets() {
        return sets;
    }

    /**
     * Sets the JSON string which represents the sets of this Workout.
     */
    public void setSets(String sets) {
        this.sets = sets;
    }

    /**
     * Returns a JSON representation of this object.
     */
    public String toJson() {
        return "{ name : \"" + name + "\", sets : " + sets + " } ";
    }

    /**
     * Creates a Workout object from the provided JSON string.
     */
    public Workout fromJson(String json) {
        // TODO: implement
        return null;
    }

    /**
     * Creates a Workout object from the provided ResultSet. Throws an exception if something goes wrong.
     */
    public static Workout fromResultSet(ResultSet rs) throws SQLException {
        Workout workout = new Workout();
        workout.setName(rs.getString("name"));
        workout.setSets(rs.getString("sets"));
        return workout;
    }

}
