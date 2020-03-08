package com.gcaldas.conceptualmodel.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcaldas.conceptualmodel.domain.City;
import com.gcaldas.conceptualmodel.repositories.CityRepository;

@Service
public class CityService {

	@Autowired
	private CityRepository rep;

	public List<City> findByState(Integer stateId) {
		return rep.findCities(stateId);
	}
}
