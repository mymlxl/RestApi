package com.example.demo;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;	
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.controller.UserController;
import com.example.demo.entity.UserEntity;
import com.example.demo.service.UserService;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyObject;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.*;

import io.restassured.module.mockmvc.RestAssuredMockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {
    @Mock
    UserService userService;
    
    @Before
    public void configMock() {
        MockitoAnnotations.initMocks(this);
        RestAssuredMockMvc.standaloneSetup(new UserController(userService));
    }
    //test case 1
    @Test
    public void testGetUserFromDB(){
        Mockito.when(userService.findById(anyInt())).thenReturn(new UserEntity(1,"test name", 20, 10000d));
        given().accept("application/json").get("/api/users/1").peek().
                then().assertThat()
                .statusCode(200)
                .body(Matchers.equalTo("{\"id\":1,\"name\":\"test name\",\"age\":20,\"salary\":10000.0}"));
    }
    
    //test case 2. Because my save() return type is void, I can't do this test.
}
