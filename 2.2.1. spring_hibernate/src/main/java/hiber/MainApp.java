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

      Car car1 = new Car("Model A", 100);
      User user1 = new User("User1", "Lastname1", "user1@mail.ru", car1);
      userService.add(user1);

      Car car2 = new Car("Model B", 200);
      User user2 = new User("User2", "Lastname2", "user2@mail.ru", car2);
      userService.add(user2);

      Car car3 = new Car("Model C", 300);
      User user3 = new User("User3", "Lastname3", "user3@mail.ru", car3);
      userService.add(user3);

      Car car4 = new Car("Model A", 100);
      User user4 = new User("User4", "Lastname4", "user4@mail.ru", car4);
      userService.add(user4);

      List<User> users = userService.listUsers();
      System.out.println("Список всех пользователей");
      for (User user : users) {
         System.out.println(user);
         System.out.println();
      }

      System.out.println("Поиск пользователя по автомобилю");
      String searchModel = "Model A";
      int searchSeries = 100;
      List<User> foundUsers = userService.findUserByCar(searchModel, searchSeries);
      if (!foundUsers.isEmpty()) {
         System.out.println("Найдены пользователи по автомобилю (" + searchModel + ", " + searchSeries + "):");
         for (User foundUser : foundUsers) {
            System.out.println(foundUser);
            System.out.println();
         }
      } else {
         System.out.println("Пользователи с автомобилем (" + searchModel + ", " + searchSeries + ") не найдены.");
      }

      context.close();
   }
}