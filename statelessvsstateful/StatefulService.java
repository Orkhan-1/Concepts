import java.util.HashMap;
import java.util.Map;

public class StatefulService {
    private final Map<String, Integer> userVisits = new HashMap<>();

    public String visit(String username) {
        int count = userVisits.getOrDefault(username, 0) + 1;
        userVisits.put(username, count);
        return "Hello " + username + "! You’ve visited " + count + " times.";
    }

    public static void main(String[] args) {
        StatefulService service = new StatefulService();
        System.out.println(service.visit("Orkhan"));
        System.out.println(service.visit("Orkhan"));
        System.out.println(service.visit("Mike"));
    }
}
