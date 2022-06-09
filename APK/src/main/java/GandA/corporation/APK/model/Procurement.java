package GandA.corporation.APK.model;

import javax.persistence.*;


@Entity
@Table(name = "procurement")
public class Procurement {

    @Id
    @Column(name = "procurement_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String purchase_date;

    private String name_purchase;

    private int price;

    private String product_type;

    private String unit;

    private int amount;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company companyToProcurement;

    @ManyToOne
    @JoinColumn(name = "expenses_id")
    private Expenses ExpensesToProcurement;

    public Company getCompanyToProcurement() {
        return companyToProcurement;
    }

    public void setCompanyToProcurement(Company companyToProcurement) {
        this.companyToProcurement = companyToProcurement;
    }

    public Expenses getExpensesToProcurement() {
        return ExpensesToProcurement;
    }

    public void setExpensesToProcurement(Expenses expensesToProcurement) {
        ExpensesToProcurement = expensesToProcurement;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPurchase_date() {
        return purchase_date;
    }

    public void setPurchase_date(String purchase_date) {
        this.purchase_date = purchase_date;
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
}
