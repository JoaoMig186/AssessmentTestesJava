package org.jsantostp1;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public final class Pedido {
    private final String id;
    private final LocalDateTime dataCriacao;
    private final String status;
    private final List<String> itens;

    public Pedido(String status, List<String> itens) {
        this.id = UUID.randomUUID().toString();
        this.dataCriacao = LocalDateTime.now();
        this.status = status;
        this.itens = Collections.unmodifiableList(itens);
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public String getStatus() {
        return status;
    }

    public List<String> getItens() {
        return itens;
    }

    public Pedido atualizarStatus(String novoStatus) {
        return new Pedido(novoStatus, this.itens);
    }

    public Pedido adicionarItem(String novoItem) {
        List<String> novaLista = new java.util.ArrayList<>(this.itens);
        novaLista.add(novoItem);
        return new Pedido(this.status, novaLista);
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id='" + id + '\'' +
                ", dataCriacao=" + dataCriacao +
                ", status='" + status + '\'' +
                ", itens=" + itens +
                '}';
    }
}
