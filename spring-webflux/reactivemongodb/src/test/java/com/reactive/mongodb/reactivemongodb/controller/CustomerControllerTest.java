package com.reactive.mongodb.reactivemongodb.controller;



import com.reactive.mongodb.reactivemongodb.model.Customer;
import com.reactive.mongodb.reactivemongodb.service.CustomerService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class CustomerControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private CustomerService service;


  @Test
  @DisplayName("GET /consumer/1 - Found")
  void getCustomerByID() throws Exception {
    Mono<Customer> customerMono = Mono.just(new Customer("1", "xx", "yy"));
    doReturn(customerMono).when(service).findById(any());

    mockMvc.perform(MockMvcRequestBuilders
            .get("/consumer/{id}", 1)
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andDo(print());

    verify(service, Mockito.timeout(1)).findById(any());



  }


  @Test
  void getAll() throws Exception {
    //Execute the Get request
    this.mockMvc.perform(get("/consumer/all"))
        .andDo(print()).andExpect(status().isOk());
    //.andExpect(content().string(containsString("Hello, World")));
  }


/*
  @Test
  void createAccount() {
  }

 */
}