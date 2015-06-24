package com.entity;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class SessionCacheTest {
	public static void main(String[] args) {
		
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		
		ServiceRegistry registry = 
				new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
		SessionFactory factory = cfg.buildSessionFactory(registry);
		
		Session session1 =factory.openSession();
		
		Transaction tx = session1.beginTransaction();
		
		
/*		Product p1 = new Product();
		p1.setId(1);
		p1.setName("Shaving Cream");
		p1.setPrice(100);
		
		Product p2= new Product();
		p2.setId(2);
		p2.setName("Air cooler");
		p2.setPrice(1000);
		
		session1.save(p1);
		session1.save(p2);
		session1.flush();
		tx.commit();*/
		
		//session1.close();
		
		Product pr = (Product) session1.get(Product.class, new Integer(1));
		System.out.println(pr.getName());
		
		Product pr1 = (Product) session1.get(Product.class, new Integer(2));
		System.out.println(pr1.getName());
		
		Session session2 =factory.openSession();
		
		Product pr2 = (Product) session2.get(Product.class, new Integer(1));
		System.out.println(pr2.getName());
		
		
		Product pr3= (Product) session2.get(Product.class, new Integer(2));
		System.out.println(pr3.getName());
		
		factory.close();
		
		
	}
}
