package vn.edu.usth.ordernow.Model;

import java.util.List;

public class Requests {

    private String Name;
    private String Phone;
    private String total;
    private List<Order> foods;

    public Requests() {
    }

    public Requests(String name, String phone, String total, List<Order> foods) {
        Name = name;
        Phone = phone;
        this.total = total;
        this.foods = foods;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<Order> getFoods() {
        return foods;
    }

    public void setFoods(List<Order> foods) {
        this.foods = foods;
    }
}
