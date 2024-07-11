package com.urloans;

import com.urloans.Model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestH2Repo extends JpaRepository<Admin, Integer> {

}
