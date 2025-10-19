public class StatelessService {

    public String visit(String username, int visitCount) {
        return "Hello " + username + "! You’ve visited " + visitCount + " times.";
    }

    public static void main(String[] args) {
        StatelessService service = new StatelessService();
        System.out.println(service.visit("Orkhan", 1));
        System.out.println(service.visit("Orkhan", 2));
        System.out.println(service.visit("Mike", 1));
    }
}
