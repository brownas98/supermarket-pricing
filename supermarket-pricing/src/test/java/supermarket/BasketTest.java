package supermarket;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class BasketTest {
	
	Basket cut;
	
	ProductOrderCalculator calculator;
	
	private static final BigDecimal SUB_TOTAL_1 = BigDecimal.valueOf(4.3).setScale(2);
	private static final BigDecimal SUB_TOTAL_2 = BigDecimal.valueOf(2.1).setScale(2);
	private static final BigDecimal MINUS_POINT_FIVE = BigDecimal.valueOf(-0.50).setScale(2);
	private static final BigDecimal MINUS_ONE = BigDecimal.valueOf(-1.0).setScale(2);
	private static final BigDecimal MINUS_POINT_EIGHT = BigDecimal.valueOf(-1.80).setScale(2);
	private static final BigDecimal TWO_POINT_FOUR = BigDecimal.valueOf(2.40).setScale(2);
	
	@Before
	public void setup()
	{
		
		Map<String, Product> productDictionary = new HashMap<>();
		
		productDictionary.put("Beans", new Product("Beans", BigDecimal.valueOf(50)));
		productDictionary.put("Coke", new Product("Coke", BigDecimal.valueOf(70)));
		productDictionary.put("Oranges", new Product("Oranges", BigDecimal.valueOf(0.199)));
		
		Map<String, ProductDiscount> productDiscountDictionary = new HashMap<>();
		
		productDiscountDictionary.put("Beans", new ProductDiscount("Beans 3 for 2", BigDecimal.valueOf(-50), 3));
		productDiscountDictionary.put("Coke", new ProductDiscount("Coke 2 for £1", BigDecimal.valueOf(-40), 2));
		
		calculator = new ProductOrderCalculator(productDictionary, productDiscountDictionary);
		
		cut = new Basket(calculator);
	}

	@Test
	public void shouldAddSingleProductOrderTest() {
		
		ProductOrder productOrder = new ProductOrder("Beans", 1);
		
		cut.add(productOrder);
		
		assertThat(cut.getProductOrders().get("Beans").getProductQuantity(), is(1));
		assertThat(cut.getProductOrders().keySet().size(), is(1));
		
		
	}
	
	@Test
	public void shouldAddMultipleProductOrderTest() {
		
		ProductOrder productOrder1 = new ProductOrder("Beans", 1);
		ProductOrder productOrder2 = new ProductOrder("Beans", 2);
		
		cut.add(productOrder1);
		cut.add(productOrder2);
		
		assertThat(cut.getProductOrders().get("Beans").getProductQuantity(), is(3));
		assertThat(cut.getProductOrders().keySet().size(), is(1));
		
	}
	
	@Test
	public void shouldAddMultipleDifferingProductOrderTest() {
		
		ProductOrder productOrder1 = new ProductOrder("Beans", 1);
		ProductOrder productOrder2 = new ProductOrder("Beans", 2);
		
		ProductOrder productOrder3 = new ProductOrder("Coke", 1);
		ProductOrder productOrder4 = new ProductOrder("Coke", 3);
		
		cut.add(productOrder1);
		cut.add(productOrder2);
		cut.add(productOrder3);
		cut.add(productOrder4);
		
		assertThat(cut.getProductOrders().get("Beans").getProductQuantity(), is(3));
		assertThat(cut.getProductOrders().get("Coke").getProductQuantity(), is(4));
		assertThat(cut.getProductOrders().keySet().size(), is(2));
		
	}
	
	@Test
	public void shouldGetSubtotalMultipleDifferingProductOrderTest() {
		
		ProductOrder productOrder1 = new ProductOrder("Beans", 1);
		ProductOrder productOrder2 = new ProductOrder("Beans", 2);
		
		ProductOrder productOrder3 = new ProductOrder("Coke", 1);
		ProductOrder productOrder4 = new ProductOrder("Coke", 3);
		
		cut.add(productOrder1);
		cut.add(productOrder2);
		cut.add(productOrder3);
		cut.add(productOrder4);
		
		assertThat(cut.getSubTotalPounds(), is(SUB_TOTAL_1));
		
	}
	
	@Test
	public void shouldGetSubtotalMultipleDifferingWithWeightProductOrderTest() {
		
		ProductOrder productOrder1 = new ProductOrder("Beans", 1);
		ProductOrder productOrder2 = new ProductOrder("Beans", 2);
		
		ProductOrder productOrder3 = new ProductOrder("Oranges", 200);
		ProductOrder productOrder4 = new ProductOrder("Oranges", 100);
		
		cut.add(productOrder1);
		cut.add(productOrder2);
		cut.add(productOrder3);
		cut.add(productOrder4);
		
		assertThat(cut.getSubTotalPounds(), is(SUB_TOTAL_2));
		
	}
	
	@Test
	public void shouldGetDiscountTotalSingleProductOrder() {
		ProductOrder productOrder1 = new ProductOrder("Beans", 3);
		
		cut.add(productOrder1);
		
		assertThat(cut.getDiscountTotalPounds(), is(MINUS_POINT_FIVE));
	}
	
	@Test
	public void shouldGetDiscountTotalMultipleProductOrder() {
		ProductOrder productOrder1 = new ProductOrder("Beans", 7);
		
		cut.add(productOrder1);
		
		assertThat(cut.getDiscountTotalPounds(), is(MINUS_ONE));
	}
	
	@Test
	public void shouldGetDiscountTotalDifferentMultipleProductOrder() {
		ProductOrder productOrder1 = new ProductOrder("Beans", 7);
		ProductOrder productOrder2 = new ProductOrder("Coke", 5);
		
		cut.add(productOrder1);
		cut.add(productOrder2);
		
		assertThat(cut.getDiscountTotalPounds(), is(MINUS_POINT_EIGHT));
	}
	
	@Test
	public void shouldGetGrandTotalMultipleProductOrder() {
		ProductOrder productOrder1 = new ProductOrder("Beans", 3);
		ProductOrder productOrder2 = new ProductOrder("Coke", 2);
		ProductOrder productOrder3 = new ProductOrder("Oranges", 200);
		
		cut.add(productOrder1);
		cut.add(productOrder2);
		cut.add(productOrder3);
		
		assertThat(cut.getGrandTotalPounds(), is(TWO_POINT_FOUR));
	}
	

}
