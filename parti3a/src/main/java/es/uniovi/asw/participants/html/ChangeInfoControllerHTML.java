package es.uniovi.asw.participants.html;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import es.uniovi.asw.business.CitizenService;
import es.uniovi.asw.model.Citizen;
import es.uniovi.asw.participants.errors.BadRequestError;
import es.uniovi.asw.participants.errors.ErrorInterface;
import es.uniovi.asw.util.Check;
import es.uniovi.asw.util.Encrypter;

/**
 * Controlador HTML para cambiar la información del usuario
 * 
 * @author UO246008
 * 
 */
@Controller
public class ChangeInfoControllerHTML {

	@Autowired
	private CitizenService citizenService;
	
	@RequestMapping(value = "/cambiarEmail", method = RequestMethod.POST)
	public String cambiarEmail(@RequestBody String parametros, Model model,
			RedirectAttributes redirectAttributes, HttpSession session) {
		
		Citizen citizen = (Citizen) session.getAttribute("citizen");
		if (citizen == null || citizen.getUser() == null 
				|| citizen.getUser().isAdmin())
			return "redirect:/";
		
		String[] parametro = parametros.split("&");

		String newEmail = parametro[0].split("=")[1].replace("%40", "@");
		String email = citizen.getEmail();
		
		Check.validEmailFormat(newEmail);
		Check.differentEmail(email, newEmail);
		
		//Todo correcto: cambiamos su e-mail y recargamos la página
		citizen.setEmail(newEmail);
		citizenService.updateInfo(citizen);
		
		return "redirect:/datos";
	}
	
	@RequestMapping(value = "/cambiarClave", method = RequestMethod.POST)
	public String cambiarClave(@RequestBody String parametros, Model model,
			RedirectAttributes redirectAttributes, HttpSession session) {
		
		Citizen citizen = (Citizen) session.getAttribute("citizen");
		if (citizen == null || citizen.getUser() == null || citizen.getUser().isAdmin())
			return "redirect:/";
		
		String[] parametro = parametros.split("&");

		String password = parametro[0].split("=")[1];
		String newPassword = parametro[1].split("=")[1];
		String email = citizen.getEmail();
		
		password = Encrypter.getInstance().makeSHA1Hash(password);
		newPassword = Encrypter.getInstance().makeSHA1Hash(newPassword);
		
		Check.participantExists(email, password, citizenService);
		Check.passwordNotEmpty(newPassword);
		Check.differentPassword(password, newPassword);
		
		//Todo correcto: cambiamos su password y recargamos la página
		citizen.getUser().setPassword(newPassword);
		citizenService.updateInfo(citizen);
		
		return "redirect:/datos";
	}
	
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(BadRequestError.class)
	public String handleErrorResponseNotFound(ErrorInterface excep, RedirectAttributes attrs) {
		attrs.addFlashAttribute("error", excep.getStringError());
		attrs.addFlashAttribute("errorPresent", true);
		return "error";
	}
	
	@ExceptionHandler(ErrorInterface.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public String handleErrorResponseNotFound(ErrorInterface excep, Model model) {
		model.addAttribute("error", excep.getStringError());

		return "error";
	}
}