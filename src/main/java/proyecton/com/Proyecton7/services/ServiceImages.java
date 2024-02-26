package proyecton.com.Proyecton7.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import proyecton.com.Proyecton7.entities.Images;
import proyecton.com.Proyecton7.exceptions.MiException;
import proyecton.com.Proyecton7.repositories.ImagesRepository;

import java.util.Optional;

@Service
public class ServiceImages {
    @Autowired
    private ImagesRepository imagesRepository;

    public Images guardarImagen(MultipartFile archivo) throws MiException {
        if (archivo != null){
            try{
                Images images = new Images();

                images.setMime(archivo.getContentType());
                images.setNombre(archivo.getName());
                images.setContenido(archivo.getBytes());

                return imagesRepository.save(images);
            }catch (Exception e) {
                System.err.println(e.getMessage());

            }
        }
        return null;
    }

    public Images actualizarImagen(MultipartFile archivo, String idImages) throws MiException{

        if(archivo != null){
            try {
                Images imagen = new Images();

                if(idImages != null){
                    Optional<Images> answer = imagesRepository.findById(idImages);
                    if(answer.isPresent()){
                        imagen = answer.get();
                    }

                }

                imagen.setMime(archivo.getContentType());
                imagen.setNombre(archivo.getName());
                imagen.setContenido(archivo.getBytes());

                return imagesRepository.save(imagen);
            } catch (Exception e) {
                System.err.println(e.getMessage());

            }
        }
        return null;
    }


    public Images eliminarImagen(MultipartFile archivo, String idImagen) throws MiException{

        if(archivo != null){

            try {
                Images imagen;

                if(idImagen != null){
                    Optional<Images> respuesta = imagesRepository.findById(idImagen);

                    if(respuesta.isPresent()){
                        imagen = respuesta.get();
                        imagesRepository.delete(imagen);
                    }
                }

            } catch (Exception e) {
                System.err.println(e.getMessage());
//                throw new MiException("Error al eliminar imagen");
            }
        }
        return null;
    }
}
