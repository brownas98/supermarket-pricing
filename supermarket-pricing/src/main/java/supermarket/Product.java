package supermarket;

import java.math.BigDecimal;

public class Product {

	private String name;
	private BigDecimal pricePence;

	public Product(String name, BigDecimal pricePence) {
		this.name = name;
		this.pricePence = pricePence;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPricePence() {
		return pricePence;
	}

	public void setPricePence(BigDecimal pricePence) {
		this.pricePence = pricePence;
	}

}
