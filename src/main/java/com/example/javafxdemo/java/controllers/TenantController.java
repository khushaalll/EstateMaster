package com.example.javafxdemo.java.controllers;
import com.example.javafxdemo.java.Database;
import com.example.javafxdemo.java.models.Tenant;
import java.util.ArrayList;

public class TenantController {

    private static TenantController instance = new TenantController();

    private TenantController(){}

    public static TenantController getInstance(){
        if(instance == null)
            instance = new TenantController();
        return instance;
    }

    public String addTenant(String firstName,String lastName,String contactNumber,String email){
                Tenant tenant =null;
                tenant = new Tenant(firstName,lastName,contactNumber,email);
                if(Database.getInstance().getTenants().contains(tenant)){
                    return "Tenant Already Exists";
                    
                }
                else{
                    Database.getInstance().addTenant(tenant);
                    return "Tenant Added";
                }
    }
    public Tenant gTenant(int value){
        return Database.getInstance().getTenants().get(value);
    }
    
    public ArrayList<Tenant> displayTenants() {
        return (ArrayList<Tenant>) Database.getInstance().getTenants();
    }
}
