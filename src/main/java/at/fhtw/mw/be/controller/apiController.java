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

@CrossOrigin
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
        
        if (!workoutService.existsWorkout(workout)) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);        
        return new ResponseEntity<>(workoutService.getWorkout(workout), httpHeaders, HttpStatus.OK);
    }

    @RequestMapping("/get_workouts")
    public ResponseEntity<String> getWorkouts() {
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(workoutService.getWorkouts(), httpHeaders, HttpStatus.OK);
    }

    @RequestMapping("/clear_workouts")
    public ResponseEntity<String> clearWorkouts() {
        try {
        workoutService.clearWorkouts();
            return new ResponseEntity(HttpStatus.OK);
        }
        catch(Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping("/delete_workout/{workout}")
    public ResponseEntity<String> deleteWorkout(@PathVariable String workout) {
        try {
         workoutService.deleteWorkout(workout);
            return new ResponseEntity("{}", HttpStatus.OK);
        }
        catch(Exception e) {
            return new ResponseEntity("{}",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/create_workout/", method = RequestMethod.POST)
    public ResponseEntity<String> createWorkout(@RequestBody String request) {
        

        Workout w = Workout.fromJson(request);
        
        if (workoutService.existsWorkout(w.getName())) {
            return new ResponseEntity("Workout with the name \"" + w.getName() + "\" already exists",HttpStatus.BAD_REQUEST);
        }
        
        workoutService.createWorkout(w);
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON); 
        return new ResponseEntity<>(w.toJson(), httpHeaders, HttpStatus.OK);
    }
    
    @CrossOrigin
    @RequestMapping(value = "/update_workout", method = RequestMethod.POST)
    public ResponseEntity<String> updateWorkout(@RequestBody String request) {
        
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON); 
        
        Workout w = Workout.fromJson(request);
        
        if (!workoutService.existsWorkout(w.getName())) {
            return new ResponseEntity("Workout with the name \""+ w.getName()+"\" not found.", HttpStatus.NOT_FOUND);
        }
        
        workoutService.updateWorkout(w);
        
        return new ResponseEntity<>(w.toJson(), httpHeaders, HttpStatus.OK);
    }
}
