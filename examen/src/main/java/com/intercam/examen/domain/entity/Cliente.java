package com.intercam.examen.domain.entity;

import com.intercam.examen.dto.ClienteDto;
import com.intercam.examen.dto.UpdateClientRequestDto;
import com.sun.istack.NotNull;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;

    @Basic(optional = false)
    @NotNull
    @Column(name = "nombre")
    private String nombre;

    @Basic(optional = false)
    @NotNull
    @Column(name = "apellido_paterno")
    private String apellidoPaterno;

    @Basic(optional = false)
    @NotNull
    @Column(name = "apellido_materno")
    private String apellidoMaterno;

    @Basic(optional = false)
    @Column(name = "fecha_nacimiento")
    private Date fechaNacimiento;

    @Basic(optional = false)
    @Column(name = "ingresos")
    private Double ingresos;

    @Basic(optional = false)
    @Column(name = "codigo_postal")
    private String codigoPostal;

    public Cliente(Integer id, String nombre, String apellidoPaterno, String apellidoMaterno, Date fechaNacimiento, Double ingresos, String codigoPostal) {
        this.id = id;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.fechaNacimiento = fechaNacimiento;
        this.ingresos = ingresos;
        this.codigoPostal = codigoPostal;
    }

    public Cliente(String nombre, String apellidoPaterno, String apellidoMaterno, Date fechaNacimiento, Double ingresos, String codigoPostal) {
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.fechaNacimiento = fechaNacimiento;
        this.ingresos = ingresos;
        this.codigoPostal = codigoPostal;
    }

    public Cliente(ClienteDto clienteDto) {
        nombre          = clienteDto.getNombre         ();
        apellidoPaterno = clienteDto.getApellidoPaterno();
        apellidoMaterno = clienteDto.getApellidoMaterno();
        fechaNacimiento = clienteDto.getFechaNacimiento();
        ingresos        = clienteDto.getIngresos       ();
        codigoPostal    = clienteDto.getCodigoPostal   ();
    }

    public Cliente(UpdateClientRequestDto clienteDto) {
        id              = clienteDto.getId             ();
        nombre          = clienteDto.getNombre         ();
        apellidoPaterno = clienteDto.getApellidoPaterno();
        apellidoMaterno = clienteDto.getApellidoMaterno();
        fechaNacimiento = clienteDto.getFechaNacimiento();
        ingresos        = clienteDto.getIngresos       ();
        codigoPostal    = clienteDto.getCodigoPostal   ();
    }

    public Cliente() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellifoMaterno) {
        this.apellidoMaterno = apellifoMaterno;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Double getIngresos() {
        return ingresos;
    }

    public void setIngresos(Double ingresos) {
        this.ingresos = ingresos;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }
}
