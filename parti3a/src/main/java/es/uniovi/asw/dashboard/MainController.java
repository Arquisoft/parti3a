package es.uniovi.asw.dashboard;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import es.uniovi.asw.model.Citizen;
import es.uniovi.asw.model.Suggestion;
import es.uniovi.asw.persistence.CitizenRepository;
import es.uniovi.asw.persistence.CommentRepository;
import es.uniovi.asw.persistence.SuggestionRepository;
import es.uniovi.asw.util.Encrypter;


@Controller
public class MainController {

    @Autowired
    private SuggestionRepository sugerenciaRepository;
    
    @Autowired
    private CommentRepository comentarioRepository;
    
    @Autowired
    private CitizenRepository repository;

    @RequestMapping("/sugerencias")
    public String vistaSugerencias(Model model, HttpSession session) {
    	Citizen citizen = (Citizen) session.getAttribute("citizen");
    	if (citizen == null || citizen.getUser() == null || !citizen.getUser().isAdmin())
    		return "redirect:/";
    	
    	model.addAttribute("sugerencias", sugerenciaRepository.findAll());
    	model.addAttribute("comentarios", comentarioRepository.findAll());
        return "vistaSugerencias";
    }
    
    @RequestMapping(path = "/sugerencias/{id}", method = RequestMethod.GET)
	public String detalles(@PathVariable("id") String id, Model model, HttpSession session) {
    	Citizen citizen = (Citizen) session.getAttribute("citizen");
    	if (citizen == null || citizen.getUser() == null || !citizen.getUser().isAdmin())
    		return "redirect:/";
    	
    	Long ident = Long.valueOf(id);
    	Suggestion sugerencia = sugerenciaRepository.findOne(ident);
		model.addAttribute("id", id);
		model.addAttribute("detalles", sugerencia);
		model.addAttribute("comentarios", sugerencia.getComments());
		model.addAttribute("valoracion", sugerencia.getRating());
		return "detallesSugerencia";
    }
    
    @RequestMapping(value = "/validarse", method = RequestMethod.POST)
	public String postUserHtml(@RequestBody String parametros, Model model,
			RedirectAttributes redirectAttributes, HttpSession session) {

		String[] parametro = parametros.split("&");

		String email = parametro[0].split("=")[1].replace("%40", "@");
		String contraseña = parametro[1].split("=")[1];

		String contraseñaEncriptada = Encrypter.getInstance().makeSHA1Hash(contraseña);
		Citizen user = repository.findByEmailAndPassword(email, contraseñaEncriptada);

		if (user != null) {
			
			session.setAttribute("citizen", user);
			
			if (user.getUser().isAdmin())
				return "redirect:/sugerencias";
			
			return "redirect:/datos";
		} else { 
			//Ciudadano no encontrado
			redirectAttributes.addFlashAttribute("invalidUser", true);
			return "redirect:/";
		}
	}
}