package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User user1 = new User("Oleg", "Kirov", "ol.kirov@gmail.com");
      user1.setCar(new Car("Honda", 96784));
      userService.add(user1);

      User user2 = new User("Irina", "Kim", "irinakim@gmail.com");
      user2.setCar(new Car("BMW", 12345));
      userService.add(user2);

      User user3 = new User("Ivan", "Ivanov", "iv.ivanov@gmail.com");
      user3.setCar(new Car("Nissan",23456));
      userService.add(user3);

      User user4 = new User("Roman", "Romanov", "rom.roma@gmail.com");
      user4.setCar(new Car("Honda", 34598));
      userService.add(user4);

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println(user);
      }

      userService.getUserBySeriesEndModelCar("Honda", 34598);


      context.close();
   }
}
