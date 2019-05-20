package supermarket;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class App {

	public static void main(String[] args) {
		
		Map<String, Product> productDictionary = new HashMap<>();
		
		productDictionary.put("Beans", new Product("Beans", BigDecimal.valueOf(50)));
		productDictionary.put("Coke", new Product("Coke", BigDecimal.valueOf(70)));
		productDictionary.put("Oranges", new Product("Oranges", BigDecimal.valueOf(0.199)));
		
		Map<String, ProductDiscount> productDiscountDictionary = new HashMap<>();
		
		productDiscountDictionary.put("Beans", new ProductDiscount("Beans 3 for 2", BigDecimal.valueOf(-50), 3));
		productDiscountDictionary.put("Coke", new ProductDiscount("Coke 2 for £1", BigDecimal.valueOf(-40), 2));
		
		Basket basket1 = new Basket( new ProductOrderCalculator(productDictionary, productDiscountDictionary));
		ProductOrder productOrder1 = new ProductOrder("Beans", 3);
		ProductOrder productOrder2 = new ProductOrder("Coke", 2);
		//weight calculated items are input using grams. eg 0.2 kg = 200 
		ProductOrder productOrder3 = new ProductOrder("Oranges", 200);
		basket1.add(productOrder1);
		basket1.add(productOrder2);
		basket1.add(productOrder3);
		
		System.out.println("Supermarket Pricing Example");
		System.out.println("");
		System.out.println("Price List");
		System.out.println("Beans 0.50");
		System.out.println("Coke 0.70");
		System.out.println("Oranges @ 1.99 per kg");
		System.out.println("");
		System.out.println("Discount List");
		System.out.println("Beans 3 for 2");
		System.out.println("Coke 2 for £1");
		System.out.println("");
		System.out.println("Quantity List");
		System.out.println("Beans X 3");
		System.out.println("Coke X 2");
		System.out.println("Oranges X 200 grams");
		System.out.println("");
		System.out.println("Sub Total " + basket1.getSubTotalPounds());
		System.out.println("Discount Total " + basket1.getDiscountTotalPounds());
		System.out.println("Grand Total " + basket1.getGrandTotalPounds());
		
	}

}
