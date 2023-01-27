package com.educandoweb.course.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

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
    private Instant moment;
    
    @ManyToOne
    @JoinColumn(name = "client_id")
    private User client;
    
    
    public Order() {
	
    }

    public Order(Long idLong, Instant timeInstant, User client) {
	super();
	this.idLong = idLong;
	this.moment = timeInstant;
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
