package cloudsim.dsl;

import org.cloudbus.cloudsim.Datacenter;


public interface DatacenterBuilder extends Cloneable
{
    DatacenterCharacteristicsBuilder characteristics();

    interface DatacenterCharacteristicsBuilder
    {
        OperatingSystemBuilder os();
    }
    
    interface StorageBuilder
    {
        Build<Datacenter> zero();
        
        StorageConfigBuilder one();
        
        StorageConfigBuilder quantity(int quantity);
    }
    
    interface StorageInfoBuilder
    {
        StorageBuilder storage();
    }

    interface OperatingSystemBuilder
    {
        VmmBuilder linux();

        VmmBuilder windows();
    }

    interface VmmBuilder
    {
        CostBuilder xen();
    }

    interface CostBuilder
    {
        CostConfigBuilder costs();
    }

    interface CostConfigBuilder
    {
        CostConfigBuilder bw(double cost);

        CostConfigBuilder cpu(double cost);
        
        CostConfigBuilder memory(double cost);

        TimezoneBuilder storage(double cost);
    }

    interface TimezoneBuilder
    {
        StorageInfoBuilder timezone(long timezone);
    }
}
