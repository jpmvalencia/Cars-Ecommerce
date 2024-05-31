package com.springboot.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.springboot.app.model.Product;
import com.springboot.app.service.ProductService;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/app/products")
    public String showProductForm(Model model) {

        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);

        model.addAttribute("product", new Product());
        return "product-form";

    }

    @PostMapping("/app/products/save-product")
    public String guardarProducto(@ModelAttribute Product product, Model model) {
        productService.saveProduct(product);
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "redirect:/app/products";
    }

    @GetMapping("/app/products/eliminar-producto/{id}")
    public String eliminarProducto(Model model, @PathVariable(name = "id") Long id) {
        productService.deleteProduct(id);
        return "redirect:/app/products"; // Redirige a la página principal
    }

    @GetMapping("/app/products/editar-producto/{id}")
    public String mostrarFormularioEdicion(@PathVariable Long id, Model model) {
    // Obtener el producto correspondiente desde la base de datos
    Product product = productService.findById(id);
    
    // Agregar el producto al modelo para que se muestre en el formulario de edición
    model.addAttribute("editingMode", true);
    model.addAttribute("product", product);
    List<Product> products = productService.getAllProducts();
            model.addAttribute("products", products);
    
    // Renderizar la vista del formulario de edición
    productService.saveProduct(product);
    return "update-form";
}

    @GetMapping("/cars")
    public String showCars(Model model) {
        model.addAttribute("cars", productService.getAllProducts());
        return "home-cars";
    }

}
