package vttp.batch5.csf.assessment.server.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vttp.batch5.csf.assessment.server.model.Menu;
import vttp.batch5.csf.assessment.server.model.UserDetails;
import vttp.batch5.csf.assessment.server.repositories.OrdersRepository;

@Service
public class RestaurantService {
@Autowired
OrdersRepository ordersRepository;

  // TODO: Task 2.2
  // You may change the method's signature
  public  List<Menu> getMenu() {
    return ordersRepository.getMenu();
    }

  
  // TODO: Task 4
  public boolean userIsValid(UserDetails userDetails){
    return ordersRepository.userIsValid(userDetails);
  }
}
