import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import net.jqwik.api.*;

public class MainTest {
        @Test
        void somaSimples() {
            int a = 2;
            int b = 3;
            assertEquals(5, a + b);
        }
}

