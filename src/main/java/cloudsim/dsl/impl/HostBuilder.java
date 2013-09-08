package cloudsim.dsl.impl;

import java.util.ArrayList;
import java.util.List;

import cloudsim.dsl.Build;

import net.vidageek.mirror.dsl.Mirror;

import org.cloudbus.cloudsim.Host;
import org.cloudbus.cloudsim.Pe;
import org.cloudbus.cloudsim.VmScheduler;
import org.cloudbus.cloudsim.provisioners.BwProvisioner;
import org.cloudbus.cloudsim.provisioners.PeProvisioner;
import org.cloudbus.cloudsim.provisioners.RamProvisioner;


public final class HostBuilder implements cloudsim.dsl.HostBuilder
{
    private int numberOfCores_;
    private double mips_;
    
    private int ramSize_;
    private long bw_;
    private long storageCapacity_;


    private Class<? extends PeProvisioner> cpuProvisionerClass_;
    private Class<? extends RamProvisioner> ramProvisionerClass_;
    private Class<? extends VmScheduler> vmSchedulerClass_;
    private Class<? extends BwProvisioner> bwProvisionerClass_;
    
    public HostBuilder()
    {
        super();
    }

    private HostBuilder(HostBuilder that)
    {
        new HostBuilder(that.numberOfCores_, that.mips_, that.ramSize_, that.bw_, that.storageCapacity_, that.cpuProvisionerClass_, that.ramProvisionerClass_,
                that.vmSchedulerClass_, that.bwProvisionerClass_);
    }

    private HostBuilder(int numberOfCores, double mips, int ramSize, long bw, long storageCapacity,
            Class<? extends PeProvisioner> cpuProvisionerClass, Class<? extends RamProvisioner> ramProvisionerClass,
            Class<? extends VmScheduler> vmSchedulerClass,
            Class<? extends BwProvisioner> bwProvisionerClass)
    {
        this.numberOfCores_ = numberOfCores;
        this.mips_ = mips;
        this.ramSize_ = ramSize;
        this.bw_ = bw;
        this.storageCapacity_ = storageCapacity;
        
        this.cpuProvisionerClass_ = cpuProvisionerClass;
        this.ramProvisionerClass_ = ramProvisionerClass;
        this.vmSchedulerClass_ = vmSchedulerClass;
        this.bwProvisionerClass_ = bwProvisionerClass;
    }

    @Override
    public CpuBuilder with(int numberOfCores)
    {
        this.numberOfCores_ = numberOfCores;
        return new CpuBuilderImpl();
    }

    private class CpuBuilderImpl implements CpuBuilder
    {
        @Override
        public WithMpis cores()
        {
            return new WithMpis()
            {
                @Override
                public Mips with(int numberOfMips)
                {
                    mips_ = numberOfMips;
                    return new MipsBuilderImpl();
                }
            };
        }
    }

    private class MipsBuilderImpl implements Mips
    {
        @Override
        public UsingPeProvisionerType mips()
        {
            return new UsingPeProvisionerType()
            {
                @Override
                public CpuProvisionerBuilder using()
                {
                    return new CpuProvisionerBuilderImpl();
                }
            };
        }
    }

    private class CpuProvisionerBuilderImpl implements CpuProvisionerBuilder
    {
        @Override
        public WithRam provisioner(Class<? extends PeProvisioner> cpuProvisionerType)
        {
            cpuProvisionerClass_ = cpuProvisionerType;
            return new WithRam()
            {
                @Override
                public RamBuilder with(int ram)
                {
                    ramSize_ = ram;
                    return new RamBuilderImpl();
                }
            };
        }
    }

    private class RamBuilderImpl implements RamBuilder
    {
        @Override
        public UsingRamProvisioner ram()
        {
            return new UsingRamProvisioner()
            {
                @Override
                public RamProvisionerBuilder using()
                {
                    return new RamProvisionerBuilderImpl();
                }
            };
        }
    }

    private class RamProvisionerBuilderImpl implements RamProvisionerBuilder
    {
        @Override
        public WithStorageCapacity provisioner(Class<? extends RamProvisioner> provisionerType)
        {
            ramProvisionerClass_ = provisionerType;
            return new WithStorageCapacity()
            {
                @Override
                public StorageBuilder with(long capacity)
                {
                    storageCapacity_ = capacity;
                    return new StorageBuilderImpl();
                }
            };
        }
    }

    private class StorageBuilderImpl implements StorageBuilder
    {
        @Override
        public UsingVmScheduler storage()
        {
            return new UsingVmScheduler()
            {
                @Override
                public WithBw with(int bw)
                {
                    bw_ = bw;
                    return new WithBw()
                    {
                        @Override
                        public UsingBwProvisioner bw()
                        {
                            return new BwProvisionerBuilder();
                        }
                    };
                }
            };
        }
    }

    private class BwProvisionerBuilder implements UsingBwProvisioner
    {
        @Override
        public BwProviderTypeBuilder using()
        {
            return new BwProviderTypeBuilder()
            {
                @Override
                public WithVmScheduler provisioner(Class<? extends BwProvisioner> bwProvisionerType)
                {
                    bwProvisionerClass_ = bwProvisionerType;
                    return new WithVmScheduler()
                    {
                        @Override
                        public VmSchedulerBuilder using()
                        {
                            return new VmSchedulerBuilderImpl();
                        }
                    };
                }
            };
        }
    }

    private class VmSchedulerBuilderImpl implements VmSchedulerBuilder
    {
        @Override
        public VmSchedulerBuilderT vm()
        {
            return new VmSchedulerBuilderT()
            {
                @Override
                public Build<Host> scheduler(Class<? extends VmScheduler> vmSchedulerType)
                {
                    vmSchedulerClass_ = vmSchedulerType;
                    return new Build<Host>()
                    {
                        @Override
                        public Host build()
                        {
                            List<Pe> cores = new ArrayList<>();
                            for (int i = 0; i < numberOfCores_; i++)
                            {
                                cores.add(new Pe(i, new Mirror().on(cpuProvisionerClass_).invoke().constructor().withArgs(mips_)));
                            }

                            Host host = new Host(0, new Mirror().on(ramProvisionerClass_).invoke().constructor().withArgs(ramSize_), 
                                    new Mirror().on(bwProvisionerClass_).invoke().constructor().withArgs(bw_), storageCapacity_, cores, 
                                    new Mirror().on(vmSchedulerClass_).invoke().constructor().withArgs(cores));
                            return host;
                        }
                    };
                }
            };
        }
    }
    
    @Override
    public HostBuilder clone()
    {
        HostBuilder clone;
        try
        {
            clone = (HostBuilder) super.clone();
        }
        catch (CloneNotSupportedException cnse)
        {
            clone = new HostBuilder(this);
        }
        
        return clone;
    }
}
