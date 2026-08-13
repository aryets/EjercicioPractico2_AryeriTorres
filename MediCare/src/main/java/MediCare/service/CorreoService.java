package MediCare.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class CorreoService {

    @Autowired
    private JavaMailSender mailSender;

    public void enviarCorreoBienvenida(String correoDestino, String nombreUsuario) {
        try {
            SimpleMailMessage mensaje = new SimpleMailMessage();
            mensaje.setTo(correoDestino);
            mensaje.setSubject("¡Bienvenido a la plataforma MediCare!");
            mensaje.setText("Hola " + nombreUsuario + ",\n\n"
                    + "Tu cuenta en MediCare ha sido creada exitosamente.\n"
                    + "Ya puedes acceder a la plataforma para gestionar tus citas de salud.\n\n"
                    + "Atentamente,\nEl equipo de MediCare.");

            mailSender.send(mensaje);
        } catch (Exception e) {
            System.err.println("Error al enviar el correo: " + e.getMessage());
        }
    }
}
