import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Task {

    private final String nameTask;
    private final String descriptionTask;
    private TypeTask typeTask;
    private Repeatability repeatability;
    private LocalDate taskDate;
    private LocalTime taskTime;

    public Task(String nameTask,
                String descriptionTask,
                Integer typeTask,
                Integer repeatability,
                LocalDate taskDate,
                LocalTime taskTime) {
        this.nameTask = nameTask;
        this.descriptionTask = descriptionTask;
        this.typeTask = typeTask==1 ? TypeTask.PERSONAL : TypeTask.WORK;
        this.repeatability = Repeatability.get(repeatability);
        this.taskDate = taskDate;
        this.taskTime = taskTime;
    }
    public boolean appersIn(LocalDate date){
        if (getTaskDate().isAfter(date)){
            return false;
            }
        switch (this.repeatability){
            case ONE_TIME:  return getTaskDate().isEqual(date);
            case DAILY:     return true;
            case WEEKLY:    return getTaskDate().getDayOfWeek().equals(date.getDayOfWeek());
            case MONTHLY:   return getTaskDate().getDayOfMonth() == date.getDayOfMonth();
            case ANNUAL:    return (getTaskDate().getDayOfMonth() == date.getDayOfMonth())
                                    &&(getTaskDate().getMonth().equals(date.getMonth()));
        }
        return false;
    }

    public String getNameTask() {
        return nameTask;
    }

    public String getDescriptionTask() {
        return descriptionTask;
    }

    public TypeTask getTypeTask() {
        return typeTask;
    }

    public Repeatability getRepeatability() {
        return repeatability;
    }

    public LocalDate getTaskDate() {
        return taskDate;
    }

    public LocalTime getTaskTime() {
        return taskTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(nameTask, task.nameTask) && Objects.equals(descriptionTask, task.descriptionTask) && typeTask == task.typeTask && repeatability == task.repeatability && Objects.equals(taskDate, task.taskDate) && Objects.equals(taskTime, task.taskTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameTask, descriptionTask, typeTask, repeatability, taskDate, taskTime);
    }

    @Override
    public String toString() {
        DateTimeFormatter fDate = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return taskDate.format(fDate).toString() +", " + taskTime.toString() + ", " + nameTask + ", " + descriptionTask +
                ", " + typeTask + ", " + repeatability+"\n";

    }


}
