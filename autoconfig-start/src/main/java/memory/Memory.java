package memory;

public class Memory {
    private long used;
    private long max;


    public Memory(long used, long max) {
        this.used = used;
        this.max = max;
    }

    public long getMax() {
        return max;
    }

    public long getUsed() {
        return used;
    }

    @Override
    public String toString(){
        return "Memory{" +
                "used = "+used+
                "max = " + max+
                "}";
    }
}
