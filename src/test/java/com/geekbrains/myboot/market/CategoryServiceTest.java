package com.geekbrains.myboot.market;

import com.geekbrains.myboot.market.models.Category;
import com.geekbrains.myboot.market.repositories.CategoryRepository;
import com.geekbrains.myboot.market.services.CategoryService;
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
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CategoryServiceTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private CategoryService categoryService;

    @Test
    @WithMockUser(username = "user", authorities = "ROLE_USER")
    public void findAllTest() throws Exception {
        Category food = new Category();
        food.setId(1L);
        food.setTitle("Food");
        Category drink = new Category();
        drink.setId(2L);
        drink.setTitle("Drink");
        List<Category> allCategories = new ArrayList<>(Arrays.asList(
                food,
                drink
        ));
         given(categoryService.findAll()).willReturn(allCategories);

        mvc.perform(get("/api/v1/categories")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].title", is(allCategories.get(0).getTitle())));
    }
}
