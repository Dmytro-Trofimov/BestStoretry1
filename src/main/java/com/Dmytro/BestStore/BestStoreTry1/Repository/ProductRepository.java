package com.Dmytro.BestStore.BestStoreTry1.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Dmytro.BestStore.BestStoreTry1.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{

}
