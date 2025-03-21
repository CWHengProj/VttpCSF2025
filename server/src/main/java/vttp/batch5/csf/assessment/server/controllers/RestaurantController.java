package vttp.batch5.csf.assessment.server.controllers;

import java.io.StringReader;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import vttp.batch5.csf.assessment.server.model.Menu;
import vttp.batch5.csf.assessment.server.model.UserDetails;
import vttp.batch5.csf.assessment.server.services.Misc;
import vttp.batch5.csf.assessment.server.services.RestaurantService;

@RestController
@RequestMapping(path="/api",produces =MediaType.APPLICATION_JSON_VALUE)
public class RestaurantController {
@Autowired
RestaurantService restaurantService;
@Autowired
Misc misc;

  // TODO: Task 2.2
  // You may change the method's signature
  @GetMapping("/menu")
  public ResponseEntity<List<Menu>> getMenus() {
    List<Menu> response = restaurantService.getMenu();
    return ResponseEntity.ok(response);
  }

  // TODO: Task 4
  // Do not change the method's signature
  @PostMapping("/food_order")
  public ResponseEntity<String> postFoodOrder(@RequestBody String payload) {
    // System.out.println("payload:"+payload);
    UserDetails userDetails = misc.getUserDetails(payload);
    List<Menu> cart =misc.getUserOrder(payload);
    if (!restaurantService.userIsValid(userDetails)){
      return ResponseEntity.status(401).body("Invalid username and/or password.");
    }
    userDetails.setUuid(UUID.randomUUID().toString().replace("-", "").substring(0,8));
    //go to the endpoint and request using restTemplate 
    //get the following values to send to the user database
    Float payment = misc.getTotalBill(payload);
    String url = "https://payment-service-production-a75a.up.railway.up";
    String responseFromServer =restaurantService.getPaymentResponse(userDetails,payment,url);
    JsonObject job = Json.createReader(new StringReader(responseFromServer)).readObject();
    //turn the payload into an object, catch any errors. if it throws error return the error
    try{
      String payment_id = job.getString("payment_id");
      String order_id = job.getString("order_id");
      Long timestamp = Long.parseLong(job.getString("payment_id"));
      //insert the values into the serve database (mysql and mongodb)
      restaurantService.insertintoDB(payment,userDetails,payment_id,order_id,timestamp,cart);
      return ResponseEntity.status(200).body("success!");
    }
    catch (Exception e){
      return ResponseEntity.status(500).body(e.getMessage());
      
    }
    
    
  }
}
