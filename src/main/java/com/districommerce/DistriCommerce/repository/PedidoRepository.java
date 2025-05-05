package com.districommerce.DistriCommerce.repository;

import com.districommerce.DistriCommerce.entity.Pedido;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface PedidoRepository extends MongoRepository<Pedido, String> {
    List<Pedido> findByUsuarioId(Long usuarioId);
}
