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
import proyecton.com.Proyecton7.entities.Images;
import proyecton.com.Proyecton7.services.ServiceServicexd;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/servicio")
public class ServicioController {

    @Autowired
    private ServiceServicexd servicioServicios;
    @GetMapping("/crear")
    public String crearServicio(){

        return "crearServicios";
    }


    @PostMapping("/creado")
    public RedirectView servicioCreado(@RequestParam String description,
                                       @RequestParam("images") List<MultipartFile> images,
                                       @RequestParam Double price,
                                       @RequestParam String availability,
                                       @RequestParam Double estimatedTime,
                                       @RequestParam String requirements,
                                       @RequestParam String specificCharacteristics,
                                       ModelMap modelo) {
        try {
            List<Images> imagesList = new ArrayList<>();

            for (MultipartFile image : images) {
                Images img = new Images();
                img.setNombre(image.getOriginalFilename());
                img.setMime(image.getContentType());
                img.setContenido(image.getBytes()); // Convertir MultipartFile a byte[]
                imagesList.add(img);
            }

            servicioServicios.createService(description, imagesList, price, availability, estimatedTime, requirements, specificCharacteristics);
            modelo.put("exito", "¡Servicio cargado correctamente!");
            return new RedirectView("/user/home");
        } catch (Exception e) {
            modelo.put("error", "¡Error al cargar el servicio!" + e.getMessage());
            return new RedirectView("/user/home");
        }
    }



}
