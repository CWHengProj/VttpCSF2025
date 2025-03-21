package vttp.batch5.csf.assessment.server.services;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import vttp.batch5.csf.assessment.server.model.Menu;
import vttp.batch5.csf.assessment.server.model.UserDetails;

@Service
public class Misc {

    public List<Menu> getUserOrder(String payload) {
        JsonObject job = Json.createReader(new StringReader(payload)).readObject();
        JsonArray jar = job.getJsonArray("items");
        List<Menu> cart = new ArrayList<>();
        for (int i = 0; i < jar.size(); i++) {
            JsonObject menuItemJson = jar.getJsonObject(i);
            Menu item = new Menu();
            
            item.set_id(menuItemJson.getString("_id")); 
            item.setDescription(menuItemJson.getString("description"));
            item.setName(menuItemJson.getString("name"));
            item.setPrice((float)menuItemJson.getJsonNumber("price").doubleValue());
            cart.add(item);
        }
        return cart;
    }

    public UserDetails getUserDetails(String payload) {
        JsonObject job = Json.createReader(new StringReader(payload)).readObject();
        String username = job.getString("username");
        String password = job.getString("password");
        UserDetails uDetails = new UserDetails();
        uDetails.setUsername(username);
        uDetails.setPassword(password);
        return uDetails;
        //perform the check here to see if the value is valid? if not i do it later
    }

    public Float getTotalBill(String payload) {
        JsonObject job = Json.createReader(new StringReader(payload)).readObject();
        JsonArray jar = job.getJsonArray("items");
        Float total =0f;
        for (int i = 0; i < jar.size(); i++) {
            JsonObject menuItemJson = jar.getJsonObject(i);
            Integer amount = Integer.parseInt(menuItemJson.getString("quantity"));
            Float price =(float)menuItemJson.getJsonNumber("price").doubleValue();
            total += (amount* price);
        }
        return total;
    }
    
}
