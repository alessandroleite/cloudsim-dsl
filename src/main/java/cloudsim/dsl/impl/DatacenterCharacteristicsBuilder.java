package cloudsim.dsl.impl;

import cloudsim.dsl.DatacenterBuilder.CostBuilder;
import cloudsim.dsl.DatacenterBuilder.CostConfigBuilder;
import cloudsim.dsl.DatacenterBuilder.OperatingSystemBuilder;
import cloudsim.dsl.DatacenterBuilder.StorageBuilder;
import cloudsim.dsl.DatacenterBuilder.TimezoneBuilder;
import cloudsim.dsl.DatacenterBuilder.VmmBuilder;

public class DatacenterCharacteristicsBuilder implements cloudsim.dsl.DatacenterBuilder.DatacenterCharacteristicsBuilder
{
    private class VmmBuilderImpl implements VmmBuilder
    {
        @Override
        public CostBuilder xen()
        {
            vmm_ = "Xen";
            return new CostBuilder()
            {
                @Override
                public CostConfigBuilder costs()
                {
                    return new CostConfigBuilderImpl();
                }
            };
        }
    }
    
    private class CostConfigBuilderImpl implements CostConfigBuilder
    {
        @Override
        public CostConfigBuilder bw(double cost)
        {
            costPerBw_ = cost;
            return this;
        }

        @Override
        public CostConfigBuilder cpu(double cost)
        {
            costPerCpu_ = cost;
            return this;
        }
        
        @Override
        public CostConfigBuilder memory(double cost)
        {
            costPerMemory_ = cost;
            return this;
        }

        @Override
        public TimezoneBuilder storage(double cost)
        {
            costPerStorage_ = cost;
            return new TimezoneBuilder()
            {
                @Override
                public cloudsim.dsl.DatacenterBuilder.StorageInfoBuilder timezone(long timezone)
                {
                    timezone_ = timezone;
                    return new cloudsim.dsl.DatacenterBuilder.StorageInfoBuilder(){
                        @Override
                        public StorageBuilder storage()
                        {
                            return new StorageBuilderImpl();
                        }
                    };
                }
            };
        }
    }
    
    private String os_;
    private String vmm_;
    
    private double costPerMemory_;
    private double costPerStorage_;
    private double costPerCpu_;
    private double costPerBw_;
    private double timezone_;
    
    @Override
    public OperatingSystemBuilder os()
    {
        return new OperatingSystemBuilder()
        {
            @Override
            public VmmBuilder windows()
            {
                os_ = "Windows";
                return new VmmBuilderImpl();
            }
            
            @Override
            public VmmBuilder linux()
            {
                os_ = "Linux";
                return new VmmBuilderImpl();
            }
        };
    }

    /**
     * @return the os_
     */
    protected String getOs()
    {
        return os_;
    }

    /**
     * @return the vmm_
     */
    protected String getVmm()
    {
        return vmm_;
    }

    /**
     * @return the costPerMemory_
     */
    protected double getCostPerMemory()
    {
        return costPerMemory_;
    }

    /**
     * @return the costPerStorage_
     */
    protected double getCostPerStorage()
    {
        return costPerStorage_;
    }

    /**
     * @return the costPerCpu_
     */
    protected double getCostPerCpu()
    {
        return costPerCpu_;
    }

    /**
     * @return the costPerBw
     */
    protected double getCostPerBw()
    {
        return costPerBw_;
    }

    /**
     * @return the timezone_
     */
    protected double getTimezone()
    {
        return timezone_;
    }
}
