package com.diba.beneficiary.core.models.order;

import com.diba.beneficiary.core.models.AbstractAggregate;
import com.diba.beneficiary.shared.messages.events.EventMetadata;
import com.diba.beneficiary.shared.messages.events.OrderEvent;
import com.diba.beneficiary.shared.messages.utils.Message;

import java.util.UUID;

public class Order  extends AbstractAggregate<OrderEvent, UUID> {


    private  String phoneNumber ;
    private Product products;

    private OrderStatus status;

    public OrderStatus getStatus(){
        return this.status ;
    }
    public String getPhoneNumber(){
        return this.phoneNumber ;
    }


    public static Order Initiate(UUID orderId, String phoneNumber, String correlationId) {
        return new Order(orderId , phoneNumber );
    }

    OrderStatus status() {
        return status;
    }

    public Order( UUID id, String phoneNumber
    ) {
        this.id = id;
        this.phoneNumber = phoneNumber;
    }
    public Product products() {
        return products;
    }

    public void addProductItem(Product productItems , String correlationId , long causeationId) {

    }

    private Order(){

    }

    public static Order empty(){
        return new Order();
    }



    public static String mapToStreamId(UUID orderId) {
        return "Order-%s".formatted(orderId);
    }


    @Override
    public void when(OrderEvent event) throws Exception {

    }
}
