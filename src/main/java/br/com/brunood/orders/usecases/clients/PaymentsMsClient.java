package br.com.brunood.orders.usecases.clients;

import br.com.brunood.orders.dtos.payments.PaymentsDTO;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class PaymentsMsClient {

    private final WebClient webClient;

    public PaymentsMsClient(WebClient.Builder webClientBuilder, @Value("${rest.client.payments}") String baseURL) {
        this.webClient = webClientBuilder.baseUrl(baseURL).build();
    }

    public PaymentsDTO getPaymentByOrderId(String orderId) {
        return this.webClient.get().uri("/api/v1/payments/{orderId}", orderId)
                .retrieve().bodyToMono(PaymentsDTO.class).block();
    }
}
