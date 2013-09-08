package cloudsim.dsl;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.cloudbus.cloudsim.Host;

public final class HostList implements Cloneable, Iterable<Host>
{
    private final List<Host> delegate_ = new LinkedList<>();
    
    public List<Host> values()
    {
        return Collections.unmodifiableList(this.delegate_);
    }

    @Override
    public Iterator<Host> iterator()
    {
        return values().iterator();
    }
}
