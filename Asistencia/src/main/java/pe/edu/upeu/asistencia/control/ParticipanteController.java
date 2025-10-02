package pe.edu.upeu.asistencia.control;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pe.edu.upeu.asistencia.enums.Carrera;
import pe.edu.upeu.asistencia.enums.TipoParticipante;
import pe.edu.upeu.asistencia.modelo.Participante;
import pe.edu.upeu.asistencia.servicio.ParticipanteServicioI;

@Controller
public class ParticipanteController {

    @FXML
    private ComboBox<Carrera> cbxCarrera;
    @FXML
    private ComboBox<TipoParticipante> cbxTipoParticipante;

    @FXML TableView<Participante> tableView;
    ObservableList<Participante> participantes;
    TableColumn<Participante, String> dniCol, nombreCol, apellidoCol, carreraCol, tipoParCol;
    TableColumn<Participante, Void> opcionCol;

    @Autowired
    ParticipanteServicioI ps;
    @FXML TextField txtdni, txtnombre, txtapellido;
    int indexE=-1;

    @FXML
    public void initialize(){
        cbxCarrera.getItems().setAll(Carrera.values());
        cbxTipoParticipante.getItems().setAll(TipoParticipante.values());
        definirNombreColumnas();
        listarParticipante();
    }
    public void definirNombreColumnas(){
        dniCol = new TableColumn("DNI");
        nombreCol = new TableColumn("Nombre");
        apellidoCol = new TableColumn("Apellido");
        apellidoCol.setMinWidth(180);
        carreraCol = new TableColumn("Carrera");
        tipoParCol = new TableColumn("Tipo Participante");
        tipoParCol.setMinWidth(160);
        opcionCol = new TableColumn("Opcionen");
        tableView.getColumns().addAll(dniCol, nombreCol, apellidoCol, carreraCol, tipoParCol, opcionCol);

    }

    public void agregarAccionBotones(){
        Callback<TableColumn<Participante, Void>, TableCell<Participante, Void>> cellFactory =
                param -> new TableCell<>() {
            Button btnEditar = new Button("Editar");
            Button btnEliminar = new Button("Eliminar");
                {
                    btnEditar.setOnAction((event) -> {
                        Participante participante = getTableView().getItems().get(getIndex());
                        editarParticipante(participante, getIndex());
                    });
                    btnEliminar.setOnAction((event) -> {
                        Participante participante = getTableView().getItems().get(getIndex());
                        eliminarParticipante(participante.getDni());
                    });

                }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                }else{
                    HBox hBox = new HBox(btnEditar, btnEliminar);
                    hBox.setSpacing(10);
                    setGraphic(hBox);
                }
            }
                };
        opcionCol.setCellFactory(cellFactory);
    }

    public void editarParticipante(Participante p, int index){
        txtdni.setText(p.getDni());
        txtnombre.setText(p.getNombre());
        txtapellido.setText(p.getApellidos());
        cbxCarrera.getSelectionModel().select(p.getCarrera());
        cbxTipoParticipante.getSelectionModel().select(p.getTipoParticipante());
        indexE=index;
    }

    public void listarParticipante(){
        dniCol.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getDni())
        );
        nombreCol.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getNombre())
        );
        apellidoCol.setCellValueFactory(cellData ->

                new SimpleStringProperty(cellData.getValue().getApellidos())
                );
        carreraCol.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getCarrera().toString()));
        tipoParCol.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getTipoParticipante().toString()));
        agregarAccionBotones();
        participantes=FXCollections.observableArrayList(ps.findAll());
        tableView.setItems(participantes);
    }
    @FXML
    public void crearParticipante(){
        Participante participante = new Participante();
        participante.setDni(txtdni.getText());
        participante.setNombre(txtnombre.getText());
        participante.setApellidos(txtapellido.getText());
        participante.setCarrera(cbxCarrera.getValue());
        participante.setTipoParticipante(cbxTipoParticipante.getValue());
        participante.setEstado(true);
        if(indexE==-1){
            ps.save(participante);
        }else {
            ps.update(participante);
            indexE=-1;
        }
        LimpiarFormulario();
        listarParticipante();
    }
    public void LimpiarFormulario(){
        txtdni.setText("");
        txtnombre.setText("");
        txtapellido.setText("");
        cbxCarrera.getSelectionModel().clearSelection();
        cbxTipoParticipante.getSelectionModel().clearSelection();
    }
    public void eliminarParticipante(String dni){
        ps.delete(dni);
        listarParticipante();

    }

}
