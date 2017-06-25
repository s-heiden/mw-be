package at.fhtw.mw.be.service;

import at.fhtw.mw.be.dal.DAL;
import at.fhtw.mw.be.dal.SQLiteDAL;
import at.fhtw.mw.be.model.Workout;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("workoutService")
@Transactional
public class WorkoutServiceImpl implements WorkoutService {

    private final DAL dal = new SQLiteDAL("Database");

    public WorkoutServiceImpl() {
    }

    @Override
    public void createWorkout(Workout workout) {
        dal.createWorkout(workout);
    }

    @Override
    public void deleteWorkout(String name) {
        dal.deleteWorkout(name);
    }

    @Override
    public boolean existsWorkout(String name) {
        return dal.existsWorkout(name);
    }

    @Override
    public String getWorkout(String name) {
        return dal.getWorkout(name).toJson();
    }

    @Override
    public String getWorkouts() {
        if (dal.getWorkouts().size() == 0) {
            return "";
        }

        String string = "[";
        for (Workout w : dal.getWorkouts()) {
            string += w.toJson();
            string += ",";
        }
        string = string.substring(0, string.length() - 1);
        string += "]";
        return string;
    }

    @Override
    public void updateWorkout(Workout workout) {
        dal.updateWorkout(workout);
    }

    @Override
    public void clearWorkouts() {
        dal.clearWorkouts();
    }

}
