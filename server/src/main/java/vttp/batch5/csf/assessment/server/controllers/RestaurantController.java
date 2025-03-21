package vttp.batch5.csf.assessment.server.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

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
@Autowired
RestTemplate restTemplate;
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
    // String url = "https://payment-service-production-a75a.up.railway.up";
    String url="heehee";
    //
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

    MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
    map.add("email", "first.last@example.com");

    HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

    ResponseEntity<String> response = restTemplate.postForEntity( url, request , String.class );
    //order_id, payer, payee, payment - just the value of all items
    // header called X-authenticate - same name as payer
    //insert the values into the serve database (mysql and mongodb)
    //return payment to the client based off the results
    
    return ResponseEntity.ok("{}");
  }
}
