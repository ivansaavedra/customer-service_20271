package com.customer_service.api.service;

import java.util.List;

import com.customer_service.api.dto.DtoRegionIn;
import com.customer_service.api.entity.Region;
import com.customer_service.api.repository.RepoRegion;
import com.customer_service.exception.ApiException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class SvcRegionImp implements SvcRegion {

    @Autowired
    RepoRegion repo;

	@Override
	public List<Region> findAll() {
		try {
		    return repo.findAll();
	    }catch (Exception e) {
        	throw new ApiException(
                HttpStatus.INTERNAL_SERVER_ERROR, // 500
                "Ocurrió un error en la petición"
            );
        }

	}

	@Override
	public List<Region> findActive() {
		try {
		    return repo.findActive();
	    }catch (Exception e) {
        	throw new ApiException(
                HttpStatus.INTERNAL_SERVER_ERROR, // 500
                "Ocurrió un error en la petición"
            );
        }
	}

	@Override
	public void create(DtoRegionIn in) {
		try {
		    repo.create(in.getRegion(), in.getTag());
	    }catch (Exception e) {
        	if (e.getLocalizedMessage().contains("ux_region"))
				throw new ApiException(
					HttpStatus.CONFLICT, 
					"El nombre de la región ya está registrado"
				);
			
			if (e.getLocalizedMessage().contains("ux_tag"))
				throw new ApiException(
					HttpStatus.CONFLICT, 
					"El tag de la región ya está registrado"
				);

			throw new ApiException(
                HttpStatus.INTERNAL_SERVER_ERROR, // 500
                "Ocurrió un error en la petición"
            );

        }
	}

	@Override
	public void update(DtoRegionIn in, Integer id) {
		try {
			validateId(id);
		    repo.update(id, in.getRegion(), in.getTag());
	    }catch (Exception e) {
        	if (e.getLocalizedMessage().contains("ux_region"))
				throw new ApiException(
					HttpStatus.CONFLICT, 
					"El nombre de la región ya está registrado"
				);
			
			if (e.getLocalizedMessage().contains("ux_tag"))
				throw new ApiException(
					HttpStatus.CONFLICT, 
					"El tag de la región ya está registrado"
				);

			throw new ApiException(
                HttpStatus.INTERNAL_SERVER_ERROR, // 500
                "Ocurrió un error en la petición"
            );

        }
	}

	@Override
	public void enable(Integer id) {
		try {
			validateId(id);
		    repo.enable(id);
	    }catch (Exception e) {
			throw new ApiException(
                HttpStatus.INTERNAL_SERVER_ERROR, // 500
                "Ocurrió un error en la petición"
            );
        }
	}

	@Override
	public void disable(Integer id) {
		try {
			validateId(id);
		    repo.disable(id);
	    }catch (Exception e) {
			throw new ApiException(
                HttpStatus.INTERNAL_SERVER_ERROR, // 500
                "Ocurrió un error en la petición"
            );
        }
	}

	@Override
	public void updateStatus(Integer id, Integer status) {
		try {
			validateId(id);
		    repo.updateStatus(id, status);
	    }catch (Exception e) {
			throw new ApiException(
                HttpStatus.INTERNAL_SERVER_ERROR, // 500
                "Ocurrió un error en la petición"
            );
        }
	}

	private void validateId(Integer id){
		try {
		    if(repo.findById(id).isEmpty())
				throw new ApiException(
					HttpStatus.NOT_FOUND, 			
					"El id de la región no existe"
				);

	    }catch (Exception e) {
        	throw new ApiException(
                HttpStatus.INTERNAL_SERVER_ERROR, // 500
                "Ocurrió un error en la petición"
            );
        }
	}
}
