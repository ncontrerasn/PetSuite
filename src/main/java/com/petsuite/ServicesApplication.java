package com.petsuite;

import com.petsuite.Services.gof.Contractor;
import com.petsuite.Services.gof.Employee;
import com.petsuite.Services.gof.IEmployed;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicesApplication.class, args);
                
             /*   Employee bob = new Employee();
                bob.setName("Bob");
 
                Employee sue = new Employee();
                sue.setName("Sue");
                bob.AddSubordinate(sue);

                Employee john = new Employee();
                john.setName("John");
                bob.AddSubordinate(john);

                Employee rita = new Employee();
                rita.setName("Rita");
                sue.AddSubordinate(rita);

                Employee jim = new Employee();
                jim.setName("Jim");
                sue.AddSubordinate(jim);

                Employee lou = new Employee();
                lou.setName("Lou");
                john.AddSubordinate(lou);

                Employee phil = new Employee();
                phil.setName("Phil");
                john.AddSubordinate(phil);

                Contractor sam = new Contractor();
                sam.setName("Sam");
                john.AddSubordinate(sam);

                Contractor tim = new Contractor();
                tim.setName("Tim");
                john.AddSubordinate(tim);
                
                
                
            System.out.println(bob.getName());
            for(IEmployed manager: bob.getList()){
                System.out.println(" "+manager.getName());
                
                for(IEmployed workers: manager.getList()){
                    System.out.println("  "+ workers.getName());
                    
                }
                
                
            }
            
              */  
        }

}