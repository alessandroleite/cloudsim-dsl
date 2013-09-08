package cloudsim.dsl.impl;

import org.cloudbus.cloudsim.Datacenter;

import cloudsim.dsl.Build;
import cloudsim.dsl.StorageConfigBuilder;

public class StorageBuilderImpl implements cloudsim.dsl.DatacenterBuilder.StorageBuilder
{
    @Override
    public StorageConfigBuilder one()
    {
        return this.quantity(1);
    }

    @Override
    public StorageConfigBuilder quantity(int quantity)
    {
        return null;
    }

    @Override
    public Build<Datacenter> zero()
    {
        return null;
    }
}
