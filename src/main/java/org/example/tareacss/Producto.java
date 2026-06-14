package org.example.tareacss;

public class Producto {
    private String codigo;
    private String nombre;
    private String categoria;
    private double precio;
    private int stock;
    private String estado;

    public Producto(String codigo, String estado, int stock, double precio, String categoria, String nombre) {
        this.codigo = codigo;
        this.estado = estado;
        this.stock = stock;
        this.precio = precio;
        this.categoria = categoria;
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
