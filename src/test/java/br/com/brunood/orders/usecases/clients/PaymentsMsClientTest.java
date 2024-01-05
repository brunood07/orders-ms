package br.com.brunood.orders.usecases.clients;

import br.com.brunood.orders.factories.PaymentInfoFactoryTest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaymentsMsClientTest {

    public static MockWebServer mockWebServer;
    private PaymentsMsClient paymentsMsClient;
    private ObjectMapper objectMapper;

    @BeforeAll
    static void setUp() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();
    }

    @AfterAll
    static void tearDown() throws IOException {
        mockWebServer.shutdown();
    }

    @BeforeEach
    void initialize() {
        String baseUrl = String.format("http://localhost:%s", mockWebServer.getPort());
        paymentsMsClient = new PaymentsMsClient(WebClient.builder(), "");
        objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        WebClient webClient = WebClient.builder().baseUrl(baseUrl).build();
        ReflectionTestUtils.setField(paymentsMsClient, "webClient", webClient);
    }

    @Test
    void shouldBeAbleToGetPaymentInfoByOrderId() throws InterruptedException, JsonProcessingException {
        mockWebServer.enqueue((new MockResponse()
                .setBody(objectMapper.writeValueAsString(PaymentInfoFactoryTest.createPaymentDto()))
                .setResponseCode(200)
                .addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)));

        var payment = paymentsMsClient.getPaymentByOrderId("1");

        RecordedRequest recordedRequest = mockWebServer.takeRequest();
        assertEquals("GET", recordedRequest.getMethod());
        assertEquals("/api/v1/payments/1", recordedRequest.getPath());
        assertEquals(1, payment.getInstallments());
    }
}
