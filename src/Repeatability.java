public enum Repeatability {

    ONE_TIME(1), //"Однократная"
    DAILY(2), //"Ежедневная"
    WEEKLY(3), //"Еженедельная"
    MONTHLY(4), //"Ежемесячная"
    ANNUAL(5);    //"Ежегодная"

    private final Integer type;

    Repeatability(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }

    public static Repeatability get(Integer type) {
        switch (type) {
            case 1: return ONE_TIME;
            case 2: return DAILY;
            case 3: return WEEKLY;
            case 4: return MONTHLY;
            case 5: return ANNUAL;
        }
    return ONE_TIME;
    }


    @Override
    public String toString() {
        switch (this) {
            case ONE_TIME: return "Однократная" ;
            case DAILY: return "Ежедневная";
            case WEEKLY: return "Еженедельная";
            case MONTHLY: return "Ежемесячная";
            case ANNUAL: return "Ежегодная";
        }
        return "";

    }
}
