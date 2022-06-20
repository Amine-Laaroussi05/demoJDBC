package demo.demoJDBC.dao;

import demo.demoJDBC.model.Utilisateur;
import jdk.jshell.execution.Util;

import java.sql.SQLException;
import java.util.List;

public interface UserDao extends dao<Utilisateur, Long> {

    public List<Utilisateur> findByAge(int age) throws SQLException;


}
