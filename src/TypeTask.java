public enum TypeTask {

    PERSONAL(1),    //"Личная"
    WORK(2);        //"Рабочая"
    private final Integer type;

    TypeTask(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }


    @Override
    public String toString() {
        if (this== PERSONAL){
            return ("Личная");
        }
        return ("Рабочая");

    }


}
