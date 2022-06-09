package GandA.corporation.APK.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "plannedproduction")
public class PlannedProduction {

    @Id
    @Column(name = "planned_production_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String product_type;

    private String product_name;

    private int planned_property;

    private int planned_revenue;

    private int planned_profit;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company companyToPlannedProduction;

    @OneToOne(mappedBy = "PlannedProductionToProductionCosts")
    private ProductionCosts PlannedProductionProductionCosts;

    @OneToOne(mappedBy = "PlannedProductionToProduct")
    private Product PlannedProductionProduct;

    public Product getPlannedProductionProduct() {
        return PlannedProductionProduct;
    }

    public ProductionCosts getPlannedProductionProductionCosts() {
        return PlannedProductionProductionCosts;
    }

    public void setPlannedProductionProductionCosts(ProductionCosts plannedProductionProductionCosts) {
        PlannedProductionProductionCosts = plannedProductionProductionCosts;
    }

    public void setPlannedProductionProduct(Product plannedProductionProduct) {
        PlannedProductionProduct = plannedProductionProduct;
    }

    public Company getCompanyToPlannedProduction() {
        return companyToPlannedProduction;
    }

    public void setCompanyToPlannedProduction(Company companyToPlannedProduction) {
        this.companyToPlannedProduction = companyToPlannedProduction;
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

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public int getPlanned_property() {
        return planned_property;
    }

    public void setPlanned_property(int planned_property) {
        this.planned_property = planned_property;
    }

    public int getPlanned_revenue() {
        return planned_revenue;
    }

    public void setPlanned_revenue(int planned_revenue) {
        this.planned_revenue = planned_revenue;
    }

    public int getPlanned_profit() {
        return planned_profit;
    }

    public void setPlanned_profit(int planned_profit) {
        this.planned_profit = planned_profit;
    }
}
