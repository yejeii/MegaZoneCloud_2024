package gof_StrategyP;

import java.util.Objects;

public class CreditCard {

	private int amount = 1_000;
	private final String number;
	private final String date;
	private final String cvv;
	
	public CreditCard(String number, String date, String cvv) {
		this.number = number;
		this.date = date;
		this.cvv = cvv;
	}
	
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getNumber() {
		return number;
	}
	public String getDate() {
		return date;
	}
	public String getCvv() {
		return cvv;
	}

	@Override
	public int hashCode() {
		return Objects.hash(amount, cvv, date, number);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CreditCard other = (CreditCard) obj;
		return amount == other.amount && Objects.equals(cvv, other.cvv) && Objects.equals(date, other.date)
				&& Objects.equals(number, other.number);
	}

	@Override
	public String toString() {
		return "CreditCard [amount=" + amount + ", number=" + number + ", date=" + date + ", cvv=" + cvv + "]";
	}
	
}
