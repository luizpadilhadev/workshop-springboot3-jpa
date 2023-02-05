package com.educandoweb.course.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import com.educandoweb.course.entities.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatTypes;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_order")
public class Order implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLong;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant moment;

    private Integer orderStatus;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private User client;

    @OneToMany(mappedBy = "id.order")
    private Set<OrderItem> items = new HashSet<>();

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private Payment payment;
    
    public Order() {

    }

    public Order(Long idLong, Instant timeInstant, OrderStatus orderStatus, User client) {
	super();
	this.idLong = idLong;
	this.moment = timeInstant;
	setOrderStatus(orderStatus);
	this.client = client;

    }

    public Long getIdLong() {
	return idLong;
    }

    public void setIdLong(Long idLong) {
	this.idLong = idLong;
    }

    public Instant getTimeInstant() {
	return moment;
    }

    public void setTimeInstant(Instant timeInstant) {
	this.moment = timeInstant;
    }

    public User getClient() {
	return client;
    }

    public void setClient(User client) {
	this.client = client;
    }

    public OrderStatus getOrderStatus() {
	return OrderStatus.valueOf(orderStatus);
    }

    public void setOrderStatus(OrderStatus orderStatus) {
	if (orderStatus != null)
	    this.orderStatus = orderStatus.getCode();
    }
    
    public Set<OrderItem> getItems(){
	return items;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
    
    public double getTotal( ) {
	double sum = 0.0;
	for (OrderItem x : items) {
	    sum = sum + x.getSubTotal();
	}
	return sum;
    }

    @Override
    public int hashCode() {
	return Objects.hash(idLong);
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Order other = (Order) obj;
	return Objects.equals(idLong, other.idLong);
    }

}
