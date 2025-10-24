package com.producto.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.producto.demo.model.Producto;
import com.producto.demo.service.ProductoService;
import jakarta.validation.Valid;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    private final ProductoService service;

    public ProductoController(ProductoService service) {
        this.service = service;
    }

    // Listar productos
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("productos", service.listar());
        return "productos/lista";
    }

    // Crear productos
    @GetMapping("/nuevo")
    public String crearProducto(Model model) {
        model.addAttribute("producto", new Producto());
        return "productos/form";
    }

    // Guardar Productos
    @PostMapping  
    public String guardar(
        @Valid @ModelAttribute("producto") Producto producto,
        BindingResult result,
        RedirectAttributes ra) {
        
        if (result.hasErrors()) {
            return "productos/form";
        }
        service.crear(producto);
        ra.addFlashAttribute("mensaje", "Producto creado con éxito");
        return "redirect:/productos";
    }

    // Editar productos
    @GetMapping("/{id}/editar")
    public String editar(@PathVariable Long id, Model model, RedirectAttributes ra) { 
        var opt = service.buscarPorId(id);
        if (opt.isEmpty()) {
            ra.addFlashAttribute("error", "Producto no encontrado con id " + id);
            return "redirect:/productos";
        }
        model.addAttribute("producto", opt.get());
        return "productos/form";
    }

    // Eliminar productos
    @PostMapping("/{id}/eliminar")
    public String eliminar(@PathVariable Long id, RedirectAttributes ra) { 
        try {
            service.eliminar(id);
            ra.addFlashAttribute("mensaje", "Producto eliminado con éxito");
        } catch (RuntimeException e) {
            ra.addFlashAttribute("error", "Error al eliminar el producto: " + e.getMessage());
        }
        return "redirect:/productos";
    }

    // Actualizar productos
    @PostMapping("/{id}/actualizar")
    public String actualizar(
        @PathVariable Long id,
        @Valid @ModelAttribute("producto") Producto producto,
        BindingResult result,
        RedirectAttributes ra) {
        
        if (result.hasErrors()) {
            return "productos/form";  
        }
        
        try {
            service.actualizar(id, producto);
            ra.addFlashAttribute("mensaje", "Producto actualizado con éxito");
        } catch (RuntimeException e) {
            ra.addFlashAttribute("error", "Error al actualizar el producto: " + e.getMessage());
        }
        return "redirect:/productos";
    }
}