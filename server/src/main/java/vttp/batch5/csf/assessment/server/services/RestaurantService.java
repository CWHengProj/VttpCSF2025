package vttp.batch5.csf.assessment.server.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import vttp.batch5.csf.assessment.server.model.Menu;
import vttp.batch5.csf.assessment.server.model.UserDetails;
import vttp.batch5.csf.assessment.server.repositories.OrdersRepository;

@Service
public class RestaurantService {
@Autowired
OrdersRepository ordersRepository;
// @Autowired
// RestTemplate restTemplate;

  // TODO: Task 2.2
  // You may change the method's signature
  public  List<Menu> getMenu() {
    return ordersRepository.getMenu();
    }

  
  // TODO: Task 4
  public boolean userIsValid(UserDetails userDetails){
    return ordersRepository.userIsValid(userDetails);
  }


  public String getPaymentResponse(UserDetails userDetails, Float payment, String url) {
        JsonObject messageToServer = Json.createObjectBuilder()
                                      .add("order_id",userDetails.getUuid())
                                      .add("payer",userDetails.getUsername())
                                      .add("payee","Heng Choon Wee")
                                      .add("payment",payment)
                                      .build();
      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_JSON);
      headers.set("Accept", "application/json");
      headers.set("X-Authenticate", userDetails.getUsername());      
      HttpEntity<String> requestEntity = new HttpEntity<>(messageToServer.toString(), headers);
      RestTemplate restTemplate = new RestTemplate();
      ResponseEntity<String> response =restTemplate.postForEntity(url, requestEntity, String.class);
      // String response="having some issues with restTemplate that i couldnt fix last minute, remove it to see if app works";
      return response.getBody();
      // return response;

  }
  public void insertintoDB(Float payment, UserDetails userDetails, String payment_id, String order_id, Long timestamp, List<Menu> cart){
    ordersRepository.insertIntoMySQL(payment,userDetails,payment_id,order_id,timestamp,cart);
    ordersRepository.insertIntoMongoDb(payment,userDetails,payment_id,order_id,timestamp,cart);

  }
  
}
