package com.centroinformacion.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.centroinformacion.entity.Editorial;
import com.centroinformacion.repository.EditorialRepository;

@Service
public class EditorialServiceImp implements EditorialService {
	@Autowired
	private EditorialRepository repository;

	@Override
	public Editorial insertaActualizaEditorial(Editorial obj) {
		return repository.save(obj);
	}

	@Override
	public List<Editorial> listaTodos() {
		return repository.findByOrderByRazonSocialAsc();
	}

	@Override
	public List<Editorial> listaEditorialPorRazonSocialLike(String razonSocial) {
		return repository.listaPorRazonSocialLike(razonSocial);
	}

	@Override
	public void eliminaEditorial(int idEditorial) {
		repository.deleteById(idEditorial);
	}

	@Override
	public List<Editorial> listaEditorialPorRazonSocialIgualRegistra(String razonSocial) {
// TODO Auto-generated method stub
		return repository.listaPorRazonSocialIgualRegistra(razonSocial);
	}

	@Override
	public List<Editorial> listaEditorialPorRazonSocialIgualActualiza(String razonSocial, int idEditorial) {
// TODO Auto-generated method stub 
		return repository.listaPorRazonSocialIgualActualiza(razonSocial, idEditorial);
	}

}