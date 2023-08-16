package com.urloans.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.urloans.Model.Queries;

@Repository
public interface QueriesRepo extends JpaRepository<Queries, Integer> {

}
