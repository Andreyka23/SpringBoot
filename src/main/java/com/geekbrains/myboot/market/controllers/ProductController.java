package com.geekbrains.myboot.market.controllers;

import com.geekbrains.myboot.market.models.Product;
import com.geekbrains.myboot.market.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {
    private ProductService productService;

    @GetMapping
    public String showAllProducts(Model model, @RequestParam(defaultValue = "1", name = "p") Integer page, @RequestParam(defaultValue = "0", name = "min") Integer min, @RequestParam(defaultValue = "0", name = "max") Integer max) {
        if (page < 1) {
            page = 1;
        }
        int perPage = 5;
        if ( min > 0 && max > 0 )
            model.addAttribute("products", productService.getProductByPriceGreaterThanEqualAndPriceLessThanEqual(page - 1, perPage, min, max));
        else if ( min > 0 )
            model.addAttribute("products", productService.getProductByPriceGreaterThanEqual(page - 1, perPage, min));
        else if ( max > 0 )
            model.addAttribute("products", productService.getProductByPriceLessThanEqual(page - 1, perPage, max));
        else
            model.addAttribute("products", productService.findAll(page - 1, perPage));

        //pagination
        int prevPage = page - 1;
        int nextPage = page + 1;
        long countPage = (productService.countProducts() / perPage);
        long ost = (productService.countProducts() % perPage);
        if (ost > 0)
            countPage++;
        model.addAttribute("page", page);
        model.addAttribute("prevPage", prevPage);
        model.addAttribute("nextPage", nextPage);
        model.addAttribute("countPage", countPage);
        model.addAttribute("min", min);
        model.addAttribute("max", max);
        return "products";
    }

}
