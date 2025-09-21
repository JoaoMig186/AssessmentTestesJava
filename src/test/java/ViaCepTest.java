import net.jqwik.api.*;
import org.jsantostp1.ViaCep;

import static org.assertj.core.api.Assertions.*;

public class ViaCepTest {
    @Property
    void emptyCepShouldBeInvalid() {
        assertThat(ViaCep.validateCep("")).isFalse();
    }

    @Property
    void cepWithLettersShouldBeInvalid(@ForAll("cepWithLetters") String cep) {
        assertThat(ViaCep.validateCep(cep)).isFalse();
    }

    @Property
    void cepWithInvalidLengthShouldBeInvalid(@ForAll("invalidLengthCep") String cep) {
        assertThat(ViaCep.validateCep(cep)).isFalse();
    }

    @Provide
    Arbitrary<String> cepWithLetters() {
        return Arbitraries.strings()
                .withChars("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789")
                .ofMinLength(1).ofMaxLength(10)
                .filter(s -> !s.matches("\\d{8}"));
    }

    @Provide
    Arbitrary<String> invalidLengthCep() {
        return Arbitraries.strings()
                .withChars("0123456789")
                .ofMinLength(1).ofMaxLength(12)
                .filter(s -> s.length() != 8);
    }
}
