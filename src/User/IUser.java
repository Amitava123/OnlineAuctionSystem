package User;

public interface IUser {
    String getName();
    default boolean isSeller() {
        return false;
    }
}
