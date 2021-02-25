package deliveryapp.jwd;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import deliveryapp.jwd.enumeration.UserRole;
import deliveryapp.jwd.model.Deliverer;
import deliveryapp.jwd.model.Order;
import deliveryapp.jwd.model.User;
import deliveryapp.jwd.repository.BillRepository;
import deliveryapp.jwd.repository.DelivererRepository;
import deliveryapp.jwd.repository.OrderRepository;
import deliveryapp.jwd.repository.UserRepository;
import deliveryapp.jwd.service.UserService;

@Component
public class TestData {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BillRepository billRepository;
	
	@Autowired
	private DelivererRepository delivererRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostConstruct
	public void init() {
		
		Deliverer deliverer1 = new Deliverer();
		deliverer1.setFirstLastName("Mike Johnson");
		
		Deliverer deliverer2 = new Deliverer();
		deliverer2.setFirstLastName("Marco Siccoli");
		
		Deliverer deliverer3 = new Deliverer();
		deliverer3.setFirstLastName("Jason Maroon");
		
		Order order1 = new Order();
		order1.setOrderNumber(12345);
		order1.setOrderDate("01.01.2021.");
		order1.setDeliveryPlace("Street number 1");
		order1.setOrderPrice(5);
		order1.setOrderDescription("Family pizza capricciosa - Pizza Casa");
		order1.setDeliverer(deliverer1);
		
		Order order2 = new Order();
		order2.setOrderNumber(23456);
		order2.setOrderDate("02.01.2021.");
		order2.setDeliveryPlace("Street number 2");
		order2.setOrderPrice(10);
		order1.setOrderDescription("2x burriton + 2x juice - Tortilla Casa");
		order2.setDeliverer(deliverer2);
		
		Order order3 = new Order();
		order3.setOrderNumber(34567);
		order3.setOrderDate("03.01.2021.");
		order3.setDeliveryPlace("Street number 3");
		order3.setOrderPrice(20);
		order3.setOrderDescription("2x Big Mac - McDonalds");
		order3.setDeliverer(deliverer3);
		
		orderRepository.save(order1);
		orderRepository.save(order2);
		orderRepository.save(order3);
		
		delivererRepository.save(deliverer1);
		delivererRepository.save(deliverer2);
		delivererRepository.save(deliverer3);
		
		List<User> users = new ArrayList<User>();
		for(int i = 1; i <= 3; i++) {
			User user = new User();
			user.setUserName("user" + i);
			user.setName("Name " + i);
			user.setLastName("Last " + i);
			user.seteMail("user"+i+"@mail.com");
			
			String encodedPass = passwordEncoder.encode("pass"+i);
			user.setPassword(encodedPass);

			List<UserRole> roles = Arrays.asList(UserRole.values());
			Random random = new Random();
			user.setRole(roles.get(random.nextInt(3)));
			
			users.add(user);
			userService.save(user);
		}
		
		
	}

}
