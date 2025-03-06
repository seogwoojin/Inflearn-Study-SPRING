package memory;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemoryFinderTest {
    @Test
    public void memoryfinderTest(){
        MemoryFinder memoryFinder = new MemoryFinder();
        Memory memory = memoryFinder.get();
        System.out.println("memory = " + memory);
    }

}