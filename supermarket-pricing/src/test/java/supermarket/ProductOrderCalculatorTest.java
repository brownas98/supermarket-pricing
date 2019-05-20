package supermarket;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

public class ProductOrderCalculatorTest {
	
	static ProductOrderCalculator cut;
	
	private static final BigDecimal FIFTY_PENCE = BigDecimal.valueOf(0.50).setScale(2);
	private static final BigDecimal ONE_POUND = BigDecimal.valueOf(1).setScale(2);
	private static final BigDecimal FOURTY_PENCE = BigDecimal.valueOf(0.40).setScale(2);
	private static final BigDecimal ZERO = BigDecimal.valueOf(0.00).setScale(2);
	
	@BeforeClass
	public static void setup()
	{
		Map<String, Product> productDictionary = new HashMap<>();
		
		productDictionary.put("Beans", new Product("Beans", BigDecimal.valueOf(50)));
		productDictionary.put("Coke", new Product("Coke", BigDecimal.valueOf(70)));
		productDictionary.put("Oranges", new Product("Oranges", BigDecimal.valueOf(0.199)));
		
		Map<String, ProductDiscount> productDiscountDictionary = new HashMap<>();
		
		productDiscountDictionary.put("Beans", new ProductDiscount("Beans 3 for 2", BigDecimal.valueOf(-50), 3));
		productDiscountDictionary.put("Coke", new ProductDiscount("Coke 2 for £1", BigDecimal.valueOf(-40), 2));
		
		cut = new ProductOrderCalculator(productDictionary, productDiscountDictionary);
	}
	
	@Test
	public void shouldCalculatePricePoundsSingleProductOrder() {
		
		ProductOrder productOrder = new ProductOrder("Beans", 1);
		
		assertThat(cut.calculatePricePounds(productOrder), is(FIFTY_PENCE));
		
	}
	
	@Test
	public void shouldCalculatePricePoundsMultipleProductOrder() {
		
		ProductOrder productOrder = new ProductOrder("Beans", 2);
		
		assertThat(cut.calculatePricePounds(productOrder), is(ONE_POUND));
		
	}
	
	@Test
	public void shouldCalculatePricePoundsWeightProductOrder() {
		
		ProductOrder productOrder = new ProductOrder("Oranges", 200);
		
		assertThat(cut.calculatePricePounds(productOrder), is(FOURTY_PENCE));
		
	}
	
	@Test
	public void shouldCalculatePricePoundsOfZeroWhenProductNotFound() {
		
		ProductOrder productOrder = new ProductOrder("unkown product", 1);
		
		assertThat(cut.calculatePricePounds(productOrder), is(ZERO));
		
	}
	
	@Test
	public void shouldCalculatePricePoundsOfZeroWhenProductNameEmpty() {
		
		ProductOrder productOrder = new ProductOrder("", 1);
		
		assertThat(cut.calculatePricePounds(productOrder), is(ZERO));
		
	}
	
	@Test
	public void shouldGetProductDiscountsPoundsWithSingleDiscountProductOrder() {
		
		ProductOrder productOrder = new ProductOrder("Beans", 3);
		
		assertThat(cut.getProductDiscountsPence(productOrder).size(), is(1));
		
	}
	
	@Test
	public void shouldGetProductDiscountsPoundsWithMultipleDiscountProductOrder() {
		
		ProductOrder productOrder = new ProductOrder("Beans", 9);
		
		assertThat(cut.getProductDiscountsPence(productOrder).size(), is(3));
		
	}
	
	@Test
	public void shouldNotGetProductDiscountsPoundsWithInsufficientQuantityProductOrder() {
		
		ProductOrder productOrder = new ProductOrder("Beans", 2);
		
		assertThat(cut.getProductDiscountsPence(productOrder).size(), is(0));
		
	}
	
	@Test
	public void shouldGetProductDiscountsPoundsWithLowRemainderQuantityProductOrder() {
		
		ProductOrder productOrder = new ProductOrder("Beans", 7);
		
		assertThat(cut.getProductDiscountsPence(productOrder).size(), is(2));
		
	}
	
	@Test
	public void shouldGetProductDiscountsPoundsWithHighRemainderQuantityProductOrder() {
		
		ProductOrder productOrder = new ProductOrder("Beans", 8);
		
		assertThat(cut.getProductDiscountsPence(productOrder).size(), is(2));
		
	}

}
