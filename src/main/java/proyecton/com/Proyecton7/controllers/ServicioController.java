package proyecton.com.Proyecton7.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;
import proyecton.com.Proyecton7.entities.Servicio;
import proyecton.com.Proyecton7.services.ServiceImages;
import proyecton.com.Proyecton7.services.ServiceServicexd;

import java.util.List;

@Controller
@RequestMapping("/servicio")
public class ServicioController {

    @Autowired
    private ServiceServicexd servicioServicios;
    @Autowired
    private ServiceImages serviceImages;
    @GetMapping("/crear")
    public String crearServicio(){

        return "createService";
    }


    @PostMapping("/creado")
    public RedirectView servicioCreado(
                                        @RequestParam String name,
                                        @RequestParam String description,
                                        @RequestParam MultipartFile archivo,
                                        @RequestParam Double price,
                                        @RequestParam String availability,
                                        @RequestParam Double estimatedTime,
                                        @RequestParam String requirements,
                                        @RequestParam String specificCharacteristics,
                                        ModelMap modelo) {
        try {

            servicioServicios.createService(name,description, archivo, price, availability, estimatedTime, requirements, specificCharacteristics);
            modelo.put("exito", "¡Servicio cargado correctamente!");
            return new RedirectView("/user/home");
        } catch (Exception e) {
            modelo.put("error", "¡Error al cargar el servicio!" + e.getMessage());
            return new RedirectView("/user/home");
        }
    }

    @GetMapping("/listImages")
    public String listImages(ModelMap model) {
        List<Servicio> service = servicioServicios.getAllServices();
        model.addAttribute("service", service);
        return "listImages"; // This is the name of the HTML file that will display the images
    }


}
