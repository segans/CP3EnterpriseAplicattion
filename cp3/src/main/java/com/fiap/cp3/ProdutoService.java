package com.fiap.cp3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

public interface ProdutoService {
    Page<Produto> listarProdutos(Pageable pageable, String categoria, BigDecimal precoMin, BigDecimal precoMax);

    Object listarProdutos(SpringDataWebProperties.Pageable pageable, String categoria, BigDecimal precoMin, BigDecimal precoMax);

    @Service
    public class ProdutoServiceImpl<Page, Specification> implements ProdutoService {
        private final ProdutoRepository produtoRepository;

        @Autowired
        public ProdutoServiceImpl(ProdutoRepository produtoRepository) {
            this.produtoRepository = produtoRepository;
        }

        @Override
        public Page<Produto> listarProdutos(SpringDataWebProperties.Pageable pageable, String categoria, BigDecimal precoMin, BigDecimal precoMax) {
            return null;
        }

        @Override
        public Page<Produto> listarProdutos(SpringDataWebProperties.Pageable pageable, String categoria, BigDecimal precoMin, BigDecimal precoMax) {
            Specification specification = Specification.where(null);

            if (categoria != null) {
                specification = specification.and((root, query, builder) ->
                        builder.equal(root.get("categoria"), categoria));
            }

            if (precoMin != null) {
                specification = specification.and((root, query, builder) ->
                        builder.greaterThanOrEqualTo(root.get("preco"), precoMin));
            }

            if (precoMax != null) {
                specification = specification.and((root, query, builder) ->
                        builder.lessThanOrEqualTo(root.get("preco"), precoMax));
            }

            return produtoRepository.findAll(specification, pageable);
        }
    }
}
