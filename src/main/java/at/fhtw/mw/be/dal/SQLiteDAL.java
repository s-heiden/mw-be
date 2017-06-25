package at.fhtw.mw.be.dal;

import at.fhtw.mw.be.model.Workout;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLiteDAL implements DAL {

    private static final String SQLITE_JDBC = "org.sqlite.JDBC";
    private Connection connection;
    public static final String TABLE_NAME = "workouts";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_SETS = "sets";
    private final String databaseUrl;

    /**
     * Establishes the connection, sets up the database if there are no tables.
     */
    public SQLiteDAL(String name) {
        this.databaseUrl = "jdbc:sqlite:" + ((name.isEmpty()) ? "default" : name.toLowerCase()) + ".db";
        openDBConnection();
        setupDatabase();
    }

    /**
     * Creates the necessary table if it does not yet exist.
     */
    private void setupDatabase() {
        try (Statement statement = connection.createStatement()) {
            String string = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
                    + COLUMN_NAME + " VARCHAR(256) PRIMARY KEY, "
                    + COLUMN_SETS + " VARCHAR(1024));";
            System.out.println("string = " + string);
            statement.executeUpdate(string);
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    /**
     * Opens the connection to the sqlite database and creates it if it does not yet exist.
     */
    private void openDBConnection() {
        if (connection == null) {
            try {
                Class.forName(SQLITE_JDBC);
                connection = DriverManager.getConnection(databaseUrl);
            } catch (SQLException | ClassNotFoundException s) {
                System.err.println(s.getClass().getName() + ": " + s.getMessage());
            }
        }
    }

    @Override
    public boolean existsWorkout(String name) {
        PreparedStatement statement = null;
        String string = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_NAME + " = ?";
        System.out.println("exists_string = " + string);
        ResultSet rs = null;
        try {
            statement = connection.prepareStatement(string);
            statement.setString(1, name);
            rs = statement.executeQuery();
            boolean flag = rs.next();
            System.out.println("exists_flag = " + flag);
            return flag;
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            closeResultSilently(rs);
            closeStatementSilently(statement);
        }
        return false;
    }

    @Override
    public void createWorkout(Workout workout) {
        if (!existsWorkout(workout.getName())) {
            addRowToWorkouts(workout.getName());
            updateWorkout(workout);
        }
    }

    @Override
    public void deleteWorkout(String name) {
        final String string = "DELETE FROM " + TABLE_NAME + " WHERE name = '" + name + "'";
        System.out.println("delete_string = " + string);
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(string);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            closeStatementSilently(statement);
        }
    }

    @Override
    public Workout getWorkout(String name) {
        Workout o = null;

        PreparedStatement statement = null;
        ResultSet rs = null;
        String string = "SELECT * FROM " + TABLE_NAME + " WHERE name = ?";

        try {
            statement = connection.prepareStatement(string);
            statement.setString(1, name);
            rs = statement.executeQuery();

            if (rs.next()) {
                o = Workout.fromResultSet(rs);
            }
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            closeResultSilently(rs);
            closeStatementSilently(statement);
        }
        return o;
    }

    @Override
    public List<Workout> getWorkouts() {
        List<Workout> workouts = new ArrayList<>();
        final String string = "SELECT * FROM " + TABLE_NAME;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            statement = connection.prepareStatement(string);
            rs = statement.executeQuery();
            while (rs.next()) {
                workouts.add(Workout.fromResultSet(rs));
            }
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            closeResultSilently(rs);
            closeStatementSilently(statement);
        }
        return workouts;
    }

    @Override
    public void updateWorkout(Workout workout) {
       
        PreparedStatement statement = null;
        String string = "UPDATE " + TABLE_NAME
                + " SET " + COLUMN_NAME + " = ? , " + COLUMN_SETS + " = ? "
                + " WHERE " + COLUMN_NAME + " = ? ";
        System.out.println("string = " + string);
        try {
            statement = connection.prepareStatement(string);
            statement.setString(1, workout.getName());
            statement.setString(2, workout.getSets());
            statement.setString(3, workout.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            closeStatementSilently(statement);
        }

    }

    /**
     * Closes a statement silently and catches possible SQLException.
     */
    private void closeStatementSilently(PreparedStatement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    /**
     * Closes a result set silently and catches possible SQLException.
     */
    private void closeResultSilently(ResultSet result) {
        try {
            if (result != null) {
                result.close();
            }
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    private void addRowToWorkouts(String name) {
        PreparedStatement statement = null;
        String string = "INSERT INTO " + TABLE_NAME + " (" + COLUMN_NAME + ") VALUES ('" + name + "')";
        System.out.println("string = " + string);
        try {
            statement = connection.prepareStatement(string);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            closeStatementSilently(statement);
        }
    }

    @Override
    public void clearWorkouts() {
        try (Statement statement = connection.createStatement()) {
            String string = "DROP TABLE " + TABLE_NAME + ";";
            System.out.println("drop_string = " + string);
            statement.executeUpdate(string);
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        setupDatabase();
    }
}
