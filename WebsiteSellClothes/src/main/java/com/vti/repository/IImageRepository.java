package com.vti.repository;

import com.vti.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IImageRepository extends JpaRepository<Image, Integer> {


}
