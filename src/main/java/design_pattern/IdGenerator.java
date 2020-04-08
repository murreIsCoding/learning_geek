package design_pattern;


import java.util.concurrent.atomic.AtomicLong;

public enum IdGenerator {
    INSTANCE;
    private AtomicLong id = new AtomicLong(0);
    public long getId() {
        return id.incrementAndGet();
    }

    public static void main(String[] args) {
        IdGenerator idGenerator =   IdGenerator.INSTANCE;
    }
}
