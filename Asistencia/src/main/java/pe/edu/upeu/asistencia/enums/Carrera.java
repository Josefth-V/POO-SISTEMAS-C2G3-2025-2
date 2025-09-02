package pe.edu.upeu.asistencia.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter

public enum Carrera {
    SISTEMAS(Facultad.FIA,  "Ing sistemas"),
    CIVIL(Facultad.FIA,  "Ing civil"),
    AMBIENTAL (Facultad.FIA,  "Ing ambiental"),


    ADMINISTRACION(Facultad.FCE,  "Administracion"),
    NUTRICION(Facultad.FCS,  "Nutricion"),
    EDUCACION(Facultad.FACIHED,  "Educacion");




    private Facultad facultad;
    private String descripcion;
}
