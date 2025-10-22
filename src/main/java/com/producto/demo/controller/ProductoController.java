package com.producto.demo.controller;
 


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.producto.demo.model.Producto;
import com.producto.demo.service.ProductoService;



@Controller
@RequestMapping("/productos")
public class ProductoController {
    
    private final ProductoService service; 

    public ProductoController(ProductoService service){
        this.service = service; 
    }


    //Listar productos 
    @GetMapping
    public String Listar(Model model){
        model.addAttribute("productos", service.listar());
        return "productos/lista";
    }
    

    // Crear productos
    @GetMapping("/nuevo")
    public String crearProducto(Model model){
        model.addAttribute("producto", new Producto());
        return "productos/form";
    }
    
    //Guardar Productos
    @PostMapping  
    public String guardar(@ModelAttribute("producto") Producto producto,
        BindingResult result){
            if (result.hasErrors()){
                return "productos/form";
            }
            service.crear(producto);
            return "redirect:/productos";
        }
    
}
 