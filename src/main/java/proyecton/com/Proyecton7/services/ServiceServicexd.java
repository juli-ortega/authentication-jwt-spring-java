package proyecton.com.Proyecton7.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyecton.com.Proyecton7.entities.Images;
import proyecton.com.Proyecton7.entities.Servicio;
import proyecton.com.Proyecton7.repositories.ServiceRepository;

import java.util.List;

@Service
public class ServiceServicexd {
    @Autowired
    private ServiceRepository serviceRepository;

    public void createService(String description, List<Images> images, Double price,  String availability, Double estimatedTime, String requirements, String specificCharacteristics) {
        Servicio servicio = new Servicio();
        servicio.setDescription(description);
        servicio.setImages(images);
        servicio.setPrice(price);
        servicio.setHighOrLow(true);
        servicio.setAvailability(availability);
        servicio.setEstimatedTime(estimatedTime);
        servicio.setRequirements(requirements);
        servicio.setSpecificCharacteristics(specificCharacteristics);
        serviceRepository.save(servicio);
    }

    public void updateService(Long id, String description, List<Images> images, Double price, boolean highOrLow, int customer, String testimonialsOrReviews, String availability, Double estimatedTime, String requirements, String specificCharacteristics) {
        Servicio service = serviceRepository.findById(String.valueOf(id)).orElseThrow(() -> new EntityNotFoundException("Service with id " + id + " not found"));
        service.setDescription(description);
        service.setImages(images);
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
}
