package com.producto.demo.service;

import com.producto.demo.model.Producto;
import com.producto.demo.repository.ProductoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
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
    public Producto crear(Producto producto) {
        // Validación adicional si es necesario
        if (producto.getNombre() == null || producto.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del producto no puede estar vacío");
        }
        if (producto.getPrecio() == null || producto.getPrecio().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("El precio debe ser mayor o igual a 0");
        }
        return repo.save(producto);
    }

    @Override
    public Producto actualizar(Long id, Producto producto) {
        return repo.findById(id)
            .map(actual -> {
                // Validaciones
                if (producto.getNombre() == null || producto.getNombre().trim().isEmpty()) {
                    throw new IllegalArgumentException("El nombre del producto no puede estar vacío");
                }
                if (producto.getPrecio() == null || producto.getPrecio().compareTo(BigDecimal.ZERO) < 0 ){
                    throw new IllegalArgumentException("El precio debe ser mayor o igual a 0");
                }
                
                actual.setNombre(producto.getNombre());
                actual.setPrecio(producto.getPrecio());
                return repo.save(actual);
            }).orElseThrow(() -> new RuntimeException("Producto no encontrado con id " + id));
    }

    @Override
    public void eliminar(Long id) {
        if (!repo.existsById(id)) {
            throw new RuntimeException("Producto no encontrado con id " + id);
        }
        repo.deleteById(id);        
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Producto> buscarPorId(Long id) {
        return repo.findById(id);   
    }
}