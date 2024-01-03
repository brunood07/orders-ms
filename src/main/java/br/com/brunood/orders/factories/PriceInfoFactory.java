package br.com.brunood.orders.factories;

import br.com.brunood.orders.dtos.CreatePriceInfoDTO;
import br.com.brunood.orders.entities.PriceInfo;

import java.math.BigDecimal;

public class PriceInfoFactory {

    public static PriceInfo createPriceInfo(Long orderId, CreatePriceInfoDTO data, BigDecimal total) {

        return PriceInfo.builder()
                .discount(data.getDiscount())
                .orderId(orderId)
                .shipping(data.getShipping())
                .subTotal(data.getSubTotal())
                .tax(data.getTax())
                .total(total)
                .build();
    }
}
