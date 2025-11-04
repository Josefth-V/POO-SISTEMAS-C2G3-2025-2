package pe.edu.upeu.sysventas.service;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private static final String EMAIL_FROM = "ebergedeon@gmail.com"; // Cambia por tu correo electrónico
    private static final String PASSWORD = "vsam olmu cbvr gkmq"; // Cambia por tu contraseña o token SMTP si usas Gmail

    public static void  sendLoginNotification(String to, String userName, String ipAddress) {
        // Configuración del servidor SMTP
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com"); // Usa el servidor SMTP de tu proveedor
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EMAIL_FROM, PASSWORD);
            }
        });

        try {
            // Crear el mensaje
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(EMAIL_FROM));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to)); // Correo del destinatario
            message.setSubject("Inicio de Sesión Exitoso");
            message.setText("Hola " + userName + ",\n\n" +
                    "Has iniciado sesión en la DB de la libreria BARNEY, TEN UN EXCELENTE DIA.\n" +
                    "Detalles del inicio de sesión:\n" +
                    "IP: " + ipAddress + "\n" +
                    "Fecha y hora: " + java.time.LocalDateTime.now() + "\n\n" +
                    "Si no fuiste tú, por favor contacta al soporte inmediatamente. att: ING josefth");

            // Enviar el mensaje
            Transport.send(message);
            System.out.println("Correo enviado exitosamente a " + to);

        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("Error al enviar el correo: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        sendLoginNotification("ebergedeon@gmail.com", "juan", "127.0.0.1");
    }
}