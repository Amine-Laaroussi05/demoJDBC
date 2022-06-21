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
        // Pour communiquer j'ai besoin de 2 infos:
        Connection connection = null;
        PreparedStatement ps = null;

        // Ouvrir ma connexion
        try {
            connection = ConfigMySQL.getInstance().getConnection();
            // Préparer ma requête
            ps = connection.prepareStatement("INSERT INTO UTILISATEUR (id, nom, prenom, age) VALUES (?,?,?,?)");
            ps.setLong(1, obj.getId());
            ps.setString(2,obj.getNom());
            ps.setString(3, obj.getPrenom());
            ps.setInt(4,obj.getAge());

            /*ps = connection.prepareStatement("INSERT INTO UTILISATEUR (nom, prenom, age) VALUES (?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(2,obj.getNom());
            ps.setString(3, obj.getPrenom());
            ps.setInt(4,obj.getAge());*/
            // Exécuter la requête avec mon interprète
            int rows = ps.executeUpdate();
            if(rows != 1){
                    throw new Exception("problème à la création de l'utilisateur:" + obj);

            } /* Dans le cas où la clé est généré par ma base de donnée
            else{
                ResultSet keys =  ps.getGeneratedKeys();
                if(keys.next()){
                    Long id = keys.getLong(1);
                    obj.setId(id);
                }
            }*/
            // parcours ma liste de résultat avec une boucle
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            // Fermer les instances de communications
            ConfigMySQL.getInstance().closeResultEtPS(null, ps);
            ConfigMySQL.getInstance().seDeconnecter();
        }
    }

    @Override
    public void update(Utilisateur obj) {
// Pour communiquer j'ai besoin de 2 infos:
        Connection connection = null;
        PreparedStatement ps = null;

        // Ouvrir ma connexion
        try {
            connection = ConfigMySQL.getInstance().getConnection();
            // Préparer ma requête
            ps = connection.prepareStatement("UPDATE Utilisateur SET nom = ?, prenom = ?, age = ? WHERE id = ?");
            ps.setLong(4, obj.getId());
            ps.setString(1,obj.getNom());
            ps.setString(2, obj.getPrenom());
            ps.setInt(3,obj.getAge());


            // Exécuter la requête avec mon interprète
            int rows = ps.executeUpdate();
            if(rows != 1){
                throw new Exception("problème à la modification de l'utilisateur:" + obj);

            }
            // parcours ma liste de résultat avec une boucle
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            // Fermer les instances de communications
            ConfigMySQL.getInstance().closeResultEtPS(null, ps);
            ConfigMySQL.getInstance().seDeconnecter();
        }
    }

    @Override
    public void delete(Long id) {
// Pour communiquer j'ai besoin de 3 infos:
        Connection connection = null;
        PreparedStatement ps = null;

        // Ouvrir ma connexion
        try {
            connection = ConfigMySQL.getInstance().getConnection();
            // Préparer ma requête
            ps = connection.prepareStatement("DELETE FROM UTILISATEUR WHERE id = ?");
            ps.setLong(1, id);

            /*ps = connection.prepareStatement("INSERT INTO UTILISATEUR (nom, prenom, age) VALUES (?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(2,obj.getNom());
            ps.setString(3, obj.getPrenom());
            ps.setInt(4,obj.getAge());*/
            // Exécuter la requête avec mon interprète
            int rows = ps.executeUpdate();
            if(rows != 1){
                throw new Exception("problème à la suppression de l'utilisateur à l'id:" + id);

            } /* Dans le cas où la clé est généré par ma base de donnée
            else{
                ResultSet keys =  ps.getGeneratedKeys();
                if(keys.next()){
                    Long id = keys.getLong(1);
                    obj.setId(id);
                }
            }*/
            // parcours ma liste de résultat avec une boucle
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            // Fermer les instances de communications
            ConfigMySQL.getInstance().closeResultEtPS(null, ps);
            ConfigMySQL.getInstance().seDeconnecter();
        }
    }
}
