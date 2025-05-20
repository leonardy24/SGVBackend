package com.proyectoSGV.demo.main;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "productosexistencias")
public class ProductosExistencias {

	@Id
	@Column(name = "idproductos")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idProductos;

	@Column(name = "codigo")
	private long codigo;

	@Column(name = "cantidad")
	private int cantidad;

	@Column(name = "nomproducto")
	private String nomProducto;

	@Column(name = "preciocosto")
	private double precioCosto;

	@Column(name = "precioventa")
	private double precioVenta;

	public ProductosExistencias() {
		super();
	}

	public int getIdProductos() {
		return idProductos;
	}

	public void setIdProductos(int idProductos) {
		this.idProductos = idProductos;
	}

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
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

	public double getPrecioCosto() {
		return precioCosto;
	}

	public void setPrecioCosto(double precioCosto) {
		this.precioCosto = precioCosto;
	}

	public double getPrecioVenta() {
		return precioVenta;
	}

	public void setPrecioVenta(double precioVenta) {
		this.precioVenta = precioVenta;
	}

	@Override
	public String toString() {
		return "ProductosExistencias [idProductos=" + idProductos + ", codigo=" + codigo + ", cantidad=" + cantidad
				+ ", nomProducto=" + nomProducto + ", precioCosto=" + precioCosto + ", precioVenta=" + precioVenta
				+ "]";
	}

}
