/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dispapeles.prueba.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.dispapeles.prueba.entity.Cliente;
import org.springframework.stereotype.Repository;

/**
 *
 * @author USER
 */

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, String>{
    
}
