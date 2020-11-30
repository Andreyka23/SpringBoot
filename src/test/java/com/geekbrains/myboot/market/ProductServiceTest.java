package com.geekbrains.myboot.market;

import com.geekbrains.myboot.market.models.Category;
import com.geekbrains.myboot.market.models.Product;
import com.geekbrains.myboot.market.repositories.ProductRepository;
import com.geekbrains.myboot.market.services.CategoryService;
import com.geekbrains.myboot.market.services.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class ProductServiceTest {
    @Autowired
    private ProductService productService;

    @MockBean
    private ProductRepository productRepository;

    @Test
    public void findProductByIdTest() {
        Product productFromDB = new Product();
        productFromDB.setId(20L);
        productFromDB.setTitle("Bread");
        productFromDB.setPrice(23);

        Mockito.doReturn(Optional.of(productFromDB))
                .when(productRepository)
                .findById(20L);

        Product productBeard = productService.findById(20L).get();
        Assertions.assertNotNull(productBeard);
        Assertions.assertEquals("Bread", productBeard.getTitle());
        Mockito.verify(productRepository, Mockito.times(1)).findById(ArgumentMatchers.eq(20L));
    }

}
