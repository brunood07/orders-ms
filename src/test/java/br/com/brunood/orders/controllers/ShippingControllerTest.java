package br.com.brunood.orders.controllers;

import br.com.brunood.orders.enums.ShippingStatus;
import br.com.brunood.orders.exceptions.EmptyBodyException;
import br.com.brunood.orders.exceptions.ShippingNotFound;
import br.com.brunood.orders.usecases.UpdateShippingStatusUseCase;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ShippingControllerTest {

    @Mock
    UpdateShippingStatusUseCase updateShippingStatusUseCase;
    @InjectMocks
    ShippingController shippingController;
    private final String baseUrl = "/api/v1/shipping/update";
    @BeforeEach
    void setUp() {
        RestAssuredMockMvc.standaloneSetup(shippingController);
    }

    @Test
    void shouldReturn404WithAMalformedUrl() {
        String malformedUrl = "/api/v1/ship";

        given().when()
                .put(malformedUrl)
                .then()
                .statusCode(404);
    }

    @Test
    void shouldReturn200WhenCreateOrderWithValidPayload() {

        given().when()
                .put(baseUrl + "/1?status=FINISHED")
                .then()
                .statusCode(200);
    }

    @Test
    void shouldReturn400WhenCreateOrderWithInvalidPayload() {
        doThrow(ShippingNotFound.class).when(updateShippingStatusUseCase).execute(anyLong(), any(ShippingStatus.class));

        given().when()
                .put(baseUrl + "/1?status=FINISHED")
                .then()
                .statusCode(400);
    }
}
