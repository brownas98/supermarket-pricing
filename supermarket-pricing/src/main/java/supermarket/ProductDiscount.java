package supermarket;

import java.math.BigDecimal;

public class ProductDiscount {

	private String discountName;
	private BigDecimal discount;
	private int qualifyingQuantity;

	public ProductDiscount(String discountName, BigDecimal discount, int qualifyingQuantity) {
		this.discountName = discountName;
		this.discount = discount;
		this.qualifyingQuantity = qualifyingQuantity;
	}

	public String getDiscountName() {
		return discountName;
	}

	public void setDiscountName(String discountName) {
		this.discountName = discountName;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public int getQualifyingQuantity() {
		return qualifyingQuantity;
	}

	public void setQualifyingQuantity(int qualifyingQuantity) {
		this.qualifyingQuantity = qualifyingQuantity;
	}
	
}
