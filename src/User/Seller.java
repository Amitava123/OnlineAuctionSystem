package User;

public class Seller implements IUser {
    private final String name;
    private final boolean isSeller;

    public Seller(String name) {
        this.name = name;
        this.isSeller = true;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isSeller() {
        return isSeller;
    }
}
