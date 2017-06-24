package at.fhtw.mw.be.dal;

import at.fhtw.mw.be.model.Workout;
import java.util.List;

/**
 * A data access layer that outlines basic CRUD operations.
 */
public interface DAL {

    /**
     * Adds a new workout to the DB. The workout is only added if no workout with the same name does already exist.
     */
    void addWorkout(Workout workout);

    /**
     * Deletes the workout with the provided name. Does nothing if name does not exist.
     */
    void deleteWorkout(String name);

    /**
     * Returns true if an equally named workout already exists.
     */
    boolean exists(String name);

    /**
     * Returns the workout with the provided name or null if such object does not exist.
     */
    Workout getWorkout(String name);

    /**
     * Returns a list of all workouts.
     */
    List<Workout> getWorkouts();

    /**
     * Overwrites the workout with the same name as provided in the argument. The call is ignored if no such workout
     * exists.
     */
    void saveWorkout(Workout workout);

    /**
     * Clears all saved objects from the DB.
     */
    public void clear();

}
