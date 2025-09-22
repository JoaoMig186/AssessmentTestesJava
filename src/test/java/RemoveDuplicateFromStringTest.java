import org.jsantostp1.RemoveDuplicateFromString;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class RemoveDuplicateFromStringTest {
    @Test
    void deveRetornarNuloQuandoEntradaForNull(){
        assertNull(RemoveDuplicateFromString.removeDuplicate(null));
    }

    @Test
    void deveRetornarVazioQuandoEntradaForStinngVazia(){
        assertEquals("", RemoveDuplicateFromString.removeDuplicate(""));
    }

    @Test
    void deveRetornarOmesmoCaracterSeAentradaForDeUmCaracter(){
        assertEquals("a", RemoveDuplicateFromString.removeDuplicate("a"));
    }

    @Test
    void deveRetornarMesmaStringQuandoNaoTiverCaracteresDuplicados(){
        assertEquals("Jair", RemoveDuplicateFromString.removeDuplicate("Jair"));
    }

    @Test
    void deveRemoverDuplicadosMantendoOrdem() {
        assertEquals("abc", RemoveDuplicateFromString.removeDuplicate("aabbcc"));
        assertEquals("abcdef", RemoveDuplicateFromString.removeDuplicate("abcabcabcdef"));
    }

    @Test
    void deveRetornarUmCaracterQuandoTodosForemDuplicados(){
        assertEquals("m", RemoveDuplicateFromString.removeDuplicate("mmmmmmmmmmmmmmmmmmm"));
        assertEquals("e", RemoveDuplicateFromString.removeDuplicate("eeeeeeee"));
    }

    @Test
    void deveAceitarEspacosEcaracteresEspeciais() {
        assertEquals("a b@c!", RemoveDuplicateFromString.removeDuplicate("a      b@@cc!!"));
    }

    @Test
    void deveDiferenciarCases(){
        assertEquals("aA", RemoveDuplicateFromString.removeDuplicate("aaaaaaAAAAAAA"));
    }

    @Test
    void deveRemoverCaracteresDuplicadosComEntradasLongas(){
        assertEquals(RemoveDuplicateFromString.removeDuplicate("Lorem Ipsuilydtxfhnga.b'vc150,wk96APM")
                , RemoveDuplicateFromString.removeDuplicate("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."));
    }
}
