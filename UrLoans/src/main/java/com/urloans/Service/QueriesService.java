package com.urloans.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.urloans.Model.Queries;
import com.urloans.Repository.QueriesRepo;

@Service
public class QueriesService {
	
	@Autowired
	private QueriesRepo queryRepo;

	//Adding Query
	public Queries addQuery(Queries query) {
		 Queries qr=null;
		 qr=queryRepo.save(query);
		return qr;
	}

	public List<Queries> getAll() {
		List<Queries> ql=null;
		ql=queryRepo.findAll();
		return ql;
	}

}
