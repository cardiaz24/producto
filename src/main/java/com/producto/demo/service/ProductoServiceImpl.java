
package com.producto.demo.service;

import com.producto.demo.model.Producto;
import com.producto.demo.repository.ProductoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository repo;

    public ProductoServiceImpl(ProductoRepository repo) {
        this.repo = repo;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Producto> listar() {
        return repo.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Producto crear(Producto producto) {
        return repo.save(producto);
    }

}

// se pueden llegar a implementar reglas de negocio