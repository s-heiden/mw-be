package at.fhtw.mw.be.model;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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
     * Returns the name of this object.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of this object.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the JSON string which represents the sets of this object.
     */
    public String getSets() {
        return sets;
    }

    /**
     * Sets the JSON string which represents the sets of this object.
     */
    public void setSets(String sets) {
        this.sets = sets;
    }

    public String toJson() {
        return "{ \"name\" : \"" + name + "\", \"sets\" : " + sets + " }";
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Workout other = (Workout) obj;
        return name.equalsIgnoreCase(other.name);
    }

    /**
     * Creates an object from the provided JSON string.
     */
    public static Workout fromJson(String json) {
        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(json);
        if (element.isJsonObject()) {
            JsonObject workoutJO = element.getAsJsonObject();
            String name = workoutJO.get("name").getAsString();
            String sets = workoutJO.get("sets").toString();
            return new Workout(name, sets);            
        }
        return null;
    }

    /**
     * Creates an object from the provided SQL ResultSet.
     */
    public static Workout fromResultSet(ResultSet rs) throws SQLException {
        Workout workout = new Workout();
        workout.setName(rs.getString("name"));
        workout.setSets(rs.getString("sets"));
        return workout;
    }

}
