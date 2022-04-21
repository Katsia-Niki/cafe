package by.jwd.cafe.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.StringJoiner;

public class Order extends AbstractEntity {
    private static long serialVersionUID = 1L;
    private int orderId;
    private int userId;
    private PaymentType paymentType;
    private Date pickUpTime;
    private BigDecimal orderCost;
    private Order.Status status;
    private boolean isPaid;
    private Date creationDate;

    public enum Status {
        ACTIVE, FINISHED, CANCELLED
    }
    public Order() {
    }

    public static class OrderBuilder {
        private Order newOrder;
        {
            newOrder = new Order();
        }
        public OrderBuilder withOrderId(int orderId) {
            newOrder.orderId = orderId;
            return this;
        }

        public OrderBuilder withUserId(int userId) {
            newOrder.userId = userId;
            return this;
        }

        public OrderBuilder withPaymentType(PaymentType paymentType) {
            newOrder.paymentType = paymentType;
            return this;
        }

        public OrderBuilder withPickUpTime(Date pickUpTime) {
            newOrder.pickUpTime = pickUpTime;
            return this;
        }

        public OrderBuilder withOrderCost(BigDecimal orderCost) {
            newOrder.orderCost = orderCost;
            return this;
        }

        public OrderBuilder withStatus(Order.Status status) {
            newOrder.status = status;
            return this;
        }

        public OrderBuilder withIsPaid(boolean isPaid) {
            newOrder.isPaid = isPaid;
            return this;
        }

        public OrderBuilder withCreationDate(Date creationDate) {
            newOrder.creationDate = creationDate;
            return this;
        }

        public Order build() {
            return newOrder;
        }
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public Date getPickUpTime() {
        return pickUpTime;
    }

    public void setPickUpTime(Date pickUpTime) {
        this.pickUpTime = pickUpTime;
    }

    public BigDecimal getOrderCost() {
        return orderCost;
    }

    public void setOrderCost(BigDecimal orderCost) {
        this.orderCost = orderCost;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (orderId != order.orderId) return false;
        if (userId != order.userId) return false;
        if (isPaid != order.isPaid) return false;
        if (paymentType != order.paymentType) return false;
        if (pickUpTime != null ? !pickUpTime.equals(order.pickUpTime) : order.pickUpTime != null) return false;
        if (orderCost != null ? !orderCost.equals(order.orderCost) : order.orderCost != null) return false;
        if (status != order.status) return false;
        return creationDate != null ? creationDate.equals(order.creationDate) : order.creationDate == null;
    }

    @Override
    public int hashCode() {
        int result = orderId;
        result = 31 * result + userId;
        result = 31 * result + (paymentType != null ? paymentType.hashCode() : 0);
        result = 31 * result + (pickUpTime != null ? pickUpTime.hashCode() : 0);
        result = 31 * result + (orderCost != null ? orderCost.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (isPaid ? 1 : 0);
        result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(", ", "Order{", "}");
        joiner.add("orderId=" + orderId);
        joiner.add("userId=" + userId);
        joiner.add("paymentType=" + paymentType);
        joiner.add("pickUpTime=" + pickUpTime);
        joiner.add("orderCost=" + orderCost);
        joiner.add("status=" + status);
        joiner.add("isPaid=" + isPaid);
        joiner.add("creationDate=" + creationDate);
        return joiner.toString();
    }
}