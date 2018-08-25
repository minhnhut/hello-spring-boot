package fire;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import entity.User;
import repository.UserRepository;

@RestController
public class GreetingController {
	
	@Autowired
	private UserRepository userRepository;

	private static final String template = "Hello, %s";
	private final AtomicLong counter = new AtomicLong();
	
	public GreetingController() {
		System.out.println("inited GreetingController");
	}
	
	@RequestMapping("/greeting")
	public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
		return new Greeting(counter.incrementAndGet(),
				String.format(template, name));
	}
	
	@RequestMapping("/user")
	public User getUserById(@RequestParam(value="id") Integer id) {
		Optional<User> u = userRepository.findById(id);
		if (u.isPresent()) {
			return u.get();
		}
		return null;
	}
	
}
