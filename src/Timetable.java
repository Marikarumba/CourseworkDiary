import java.time.LocalDate;
import java.util.*;

public class Timetable {

    Map <Integer, Task > taskMap = new HashMap<>();

    private static int taskCounter = 1;


    public Collection<Task> getTaskForDay (LocalDate date){

        Set < Task> taskForDay = new TreeSet<>(new TimeTaskComparator());
        for (Map.Entry<Integer, Task> meaning: taskMap.entrySet()) {
           if (meaning.getValue().appersIn(date)){
               taskForDay.add(meaning.getValue());
           }
        }
        return taskForDay;
    }

    public void addTask(Task task){
        taskMap.put( taskCounter++, task) ;
    }

    public boolean removeTask(int id){
        if (!taskMap.containsKey(id)){
           return false;
        }
        taskMap.remove(id);
        return true;
    }

    public void printTasks(){
        StringBuilder out= new StringBuilder();
        for (Map.Entry<Integer, Task> meaning: taskMap.entrySet()) {
            out.append("ID: "+meaning.getKey()).append(" ").append(meaning.getValue());
        }
        System.out.println(out);
    }

    public void toDate( LocalDate date){

    }

}
