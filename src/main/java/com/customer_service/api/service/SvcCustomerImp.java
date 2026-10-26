package com.customer_service.api.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;

import com.customer_service.api.dto.DtoCustomerIn;
import com.customer_service.api.dto.DtoCustomerListOut;
import com.customer_service.api.dto.DtoCustomerOut;
import com.customer_service.api.entity.Customer;
import com.customer_service.api.entity.CustomerImage;
import com.customer_service.api.repository.RepoCustomer;
import com.customer_service.api.repository.RepoCustomerImage;
import com.customer_service.commons.mapper.MapperCustomer;
import com.customer_service.exception.ApiException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class SvcCustomerImp implements SvcCustomer {

    @Autowired
    RepoCustomer repo;

	@Autowired
	RepoCustomerImage repoCustomerImage;

	@Autowired
	MapperCustomer mapper;

	@Value("${app.upload.dir}")
	private String uploadDir;


	@Value("${app.upload.images}")
	private String uploadImages;


	@Override
	public List<DtoCustomerListOut> findAll() {
		try {
		    List<Customer> customers = repo.findAllByOrderByNameAsc();
			List<DtoCustomerListOut> list = mapper.toListOut(customers);
			return list;
	    }catch (Exception e) {
        	throw new ApiException(
                HttpStatus.INTERNAL_SERVER_ERROR, // 500
                "Ocurrio un error en la petición"
            );
        }
	}

	@Override
	public DtoCustomerOut findById(Integer id) {
		try {
			DtoCustomerOut dto = repo.getCustomer(id);

			String img = readCustomerImageFile(dto.getImage());
			dto.setImage(img);

		    return dto;
	    }catch (Exception e) {
        	throw new ApiException(
                HttpStatus.INTERNAL_SERVER_ERROR, // 500
                "Ocurrió un error en la petición"
            );
        }
	}

	@Override
	public void create(DtoCustomerIn in) {
		try {
			Customer customer = mapper.fromDtoIn(in);
		    customer = repo.save(customer);

			CustomerImage customerImage = new CustomerImage();
			customerImage.setCustomerId(customer.getCustomer_id());
			customerImage.setImage("");
			customerImage.setStatus(1);
	       
			repoCustomerImage.save(customerImage);

	    }catch (ApiException e) {
			throw e;
		}catch (Exception e) {

			if (e.getLocalizedMessage().contains("ux_rfc"))
				throw new ApiException(
					HttpStatus.CONFLICT, 
					"El RFC del cliente ya está registrado"
				);
			
			if (e.getLocalizedMessage().contains("ux_mail"))
				throw new ApiException(
					HttpStatus.CONFLICT, 
					"El correo electrónico del cliente ya está registrado"
				);

			throw new ApiException(
                HttpStatus.INTERNAL_SERVER_ERROR, // 500
                "Ocurrió un error en la petición"
            );
        }
	}

	@Override
	public void update(DtoCustomerIn in, Integer id) {
		try {
			Customer customer = mapper.fromDtoIn(in, id);
		    repo.save(customer);
	    }catch (ApiException e) {
			throw e;
		}catch (Exception e) {
			if (e.getLocalizedMessage().contains("ux_rfc"))
				throw new ApiException(
					HttpStatus.CONFLICT, 
					"El RFC del cliente ya está registrado"
				);
			
			if (e.getLocalizedMessage().contains("ux_mail"))
				throw new ApiException(
					HttpStatus.CONFLICT, 
					"El correo electrónico del cliente ya está registrado"
				);

			throw new ApiException(
                HttpStatus.INTERNAL_SERVER_ERROR, // 500
                "Ocurrió un error en la petición"
            );
        }
	}

	@Override
	public void enable(Integer id) {
		updateStatus(id, 1);
	}

	@Override
	public void disable(Integer id) {
		updateStatus(id, 0);
	}

	private void updateStatus(Integer id, Integer status) {
		try {
			Customer customer = getCustomer(id);
			customer.setStatus(status);
		    repo.save(customer);
	    }catch (ApiException e) {
			throw e;
		}catch (Exception e) {
			throw new ApiException(
                HttpStatus.INTERNAL_SERVER_ERROR, // 500
                "Ocurrió un error en la petición"
            );
        }
	}

	private Customer getCustomer(Integer id){
		try {
		    return repo.findById(id).orElseThrow(() -> new ApiException(
				HttpStatus.NOT_FOUND,
				"El id del cliente no existe"
			));
	    }catch (ApiException e) {
			throw e;
		}catch (Exception e) {
        	throw new ApiException(
                HttpStatus.INTERNAL_SERVER_ERROR, // 500
                "Ocurrió un error en la petición"
            );
        }
	}

	private String readCustomerImageFile(String imageUrl) {
    	try {
	
			// Si la URL comienza con "/" la eliminamos para obtener la ruta relativa
  	 		if (imageUrl.startsWith("/")) {
       	    	imageUrl = imageUrl.substring(1);
   			}
  
  	 		// Construir el Path
  	 		Path imagePath = Paths.get(uploadDir, uploadImages, imageUrl);
  
  			// Verifica que el archivo exista
   			if (!Files.exists(imagePath)){
   	    		return "";
			}
  
			// Leer los bytes de la imagen y codificarlos a Base64
			byte[] imageBytes = Files.readAllBytes(imagePath);
			return Base64.getEncoder().encodeToString(imageBytes);
    
    	}catch (IOException e) {
			throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al leer el archivo");
    	}
	}

}
