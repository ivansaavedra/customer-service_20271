package com.customer_service.api.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.customer_service.api.dto.DtoCustomerImageIn;
import com.customer_service.api.entity.CustomerImage;
import com.customer_service.api.repository.RepoCustomerImage;
import com.customer_service.exception.ApiException;

@Service
public class SvcCustomerImageImp implements SvcCustomerImage{

    @Autowired
	RepoCustomerImage repo;

    @Value("${app.upload.dir}")
    private String uploadDir;

    @Value("${app.upload.images}")
    private String uploadImages;

    private String dirCustomer = "customer";

    @Override
    public void upload(DtoCustomerImageIn in) {
        try {

            // 0 Validar que el customer_id exista en la base de datos
            
            CustomerImage customerImage = repo.findByCustomerId(in.getCustomerId());
            if(customerImage == null)
                throw new ApiException(HttpStatus.NOT_FOUND, "El id del cliente no existe");

            // Validar prefijo de la imagen en base64
            if (!in.getImage().matches("^data:image/[a-zA-Z0-9.+-]+;base64,.*$")) 
                throw new ApiException(HttpStatus.BAD_REQUEST, "La imagen debe tener una extensión válida");

		    // 1 convertir la imagen base64 recibida a un file

            // Decodifica la cadena Base64 a bytes quitando el prefijo de la imagen
            String image = in.getImage();
            byte[] imageBytes = Base64.getDecoder().decode(image.substring(image.indexOf(",") + 1));

            // Genera un nombre único para la imagen (se asume extensión PNG)
            String fileName = UUID.randomUUID().toString() + ".png";

            // Construye la ruta completa donde se guardará la imagen
            Path imagePath = Paths.get(uploadDir, uploadImages, dirCustomer, fileName);

            
            // 2 Guardar el File en el sistema de archivos

            // Asegurarse de que el directorio exista
            Files.createDirectories(imagePath.getParent());

            // Escribir el archivo en el sistema de archivos
            Files.write(imagePath, imageBytes);


            // 3 Guardar la ruta de la imagen en la base de datos
            
            customerImage.setImage("/" + dirCustomer + "/" + fileName);

            // Guardar la ruta de la imagen
            repo.save(customerImage);

		}catch (IOException e) {
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al guardar el archivo");
		}

    }

}
