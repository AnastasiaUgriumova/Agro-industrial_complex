package GandA.corporation.APK.model;

import javax.persistence.*;

@Entity
@Table(name = "sale")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String ProductType;

    private String DateSale;

    private String unit;

    private int amount;

    private int price;

    private int revenue;

    private int profit;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company companyToSale;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Company productToSale;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Company getCompanyToSale() {
        return companyToSale;
    }

    public void setCompanyToSale(Company companyToSale) {
        this.companyToSale = companyToSale;
    }

    public Company getProductToSale() {
        return productToSale;
    }

    public void setProductToSale(Company productToSale) {
        this.productToSale = productToSale;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductType() {
        return ProductType;
    }

    public void setProductType(String productType) {
        ProductType = productType;
    }

    public String getDateSale() {
        return DateSale;
    }

    public void setDateSale(String dateSale) {
        DateSale = dateSale;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getRevenue() {
        return revenue;
    }

    public void setRevenue(int revenue) {
        this.revenue = revenue;
    }

    public int getProfit() {
        return profit;
    }

    public void setProfit(int profit) {
        this.profit = profit;
    }
}
