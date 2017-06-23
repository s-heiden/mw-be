package at.fhtw.mw.be.dal;

import at.fhtw.mw.be.model.Workout;
import java.util.ArrayList;
import java.util.List;

public class DataAccessLayer {

    private static DataAccessLayer instance;
    private List<Workout> workouts;

    private DataAccessLayer() {
        workouts = new ArrayList<Workout>();
    }

    public static DataAccessLayer getInstance() {
        if (instance == null) {
            instance = new DataAccessLayer();
        }
        return instance;
    }

    /**
     * Adds a new workout to the DB. The workout is only added if no workout with the same name does already exist.
     */
    public void addWorkout(Workout workout) {
        if (!exists(workout)) {
            workouts.add(workout);
        }
    }

    /**
     * Returns the workout with the provided name or null if such object does not exist.
     */
    public Workout getWorkout(String name) {
        for (Workout w : workouts) {
            if (w.getName().equals(name)) {
                return w;
            }
        }
        return null;
    }

    /**
     * Overwrites the workout with the same name as provided in the argument. The call is ignored if no such workout
     * exists.
     */
    public void saveWorkout(Workout workout) {
        if (getWorkout(workout.getName()) != null) {
            getWorkout(workout.getName()).setSets(workout.getSets());
        }
    }

    /**
     * Deletes the workout with the provided name. Does nothing if name does not exist.
     */
    public void deleteWorkout(String name) {
        for (Workout w : workouts) {
            if (w.getName().equals(name)) {
                workouts.remove(w);
            }
        }
    }

    /**
     * Returns a list of all workouts.
     */
    public List<Workout> getWorkouts() {
        return workouts;
    }

    /**
     * Returns true if an equally named workout already exists.
     */
    private boolean exists(Workout workout) {
        for (Workout w : workouts) {
            if (w.getName().equals(workout.getName())) {
                return true;
            }
        }
        return false;
    }

}
