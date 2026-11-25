
package com.delivery.pedidos.service;

import com.delivery.pedidos.entity.Pedido;
import com.delivery.pedidos.repository.PedidoRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

@Service
public class PedidoService {

    private final PedidoRepository repository;
    private final RestClient restClient;

    public PedidoService(PedidoRepository repository, RestClient.Builder builder) {
        this.repository = repository;
        this.restClient = builder
                .baseUrl("http://localhost:8081")
                .build();
    }

    public Pedido salvar(Pedido pedido) {
        try {
            restClient.get()
                    .uri("/clientes/{id}", pedido.getClienteId())
                    .retrieve()
                    .toBodilessEntity();
        } catch (HttpClientErrorException.NotFound e) {
            throw new IllegalArgumentException("Cliente não encontrado!");
        }

        return repository.save(pedido);
    }

    public List<Pedido> listarTodos() {
        return repository.findAll();
    }

    public Pedido buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }
}
