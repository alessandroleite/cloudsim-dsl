package cloudsim.dsl;

public interface Build<T> extends Cloneable
{
    T build();
}
