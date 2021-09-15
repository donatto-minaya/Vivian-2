package com.project.vivian.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Producto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idProducto;
	
	private int reparto, stock, idCategoria;	
	private String producto, moneda, categoria;
	private double precio;
	
	public Producto() {}

	public Producto(int idProducto, int reparto, int stock, int idCategoria, String producto, String moneda, String categoria, double precio) {
		this.idProducto = idProducto;
		this.reparto = reparto;
		this.stock = stock;
		this.idCategoria = idCategoria;
		this.producto = producto;
		this.moneda = moneda;
		this.categoria = categoria;
		this.precio = precio;
	}

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public int getReparto() {
		return reparto;
	}

	public void setReparto(int reparto) {
		this.reparto = reparto;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getProducto() {
		return producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}

	public String getMoneda() {
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}	
}
