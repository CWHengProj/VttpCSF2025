package vttp.batch5.csf.assessment.server.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vttp.batch5.csf.assessment.server.model.Menu;
import vttp.batch5.csf.assessment.server.repositories.RestaurantRepository;

@Service
public class RestaurantService {
@Autowired
RestaurantRepository restaurantRepository;

  // TODO: Task 2.2
  // You may change the method's signature
  public  List<Menu> getMenu() {
    return restaurantRepository.getMenu();
    }

  }
  
  // TODO: Task 4