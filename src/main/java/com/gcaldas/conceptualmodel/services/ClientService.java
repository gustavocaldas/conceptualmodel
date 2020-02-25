package com.gcaldas.conceptualmodel.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gcaldas.conceptualmodel.domain.Address;
import com.gcaldas.conceptualmodel.domain.City;
import com.gcaldas.conceptualmodel.domain.Client;
import com.gcaldas.conceptualmodel.domain.enums.ClientType;
import com.gcaldas.conceptualmodel.domain.enums.Profile;
import com.gcaldas.conceptualmodel.dto.ClientDTO;
import com.gcaldas.conceptualmodel.dto.ClientNewDTO;
import com.gcaldas.conceptualmodel.repositories.AddressRepository;
import com.gcaldas.conceptualmodel.repositories.ClientRepository;
import com.gcaldas.conceptualmodel.security.UserSS;
import com.gcaldas.conceptualmodel.services.exceptions.AuthorizationException;
import com.gcaldas.conceptualmodel.services.exceptions.DataIntegrityException;
import com.gcaldas.conceptualmodel.services.exceptions.ObjectNotFoundException;

@Service
public class ClientService {

	@Autowired
	private BCryptPasswordEncoder pe;

	@Autowired
	private ClientRepository rep;

	@Autowired
	private AddressRepository addressRepository;

	public Client find(Integer id) {
		UserSS user = UserService.authenticated();
		if (user == null || !user.hasRole(Profile.ADMIN) && !id.equals(user.getId())) {
			throw new AuthorizationException("Access denied");
		}

		Optional<Client> obj = rep.findById(id);

		return obj.orElseThrow(
				() -> new ObjectNotFoundException("Object not found. Id: " + id + ", Type: " + Client.class.getName()));
	}

	@Transactional
	public Client insert(Client obj) {
		obj.setId(null);
		obj = rep.save(obj);
		addressRepository.saveAll(obj.getAddress());
		return obj;
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
		return new Client(objDTO.getId(), objDTO.getName(), objDTO.getEmail(), null, null, null);
	}

	public Client fromDTO(ClientNewDTO objDTO) {
		Client cli = new Client(null, objDTO.getName(), objDTO.getEmail(), objDTO.getCpfCnpj(),
				ClientType.toEnum(objDTO.getType()), pe.encode(objDTO.getPassword()));
		City city = new City(objDTO.getCityId(), null, null);
		Address ad = new Address(null, objDTO.getStreetAddress(), objDTO.getNumber(), objDTO.getComplement(),
				objDTO.getDistrict(), objDTO.getZipCode(), cli, city);
		cli.getAddress().add(ad);
		cli.getPhones().add(objDTO.getPhone1());
		if (objDTO.getPhone2() != null) {
			cli.getPhones().add(objDTO.getPhone2());
		}

		if (objDTO.getPhone3() != null) {
			cli.getPhones().add(objDTO.getPhone3());
		}

		return cli;
	}

	private void updateData(Client newObj, Client obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
	}
}
