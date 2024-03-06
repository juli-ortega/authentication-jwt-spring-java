package proyecton.com.Proyecton7.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import proyecton.com.Proyecton7.entities.Servicio;
import proyecton.com.Proyecton7.services.ServiceServicexd;

import java.util.Optional;

@Controller
@RequestMapping("/imagen")
public class ImagesController {
    @Autowired
    ServiceServicexd serviceService;
    @GetMapping("/service/{id}")
    public ResponseEntity<byte[]> imageServicio(@PathVariable Long id){
        Optional<Servicio> answer = serviceService.getOne(String.valueOf(id));
        if (answer != null) {
            Servicio service = answer.get();
            byte[] image = service.getImages().getContenido();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            return new ResponseEntity<>(image, headers, HttpStatus.OK);
        }
        return  null;
    }
}
