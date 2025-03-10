package th.co.cdg.SimpleApplication.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
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
           user.setImage((byte[]) result[4]);
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
                " VALUES(:id, :name, :surname, :age, :image) " ;

        // Execute SQL
        Query query = entityManager.createNativeQuery(sql);

        // Set parameter on SQL
        query.setParameter("id", user.getId());
        query.setParameter("name", user.getName());
        query.setParameter("surname", user.getSurname());
        query.setParameter("age", user.getAge());
        query.setParameter("image", user.getImage());

        // Return effected row in table
        return query.executeUpdate();
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public int updateUserById(User user){

        String sql = " UPDATE USER SET ";

        if (null != user.getName()) {
            sql += " NAME = :name ";
            if (null != user.getSurname() || null != user.getAge() || null != user.getImage()) {
                sql += " , ";
            }
        }

        if (null != user.getSurname()) {
            sql += " SURNAME = :surname ";
            if (null != user.getAge() || null != user.getImage()) {
                sql += " , ";
            }
        }

        if (null != user.getAge()) {
            sql += " AGE = :age ";
            if (null != user.getImage()) {
                sql += " , ";
            }
        }

        if (null != user.getImage()) {
            sql += " IMAGE = :image ";
        }

        sql += " WHERE ID = :id ";

        Query query = entityManager.createNativeQuery(sql);

        query.setParameter("id", user.getId());

        if (null != user.getName()) {
            query.setParameter("name", user.getName());
        }

        if (null != user.getSurname()) {
            query.setParameter("surname", user.getSurname());
        }

        if (null != user.getAge()) {
            query.setParameter("age", user.getAge());
        }

        if (null != user.getImage()) {
            query.setParameter("image", user.getImage());
        }

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

    @Transactional(Transactional.TxType.SUPPORTS)
    public byte[] getImageById(Long id){

        String sql = " SELECT IMAGE FROM USER " +
                " WHERE ID = :id ";

        Query query = entityManager.createNativeQuery(sql);

        query.setParameter("id", id);

        try {
            return (byte[]) query.getSingleResult();
        } catch (NoResultException noResultException) {
            return null;
        }
    }

}


