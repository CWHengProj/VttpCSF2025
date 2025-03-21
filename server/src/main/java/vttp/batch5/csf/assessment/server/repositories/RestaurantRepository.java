package vttp.batch5.csf.assessment.server.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import vttp.batch5.csf.assessment.server.model.Menu;

@Repository
// Use the following class for MySQL database
public class RestaurantRepository {
    @Autowired
    MongoTemplate mongoTemplate;
    public List<Menu> getMenu() {
        Query query = new Query();        
        query.with(Sort.by(Sort.Direction.ASC, "name"));      
        return mongoTemplate.find(query, Menu.class);
    }

}
