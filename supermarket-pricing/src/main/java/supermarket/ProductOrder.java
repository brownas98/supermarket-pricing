package supermarket;

public class ProductOrder {
	private String productName;
	private int productQuantity;

	public ProductOrder(String productName, int productQuantity) {
		this.productName = productName;
		this.productQuantity = productQuantity;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}

}
