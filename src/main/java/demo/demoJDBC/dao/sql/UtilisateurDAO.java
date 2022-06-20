package demo.demoJDBC.dao.sql;

import demo.demoJDBC.config.ConfigMySQL;
import demo.demoJDBC.dao.UserDao;
import demo.demoJDBC.model.Utilisateur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UtilisateurDAO implements UserDao {
    @Override
    public List<Utilisateur> findByAge(int age) {
        // Créer ma Liste à récupérer
        List<Utilisateur> users = new ArrayList<Utilisateur>();
        // Pour communiquer j'ai besoin de 3 infos:
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet result = null;

        // Ouvrir ma connexion
        try {
            connection = ConfigMySQL.getInstance().getConnection();
            // Préparer ma requête
            ps = connection.prepareStatement("SELECT id, nom, prenom, age FROM Utilisateur WHERE age = ?");
            ps.setInt(1,age);
            // Exécuter la requête avec mon interprète
            result = ps.executeQuery();
            // parcours ma liste de résultat avec une boucle
            while(result.next()){
                // Pour chaque ligne obj sql: je crée un utilisateur obj java
                Long id = result.getLong("id");
                String nom = result.getString("nom");
                String prenom = result.getString("prenom");
                Utilisateur findUser = new Utilisateur(id, nom, prenom, age);
                users.add(findUser);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            // Fermer les instances de communications
            ConfigMySQL.getInstance().closeResultEtPS(result, ps);
            ConfigMySQL.getInstance().seDeconnecter();;
        }

        return users;
    }

    @Override
    public List<Utilisateur> findAll() {
        // Créer ma Liste à récupérer
        List<Utilisateur> users = new ArrayList<Utilisateur>();
        // Pour communiquer j'ai besoin de 3 infos:
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet result = null;

        // Ouvrir ma connexion
        try {
            connection = ConfigMySQL.getInstance().getConnection();
            // Préparer ma requête
            ps = connection.prepareStatement("SELECT id, nom, prenom, age FROM Utilisateur");
            // Exécuter la requête avec mon interprète
            result = ps.executeQuery();
            // parcours ma liste de résultat avec une boucle
            while(result.next()){
                // Pour chaque ligne obj sql: je crée un utilisateur obj java
                Long id = result.getLong("id");
                String nom = result.getString("nom");
                String prenom = result.getString("prenom");
                Integer age = result.getInt("age");
                Utilisateur findUser = new Utilisateur(id, nom, prenom, age);
                users.add(findUser);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            // Fermer les instances de communications
            ConfigMySQL.getInstance().closeResultEtPS(result, ps);
            ConfigMySQL.getInstance().seDeconnecter();;
        }

        return users;
    }

    @Override
    public Utilisateur findById(Long id) {
        // Créer un utilisateur à récupérer
        Utilisateur user = new Utilisateur();
        // Pour communiquer j'ai besoin de 3 infos:
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet result = null;

        // Ouvrir ma connexion
        try {
            connection = ConfigMySQL.getInstance().getConnection();
            // Préparer ma requête
            ps = connection.prepareStatement("SELECT id, nom, prenom, age FROM Utilisateur WHERE id = ?");
            ps.setLong(1,id);
            // Exécuter la requête avec mon interprète
            result = ps.executeQuery();
            // parcours ma liste de résultat avec une boucle
            if(result.next()){
                // Pour chaque ligne obj sql: je crée un utilisateur obj java
                String nom = result.getString("nom");
                String prenom = result.getString("prenom");
                Integer age = result.getInt("age");
                user = new Utilisateur(id, nom, prenom, age);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            // Fermer les instances de communications
            ConfigMySQL.getInstance().closeResultEtPS(result, ps);
            ConfigMySQL.getInstance().seDeconnecter();;
        }

        return user;
    }

    @Override
    public void create(Utilisateur obj) {

    }

    @Override
    public void update(Utilisateur obj) {

    }

    @Override
    public void delete(Long id) {

    }
}
