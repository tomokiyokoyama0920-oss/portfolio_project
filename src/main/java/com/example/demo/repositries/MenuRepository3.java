package com.example.demo.repositries;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.models.MenuForm;

@Repository
public interface MenuRepository3 extends JpaRepository<MenuForm, Long>{
	Optional<MenuForm> findById(long id);
	List<MenuForm> findAll();
}