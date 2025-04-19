package com.proyectoSGV.demo.main;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "productosventas")
public class ProductosVentas {

	
	@Id
	@Column(name = "idproducto")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idProductos;
	
	@Column(name = "codigo")
	private int codigo;
	
	@Column(name = "cantidad")
	private int cantidad;
	
	@Column(name = "nombreproducto")
	private String nomProducto;
	
	
	@Column(name = "precio")
	private double precio;
	
	
	public ProductosVentas() {
		
	}


	public int getIdProductos() {
		return idProductos;
	}


	public void setIdProductos(int idProductos) {
		this.idProductos = idProductos;
	}


	public int getCodigo() {
		return codigo;
	}


	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}


	public int getCantidad() {
		return cantidad;
	}


	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}


	public String getNomProducto() {
		return nomProducto;
	}


	public void setNomProducto(String nomProducto) {
		this.nomProducto = nomProducto;
	}


	public double getPrecio() {
		return precio;
	}


	public void setPrecio(double precio) {
		this.precio = precio;
	}


	@Override
	public String toString() {
		return "ProductosVentas [idProductos=" + idProductos + ", codigo=" + codigo + ", cantidad=" + cantidad
				+ ", nomProducto=" + nomProducto + ", precio=" + precio + "]";
	}

	
	
	
}
