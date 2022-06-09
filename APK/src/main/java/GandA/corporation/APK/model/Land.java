package GandA.corporation.APK.model;

import javax.persistence.*;

@Entity
@Table(name = "land")
public class Land {

    @Id
    @Column(name = "land_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String location;

    private String type_permitted_use;

    private String actual;

    private String conditions;

    private String crop_rotation_type;

    private String square;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company companyToLand;

    public Company getCompanyToLand() {
        return companyToLand;
    }

    public void setCompanyToLand(Company companyToLand) {
        this.companyToLand = companyToLand;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getType_permitted_use() {
        return type_permitted_use;
    }

    public void setType_permitted_use(String type_permitted_use) {
        this.type_permitted_use = type_permitted_use;
    }

    public String getActual() {
        return actual;
    }

    public void setActual(String actual) {
        this.actual = actual;
    }

    public String getConditions() {
        return conditions;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }

    public String getCrop_rotation_type() {
        return crop_rotation_type;
    }

    public void setCrop_rotation_type(String crop_rotation_type) {
        this.crop_rotation_type = crop_rotation_type;
    }

    public String getSquare() {
        return square;
    }

    public void setSquare(String square) {
        this.square = square;
    }
}
