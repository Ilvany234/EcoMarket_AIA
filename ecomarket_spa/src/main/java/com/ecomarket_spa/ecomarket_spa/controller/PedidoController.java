package com.ecomarket_spa.ecomarket_spa.controller;


import com.ecomarket_spa.ecomarket_spa.model.Pedido;
import com.ecomarket_spa.ecomarket_spa.service.PedidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping("/api/pedidos")
@Tag(name = "Pedidos", description = "Operaciones relacionadas con pedidos")
public class PedidoController {


    private final PedidoService pedidoService;


    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }


    @GetMapping
    @Operation(summary = "Obtener todos los pedidos")
    public List<Pedido> obtenerTodos() {
        return pedidoService.obtenerTodosLosPedidos();
    }


    @GetMapping("/{id}")
    @Operation(summary = "Obtener un pedido por su ID")
    public Pedido obtenerPorId(@PathVariable Long id) {
        return pedidoService.obtenerPedidoPorId(id);
    }


    @PostMapping
    @Operation(summary = "Crear un nuevo pedido")
    public Pedido crearPedido(@RequestBody Pedido pedido) {
        return pedidoService.crearPedido(pedido);
    }


    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un pedido existente")
    public Pedido actualizarPedido(@PathVariable Long id, @RequestBody Pedido pedido) {
        return pedidoService.actualizarPedido(id, pedido);
    }


    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un pedido")
    public void eliminarPedido(@PathVariable Long id) {
        pedidoService.eliminarPedido(id);
    }
}
