package at.fhtw.mw.be;

import at.fhtw.mw.be.dal.DAL;
import at.fhtw.mw.be.dal.MockDAL;
import at.fhtw.mw.be.dal.SQLiteDAL;
import at.fhtw.mw.be.model.Workout;
import org.junit.Assert;
import org.junit.Test;

public class UnitTests {

    ///// \\\\\ ///// MockDAL ///// \\\\\ ///// 
    @Test
    public void mockDAL_addWorkout_should_insert_element() {
        DAL dal = new MockDAL();
        Workout w1 = new Workout("workout1", "Complex_JSON_String1");
        Workout w2 = new Workout("workout2", "Complex_JSON_String2");
        dal.createWorkout(w1);
        dal.createWorkout(w2);
        Assert.assertEquals(2, dal.getWorkouts().size());
    }

    @Test
    public void mockDAL_addWorkout_must_not_insert_element_if_equally_named_element_exists() {
        DAL dal = new MockDAL();
        Workout w1 = new Workout("workout1", "Complex_JSON_String1");
        Workout w2 = new Workout("workout1", "Complex_JSON_String2");
        dal.createWorkout(w1);
        dal.createWorkout(w2);
        Assert.assertEquals(1, dal.getWorkouts().size());
    }

    @Test
    public void mockDAL_getWorkout_should_return_correct_workout() {
        DAL dal = new MockDAL();
        Workout w1 = new Workout("workout1", "Complex_JSON_String1");
        Workout w2 = new Workout("workout2", "Complex_JSON_String2");
        dal.createWorkout(w1);
        dal.createWorkout(w2);
        Assert.assertEquals("Complex_JSON_String2", dal.getWorkout("workout2").getSets());
        Assert.assertEquals("Complex_JSON_String1", dal.getWorkout("workout1").getSets());
    }

    @Test
    public void mockDAL_getWorkout_should_return_null_if_no_such_object_exists() {
        DAL dal = new MockDAL();
        Workout w1 = new Workout("workout1", "Complex_JSON_String1");
        Workout w2 = new Workout("workout2", "Complex_JSON_String2");
        dal.createWorkout(w1);
        dal.createWorkout(w2);
        Assert.assertNull(dal.getWorkout("workout3"));
    }

    @Test
    public void mockDAL_saveWorkout_should_alter_existing_object() {
        DAL dal = new MockDAL();
        Workout w1 = new Workout("workout1", "Complex_JSON_String1");
        Workout w2 = new Workout("workout2", "Complex_JSON_String2");
        dal.createWorkout(w1);
        dal.createWorkout(w2);
        Workout w3 = new Workout("workout1", "changed");
        dal.updateWorkout(w3);
        Assert.assertEquals("changed", dal.getWorkout("workout1").getSets());
    }

    @Test
    public void mockDAL_getWorkouts_should_return_existing_elements() {
        DAL dal = new MockDAL();
        Workout w1 = new Workout("workout1", "Complex_JSON_String1");
        Workout w2 = new Workout("workout2", "Complex_JSON_String2");
        dal.createWorkout(w1);
        dal.createWorkout(w2);
        Assert.assertEquals(2, dal.getWorkouts().size());
    }

    @Test
    public void mockDAL_deleteWorkout_should_remove_element() {
        DAL dal = new MockDAL();
        Workout w1 = new Workout("workout1", "Complex_JSON_String1");
        Workout w2 = new Workout("workout2", "Complex_JSON_String2");
        dal.createWorkout(w1);
        dal.createWorkout(w2);
        dal.deleteWorkout("workout1");
        dal.deleteWorkout("workout2");
        Assert.assertEquals(0, dal.getWorkouts().size());
    }

    @Test
    public void mockDAL_getWorkouts_should_return_empty_list() {
        DAL dal = new MockDAL();
        Assert.assertEquals(0, dal.getWorkouts().size());
    }

    ///// \\\\\ ///// SQLiteDAL ///// \\\\\ ///// 
    @Test
    public synchronized void SQLiteDAL_getWorkouts_should_return_empty_list() {
        DAL dal = new SQLiteDAL("Test");
        Assert.assertEquals(0, dal.getWorkouts().size());
        dal.clearWorkouts();
    }

    @Test
    public synchronized void SQLiteDAL_addWorkout_should_insert_element() {
        DAL dal = new SQLiteDAL("Test");
        Workout w1 = new Workout("workout1", "Complex_JSON_String1");
        Workout w2 = new Workout("workout2", "Complex_JSON_String2");
        dal.createWorkout(w1);
        dal.createWorkout(w2);
        Assert.assertEquals(2, dal.getWorkouts().size());
        dal.clearWorkouts();
    }

    @Test
    public synchronized void SQLiteDAL_addWorkout_must_not_insert_element_if_equally_named_element_exists() {
        DAL dal = new SQLiteDAL("Test");
        Workout w1 = new Workout("workout1", "Complex_JSON_String1");
        Workout w2 = new Workout("workout1", "Complex_JSON_String2");
        dal.createWorkout(w1);
        dal.createWorkout(w2);
        Assert.assertEquals(1, dal.getWorkouts().size());
        dal.clearWorkouts();
    }

    @Test
    public synchronized void SQLiteDAL_getWorkout_should_return_correct_workout() {
        DAL dal = new SQLiteDAL("Test");
        Workout w1 = new Workout("workout1", "Complex_JSON_String1");
        Workout w2 = new Workout("workout2", "Complex_JSON_String2");
        dal.createWorkout(w1);
        dal.createWorkout(w2);
        Assert.assertEquals("Complex_JSON_String2", dal.getWorkout("workout2").getSets());
        Assert.assertEquals("Complex_JSON_String1", dal.getWorkout("workout1").getSets());
        dal.clearWorkouts();
    }

    @Test
    public synchronized void SQLiteDAL_getWorkout_should_return_null_if_no_such_object_exists() {
        DAL dal = new SQLiteDAL("Test");
        Workout w1 = new Workout("workout1", "Complex_JSON_String1");
        Workout w2 = new Workout("workout2", "Complex_JSON_String2");
        dal.createWorkout(w1);
        dal.createWorkout(w2);
        Assert.assertNull(dal.getWorkout("workout3"));
        dal.clearWorkouts();
    }

    @Test
    public synchronized void SQLiteDAL_saveWorkout_should_alter_existing_object() {
        DAL dal = new SQLiteDAL("Test");
        Workout w1 = new Workout("workout1", "Complex_JSON_String1");
        Workout w2 = new Workout("workout2", "Complex_JSON_String2");
        dal.createWorkout(w1);
        dal.createWorkout(w2);
        Workout w3 = new Workout("workout1", "changed");
        dal.updateWorkout(w3);
        Assert.assertEquals("changed", dal.getWorkout("workout1").getSets());
        dal.clearWorkouts();
    }

    @Test
    public synchronized void SQLiteDAL_getWorkouts_should_return_existing_elements() {
        DAL dal = new SQLiteDAL("Test");
        Workout w1 = new Workout("workout1", "Complex_JSON_String1");
        Workout w2 = new Workout("workout2", "Complex_JSON_String2");
        dal.createWorkout(w1);
        dal.createWorkout(w2);
        Assert.assertEquals(2, dal.getWorkouts().size());
        dal.clearWorkouts();
    }

    @Test
    public synchronized void SQLiteDAL_deleteWorkout_should_remove_element() {
        DAL dal = new SQLiteDAL("Test");
        Workout w1 = new Workout("workout1", "Complex_JSON_String1");
        Workout w2 = new Workout("workout2", "Complex_JSON_String2");
        dal.createWorkout(w1);
        dal.createWorkout(w2);
        dal.deleteWorkout("workout1");
        dal.deleteWorkout("workout2");
        Assert.assertEquals(0, dal.getWorkouts().size());
        dal.clearWorkouts();
    }

}
