/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.upeu.sysventas.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "upeu_usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long idUsuario;
    @Column(name = "user", nullable = false, unique = true, length = 20)
    private String user;
    @Column(name = "clave", nullable = false, length = 100)
    private String clave;
    @Column(name = "estado", nullable = false, length = 10)
    private String estado;

    @Column(name = "email", nullable = false)
    private String email;

    @JoinColumn(name = "id_perfil", referencedColumnName = "id_perfil")
    @ManyToOne(optional = false)
    private Perfil idPerfil;


/*

        private String idUsuario;
        private String user;
        private String email; // Agregamos el atributo email

        // Constructor
        public Usuario(String idUsuario, String user, String email) {
            this.idUsuario = idUsuario;
            this.user = user;
            this.email = email;
        }

        // Getter para email
        public String getEmail() {
            return email;
        }

        // Setter para email
        public void setEmail(String email) {
            this.email = email;
        }

        // Otros getters y setters
        public String getIdUsuario() {
            return idUsuario;
        }

        public void setIdUsuario(String idUsuario) {
            this.idUsuario = idUsuario;
        }

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }
*/

}
