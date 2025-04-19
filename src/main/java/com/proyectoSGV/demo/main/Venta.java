package com.proyectoSGV.demo.main;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "ventas")
public class Venta {

	@Id
	@Column(name = "idventas")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "metodopago")
	private String metodoPago;

	@Column(name = "totalventa")
	private double totalVenta;

	@Column(name = "iva")
	private double iva;

	@OneToMany(mappedBy = "venta", cascade = CascadeType.ALL)
	private List<Productos> productos;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMetodoPago() {
		return metodoPago;
	}

	public void setMetodoPago(String metodoPago) {
		this.metodoPago = metodoPago;
	}

	public double getTotalVenta() {
		return totalVenta;
	}

	public void setTotalVenta(double totalVenta) {
		this.totalVenta = totalVenta;
	}

	public double getIva() {
		return iva;
	}

	public void setIva(double iva) {
		this.iva = iva;
	}

	public List<Productos> getProductos() {
		return productos;
	}

	public void setProductos(List<Productos> productos) {
		this.productos = productos;
		for (Productos p : productos) {
	        p.setVenta(this); // ✅ se asegura la relación
	    }
	}

	@Override
	public String toString() {
		return "Venta [metodoPago=" + metodoPago + ", totalVenta=" + totalVenta + ", iva=" + iva + ", productos="
				+ productos + "]";
	}

}
