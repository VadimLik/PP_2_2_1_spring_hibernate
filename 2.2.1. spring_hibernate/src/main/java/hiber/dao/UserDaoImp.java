package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDaoImp implements UserDao {

//   private final String HGL =

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public User getUserBySeriesEndModelCar(String model, int series) {

      String hgl = "FROM User user LEFT OUTER JOIN FETCH user.car  WHERE user.car.model=:model and user.car.series=:series ";

      User user = null;

      try {

       user = (User) sessionFactory.getCurrentSession().createQuery(hgl, User.class)
                 .setParameter("model", model)
                 .setParameter("series", series)
               .uniqueResult();

         System.out.println("Атомобилем с заданными параметрами владеет User : \n" + user);

      } catch (HibernateException e) {

         System.out.println("Заданные параметры автомобиля не являются уникальными");

         user = (User) sessionFactory.getCurrentSession().createQuery(hgl, User.class)
                 .setParameter("model", model)
                 .setParameter("series", series)
                 .getResultList().get(0);

         System.out.println("Первым в списке владельцев атомобиля с заданными параметрами является User : \n" + user);

      }

      return user;

   }
}
