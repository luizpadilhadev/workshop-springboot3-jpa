package com.educandoweb.course.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import java.util.Objects;

import com.educandoweb.course.entities.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatTypes;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_order")
public class Order implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLong;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone =  "GMT")
    private Instant moment;
    
    private Integer  orderStatus;
    
    @ManyToOne
    @JoinColumn(name = "client_id")
    private User client;
    
    
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
