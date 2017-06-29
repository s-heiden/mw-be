# mw-be
## SETUP
- Clone the mw-be project on your machine
- Open the folder ``mw-be`` with Netbeans
- Unless you have a current version of Apache Tomcat (tested with v.8.5.15), please add one in the Tab 'Services' -> 'Servers'.
- As soon as is configured, select the project in the project view and click the play button to deploy and run the backend server.
- Please contact me at stefan.w.heiden{{at}}gmail.com if any questions should arise.
- If no errors occured you can access the backend using the following URLS:

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
