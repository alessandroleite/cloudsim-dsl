package cloudsim.dsl;

import java.io.Serializable;

public class Ram implements Serializable
{
    private final long value_;

    public Ram(long value)
    {
        this.value_ = value;
    }
}
