package net.hrsoft.transparent_factory_manager.order.models;

import net.hrsoft.transparent_factory_manager.base.models.BaseModel;

/**
 * @author abtion.
 * @since 17/9/10 11:47.
 * email caiheng@hrsoft.net
 */

public class CreateOrderResponse extends BaseModel {
    private String orderCode;
    private int orderId;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public CreateOrderResponse(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }
}
