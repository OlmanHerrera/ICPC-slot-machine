import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 * Write a description of class testSlotMachine here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */


    public class SlotMachineTest {

    private SlotMachine machine;

    @Before
    public void setUp() {
        machine = new SlotMachine(3);
    }

    @Test
    public void shouldValidtestconstructorSlotmachine() {
        SlotMachine customMachine = new SlotMachine(2);
        assertNotNull(customMachine);
    }

    @Test
    public void shouldValidtestverificarsimbolosdistintos() {
        machine.spin(1, 5);
        int result = machine.distinctSymbols();
        assertTrue(result >= 0);
    }

    @Test
    public void shouldValidtestmetodospin() {
        machine.spin(1, 5);
        assertNotNull(machine.configuration());
    }

    @Test
    public void shouldValidtestsimbolosdistintosspin() {
        machine.spin(1, 5);
        int result = machine.distinctSymbols();
        assertTrue(result >= -1);
    }

    @Test
    public void shouldValidtestcombinacionspinysimbolosdistintos() {
        machine.spin(2, 3);
        int symbols = machine.distinctSymbols();
        assertTrue(symbols >= -1);
        
    }
    @Test
    public void shouldValidtestlockrueda() {
        machine.lock(1);
        machine.spin(1, 3);
    }
}
    