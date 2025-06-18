package com.proyectoSGV.demo.main;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "detallesventas")
public class DetallesVentas {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "codigo")
	private long codigo;

	@Column(name = "nomproducto")
	private String nomProducto;

	@Column(name = "cantidad")
	private int cantidad;

	@Column(name = "precio")
	private double precio;
	
	 // Relación inversa
    @ManyToOne
    @JoinColumn(name = "idventas")
    private Venta venta;
    
    @ManyToOne
    @JoinColumn(name="idproducto")
    private ProductosVentas produVentas;
    

	public DetallesVentas() {

	}

	public DetallesVentas(int codigo, String nomProducto, int cantidad, double precio) {
		super();
		this.codigo = codigo;
		this.nomProducto = nomProducto;
		this.cantidad = cantidad;
		this.precio = precio;
	}
	
	
	

	public ProductosVentas getProduVentas() {
		return produVentas;
	}

	public void setProduVentas(ProductosVentas produVentas) {
		this.produVentas = produVentas;
	}

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public String getNomProducto() {
		return nomProducto;
	}

	public void setNomProducto(String nomProducto) {
		this.nomProducto = nomProducto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Venta getVenta() {
		return venta;
	}

	public void setVenta(Venta venta) {
		this.venta = venta;
	}

	@Override
	public String toString() {
		return "ProductosJSON [codigo=" + codigo + ", nomProducto=" + nomProducto + ", cantidad=" + cantidad
				+ ", precio=" + precio + "]";
	}

}
