package demo.demoJDBC.model;

public class Utilisateur {

    private Long id;
    private String nom;
    private String prenom;
    private Integer age;

    public Utilisateur() {
        super();
    }

    public Utilisateur(String nom, String prenom, Integer age) {
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
    }

    public Utilisateur(Long id, String nom, String prenom, Integer age) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        if(age >0) {
            this.age = age;
        } else {
            throw new RuntimeException("l'âge doit être positif");
        }
    }

    @Override
    public String toString() {
        return "Utilisateur{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", age=" + age +
                '}';
    }
}
