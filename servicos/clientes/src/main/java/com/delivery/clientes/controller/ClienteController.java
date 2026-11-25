package com.delivery.clientes.controller;

import com.delivery.clientes.entity.Cliente;
import com.delivery.clientes.service.ClienteService;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService service;

    public ClienteController(ClienteService service) {
        this.service = service;
    }

    @PostMapping
    public Cliente cadastrar(@RequestBody Cliente cliente) {
        return service.salvar(cliente);
    }

    @GetMapping
    public List<Cliente> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public Cliente buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }
}