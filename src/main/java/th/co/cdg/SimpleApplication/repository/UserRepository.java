package th.co.cdg.SimpleApplication.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import th.co.cdg.SimpleApplication.model.User;

import java.util.ArrayList;

@Repository
public class UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(Transactional.TxType.SUPPORTS) //
    public ArrayList<User> queryAllUser(){

        // String SQL
        String sql = " SELECT * " +
                " FROM USER " ;

//        StringBuilder sql2 = new StringBuilder();
//        sql2.append(" SELECT * ");
//        sql2.append(" FROM USER ");
//        entityManager.createNativeQuery(sql2.toString());

        // Query
        Query query = entityManager.createNativeQuery(sql);

        // Get Result List
        ArrayList<Object[]> resultList = (ArrayList<Object[]>) query.getResultList();

        // New Return Value
        ArrayList<User> users = new ArrayList<>();

        // Put Result from DB into Object Ex.{1, Boo1, BooBoo, 21}
        resultList.forEach(result -> {
           User user = new User();
           user.setId(((Integer) result[0]).longValue());
           user.setName((String) result[1]);
           user.setSurname((String) result[2]);
           user.setAge(((Integer) result[3]).longValue());
            users.add(user);
        });

        // Return Result
        return users;
    }

    // Transactional : REQUIRED >> สำหรับการทำ INSERT, UPDATE, DELETE หรือ การแก้ไขข้อมูลของ Database
    @Transactional(Transactional.TxType.REQUIRED)
    public int insertNewUser(User user){

        // String SQL
        String sql = " INSERT INTO USER " +
                " VALUES(:id, :name, :surname, :age) " ;

        // Execute SQL
        Query query = entityManager.createNativeQuery(sql);

        // Set parameter on SQL
        query.setParameter("id", user.getId());
        query.setParameter("name", user.getName());
        query.setParameter("surname", user.getSurname());
        query.setParameter("age", user.getAge());

        // Return effected row in table
        return query.executeUpdate();
    }

    @Transactional(Transactional.TxType.REQUIRED)
        public int deleteUserById(Long id){

        String sql = " DELETE FROM USER " +
                " WHERE ID = :id ";

        Query query = entityManager.createNativeQuery(sql);

        query.setParameter("id", id);

        return query.executeUpdate();
    }
}


