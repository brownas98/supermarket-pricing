package supermarket;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ProductOrderCalculator {

	private final Map<String, Product> productDictionary;
	private final Map<String, ProductDiscount> productDiscountDictionary;
	
	private static final BigDecimal ZERO = BigDecimal.valueOf(0.00).setScale(2);

	public ProductOrderCalculator(Map<String, Product> productDictionary, Map<String, ProductDiscount> productDiscountDictionary) {
		this.productDictionary = productDictionary;
		this.productDiscountDictionary = productDiscountDictionary;
	}

	public BigDecimal calculatePricePounds(ProductOrder productOrder) {
		
		if (productOrder.getProductName().isEmpty() || !productDictionary.containsKey(productOrder.getProductName())) return ZERO;
		
		BigDecimal price = productDictionary.get(productOrder.getProductName()).getPricePence()
				.multiply(BigDecimal.valueOf(productOrder.getProductQuantity()));
		
		return formatToPounds(price);
	}
	
	public List<ProductDiscount> getProductDiscountsPence(ProductOrder productOrder)
	{
		if(!productDiscountDictionary.containsKey(productOrder.getProductName())) return Collections.emptyList();
		
		ProductDiscount productDiscount = productDiscountDictionary.get(productOrder.getProductName());
		
		List<ProductDiscount> productDiscounts = new ArrayList<ProductDiscount>();
		
		for (int i = productOrder.getProductQuantity(); i >= productDiscount.getQualifyingQuantity(); i = i - productDiscount.getQualifyingQuantity() )
		{
			productDiscounts.add(productDiscount);
		}
		
		return productDiscounts;
	}
	
	private BigDecimal formatToPounds(BigDecimal price)
	{
		return price.divide(BigDecimal.valueOf(100)).setScale(2, RoundingMode.HALF_UP);
	}

}
