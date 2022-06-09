package GandA.corporation.APK.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String product_type;

    private String date_manufacture;

    private String unit;

    private int amount;

    private int production_cost;

    private int cost_price;

    @OneToMany(mappedBy = "productToSale")
    private List<Sale> productSale;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company companyToProduct;

    @OneToOne
    @JoinColumn(name = "planned_production_id")
    private PlannedProduction PlannedProductionToProduct;

    public List<Sale> getProductSale() {
        return productSale;
    }

    public void setProductSale(List<Sale> productSale) {
        this.productSale = productSale;
    }

    public PlannedProduction getPlannedProductionToProduct() {
        return PlannedProductionToProduct;
    }

    public void setPlannedProductionToProduct(PlannedProduction plannedProductionToProduct) {
        PlannedProductionToProduct = plannedProductionToProduct;
    }

    public Company getCompanyToProduct() {
        return companyToProduct;
    }

    public void setCompanyToProduct(Company companyToProduct) {
        this.companyToProduct = companyToProduct;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProduct_type() {
        return product_type;
    }

    public void setProduct_type(String product_type) {
        this.product_type = product_type;
    }

    public String getDate_manufacture() {
        return date_manufacture;
    }

    public void setDate_manufacture(String date_manufacture) {
        this.date_manufacture = date_manufacture;
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

    public int getProduction_cost() {
        return production_cost;
    }

    public void setProduction_cost(int production_cost) {
        this.production_cost = production_cost;
    }

    public int getCost_price() {
        return cost_price;
    }

    public void setCost_price(int cost_price) {
        this.cost_price = cost_price;
    }
}
