package User;

public class Buyer implements IUser {
    private final String name;

    public Buyer(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
