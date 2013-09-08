package cloudsim.dsl;

public interface Buildable<T> extends Cloneable
{
    <E> Buildable<T> with(Buildable<E> type);
    
    <E> Buildable<T> with(int copies, Buildable<E> type);
    
    T build();
}
