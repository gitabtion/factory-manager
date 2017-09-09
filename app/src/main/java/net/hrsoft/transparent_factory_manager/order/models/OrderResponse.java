package net.hrsoft.transparent_factory_manager.order.models;

import net.hrsoft.transparent_factory_manager.base.models.BaseModel;

/**
 * @author abtion.
 * @since 17/9/5 10:28.
 * email caiheng@hrsoft.net
 */

public class OrderResponse<T> extends BaseModel {
    private T orders;
    private int totalCount;

    public OrderResponse(T orders) {
        this.orders = orders;
    }

    public T getOrders() {
        return orders;
    }

    public void setOrders(T orders) {
        this.orders = orders;
    }
}
