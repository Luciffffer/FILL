import FILL.models.core.User;

public class App {
    public static void main(String[] args) {
        try {
            //User bort = new User("Bert", "123456789");
            //ort.register();

            User user = User.login("Bert", "123456789");
            System.out.println(user.getPassword() + " " + user.getUsername());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
