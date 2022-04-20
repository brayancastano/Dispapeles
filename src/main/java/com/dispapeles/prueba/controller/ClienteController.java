/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dispapeles.prueba.controller;

import com.dispapeles.prueba.repository.ClienteRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.dispapeles.prueba.entity.Cliente;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author USER
 */
@RestController
@RequestMapping("/api")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping("/clientes")
    public ResponseEntity<List<Cliente>> getListClientes() {
        return ResponseEntity.ok(clienteRepository.findAll());
    }

    @GetMapping("/cliente/{identificacion}")
    public ResponseEntity<Cliente> getClientesByIdentificacion(@PathVariable String identificacion) {
        Cliente cliente = clienteRepository.findById(identificacion).orElseThrow(
                () -> new ResourceNotFoundException("No se encuentra el cliente con identificacion:" + identificacion));

        return ResponseEntity.ok(cliente);
    }

    @PutMapping("/cliente")
    public ResponseEntity<?> putClienteByIdentificacion(@RequestBody Cliente cliente) {
        clienteRepository.findById(cliente.getIdentificacion()).orElseThrow(
                () -> new ResourceNotFoundException("No se encuentra el cliente con identificacion:" + cliente.getIdentificacion()));
        clienteRepository.saveAndFlush(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/cliente")
    public ResponseEntity<?> postCliente(@RequestBody Cliente cliente) {
        if (clienteRepository.existsById(cliente.getIdentificacion())) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } else {
            clienteRepository.saveAndFlush(cliente);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
    }

    @DeleteMapping("/cliente/{identificacion}")
    public ResponseEntity<?> deleteClienteByIdentificacion(@PathVariable String identificacion ){
        if (clienteRepository.existsById(identificacion)) {
            clienteRepository.deleteById(identificacion);
            return ResponseEntity.status(HttpStatus.OK).build();            
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
