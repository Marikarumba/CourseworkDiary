import java.util.Comparator;

public class TimeTaskComparator implements Comparator <Task> {

    public int compare (Task task1, Task task2){
        return task1.getTaskTime().compareTo(task2.getTaskTime());
    }
}
