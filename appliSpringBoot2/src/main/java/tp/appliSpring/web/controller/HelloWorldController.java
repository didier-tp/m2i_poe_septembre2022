package tp.appliSpring.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller //composant spring de type Crontroller spring-mvc
public class HelloWorldController {

	@RequestMapping("/helloWorld")
	 public String helloWorld(Model model) {
	    model.addAttribute("message", "Hello World!");
	    return "showMessage"; //aiguiller sur la vue "showMessage"
	    //selon la config de application.xml jsp/showMessage.jsp
	 }
}
