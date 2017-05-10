package es.uniovi.asw.participants.html;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import es.uniovi.asw.model.Citizen;
import es.uniovi.asw.participants.errors.ErrorInterface;

/**
 * Representa la información que irá en el JSON cuando se 
 * envíen sus datos al usuario
 * 
 * @author UO237394 (base principal), UO247242 (gestión de cuando la información no se encuentra
 *  y de la encrioptacion de contraseña)
 * 
 */
@Controller
public class GetParticipantInfoControllerHTML {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getLoginHtml(Model model, HttpSession session) {
		Citizen citizen = (Citizen) session.getAttribute("citizen");
    	if (citizen != null && citizen.getUser() != null) {
    		return citizen.getUser().isAdmin() ? "redirect:/sugerencias" : "redirect:/datos";
    	}
		return "login";
	}
	
	@RequestMapping(value = "/cerrarSesion", method = RequestMethod.GET)
	public String cerrarSesion(Model model, HttpSession session) {
		session.setAttribute("citizen", null);
		return "redirect:/";
	}

	@RequestMapping("/datos")
    public String vistaDatos(Model model, HttpSession session) {
		Citizen citizen = (Citizen) session.getAttribute("citizen");
    	if (citizen == null || citizen.getUser() == null || citizen.getUser().isAdmin())
    		return "redirect:/";
    	
		model.addAttribute("email", citizen.getEmail());
		model.addAttribute("firstName", citizen.getName());
		model.addAttribute("lastName", citizen.getSurname());
		model.addAttribute("nif", citizen.getDni());
		model.addAttribute("address", citizen.getResidence());
		model.addAttribute("nationality", citizen.getNationality());
		model.addAttribute("admin", citizen.getUser().isAdmin());
		
    	return "datos";
    }

	@ExceptionHandler(ErrorInterface.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public String handleErrorResponseNotFound(ErrorInterface excep, Model model) {
		model.addAttribute("error", excep.getStringError());

		return "error";
	}
}