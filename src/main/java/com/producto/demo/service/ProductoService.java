package com.producto.demo.service;

import com.producto.demo.model.Producto;
import java.util.List; 
import java.util.Optional; 


public interface ProductoService {
    List<Producto> listar();
    Producto crear(Producto producto);

    Optional<Producto> buscarPorId(Long id);
    Producto actualizar(Long id, Producto producto);
    void eliminar(Long id);
    
}
 