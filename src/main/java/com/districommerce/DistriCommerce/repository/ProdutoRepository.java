package com.districommerce.DistriCommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.districommerce.DistriCommerce.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
