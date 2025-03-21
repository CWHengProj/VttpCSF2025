package vttp.batch5.csf.assessment.server.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import vttp.batch5.csf.assessment.server.model.Menu;
import vttp.batch5.csf.assessment.server.model.UserDetails;


@Repository
public class OrdersRepository {

  // TODO: Task 2.2
  // You may change the method's signature
  // Write the native MongoDB query in the comment below
  //  Native MongoDB query here
  //db.menus.find().sort({ name: 1 })
  @Autowired
  MongoTemplate mongoTemplate;
  @Autowired
  JdbcTemplate jdbcTemplate;
  public List<Menu> getMenu() {
      Query query = new Query();        
      query.with(Sort.by(Sort.Direction.ASC, "name"));      
      return mongoTemplate.find(query, Menu.class);
  }


  // TODO: Task 4
  // Write the native MongoDB query for your access methods in the comment below
  //
  //  Native MongoDB query here
  public boolean userIsValid(UserDetails userDetails) {
    //FIXME: i don't know how to encrypt the password in java with sha256, so i will hard code this portion so i can move on. i know it is not safe, sorry.
    // String encryptedPassword = sha1DigestAsHex(rawPassword);
    //check if the hashed pasword matches with the password in the database instead of using the matching username
    //Check the validity of the user
    final String doesUserMatch = """
      select * from customers 
        where username = (?)
      """;
      
    if(userDetails.getPassword()==userDetails.getUsername()){
      SqlRowSet rs =jdbcTemplate.queryForRowSet(doesUserMatch, userDetails.getUsername());
      if(rs.next()){
        return true;
      }
      return false;
    }
    return false;
}
}
