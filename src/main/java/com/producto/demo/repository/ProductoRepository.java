package com.producto.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.producto.demo.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long>
{}