package com.producto.demo.model;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;



@Entity
public class Producto {
    
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id; 

    @NotBlank(message="Nombre de producto requerido")
    @Size(max=150, message="Nombre de producto maximo 150 caracteres")
    private String nombre; 

    @NotNull(message="El precio es obligatorio")
    @DecimalMin(value="0.0", inclusive=true, message="El precio no puede ser negativo")
    private BigDecimal precio; 

    //Getters y Setters
    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id; 
    }

    public String getNombre(){
        return nombre; 
    }

    public void setNombre(String nombre){
        this.nombre=nombre; 
    }
    
    public BigDecimal getPrecio(){
        return precio; 
    }

    public void setPrecio(BigDecimal precio){
        this.precio = precio; 
    }

}
