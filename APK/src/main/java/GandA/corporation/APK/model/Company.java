package GandA.corporation.APK.model;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "company")
public class Company {

    @Id
    @Column(name = "company_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long company_code;

    private String company_name;

    private String INN;

    private boolean active;

    private String type_property;

    private String region;

    private String area;

    private String address;

    private String phone;

    private String email;

    private String industry;

    private String main_product;

    private int amount_workers;

    @OneToMany(mappedBy = "companyToUser")
    private List<User> companyUser;

    @OneToMany(mappedBy = "companyToLand")
    private List<Land> companyLand;

    @OneToMany(mappedBy = "companyToEmployee")
    private List<Employee> companyEmployee;

    @OneToMany(mappedBy = "companyToPlannedProduction")
    private List<PlannedProduction> companyPlannedProduction;

    @OneToMany(mappedBy = "companyToProductionCosts")
    private List<ProductionCosts> companyProductionCosts;

    @OneToMany(mappedBy = "companyToExpenses")
    private List<Expenses> companyExpenses;

    @OneToMany(mappedBy = "companyToProcurement")
    private List<Procurement> companyProcurement;

    @OneToMany(mappedBy = "companyToProduct")
    private List<Product> companyProduct;

    @OneToMany(mappedBy = "companyToSale")
    private List<Sale> companySale;

    public List<Sale> getCompanySale() {
        return companySale;
    }

    public void setCompanySale(List<Sale> companySale) {
        this.companySale = companySale;
    }

    public List<Product> getCompanyProduct() {
        return companyProduct;
    }

    public void setCompanyProduct(List<Product> companyProduct) {
        this.companyProduct = companyProduct;
    }

    public List<Procurement> getCompanyProcurement() {
        return companyProcurement;
    }

    public void setCompanyProcurement(List<Procurement> companyProcurement) {
        this.companyProcurement = companyProcurement;
    }

    public List<Expenses> getCompanyExpenses() {
        return companyExpenses;
    }

    public void setCompanyExpenses(List<Expenses> companyExpenses) {
        this.companyExpenses = companyExpenses;
    }

    public List<ProductionCosts> getCompanyProductionCosts() {
        return companyProductionCosts;
    }

    public void setCompanyProductionCosts(List<ProductionCosts> companyProductionCosts) {
        this.companyProductionCosts = companyProductionCosts;
    }

    public List<PlannedProduction> getCompanyPlannedProduction() {
        return companyPlannedProduction;
    }

    public void setCompanyPlannedProduction(List<PlannedProduction> companyPlannedProduction) {
        this.companyPlannedProduction = companyPlannedProduction;
    }

    public List<Employee> getCompanyEmployee() {
        return companyEmployee;
    }

    public void setCompanyEmployee(List<Employee> companyEmployee) {
        this.companyEmployee = companyEmployee;
    }

    public List<User> getCompanyUser() {
        return companyUser;
    }

    public void setCompanyUser(List<User> companyUser) {
        this.companyUser = companyUser;
    }

    public List<Land> getCompanyLand() {
        return companyLand;
    }

    public void setCompanyLand(List<Land> companyLand) {
        this.companyLand = companyLand;
    }

    public List<User> getUserCompany() {
        return companyUser;
    }

    public void setUserCompany(List<User> userCompany) {
        this.companyUser = userCompany;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCompany_code() {
        return company_code;
    }

    public void setCompany_code(Long company_code) {
        this.company_code = company_code;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getINN() {
        return INN;
    }

    public void setINN(String INN) {
        this.INN = INN;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getType_property() {
        return type_property;
    }

    public void setType_property(String type_property) {
        this.type_property = type_property;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getMain_product() {
        return main_product;
    }

    public void setMain_product(String main_product) {
        this.main_product = main_product;
    }

    public int getAmount_workers() {
        return amount_workers;
    }

    public void setAmount_workers(int amount_workers) {
        this.amount_workers = amount_workers;
    }
}
