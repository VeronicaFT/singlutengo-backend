package com.singlutengo.dto;

/**
 * Objeto de transferencia de datos para el login.
 * Contiene las credenciales necesarias para autenticar a un usuario.
 */
public class LoginRequest {

    /**
     * Correo electrónico del usuario.
     */
    private String email;

    /**
     * Contraseña del usuario (en texto plano, se encripta en el backend).
     */
    private String password;

    /**
     * Devuelve el email del usuario.
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Establece el email del usuario.
     * @param email correo electrónico
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Devuelve la contraseña del usuario.
     * @return contraseña
     */
    public String getPassword() {
        return password;
    }

    /**
     * Establece la contraseña del usuario.
     * @param password contraseña en texto plano
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
