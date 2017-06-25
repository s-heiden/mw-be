# mw-be
## SETUP
in project folder execute ```mvn deploy```
the resulting *.war file should be in ```./target``` and can be deployed on a Java server (successfully tested on Apache Tomcat 8.5.15)

## API DESCRIPTION
### Create Workout
URL: ```http://localhost:8080/mw-be/create_workout/```
METHOD: ```POST```
EXAMPLE BODY:
```
{
      "name":"TestWorkout99",
      "sets": [
        {"exercise": "exercise1", "duration": "2017-06-07T23:00:10.828Z"},
      ]
}
```

### Update Workout
URL: ```http://localhost:8080/mw-be/update_workout/```
METHOD: ```POST```
EXAMPLE BODY:
```
{
      "name":"TestWorkout1",
      "sets": [
      	{
      		"Test1" : "Test1"      		
      	}
      ]
    }
```

### Get Workouts
URL: ```http://localhost:8080/mw-be/get_workouts/```
METHOD: ```GET```

### Get Workout
URL: ```http://localhost:8080/mw-be/get_workout/{Name of Workout}```
METHOD: ```GET```

### Delete Workout
URL: ```http://localhost:8080/mw-be/delete_workout/{Name of Workout}```
METHOD: ```GET```

### Clear Workouts
URL: ```localhost:8080/mw-be/clear_workouts/```
METHOD: ```GET```
