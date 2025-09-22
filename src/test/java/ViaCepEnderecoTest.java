import com.google.gson.Gson;
import com.google.gson.JsonArray;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ViaCepEnderecoTest {

    private final HttpClient client = HttpClient.newHttpClient();
    private final Gson gson = new Gson();

    private JsonArray consultarEndereco(String uf, String cidade, String logradouro) throws Exception {
        String url = String.format("https://viacep.com.br/ws/%s/%s/%s/json/",
                uf, cidade.replace(" ", "%20"), logradouro.replace(" ", "%20"));

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return gson.fromJson(response.body(), JsonArray.class);
    }

    @Test
    void consultaValidaDeveRetornarResultados() throws Exception {
        JsonArray resultado = consultarEndereco("SP", "Sao Paulo", "Avenida Paulista");
        assertThat(resultado).isNotEmpty();
    }

    @Test
    void ufInvalidaDeveRetornarErroOuListaVazia() throws Exception {
        JsonArray resultado = consultarEndereco("ZZ", "Sao Paulo", "Avenida Paulista");
        assertThat(resultado).isEmpty();
    }

    @Test
    void cidadeInexistenteDeveRetornarListaVazia() throws Exception {
        JsonArray resultado = consultarEndereco("SP", "CidadeInvalidaTeste", "Avenida Paulista");
        assertThat(resultado).isEmpty();
    }

    @Test
    void logradouroInexistenteDeveRetornarListaVazia() throws Exception {
        JsonArray resultado = consultarEndereco("SP", "Sao Paulo", "RuaInexistente12345");
        assertThat(resultado).isEmpty();
    }
    @Test
    void ufComFormatoInvalidoDeveGerarErro() {
        assertThatThrownBy(() -> consultarEndereco("S", "Sao Paulo", "Avenida Paulista"))
                .isInstanceOf(Exception.class);
        assertThatThrownBy(() -> consultarEndereco("SPP", "Sao Paulo", "Avenida Paulista"))
                .isInstanceOf(Exception.class);
    }

    @Test
    void cidadeComAcentoDeveRetornarResultados() throws Exception {
        JsonArray resultado = consultarEndereco("SP", "São Paulo", "Avenida Paulista");
        assertThat(resultado).isNotEmpty();
    }

    @Test
    void cidadeVaziaDeveGerarErro() {
        assertThatThrownBy(() -> consultarEndereco("SP", "", "Avenida Paulista"))
                .isInstanceOf(Exception.class);
    }

    @Test
    void logradouroVazioDeveGerarErro() {
        assertThatThrownBy(() -> consultarEndereco("SP", "Sao Paulo", ""))
                .isInstanceOf(Exception.class);
    }

    @Test
    void cidadeOuLogradouroMuitoLongosDevemGerarErroOuListaVazia() throws Exception {
        String cidadeLonga = "A".repeat(300);
        String logradouroLongo = "B".repeat(300);
        JsonArray resultado = consultarEndereco("SP", cidadeLonga, logradouroLongo);
        assertThat(resultado).isEmpty();
    }
}
