package main.model;

public class Distributor {
	protected int id;
	protected String article_name;
	protected int article_quantity;
	protected int article_price;
	
	public Distributor(int id, String article_name, int article_quantity, int article_price) {
		super();
		this.id = id;
		this.article_name = article_name;
		this.article_quantity = article_quantity;
		this.article_price = article_price;
	}

	public Distributor(String article_name, int article_quantity, int article_price) {
		super();
		this.article_name = article_name;
		this.article_quantity = article_quantity;
		this.article_price = article_price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getArticle_name() {
		return article_name;
	}

	public void setArticle_name(String article_name) {
		this.article_name = article_name;
	}

	public int getArticle_quantity() {
		return article_quantity;
	}

	public void setArticle_quantity(int article_quantity) {
		this.article_quantity = article_quantity;
	}

	public int getArticle_price() {
		return article_price;
	}

	public void setArticle_price(int article_price) {
		this.article_price = article_price;
	}	
}
