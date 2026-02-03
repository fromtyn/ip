public class Task {
    protected String description;
    protected boolean isDone;
    protected boolean isToDos;
    protected boolean hasDeadlines;
    protected String deadlineTime;
    protected boolean isEvent;
    protected String eventFrom;
    protected String eventTo;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.isToDos = false;
        this.hasDeadlines = false;
        this.isEvent = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

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

    public void markAsDone() {
        isDone = true;
    }

    public void markAsNotDone() {
        isDone = false;
    }

    public void setToDos() {
        isToDos = true;
    }

    public void setDeadline(String time) {
        hasDeadlines = true;
        deadlineTime = time;
    }

    public void setEvent(String fromTime, String toTime) {
        isEvent = true;
        eventFrom = fromTime;
        eventTo = toTime;
    }

    public void printInformation() {
        String typeIcon = getTypeIcon();
        String statusIcon = getStatusIcon();
        String time;
        if (hasDeadlines) {
            time = "(by: " + deadlineTime + ")";
        }

        else if (isEvent) {
            time = "(from: " + eventFrom + " " + "to: " + eventTo + ")";
        }

        else {
            time = "";
        }

        System.out.println(typeIcon + statusIcon + " " + description + " " + time);
    }
}