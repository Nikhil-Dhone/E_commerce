package demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import pojo.user;

@Controller
public class demo1 {
	
	@RequestMapping("/")
	public String test() {
		
		return "test";
	}
	
	
	@RequestMapping(path = "/pro",method = RequestMethod.POST)
	public String handel(@RequestParam("fname") String fname , @RequestParam("lname") String lname, Model model) {
		
		
		model.addAttribute("name", fname);
		model.addAttribute("last", lname);
		
		user u = new user();
		u.setFname(fname);
		u.setLname(lname);
		
		//hibernate
		Configuration cfg = new Configuration();
		cfg.configure().addAnnotatedClass(user.class);
		
		SessionFactory factory = cfg.buildSessionFactory();
		
		Session session  = factory.openSession();
		
		Transaction t = session.beginTransaction();
		session.persist(u);
		t.commit();
		
		
		return "demo";
	}
	
	
	
//	@RequestMapping(path = "/pro",method = RequestMethod.POST)
//	public String handel(@ModelAttribute("user") user u) {
//			
//		
//		
//		return "demo";
//	}

	
	
	

}
