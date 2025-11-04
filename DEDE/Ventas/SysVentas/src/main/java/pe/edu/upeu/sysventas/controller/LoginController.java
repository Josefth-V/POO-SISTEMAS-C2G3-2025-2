package pe.edu.upeu.sysventas.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import pe.edu.upeu.sysventas.components.StageManager;
import pe.edu.upeu.sysventas.components.Toast;
import pe.edu.upeu.sysventas.dto.SessionManager;
import pe.edu.upeu.sysventas.model.Usuario;
import pe.edu.upeu.sysventas.service.IUsuarioService;
import pe.edu.upeu.sysventas.service.EmailService;

import java.io.IOException;

@Controller
public class LoginController {

    @Autowired
    private ApplicationContext context;
    @Autowired
    IUsuarioService us;
    @Autowired
    EmailService emailService; // Servicio para enviar notificaciones por correo electrónico
    @FXML
    TextField txtUsuario;
    @FXML
    PasswordField txtClave;
    @FXML
    Button btnIngresar;

    @FXML
    public void cerrar(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
        Platform.exit();
        System.exit(0);
    }

    @FXML
    public void login(ActionEvent event) throws IOException {
        try {
            Usuario usu = us.loginUsuario(txtUsuario.getText(), txtClave.getText());
            if (usu != null) {
                // Guardar detalles de la sesión
                SessionManager.getInstance().setUserId(usu.getIdUsuario());
                SessionManager.getInstance().setUserName(usu.getUser());
                SessionManager.getInstance().setUserPerfil(usu.getIdPerfil().getNombre());

                // Notificación por correo electrónico
                String ipAddress = "127.0.0.1"; // Simulación de la IP (puedes obtener la real si es necesario)
                emailService.sendLoginNotification(usu.getEmail(), usu.getUser(), ipAddress);

                // Cargar la interfaz principal
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/maingui.fxml"));
                loader.setControllerFactory(context::getBean);
                Parent mainRoot = loader.load();
                Screen screen = Screen.getPrimary();
                Rectangle2D bounds = screen.getBounds();
                Scene mainScene = new Scene(mainRoot, bounds.getWidth(), bounds.getHeight() - 30);
                mainScene.getStylesheets().add(getClass().getResource("/css/styles.css").toExternalForm());
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.getIcons().add(new Image(getClass().getResource("/img/store.png").toExternalForm()));
                stage.setScene(mainScene);
                stage.setTitle("SysVentas SysCenterLife");
                stage.setX(bounds.getMinX());
                stage.setY(bounds.getMinY());
                stage.setResizable(true);
                StageManager.setPrimaryStage(stage);
                stage.setWidth(bounds.getWidth());
                stage.setHeight(bounds.getHeight());
                stage.show();
            } else {
                // Credenciales inválidas
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                double with = stage.getWidth() * 2;
                double h = stage.getHeight() / 2;
                System.out.println(with + " h:" + h);
                Toast.showToast(stage, "Credencial invalido!! intente nuevamente", 2000, with, h);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}