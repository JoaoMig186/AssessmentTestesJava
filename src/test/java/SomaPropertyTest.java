import net.jqwik.api.ForAll;
import net.jqwik.api.Property;

public class SomaPropertyTest {

    @Property
    boolean somaEhComutativa(@ForAll int a, @ForAll int b) {
        return (a + b) == (b + a);
    }

    @Property
    boolean somaEhAssociativa(@ForAll int a, @ForAll int b, @ForAll int c) {
        return ((a + b) + c) == (a + (b + c));
    }
}
