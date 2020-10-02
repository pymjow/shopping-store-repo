package com.productservice.interfaces.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.productservice.application.internal.commandservice.ProductCommandService;
import com.productservice.application.internal.queryservice.ProductQueryService;
import com.productservice.config.ResourceServerConfig;
import com.productservice.document.model.aggregates.Product;
import com.productservice.document.model.command.CreateProductCommand;
import com.productservice.interfaces.rest.dto.CreateProductDto;
import com.productservice.testutil.ProductTestUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
@Import({ResourceServerConfig.class})
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductQueryService productQueryService;

    @MockBean
    private ProductCommandService productCommandService;

    private ObjectMapper objectMapper;

    @Before
    public void setupTest(){
        objectMapper=new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,false);
    }

    private void setupFetchAll(){

        List<Product> productList=new ArrayList<>();
        productList.add(ProductTestUtil.mockProductWithId(1L));
        productList.add(ProductTestUtil.mockProductWithId(2L));
        Mockito.when(productQueryService.getProductList()).thenReturn(productList);

    }


    @Test
    @WithMockUser("test")
    public void testFetchAll() throws Exception {
        setupFetchAll();
        mockMvc.perform(MockMvcRequestBuilders.get("/products/all"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.*.name").exists());
    }

    private void setupSaveProduct(){

        CreateProductCommand createProductCommand=new CreateProductCommand();
        createProductCommand.setColor("green");
        createProductCommand.setHeight(1);
        createProductCommand.setWeight(1);
        createProductCommand.setWidth(11);
        createProductCommand.setPrice(1.0);
        createProductCommand.setManufactureDate(LocalDate.now());
        createProductCommand.setName("test");
        createProductCommand.setProductTypeCode("CR");

        Product product=ProductTestUtil.mockProductWithId(1L);

        Mockito.when(productCommandService.saveProduct(createProductCommand))
                .thenReturn(product);

    }

    @Test
    @WithMockUser("test")
    public void saveProduct() throws Exception {

        setupSaveProduct();

        CreateProductDto createProductDto=new CreateProductDto();
        createProductDto.setColor("green");
        createProductDto.setHeight(1);
        createProductDto.setWeight(1);
        createProductDto.setWidth(11);
        createProductDto.setPrice(1.0);
        createProductDto.setManufactureDate(LocalDate.now());
        createProductDto.setName("test");
        createProductDto.setProductTypeCode("CR");

        String json=objectMapper.writeValueAsString(createProductDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/products/save")
                .content(json).contentType(MediaType.APPLICATION_JSON_VALUE)
                .with(SecurityMockMvcRequestPostProcessors.csrf()))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }


}
