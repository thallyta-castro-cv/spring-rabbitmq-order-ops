package br.com.thallyta.order.api.controller.openapi;

import br.com.thallyta.order.api.model.Order;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Pedidos", description = "Contém a operação para realização de pedidos")
public interface OrderControllerOpenApi {

    @Operation(summary = "Cria um novo pedido", description = "Recurso para criar um novo pedido",
            responses = {@ApiResponse(responseCode = "201", description = "Recurso criado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Order.class)))
            })
    ResponseEntity<Order> create(@RequestBody Order order);
}
