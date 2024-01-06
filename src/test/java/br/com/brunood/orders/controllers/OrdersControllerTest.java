package br.com.brunood.orders.controllers;

import br.com.brunood.orders.dtos.CreateOrderUseCaseRequestDTO;
import br.com.brunood.orders.dtos.CreateOrderUseCaseResponseDTO;
import br.com.brunood.orders.dtos.GetOrderByOrderIdUseCaseDTO;
import br.com.brunood.orders.exceptions.EmptyBodyException;
import br.com.brunood.orders.exceptions.OrderNotFoundException;
import br.com.brunood.orders.factories.*;
import br.com.brunood.orders.usecases.CreateOrderUseCase;
import br.com.brunood.orders.usecases.FindOrdersByClientIdUseCase;
import br.com.brunood.orders.usecases.GetOrderByOrderIdUseCase;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrdersControllerTest {

    @Mock
    CreateOrderUseCase createOrderUseCase;
    @Mock
    GetOrderByOrderIdUseCase getOrderByOrderIdUseCase;
    @Mock
    FindOrdersByClientIdUseCase findOrdersByClientIdUseCase;
    @InjectMocks
    private OrdersController ordersController;

    private final String baseUrl = "/api/v1/orders";

    @BeforeEach
    void setUp() {
        RestAssuredMockMvc.standaloneSetup(ordersController);
    }

    @Test
    void shouldReturn404WithAMalformedUrl() {
        String malformedUrl = "/api/v1/order";

        given().when()
                .put(malformedUrl)
                .then()
                .statusCode(404);
    }

    @Test
    void shouldReturn200WhenCreateOrderWithValidPayload() {
        when(createOrderUseCase.execute(any())).thenReturn(orderReturn());

        given().body(orderPayload()).contentType(MediaType.APPLICATION_JSON).when()
                .post(baseUrl)
                .then()
                .statusCode(201);
    }

    @Test
    void shouldReturn400WhenCreateOrderWithInvalidPayload() {
        when(createOrderUseCase.execute(null)).thenThrow(EmptyBodyException.class);

        given().body("{}").contentType(MediaType.APPLICATION_JSON).when()
                .post(baseUrl)
                .then()
                .statusCode(400);
    }

    @Test
    void shouldReturn200WhenGetOrderById() {
        when(getOrderByOrderIdUseCase.execute(anyLong())).thenReturn(any(GetOrderByOrderIdUseCaseDTO.class));

        given().body(orderPayload()).contentType(MediaType.APPLICATION_JSON).when()
                .get(baseUrl + "/1")
                .then()
                .statusCode(200);
    }

    @Test
    void shouldReturn400WhenGetOrderById() {
        when(getOrderByOrderIdUseCase.execute(anyLong())).thenThrow(OrderNotFoundException.class);

        given().contentType(MediaType.APPLICATION_JSON).when()
                .get(baseUrl + "/1")
                .then()
                .statusCode(404);
    }

    @Test
    void shouldReturn200WhenFindOrderByClientId() {
        when(findOrdersByClientIdUseCase.execute(anyLong())).thenReturn(any());

        given().body(orderPayload()).contentType(MediaType.APPLICATION_JSON).when()
                .get(baseUrl + "/client/1")
                .then()
                .statusCode(200);
    }

    public static CreateOrderUseCaseResponseDTO orderReturn() {

        return CreateOrderUseCaseResponseDTO.builder()
                .order(OrderFactoryTest.createOrder())
                .address(AddressFactoryTest.createAddress())
                .priceInfo(PriceInfoFactoryTest.createPriceInfo())
                .products(ProductsFactoryTest.createOrderProducts())
                .build();
    }

    public static CreateOrderUseCaseRequestDTO orderPayload() {

        return CreateOrderUseCaseRequestDTO.builder()
                .addressInfo(AddressFactoryTest.createAddressPayload())
                .order(OrderFactoryTest.createOrderPayload())
                .paymentInfo(PaymentInfoFactoryTest.createPaymentInfoWithCreditCardPayload())
                .priceInfo(PriceInfoFactoryTest.createPriceInfoPayload())
                .products(ProductsFactoryTest.createOrderProductsPayload())
                .build();
    }

}
