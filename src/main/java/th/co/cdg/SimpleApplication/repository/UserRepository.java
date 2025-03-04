package th.co.cdg.SimpleApplication.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import th.co.cdg.SimpleApplication.model.User;

@Repository
public class UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(value =  Transactional.TxType.SUPPORTS)
    public User save(User user) {
        return null;
    }
}


