/**
 * Represents one task in the task list.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected boolean isToDos;
    protected boolean hasDeadlines;
    protected String deadlineTime;
    protected boolean isEvent;
    protected String eventFrom;
    protected String eventTo;

    /**
     * Returns the description of the task.
     *
     * @return task description
     */
    public String getDescription() { return description; }
    /**
     * Returns whether the task is done.
     *
     * @return true if the task is done
     */
    public boolean isDone() { return isDone; }
    /**
     * Returns the deadline time of the task.
     *
     * @return deadline time string
     */
    public String getDeadlineTime() { return deadlineTime; }
    /**
     * Returns the start time of an event.
     *
     * @return event start time
     */
    public String getEventFrom() { return eventFrom; }
    /**
     * Returns the end time of an event.
     *
     * @return event end time
     */
    public String getEventTo() { return eventTo; }

    /**
     * Returns the type code used for saving.
     *
     * @return T for todo, D for deadline, E for event
     */
    public String getSaveType() {
        if (isToDos) return "T";
        if (hasDeadlines) return "D";
        if (isEvent) return "E";
        return "T";
    }

    /**
     * Creates a Task object with the given description.
     *
     * @param description description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.isToDos = false;
        this.hasDeadlines = false;
        this.isEvent = false;
    }

    /**
     * Returns the status icon of the task.
     *
     * @return [X] if done, otherwise [ ]
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    /**
     * Returns the type icon of the task.
     *
     * @return task type icon
     */
    public String getTypeIcon() {
        if (isToDos) {
            return ("[T]");
        }

        if (hasDeadlines) {
            return ("[D]");
        }

        if (isEvent) {
            return ("[E]");
        }

        return ("[ ]");
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        isDone = false;
    }

    /**
     * Sets this task as a todo task.
     */
    public void setToDos() {
        isToDos = true;
    }

    /**
     * Sets this task as a deadline task.
     *
     * @param time deadline time
     */
    public void setDeadline(String time) {
        hasDeadlines = true;
        deadlineTime = time;
    }

    /**
     * Sets this task as an event task.
     *
     * @param fromTime start time
     * @param toTime end time
     */
    public void setEvent(String fromTime, String toTime) {
        isEvent = true;
        eventFrom = fromTime;
        eventTo = toTime;
    }

    /**
     * Prints the task information in the required format.
     */
    public void printInformation() {
        String typeIcon = getTypeIcon();
        String statusIcon = getStatusIcon();
        String time;
        if (hasDeadlines) {
            time = "(by: " + deadlineTime + ")";
        } else if (isEvent) {
            time = "(from: " + eventFrom + " " + "to: " + eventTo + ")";
        } else {
            time = "";
        }

        System.out.println(typeIcon + statusIcon + " " + description + " " + time);
    }
}