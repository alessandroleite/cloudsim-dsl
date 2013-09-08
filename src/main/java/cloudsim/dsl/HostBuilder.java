package cloudsim.dsl;

import org.cloudbus.cloudsim.Host;
import org.cloudbus.cloudsim.VmScheduler;
import org.cloudbus.cloudsim.provisioners.BwProvisioner;
import org.cloudbus.cloudsim.provisioners.PeProvisioner;
import org.cloudbus.cloudsim.provisioners.RamProvisioner;

public interface HostBuilder extends Cloneable
{
    interface CpuBuilder
    {
        WithMpis cores();
    }
    
    interface WithMpis
    {
        Mips with(int numberOfMips);
    }

    interface Mips
    {
        UsingPeProvisionerType mips();
    }

    interface UsingPeProvisionerType
    {
        CpuProvisionerBuilder using();
    }

    interface CpuProvisionerBuilder
    {
        WithRam provisioner(Class<? extends PeProvisioner> cpuProvisionerType);
    }

    interface WithRam
    {
        RamBuilder with(int ram);
    }

    interface RamBuilder
    {
        UsingRamProvisioner ram();
    }

    interface UsingRamProvisioner
    {
        RamProvisionerBuilder using();
    }

    interface RamProvisionerBuilder
    {
        WithStorageCapacity provisioner(Class<? extends RamProvisioner> provisionerType);
    }
       
    interface WithStorageCapacity 
    {
        StorageBuilder with(long capacity);
    }

    interface StorageBuilder
    {
        UsingVmScheduler storage();
    }

    interface UsingVmScheduler
    {
        WithBw with(int bw);
    }

    interface WithBw
    {
        UsingBwProvisioner bw();
    }
    
    interface UsingBwProvisioner
    {
        BwProviderTypeBuilder using();
    }
    
    interface BwProviderTypeBuilder
    {
        WithVmScheduler provisioner(Class<? extends BwProvisioner> bwProvisionerType);
    }
    
    interface WithVmScheduler
    {
        VmSchedulerBuilder using();   
    }
    
    interface VmSchedulerBuilder
    {
        VmSchedulerBuilderT vm();
    }
    
    interface VmSchedulerBuilderT 
    {
        Build<Host> scheduler(Class<? extends VmScheduler> vmSchedulerType);
    }

    CpuBuilder with(int quantity);
}
