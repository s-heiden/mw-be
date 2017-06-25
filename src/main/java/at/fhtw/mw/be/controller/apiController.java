package at.fhtw.mw.be.controller;

import at.fhtw.mw.be.model.Workout;
import at.fhtw.mw.be.service.WorkoutService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class apiController {

    /*
        {
          "id": 0,
          "name":"TestWorkout",
          "sets": [
            {"exercise": "exercise1", "duration": "2017-06-07T23:00:10.828Z"},
            {"exercise": "exercise2", "duration": "2017-06-07T23:00:15.828Z"},
            {"exercise": "exercise3", "duration": "2017-06-07T23:00:05.828Z"}
          ]
        }        
     */

    @Autowired
    WorkoutService workoutService;

    private static final Logger logger = Logger.getLogger(apiController.class);

    @RequestMapping("/")
    public String welcome() {
        return "Welcome to MyWorkout REST API.";
    }

    @RequestMapping("/get_workout/{workout}")
    public ResponseEntity<String> getWorkout(@PathVariable String workout) {
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);        
        return new ResponseEntity<>(workoutService.getWorkout(workout), httpHeaders, HttpStatus.OK);
    }

    @RequestMapping("/get_workouts/")
    public ResponseEntity<String> getWorkouts() {
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(workoutService.getWorkouts(), httpHeaders, HttpStatus.OK);
    }

    @RequestMapping("/clear_workouts/")
    public void clearWorkouts() {
        workoutService.clearWorkouts();
    }

    @RequestMapping("/delete_workout/{workout}")
    public void deleteWorkout(@PathVariable String workout) {
        workoutService.deleteWorkout(workout);
    }

    @RequestMapping(value = "/create_workout/", method = RequestMethod.POST)
    public void createWorkout(@RequestBody String request) {
        Workout w = Workout.fromJson(request);
        workoutService.createWorkout(w);
    }

    @RequestMapping(value = "/update_workout/", method = RequestMethod.POST)
    public void updateWorkout(@RequestBody String request) {
        Workout w = Workout.fromJson(request);
        workoutService.updateWorkout(w);
    }
}
