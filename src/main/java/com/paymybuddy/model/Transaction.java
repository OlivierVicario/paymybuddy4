package com.paymybuddy.model;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "transaction")
public class Transaction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idtransaction")
	private int transactionId;
	@Column(name = "description")
	private String description;
	@Column(name = "montant")
	private BigDecimal amount;
	
	@ManyToOne(
			cascade = CascadeType.ALL
                                      )
	@JoinColumn(name="utilisateur_idutilisateur")
	private User userFrom;
	
	@ManyToOne(
			cascade = CascadeType.ALL
                                      )
	@JoinColumn(name="utilisateur_idutilisateur1")
	private User userTo;

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public User getUserFrom() {
		return userFrom;
	}

	public void setUserFrom(User userFrom) {
		this.userFrom = userFrom;
	}

	public User getUserTo() {
		return userTo;
	}

	public void setUserTo(User userTo) {
		this.userTo = userTo;
	}
	
	public Transaction() {}
	
}