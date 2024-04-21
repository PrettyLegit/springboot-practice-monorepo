package com.jimmy.rest.webservices.restfulwebservices.user;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

// DAO is Data Access Object
@Component
public class UserDaoService {

     private static List<User> users = new ArrayList<>();

     static {
         users.add(new User(1,"Adam", LocalDate.now().minusYears(30)));
         users.add(new User(2,"Eve", LocalDate.now().minusYears(25)));
         users.add(new User(3,"Jim", LocalDate.now().minusYears(20)));
     }
     public List<User> findAll(){
         return users;
     }

     public User findOne(Integer id){
         for (User user : users) {
             if (Objects.equals(user.getId(), id)) {
                 return user;
             }
         }
         return null;
     }


}
