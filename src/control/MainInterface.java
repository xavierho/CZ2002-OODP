package control;

public interface MainInterface<T> {
    public abstract void createDetails();
    public abstract T getDetails();
    public abstract void updateDetails();

}