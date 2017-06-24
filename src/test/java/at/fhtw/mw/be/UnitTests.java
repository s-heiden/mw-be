package at.fhtw.mw.be;

import at.fhtw.mw.be.dal.MockDAL;
import at.fhtw.mw.be.model.Workout;
import org.junit.Assert;
import org.junit.Test;

public class UnitTests {

    @Test
    public void addWorkout_should_insert_element() {
        MockDAL dal = new MockDAL();
        Workout w1 = new Workout("workout1", "Complex_JSON_String1");
        Workout w2 = new Workout("workout2", "Complex_JSON_String2");
        dal.addWorkout(w1);
        dal.addWorkout(w2);
        Assert.assertEquals(2, dal.getWorkouts().size());
    }
    
    @Test
    public void getWorkout_should_return_correct_workout() {
        MockDAL dal = new MockDAL();
        Workout w1 = new Workout("workout1", "Complex_JSON_String1");
        Workout w2 = new Workout("workout2", "Complex_JSON_String2");
        dal.addWorkout(w1);
        dal.addWorkout(w2);
        Assert.assertEquals("Complex_JSON_String2", dal.getWorkout("workout2").getSets());
        Assert.assertEquals("Complex_JSON_String1", dal.getWorkout("workout1").getSets());
    }

    @Test
    public void getWorkout_should_return_null_if_no_such_object_exists() {
        MockDAL dal = new MockDAL();
        Workout w1 = new Workout("workout1", "Complex_JSON_String1");
        Workout w2 = new Workout("workout2", "Complex_JSON_String2");
        dal.addWorkout(w1);
        dal.addWorkout(w2);
        Assert.assertNull(dal.getWorkout("workout3"));
    }

    @Test
    public void saveWorkout_should_alter_existing_object() {
        MockDAL dal = new MockDAL();
        Workout w1 = new Workout("workout1", "Complex_JSON_String1");
        Workout w2 = new Workout("workout2", "Complex_JSON_String2");
        dal.addWorkout(w1);
        dal.addWorkout(w2);
        Workout w3 = new Workout("workout1", "changed");
        dal.saveWorkout(w3);
        Assert.assertEquals("changed", dal.getWorkout("workout1").getSets());
    }

    @Test
    public void getWorkouts_should_return_existing_elements() {
        MockDAL dal = new MockDAL();
        Workout w1 = new Workout("workout1", "Complex_JSON_String1");
        Workout w2 = new Workout("workout2", "Complex_JSON_String2");
        dal.addWorkout(w1);
        dal.addWorkout(w2);
        Assert.assertEquals(2, dal.getWorkouts().size());
    }

    @Test
    public void deleteWorkout_should_remove_element() {
        MockDAL dal = new MockDAL();
        Workout w1 = new Workout("workout1", "Complex_JSON_String1");
        Workout w2 = new Workout("workout2", "Complex_JSON_String2");
        dal.addWorkout(w1);
        dal.addWorkout(w2);        
        dal.deleteWorkout("workout1");
        dal.deleteWorkout("workout2");
        Assert.assertEquals(0, dal.getWorkouts().size());
    }

    @Test
    public void getWorkouts_should_return_empty_list() {
        MockDAL dal = new MockDAL();
        Assert.assertEquals(0, dal.getWorkouts().size());
    }

}
