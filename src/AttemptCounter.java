import java.util.HashMap;

public class AttemptCounter {
    private static AttemptCounter instance;
    private HashMap<String, Integer> counter = new HashMap<>();

    public static synchronized AttemptCounter getInstance() {
        if (instance == null) {
            instance = new AttemptCounter();
        }
        return instance;
    }



    public int count(String email) {
        if (counter.containsKey(email)) {
            counter.put(email, counter.get(email) + 1);
        }
        else {
            counter.put(email, 1);
        }
        return counter.get(email);
    }
}
