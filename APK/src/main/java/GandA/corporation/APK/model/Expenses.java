package GandA.corporation.APK.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "expenses")
public class Expenses {

    @Id
    @Column(name = "expenses_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String expenses_date;

    private String name_purchase;

    private int price;

    private String product_type;

    @OneToMany(mappedBy = "ExpensesToProcurement")
    private List<Procurement> expensesProcurement;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company companyToExpenses;

    @ManyToOne
    @JoinColumn(name = "production_costs_id")
    private ProductionCosts ProductionCostsToExpenses;

    public List<Procurement> getExpensesProcurement() {
        return expensesProcurement;
    }

    public void setExpensesProcurement(List<Procurement> expensesProcurement) {
        this.expensesProcurement = expensesProcurement;
    }

    public ProductionCosts getProductionCostsToExpenses() {
        return ProductionCostsToExpenses;
    }

    public void setProductionCostsToExpenses(ProductionCosts productionCostsToExpenses) {
        ProductionCostsToExpenses = productionCostsToExpenses;
    }

    public Company getCompanyToExpenses() {
        return companyToExpenses;
    }

    public void setCompanyToExpenses(Company companyToExpenses) {
        this.companyToExpenses = companyToExpenses;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExpenses_date() {
        return expenses_date;
    }

    public void setExpenses_date(String expenses_date) {
        this.expenses_date = expenses_date;
    }

    public String getName_purchase() {
        return name_purchase;
    }

    public void setName_purchase(String name_purchase) {
        this.name_purchase = name_purchase;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getProduct_type() {
        return product_type;
    }

    public void setProduct_type(String product_type) {
        this.product_type = product_type;
    }
}
