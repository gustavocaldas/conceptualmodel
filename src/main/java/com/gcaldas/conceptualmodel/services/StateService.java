package com.gcaldas.conceptualmodel.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcaldas.conceptualmodel.domain.State;
import com.gcaldas.conceptualmodel.repositories.StateRepository;

@Service
public class StateService {

	@Autowired
	private StateRepository rep;

	public List<State> findAll() {
		return rep.findAllByOrderByName();
	}
}
