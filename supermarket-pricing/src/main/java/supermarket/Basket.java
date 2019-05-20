package supermarket;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Basket {

	private Map<String, ProductOrder> productOrders;
	private ProductOrderCalculator calculator;

	public Basket(ProductOrderCalculator calculator) {
		this.calculator = calculator;
		productOrders = new HashMap<>();
	}

	public void add(ProductOrder productOrder) {
		int existingQuantity = productOrders.containsKey(productOrder.getProductName())
				? productOrders.get(productOrder.getProductName()).getProductQuantity()
				: 0;

		productOrders.put(productOrder.getProductName(),
				new ProductOrder(productOrder.getProductName(), existingQuantity + productOrder.getProductQuantity()));
	}

	public Map<String, ProductOrder> getProductOrders() {
		return productOrders;
	}

	public void setProductOrders(Map<String, ProductOrder> productOrders) {
		this.productOrders = productOrders;
	}

	public BigDecimal getSubTotalPounds() {
		BigDecimal subTotal = BigDecimal.valueOf(0.0);

		for (ProductOrder value : productOrders.values()) {
			BigDecimal orderTotal = calculator.calculatePricePounds(value);
			subTotal = subTotal.add(orderTotal);
		}

		return subTotal.setScale(2);
	}

	public BigDecimal getDiscountTotalPounds() {
		BigDecimal discountTotal = BigDecimal.valueOf(0.0);

		for (ProductOrder value : productOrders.values()) {
			List<ProductDiscount> productDiscounts = calculator.getProductDiscountsPence(value);

			for (ProductDiscount productDiscount : productDiscounts) {
				discountTotal = discountTotal.add(productDiscount.getDiscount().divide(BigDecimal.valueOf(100)));
			}
		}

		return discountTotal.setScale(2);
	}
	
	public BigDecimal getGrandTotalPounds()
	{
		BigDecimal grandTotal = getSubTotalPounds().add(getDiscountTotalPounds());
		return grandTotal;
	}
}
