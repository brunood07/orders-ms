package br.com.brunood.orders.factories;

import br.com.brunood.orders.dtos.CreatePriceInfoDTO;
import br.com.brunood.orders.entities.PriceInfo;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PriceInfoFactory {

    public static PriceInfo createPriceInfo() {

        return PriceInfo.builder()
                .discount(BigDecimal.ZERO)
                .idPriceInfo(1L)
                .orderId(1L)
                .shipping(BigDecimal.TEN)
                .subTotal(new BigDecimal(1000))
                .build();
    }

    public static CreatePriceInfoDTO createPriceInfoPayload() {

        return CreatePriceInfoDTO.builder()
                .discount(BigDecimal.ZERO)
                .shipping(BigDecimal.TEN)
                .subTotal(new BigDecimal(1000))
                .tax(BigDecimal.ZERO)
                .build();
    }
}
