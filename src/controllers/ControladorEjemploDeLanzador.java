package controllers;

import java.sql.SQLException;
import java.util.HashMap;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperPrint;

import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;


import javafx.scene.control.ToggleGroup;

public class ControladorEjemploDeLanzador {

    @FXML
    private ToggleGroup selects;

    @FXML
    void abrirInforme(ActionEvent event) throws SQLException {
    	ConnectionDB con = new ConnectionDB();
    	
    	// Si no neceistamos parametros
    	//HashMap<String, Object> parameters = new HashMap<String, Object>();
        //parameters.put("codigo", 5);
        //parameters.put("nombre", "xxxx");
    	
    	//llamada con parametros y bbdd
    	//JasperReport report = (JasperReport) JRLoader.loadObject(getClass().getResource("/reports/informe.jasper"));
		try {
			
			JasperReport report = null;
			switch ( ((RadioButton) selects.getSelectedToggle()).getText()) {
				case "Informe personas":
					report = (JasperReport) JRLoader.loadObject(getClass().getResource("personas.jasper"));
					break;
				case "Informe personas con calculos":
					report = (JasperReport) JRLoader.loadObject(getClass().getResource("personas1.jasper"));
					break;
				case "informe personas con subinformes":
					report = (JasperReport) JRLoader.loadObject(getClass().getResource("personas2.jasper"));
					break;
			}
			
	        JasperPrint jprint = JasperFillManager.fillReport(report, null, con.getConexion());
	        JasperViewer viewer = new JasperViewer(jprint, false);
	        viewer.setVisible(true);
		} catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("ERROR");
            alert.setContentText("Ha ocurrido un error");
            alert.showAndWait();
            e.printStackTrace();
        }
        

    }
    

    @FXML
    void cerrar(ActionEvent event) {
    	Platform.exit();
    }

}
