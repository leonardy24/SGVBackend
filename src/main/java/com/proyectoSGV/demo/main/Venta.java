package com.proyectoSGV.demo.main;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
	private List<DetallesVentas> productos;

	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuarios usuario;

	@Column(name = "fecha_venta")
	private LocalDateTime fecha;

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public Usuarios getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuarios usuario) {
		this.usuario = usuario;
	}

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

	public List<DetallesVentas> getProductos() {
		return productos;
	}

	public void setProductos(List<DetallesVentas> productos) {
		this.productos = productos;
		for (DetallesVentas p : productos) {
			p.setVenta(this); // ✅ se asegura la relación
		}
	}

	@Override
	public String toString() {
		return "Venta [id=" + id + ", metodoPago=" + metodoPago + ", totalVenta=" + totalVenta + ", iva=" + iva
				+ ", productos=" + productos + ", usuario=" + usuario + ", fecha=" + fecha + "]";
	}

	

}
