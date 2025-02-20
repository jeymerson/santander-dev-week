package spring.jey.santander_dev_week.domain.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity(name = "tb_account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Gera uma constraint
    @Column(unique = true) // diz que o valor é unico, no caso o nº da conta
    private String number;
    private String agency;

    // nullable = não aceita valores nulos se for falso
    // ajusta a precisão da coluna, 11 inteiro e 2 decimais de precisão
    @Column( nullable = false ,scale = 13,precision = 2) // feita uma escala de 13 com precisã ode dois
    private BigDecimal blance;

    @Column(name="additional_limit" ,nullable = false ,scale = 13,precision = 2)
    private BigDecimal limit;

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public BigDecimal getBlance() {
        return blance;
    }

    public void setBlance(BigDecimal blance) {
        this.blance = blance;
    }

    public BigDecimal getLimit() {
        return limit;
    }

    public void setLimit(BigDecimal limit) {
        this.limit = limit;
    }
}
