package co.grandcircus.coffeeshop;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import co.grandcircus.coffeeshop.Dao.ProductDao;
import co.grandcircus.coffeeshop.Dao.UserDao;
import co.grandcircus.coffeeshop.entity.Product;
import co.grandcircus.coffeeshop.entity.User;

import co.grandcircus.coffeeshop.Dao.UserRepository;

@Controller
public class CoffeeShopController {

	@Autowired
	private ProductDao productdao;
	@Autowired
	private UserDao userdao;

	@Autowired
	private UserRepository dao;

	@RequestMapping("/")
	public ModelAndView list() {
		ModelAndView mv = new ModelAndView("index");
		// key value pair
		mv.addObject("products", productdao.findAll());

		return mv;
	}

	@RequestMapping("/edit")
	public ModelAndView showEditForm(@RequestParam("id") Long id) {
		ModelAndView mav = new ModelAndView("edit");
		mav.addObject("products", productdao.findById(id));

		return mav;
	}
	
	@PostMapping("/admin/edit")
	public ModelAndView editPost(Product product) {
		
		productdao.update(product);

		return new ModelAndView("redirect:/admin");

	}
	
	

	@RequestMapping("/admin")
	public ModelAndView add() {
		ModelAndView mv = new ModelAndView("admin");
		mv.addObject("products", productdao.findAll());

		return mv;
	}

	@RequestMapping("/add")
	public ModelAndView addForm(Product product) {

		return new ModelAndView("add");
	}

	@PostMapping("/add")
	public ModelAndView addPost(Product product) {
		productdao.create(product);

		return new ModelAndView("add");

	}

	@RequestMapping("/delete")
	public ModelAndView remove(@RequestParam("id") Long id) {
		productdao.delete(id);
		return new ModelAndView("redirect:/admin");
	}

///////////////////////////////////////////////////////////////////////////User Data	
	@RequestMapping("/register")
	public ModelAndView addSubmit(User user) {

		return new ModelAndView("register");
	}

	@PostMapping("/thanks")
	public ModelAndView thanks(User user, HttpSession session) {
		userdao.create(user);

		return new ModelAndView("thanks");

	}

/////////////////////////////////////////////////////////////////////////////////Login /Logout

	@RequestMapping("/login-form")
	public ModelAndView loginForm(User user) {

		return new ModelAndView("login-form");
	}

	@PostMapping("/login-form")
	public ModelAndView loginConfirm(@RequestParam("username") String username,
			@RequestParam("password") String password, HttpSession session) {

		User user = dao.findByUsernameAndPassword(username, password);
		System.out.println(user);

		if (user == null) {
			return new ModelAndView("/login-form", "message", "Incorrect username or password.");
		}
		System.out.println("death");
		session.setAttribute("user", user);
		return new ModelAndView("redirect:/");
	}

	@RequestMapping("/logout")
	public ModelAndView logout(HttpSession session) {
		session.invalidate();

		return new ModelAndView("redirect:/");
	}
//		
}
