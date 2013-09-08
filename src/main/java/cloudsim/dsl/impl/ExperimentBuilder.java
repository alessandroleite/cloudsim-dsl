package cloudsim.dsl.impl;

import cloudsim.dsl.Buildable;
import cloudsim.dsl.Experiment;

public class ExperimentBuilder implements Buildable<Experiment>
{
    @Override
    public <E> Buildable<Experiment> with(Buildable<E> type)
    {
        return this;
    }

    @Override
    public <E> Buildable<Experiment> with(int copies, Buildable<E> type)
    {
        return null;
    }

    @Override
    public Experiment build()
    {
        return null;
    }
    
}
