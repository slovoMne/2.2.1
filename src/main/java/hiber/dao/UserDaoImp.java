package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;

import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   private final SessionFactory sessionFactory;

   public UserDaoImp(SessionFactory sessionFactory) {
      this.sessionFactory = sessionFactory;
   }

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public Object getUserByModelAndSeries(String model, int series){
      Query typedQuery =
              sessionFactory.getCurrentSession().createQuery("SELECT u FROM User u WHERE u.car.model=:model and u.car.series=:series");
      typedQuery.setParameter("model", model);
      typedQuery.setParameter("series", series);
      return typedQuery.getSingleResult();
   }
}
