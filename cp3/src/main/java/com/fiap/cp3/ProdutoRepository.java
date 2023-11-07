package com.fiap.cp3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

public interface ProdutoRepository extends JpaSpecificationExecutor<Produto> {



@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    private final ProdutoService produtoService;

    @Autowired
    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping
    public ResponseEntity<Page<Produto>> listarProdutos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String categoria,
            @RequestParam(required = false) BigDecimal precoMin,
            @RequestParam(required = false) BigDecimal precoMax,
            @RequestParam(defaultValue = "id,asc") String sort) {

        SpringDataWebProperties.Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Order.asc("id")));

        if (!sort.isEmpty()) {
            String[] parts = sort.split(",");
            if (parts.length == 2) {
                Sort.Direction direction = Sort.Direction.fromString(parts[1]);
                pageable = PageRequest.of(page, size, direction, parts[0]);
            }
        }

        Page<Produto> produtos = produtoService.listarProdutos(pageable, categoria, precoMin, precoMax);
        return ResponseEntity.ok(produtos);
    }
}

}
