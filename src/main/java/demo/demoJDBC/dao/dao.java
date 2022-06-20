package demo.demoJDBC.dao;

import java.util.List;

/**
 * Interface des DAO génériques
 */
public interface dao<T,PK> {
    /**
     * Permet de récupérer la liste de tous les éléments
     * @return liste d'éléments
     */
    public List<T> findAll();

    /**
     * Permet de récupérer un élément par son id
     * @param id de l'élément
     * @return l'élément
     */
    public T findById(PK id);

    /**
     * Permet de créer l'élément
     * @param obj à créer
     */
    // ou public T create(T obj) si on veut récupérer l'utilisateur
    public void create(T obj);

    /**
     * Permet de mettre à jour l'élément
     * @param obj à mettre à jour
     */
    public void update(T obj);

    /**
     * Permet de supprimer un élément par son id
     * @param id de l'élément à supprimer
     */
    public void delete(PK id);

}
