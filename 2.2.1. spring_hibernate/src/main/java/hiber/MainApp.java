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

      userService.add(new User("User1", "Lastname1", "user1@mail.ru", new Car("Kia", 1)));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru", new Car("Audi",2)));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru", new Car("Lada", 3)));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru", new Car("BMW", 4)));
      userService.add(new User("User5", "Lastname5", "user5@mail.ru", new Car("BMW", 4)));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = "+ user.getCar().getModel() + ", Series = "+user.getCar().getSeries());
         System.out.println();
      }

      User userOfCar = userService.getOfCar("Lada", 3);
      System.out.println("User of Car");
      System.out.println("Id = "+userOfCar.getId());
      System.out.println("First Name = "+userOfCar.getFirstName());
      System.out.println("Last Name = "+userOfCar.getLastName());
      System.out.println("Email = "+userOfCar.getEmail());
      System.out.println("Car = "+ userOfCar.getCar().getModel() + ", Series = "+userOfCar.getCar().getSeries());
      System.out.println();

      context.close();
   }
}
