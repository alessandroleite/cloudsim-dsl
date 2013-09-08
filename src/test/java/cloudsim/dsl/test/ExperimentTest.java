package cloudsim.dsl.test;

import org.cloudbus.cloudsim.Datacenter;
import org.cloudbus.cloudsim.DatacenterCharacteristics;
import org.cloudbus.cloudsim.Host;
import org.cloudbus.cloudsim.VmSchedulerSpaceShared;
import org.cloudbus.cloudsim.provisioners.BwProvisionerSimple;
import org.cloudbus.cloudsim.provisioners.PeProvisionerSimple;
import org.cloudbus.cloudsim.provisioners.RamProvisionerSimple;
import org.junit.Test;

import cloudsim.dsl.impl.DatacenterBuilder;
import cloudsim.dsl.impl.HostBuilder;

public class ExperimentTest
{

    @Test
    public void must_execute_an_experiment_with_one_datacenter()
    {
//        Experiment experiment = new ExperimentBuilder()
//                                    .with(new DatacenterBuilder().with(5).hosts(
//                                          new HostBuilder()
//                                          .with(4, new CoreBuilder())
//                                          .with(new RamBuilder().with().capacity(512))
//                                          .with(new StorageBuilder().with().capacity(1000))))
//                                    .build();
//        experiment.run();
        
        Host host = new HostBuilder().with(4).cores().with(1000).mips().using().provisioner(PeProvisionerSimple.class)
                         .with(512).ram().using().provisioner(RamProvisionerSimple.class)
                         .with(1000).storage().with(1000).bw().using().provisioner(BwProvisionerSimple.class)
                         .using().vm().scheduler(VmSchedulerSpaceShared.class).build();
        
        
        Datacenter datacenter = new DatacenterBuilder().characteristics()
                                                       .os().linux().xen()
                                                       .costs().bw(1).cpu(1).memory(1).storage(1).timezone(1)
                                                       .storage().zero().build();
        
        System.out.println(host);
        
//        new ExperimentBuilder()
//                .with(1)
//                .dataCenter()
//                .each()
//                .with(5).hosts(with(4).cpu().and().with(512).ram().and().with(1).storage(with().capacity(1000)))
//                .architecture(xen()).cost(cpu(2.3).and().memory(2.8).and().storage(3.2).and().bw(2.8));
        
    }
}
