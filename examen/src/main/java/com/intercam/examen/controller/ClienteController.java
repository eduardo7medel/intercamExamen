package com.intercam.examen.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.intercam.examen.domain.entity.Cliente;
import com.intercam.examen.domain.repository.ClienteRepository;
import com.intercam.examen.dto.ClienteDto;
import com.intercam.examen.dto.DeleteRequestDto;
import com.intercam.examen.dto.UpdateClientRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClienteController {

    @Autowired
    ClienteRepository clienteRepository;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/getAllClients")
    public @ResponseBody Iterable<Cliente> getAllClients(){
        return clienteRepository.findAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/getClientById")
    public @ResponseBody Cliente getClientById(Integer id){
        return clienteRepository.findById(id).get();
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/deleteClient")
    public @ResponseBody boolean deleteClient(@RequestBody DeleteRequestDto idCliente){
        try {
            clienteRepository.deleteById(idCliente.getId());
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    @PostMapping(value = "/addClient")
    public @ResponseBody boolean addClient(@RequestBody ClienteDto clienteDto){
        try {
            clienteRepository.save(new Cliente(clienteDto));
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    @PutMapping(value = "/updateClient")
    public @ResponseBody boolean updateClient(@RequestBody UpdateClientRequestDto clienteDto){
        try {
            if(clienteRepository.findById(clienteDto.getId()).get() == null)
                return false;
            clienteRepository.save(new Cliente(clienteDto));
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

}
