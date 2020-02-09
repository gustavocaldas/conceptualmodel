package com.gcaldas.conceptualmodel.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.gcaldas.conceptualmodel.domain.Client;
import com.gcaldas.conceptualmodel.dto.ClientDTO;
import com.gcaldas.conceptualmodel.repositories.ClientRepository;
import com.gcaldas.conceptualmodel.services.exceptions.DataIntegrityException;
import com.gcaldas.conceptualmodel.services.exceptions.ObjectNotFoundException;

@Service
public class ClientService {

	@Autowired
	private ClientRepository rep;

	public Client find(Integer id) {
		Optional<Client> obj = rep.findById(id);

		return obj.orElseThrow(
				() -> new ObjectNotFoundException("Object not found. Id: " + id + ", Type: " + Client.class.getName()));
	}

	public Client update(Client obj) {
		Client newObj = find(obj.getId());
		updateData(newObj, obj);
		return rep.save(newObj);
	}

	public void delete(Integer id) {
		find(id);
		try {
			rep.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("It is not possible to exclude a category that has products.");
		}
	}

	public List<Client> findAll() {
		return rep.findAll();
	}

	public Page<Client> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return rep.findAll(pageRequest);
	}

	public Client fromDTO(ClientDTO objDTO) {
		return new Client(objDTO.getId(), objDTO.getName(), objDTO.getEmail(), null, null);
	}
	
	private void updateData(Client newObj, Client obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
	}
}
