package task;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

/**
 * Class from which task.Todo, task.Deadline and task.Event are extended from.
 */
public abstract class Task {
    protected Date dateTime;

    /**
     * task.Task description
     */
    protected String description;

    /**
     * Whether task has been completed.
     */
    protected boolean isDone;

    /**
     * Whether task is active or snooze.
     */
    protected boolean isSnooze;

    /**
     * Create task with a description.
     * @param description of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.isSnooze = false;
    }

    /**
     * Get status icon.
     * @return String status icon of task
     */
    public String getStatusIcon() {
        return (isDone ? "✓" : "✘"); //return tick or X symbols
    }

    /**
     * Get icon of snooze status.
     * @return String snooze status icon of task
     */
    public String getActiveIcon() {
        return (isSnooze ? "S" : "A");
    }

    /**
     * Mark task as done.
     */
    public void markAsDone() {
        this.isDone = true;
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(this.toString());
    }

    /**
     * Mark task as snooze.
     */
    public void markAsSnooze() {
        this.isSnooze = true;
        System.out.println("Nice! I've deactivated this task:");
        System.out.println(this.toString());
    }

    /**
     * Mark task as active.
     */
    public void markAsUnSnooze() {
        this.isSnooze = false;
        System.out.println("Nice! I've activated this task:");
        System.out.println(this.toString());
    }


    /**
     * check if task description contains a certain string.
     * @param s string to find
     * @return true if description contains string
     */
    public boolean contains(String s) {
        return this.description.contains(s);
    }

    public boolean containsDate(String s) {
        return this.description.contains(s);
    }


    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + "[" + this.getActiveIcon() + "] " + this.description;
    }

    /**
     * Returns a string that is formatted for the text file.
     * @return String that will be stored in text file
     */
    public String toWriteFile() {
        return this.description;
    }

    public Date getDateTime() {
        return this.dateTime;
    }
}