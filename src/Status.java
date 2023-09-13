import java.util.*;

public enum Status {
    STEP0(0, "Current gallows status is :\n_______\n|/\n|\n|\n|\n|\n|\n|________"),
    STEP1(1, "Current gallows status is :\n_______\n|/    |\n|\n|\n|\n|\n|\n|________"),
    STEP2(2, "Current gallows status is :\n_______\n|/    |\n|    ( )\n|\n|\n|\n|\n|________"),
    STEP3(3, "Current gallows status is :\n_______\n|/    |\n|    ( )\n|    \\|/\n|\n|\n|\n|________"),
    STEP4(4, "Current gallows status is :\n_______\n|/    |\n|    ( )\n|    \\|/\n|     |\n|\n|\n|________"),
    STEP5(5, "Current gallows status is :\n_______\n|/    |\n|    ( )\n|    \\|/\n|     |\n|    / \\\n|\n|________");
    public final int number ;
    public final String status;


    Status(int number,String status) {
        this.number = number;
        this.status = status;

    }
    private static final Map<Integer,Status> lookup
            = new HashMap<Integer,Status>();
    private static final Map<String,Status> checkstat
            = new HashMap<String,Status>();

    static {
        for(Status stat : EnumSet.allOf(Status.class))
            lookup.put(stat.getValue(), stat);
        for(Status stat : EnumSet.allOf(Status.class))
            checkstat.put(stat.getStatus(), stat);
    }
    public static Status getByVal(int number) {
        return lookup.get(number);
    }
    public static Status getByStat(String status) {
        return checkstat.get(status);
    }
    public String getStatus() {
        return status;
    }

    public Integer getValue() {
        return number;
    }
}
