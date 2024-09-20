package Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.User;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ContactsController {

    private final List<User> users = new ArrayList<>();

    
    @GetMapping("/home")
    public String homePage() {
        return "home";  
    }

    @PostMapping("/login")
    public String loginRedirect() {
        return "new";  
    }

	public List<User> getUsers() {
		return users;
	}
	

    }