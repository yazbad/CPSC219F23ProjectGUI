package calorietracker.core.data;

import java.util.Objects;

/**
 * Represents an exercise session with a name and calories burned.
 */
public class ExerciseSession {
    private String name;
    private int calorieBurned;

    /**
     * Constructor for calorietracker.core.data.ExerciseSession.
     *
     * @param name         the name of the exercise session
     * @param calorieBurned the number of calories burned in the session
     */
    public ExerciseSession(String name, int calorieBurned) {
        this.name = name;
        this.calorieBurned = calorieBurned;
    }

    /**
     * Gets the name of the exercise session.
     *
     * @return the name of the exercise session
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the number of calories burned in the exercise session.
     *
     * @return the number of calories burned
     */
    public int getCalorieBurned() {
        return calorieBurned;
    }

    /**
     * Returns a string representation of the exercise session.
     *
     * @return a string representation of the exercise session
     */
    @Override
    public String toString() {
        return name + " - " + calorieBurned + " calories burned";
    }

    /**
     * Checks if this exercise session is equal to another object.
     *
     * @param o the object to compare with the exercise session
     * @return true if the specified object is equal to this exercise session
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExerciseSession that = (ExerciseSession) o;

        if (calorieBurned != that.calorieBurned) return false;
        return Objects.equals(name, that.name);
    }

    /**
     * Generates a hash code for the exercise session.
     *
     * @return a hash code value for this object
     */
    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + calorieBurned;
        return result;
    }
}
