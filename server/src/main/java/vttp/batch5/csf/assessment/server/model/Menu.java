package vttp.batch5.csf.assessment.server.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="menus")
public class Menu {
    String _id;
    String name;
    Float price;
    String description;
        
    public Menu(String _id, String name, Float price, String description) {
        this._id = _id;
        this.name = name;
        this.price = price;
        this.description = description;
    }
    public Menu() {
    }
    public String get_id() {
        return _id;
    }
    public void set_id(String _id) {
        this._id = _id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Float getPrice() {
        return price;
    }
    public void setPrice(Float price) {
        this.price = price;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    
}
