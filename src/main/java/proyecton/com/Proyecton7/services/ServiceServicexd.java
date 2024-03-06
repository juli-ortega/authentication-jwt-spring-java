package proyecton.com.Proyecton7.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import proyecton.com.Proyecton7.entities.Images;
import proyecton.com.Proyecton7.entities.Servicio;
import proyecton.com.Proyecton7.exceptions.MiException;
import proyecton.com.Proyecton7.repositories.ServiceRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceServicexd {
    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private ServiceImages serviceImages;

    public void createService(String name ,String description, MultipartFile images, Double price, String availability, Double estimatedTime, String requirements, String specificCharacteristics) throws MiException {
        Servicio servicio = new Servicio();
        servicio.setName(name);
        servicio.setDescription(description);
        Images imagen = serviceImages.guardarImagen(images);
        servicio.setImages(imagen);
        servicio.setPrice(price);
        servicio.setHighOrLow(true);
        servicio.setAvailability(availability);
        servicio.setEstimatedTime(estimatedTime);
        servicio.setRequirements(requirements);
        servicio.setSpecificCharacteristics(specificCharacteristics);


        serviceRepository.save(servicio);
    }

    public void updateService(Long id,String name, String description, MultipartFile images, Double price, boolean highOrLow, int customer, String testimonialsOrReviews, String availability, Double estimatedTime, String requirements, String specificCharacteristics) throws MiException {
        Servicio service = serviceRepository.findById(String.valueOf(id)).orElseThrow(() -> new EntityNotFoundException("Service with id " + id + " not found"));
        service.setDescription(description);
        String idImagen = null;
        if (service.getImages() != null){
            idImagen = service.getImages().getIdImagen();
        }
        Images imagen = serviceImages.actualizarImagen(images,idImagen);
        service.setImages(imagen);
        service.setName(name);
        service.setPrice(price);
        service.setHighOrLow(highOrLow);
        service.setCustomer(customer);
        service.setTestimonialsOrReviews(testimonialsOrReviews);
        service.setAvailability(availability);
        service.setEstimatedTime(estimatedTime);
        service.setRequirements(requirements);
        service.setSpecificCharacteristics(specificCharacteristics);
         serviceRepository.save(service);
    }

    public List<Servicio> getAllServices() {
        List<Servicio> servicios = serviceRepository.findAll();
        return servicios;
    }

    public void deleteService(Long id) {
        serviceRepository.deleteById(String.valueOf(id));
    }
    public Optional<Servicio> getOne(String id){
        return serviceRepository.findById(id);
    }
}
