package cloudsim.dsl.impl;

import cloudsim.dsl.Buildable;
import cloudsim.dsl.Ram;

public class RamBuilder implements Buildable<Ram>
{
    public RamBuilder with()
    {
        return this;
    }
    
    public RamBuilder capacity(long value)
    {
        return this;
    }
    
    @Override
    public <E> Buildable<Ram> with(Buildable<E> type)
    {
        return this;
    }

    @Override
    public <E> Buildable<Ram> with(int copies, Buildable<E> type)
    {
        return this;
    }

    @Override
    public Ram build()
    {
        return null;
    }
}
