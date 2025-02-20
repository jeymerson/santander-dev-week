package spring.jey.santander_dev_week.domain.model;

import jakarta.persistence.*;

import java.util.List;

//Declara que é uma tabela e dá um nome a mesma
@Entity(name = "tb_user")
public class User {
    // se define um ID
    @Id
    // Com isso definimos que o ID será gerado altomaticamente
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    //Um usuário tem uma conta então o relacionamento
    //deverá ser de um para um
    //Definimos o Cascade para deixar a relação entre elas
    //mais forte por questões de integridade
    @OneToOne(cascade = CascadeType.ALL)
    private Account account;

    //mesma explicação do anterior
    //Isso vai fazer na prática,  a tabela
    //user ter duas chaves estrangeiras
    @OneToOne(cascade = CascadeType.ALL)
    private Card card;

    // relacionamento de um para muitos
    //fetch = FetchType.EAGER => Toda vez que for procurar algum usuário no banco
    // sempre irá trazer a lista de Feature do banco, porque sempre irá
    // precisar delas! Para esse contexto de API
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Feature> features;

    // igual ao anterior
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<News> news;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public List<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(List<Feature> features) {
        this.features = features;
    }

    public List<News> getNews() {
        return news;
    }

    public void setNews(List<News> news) {
        this.news = news;
    }
}
