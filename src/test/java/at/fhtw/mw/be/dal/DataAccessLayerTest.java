/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.fhtw.mw.be.dal;

import at.fhtw.mw.be.model.Workout;
import java.util.List;
import static org.junit.Assert.fail;
import org.junit.Test;

/**
 *
 * @author stefan
 */
public class DataAccessLayerTest {
    
    public DataAccessLayerTest() {
    }

    @Test
    public void testDal() {
        DataAccessLayer dal = DataAccessLayer.getInstance();
        
        System.out.println("Adding two workouts.");
        Workout w1 = new Workout("Erstes Workout", "[{exercise: exercise1, duration: '2017-06-07T23:00:10.828Z'},{exercise: exercise2, duration: '2017-06-07T23:00:15.828Z'},{exercise: exercise3, duration: '2017-06-07T23:00:05.828Z'}]");
        Workout w2 = new Workout("Zweites Workout", "[{exercise: exercise4, duration: '2017-06-07T23:00:30.828Z'},{exercise: exercise2, duration: '2017-06-07T23:00:45.828Z'},{exercise: exercise3, duration: '2017-06-07T23:00:12.828Z'}]");
        System.out.println("Changing the first's sets to #");
       
        dal.addWorkout(w1);
        w1.setSets("#");
        System.out.println("Saving changed workout and adding to DAL");
        dal.saveWorkout(w1);
        dal.addWorkout(w2);
                
        List<Workout> workouts = dal.getWorkouts();
        System.out.println("Showing all in getWorkouts()");
        for (Workout w : workouts){
            System.out.println(w.toJson());
        }
       
        System.out.println("Deleting 'Erstes Workout'");
        dal.deleteWorkout("Erstes Workout");
        for (Workout w : workouts){
            System.out.println(w.toJson());
        }
        
        System.out.println("Getting only 'zweites Workout'");        
        System.out.println(dal.getWorkout("Zweites Workout").toJson());
        
        
        
        fail();
        
    }

    
}
