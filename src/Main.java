import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Formatter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

        static Timetable timeTable = new Timetable();
        public static void main(String[] args) {
            try (Scanner scanner = new Scanner(System.in)) {
                boolean run=true;
                while (run) {
                    printMenu();
                    System.out.print("Выберите пункт меню: ");
                    if (scanner.hasNextInt()) {
                        int menu = scanner.nextInt();
                        switch (menu) {
                            case 1:
                                inputTask(scanner);
                                break;
                            case 2:
                                deleteTask(scanner);
                                break;
                            case 3:
                                toDayTask(scanner);
                                break;
                            case 4:             //Весь список задач
                                printAllTasks();
                                break;
                            case 5:             //Массовый ввод задач
                                fillTimeTable();
                                break;
                            case 0:
                                run=false;
                                break;
                        }
                    } else {
                        scanner.next();
                        System.out.println("Выберите пункт меню из списка!");
                    }
                }
            }
        }

        static void fillTimeTable(){
            timeTable.addTask( new Task( "Задача1","Описание",1,5,
                    LocalDate.of(2010,1,1),LocalTime.of(12,0)));
            timeTable.addTask( new Task( "Задача2","Описание",1,1,
                    LocalDate.of(2022,11,22),LocalTime.of(13,0)));
            timeTable.addTask( new Task( "Задача3","Описание",2,2,
                    LocalDate.of(2022,10,1),LocalTime.of(17,0)));
            timeTable.addTask( new Task( "Задача44","Описание",1,2,
                    LocalDate.of(2022,12,31),LocalTime.of(14,0)));
            timeTable.addTask( new Task( "Задача45","Описание",1,2,
                    LocalDate.of(2022,12,31),LocalTime.of(12,30)));
            timeTable.addTask( new Task( "Задача46","Описание",1,2,
                    LocalDate.of(2022,12,31),LocalTime.of(11,0)));
            timeTable.addTask( new Task( "Задача5","Описание",1,3,
                    LocalDate.of(2022,1,1),LocalTime.of(16,0)));
            timeTable.addTask( new Task( "Задача6","Описание",1,4,
                    LocalDate.of(2021,5,1),LocalTime.of(18,0)));
        }

        private static void printAllTasks( )
        {
            timeTable.printTasks();
        }
        private static void inputTask(Scanner scanner) {
            boolean isInputOk=false;

            String nameTask="";
            while (true) {
                System.out.print("Введите название задачи: ");
                nameTask = scanner.useDelimiter("\n").next();
                if ( !(nameTask.isEmpty()|| nameTask.isBlank())) {
                    break;
                } else {
                    System.out.println("Ошибка! Название задачи не может быть пустым!: ");
                }
            }

            String descriptionTask = "";
            while (true) {
                System.out.print("Введите описание задачи: ");
                descriptionTask = scanner.useDelimiter("\n").next();
                if ( !(descriptionTask.isEmpty()|| descriptionTask.isBlank())) {
                    break;
                } else {
                    System.out.println("Ошибка! Описание задачи не может быть пустым!: ");
                }
            }

            Integer typeTask=0;
            while (!isInputOk) {
                try {
                    System.out.print("Выберите тип задачи.\n1- Личная задача\n2 - Рабочая задача\n");
                    System.out.print("Выберите пункт меню: ");
                    typeTask = scanner.nextInt();
                    if (typeTask < 1 || typeTask > 2) {
                        System.out.println("Ошибка! Не верный номер!");
                        } else {
                        isInputOk = true;
                        }
                    } catch (InputMismatchException e) {
                    System.out.println("Ошибка! Введите число.");
                    scanner.next();
                }
            }

            isInputOk=false;
            Integer repeatability=0;
            while (!isInputOk) {
                try{
                System.out.print("Выберите повторяемость задачи.\n1 - Однократная\n2 - Ежедневная\n3 - Еженедельная\n4 - Ежемесячная \n5 - Ежегодная\n");
                System.out.print("Выберите пункт меню: ");
                repeatability = scanner.nextInt();
                if (repeatability<1 || repeatability>5) {
                    System.out.println("Ошибка! Не верный номер!");
                     } else {
                    isInputOk = true;
                     }
                } catch (InputMismatchException e) {
                    System.out.println("Ошибка! Введите число.");
                    scanner.next();
                }
            }

            isInputOk=false;
            LocalDate taskDate=LocalDate.now();
            DateTimeFormatter fDate = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            while (!isInputOk) {
                try {
                    System.out.print("Введите дату в формате dd.MM.yyyy: ");
                    taskDate = LocalDate.parse(scanner.next(), fDate);
                    isInputOk=true;
                }
                catch ( DateTimeParseException e){
                    System.out.println("Ошибка! Не правильная дата.");
                }
            }

            isInputOk=false;
            LocalTime taskTime= LocalTime.now();
            DateTimeFormatter fTime= DateTimeFormatter.ofPattern("HH:mm" );
            while (!isInputOk){
                try {
                    System.out.print("Введите время в формате HH:mm: ");
                    taskTime = LocalTime.parse( scanner.next(), fTime);
                    isInputOk=true;
                } catch (DateTimeParseException e){
                    System.out.println("Ошибка! Не верный формат времени.");
                }
            }

            Task task=new Task( nameTask,descriptionTask,typeTask,repeatability,taskDate,taskTime);
            timeTable.addTask(task);
            System.out.println("Добавлена задача: " + task);
        }

        private static void deleteTask(Scanner scanner){

            boolean isInputOk=false;
            Integer id = 0;
            printAllTasks( );
            while (!isInputOk) {
                try {
                    System.out.print("Введите номер задачи для удаления: ");
                    id = scanner.nextInt();
                    isInputOk = true;
                } catch (InputMismatchException e) {
                    System.out.println("Ошибка! Введите корректный id!");
                    scanner.next();
                }
            }
            if (timeTable.removeTask(id)) {
                System.out.println("Задача " + id + " удалена.");
            } else {
                System.out.println("Задачи с таким номером " + id + " не существует");
            }
        }

        private static void toDayTask(Scanner scanner){
            boolean isInputOk=false;
            LocalDate taskDate = LocalDate.now();
            DateTimeFormatter fDate = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            while (!isInputOk) {
                try {
                    System.out.print("Введите дату списка задач в формате dd.MM.yyyy: ");
                    taskDate = LocalDate.parse(scanner.next(), fDate);
                    isInputOk=true;
                    System.out.println(taskDate);
                }
                catch ( DateTimeParseException e){
                    System.out.println("Ошибка! Не правильная дата.");
                }
            }

            System.out.println(timeTable.getTaskForDay(taskDate));
        }


        private static void printMenu() {
            System.out.println(
                            "1. Добавить задачу\n" +
                            "2. Удалить задачу\n" +
                            "3. Получить задачу на указанный день\n" +
                            "4. Весь список задач\n" +
                            "5. Массовый ввод задач\n"+
                            "0. Выход");
        }
}
