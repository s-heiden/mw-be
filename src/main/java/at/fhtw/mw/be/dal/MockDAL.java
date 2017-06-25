package at.fhtw.mw.be.dal;

import at.fhtw.mw.be.model.Workout;
import java.util.ArrayList;
import java.util.List;

public class MockDAL implements DAL {

    private List<Workout> workouts;

    public MockDAL() {
        workouts = new ArrayList<>();
    }

    @Override
    public void createWorkout(Workout workout) {
        if (!existsWorkout(workout.getName())) {
            workouts.add(workout);
        }
    }

    @Override
    public Workout getWorkout(String name) {
        for (Workout w : workouts) {
            if (w.getName().equals(name)) {
                return w;
            }
        }
        return null;
    }

    @Override
    public void updateWorkout(Workout workout) {
        if (getWorkout(workout.getName()) != null) {
            getWorkout(workout.getName()).setSets(workout.getSets());
        }
    }

    @Override
    public void deleteWorkout(String name) {
        for (int i = 0; i < workouts.size(); i++) {
            if (workouts.get(i).getName().equals(name)) {
                workouts.remove(workouts.get(i));
                return;
            }
        }
    }

    @Override
    public List<Workout> getWorkouts() {
        return workouts;
    }

    @Override
    public boolean existsWorkout(String name) {
        for (Workout w : workouts) {
            if (w.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void clearWorkouts() {
        workouts = new ArrayList<>();
    }

}
