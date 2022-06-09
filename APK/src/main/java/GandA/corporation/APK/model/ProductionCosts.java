package GandA.corporation.APK.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "productioncosts")
public class ProductionCosts {

    @Id
    @Column(name = "production_costs_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String product_type;

    private int salary;

    private int material_costs;

    private int depreciation_costs;

    private int other_costs;

    @OneToMany(mappedBy = "ProductionCostsToExpenses")
    private List<Expenses> ProductionCostsExpenses;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company companyToProductionCosts;

    @OneToOne
    @JoinColumn(name = "planned_production_id")
    private PlannedProduction PlannedProductionToProductionCosts;

    public List<Expenses> getProductionCostsExpenses() {
        return ProductionCostsExpenses;
    }

    public void setProductionCostsExpenses(List<Expenses> productionCostsExpenses) {
        ProductionCostsExpenses = productionCostsExpenses;
    }

    public PlannedProduction getPlannedProductionToProductionCosts() {
        return PlannedProductionToProductionCosts;
    }

    public void setPlannedProductionToProductionCosts(PlannedProduction plannedProductionToProductionCosts) {
        PlannedProductionToProductionCosts = plannedProductionToProductionCosts;
    }

    public Company getCompanyToProductionCosts() {
        return companyToProductionCosts;
    }

    public void setCompanyToProductionCosts(Company companyToProductionCosts) {
        this.companyToProductionCosts = companyToProductionCosts;
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

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getMaterial_costs() {
        return material_costs;
    }

    public void setMaterial_costs(int material_costs) {
        this.material_costs = material_costs;
    }

    public int getDepreciation_costs() {
        return depreciation_costs;
    }

    public void setDepreciation_costs(int depreciation_costs) {
        this.depreciation_costs = depreciation_costs;
    }

    public int getOther_costs() {
        return other_costs;
    }

    public void setOther_costs(int other_costs) {
        this.other_costs = other_costs;
    }
}
