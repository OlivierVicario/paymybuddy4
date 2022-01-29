package com.paymybuddy.model;

import java.util.ArrayList;
import java.util.List;

public class TransactionFormData {
	private String connection;
	private List<String> connections;
	private String description;
	private String amount;

	public String getConnection() {
		return connection;
	}

	public void setConnection(String connection) {
		this.connection = connection;
	}

	public List<String> getConnections() {
		return connections;
	}

	public void setConnections(List<User> userTo) {
		this.connections = new ArrayList<String>();
		for (User user : userTo) {
			this.connections.add(user.getFullName());
		}
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

}
