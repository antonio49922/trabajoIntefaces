package org.example.proyecto;

public class Usuario {

    // Variables
    private String nombre;
    private String apellido;
    private String correo;
    private String password;
    private Rol rol;

    // Constructor
    public Usuario(String nombre, String apellido, String correo, String password, Rol rol) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.password = password;
        this.rol = rol;}

    public Usuario() {}

    // Getter and Setter
    public String getApellido() {
        return apellido;}

    public void setApellido(String apellido) {
        this.apellido = apellido;}

    public String getNombre() {
        return nombre;}

    public void setNombre(String nombre) {
        this.nombre = nombre;}

    public String getCorreo() {
        return correo;}

    public void setCorreo(String correo) {
        this.correo = correo;}

    public String getPassword() {
        return password;}

    public void setPassword(String password) {
        this.password = password;}

    public Rol getRol() {
        return rol;}

    public void setRol(Rol rol) {
        this.rol = rol;}
}

    // Enum para el tipo de usuario
    enum Rol {
        ADMIN, USUARIO
    }