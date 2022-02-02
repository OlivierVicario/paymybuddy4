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
@Table(name = "virement")
public class BankTransfert {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idvirement")
	private int bankTransfertId;
	@Column(name = "montant")
	private BigDecimal amount;
	
	@ManyToOne(
			cascade = CascadeType.ALL
                                      )
	@JoinColumn(name="compte_bancaire_utilisateur_idutilisateur")
	private User user;

	public int getBankTransfertId() {
		return bankTransfertId;
	}

	public void setBankTransfertId(int bankTransfertId) {
		this.bankTransfertId = bankTransfertId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
