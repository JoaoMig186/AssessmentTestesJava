import net.jqwik.api.*;
import net.jqwik.api.constraints.IntRange;
import org.jsantostp1.MathFunctions;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;

public class MathFunctionsPropertyTest {

    @Property
    public void dobroSempreMaiorOuIgual(@ForAll @IntRange(min = 0,  max = 1073741823) int n) {
        int resultado = MathFunctions.multiplyByTwo(n);
        assertThat(resultado).isGreaterThanOrEqualTo(n);
    }

    @Property
    public  void dobroEhIgualASomaDeleMesmo(
            @ForAll @IntRange(min = -1000000, max = 1000000) int n) {
        int resultado = MathFunctions.multiplyByTwo(n);
        assertThat(resultado).isEqualTo(n + n);
    }

    @Property
    public void dobroDeNumeroParTambemEhPar(@ForAll @IntRange(min = -100000, max = 100000) int n) {
        int x = n * 2;
        int resultado = MathFunctions.multiplyByTwo(x);

        assertThat(resultado % 2).isEqualTo(0);
    }

    //QUESTÃO 2 AT - MultiplyByTwo: valide que o resultado é sempre par.
    @Property
    public void multiplyByTwoShouldAlwaysBeEven(@ForAll int number) {
        int result = MathFunctions.multiplyByTwo(number);
        assertThat(result % 2).isEqualTo(0);
    }

    //QUESTÃO 2 AT - GenerateMultiplicationTable: valide que todos os elementos são múltiplos do número original.
    @Property
    public void allElementsShouldBeMultiplesOfNumber(
            @ForAll @IntRange(min = 1, max = 1000) int number,
            @ForAll @IntRange(min = 1, max = 100) int limit
    ) {
        int[] table = MathFunctions.generateMultiplicationTable(number, limit);

        for (int value : table) {
            assertThat(value % number).isEqualTo(0);
        }
    }

    //QUESTÃO 2 AT - IsPrime: valide que para qualquer número primo, não há divisores além de 1 e ele mesmo.
    @Property
    public void primeNumbersHaveNoDivisorsExcept1AndItself(@ForAll @IntRange(min = 2, max = 10000) int number) {
        if (MathFunctions.isPrime(number)) {
            for (int i = 2; i < number; i++) {
                assertThat(number % i).isNotEqualTo(0);
            }
        }
    }

    //QUESTÃO 2 AT - CalculateAverage: verifique se o resultado está sempre entre o menor e o maior valor do array.
    @Property
    public void averageShouldBeBetweenMinAndMax(@ForAll int[] numbers) {
        Assume.that(numbers != null && numbers.length > 0);
        double average = MathFunctions.calculateAverage(numbers);
        int min = Arrays.stream(numbers).min().getAsInt();
        int max = Arrays.stream(numbers).max().getAsInt();

        assertThat(average)
                .isGreaterThanOrEqualTo(min)
                .isLessThanOrEqualTo(max);
    }

}