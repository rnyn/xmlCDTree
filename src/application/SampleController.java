package application;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TreeView;
import javafx.scene.control.Alert.AlertType;

public class SampleController {

	@FXML private TreeView<String> cdTreeView;
	
	
	@FXML
	void initialize()  {
		System.out.println("init...");
		
		try {
			cdTreeView.setRoot(CDTreeCreator.createTree("cd-catalog.xml"));
		} catch (CDTreeCreatorException e) {
			Alert alert = new Alert(AlertType.ERROR);
			
			alert.setContentText("XML fehlerhaft oder nicht gefunden! Fehler: "+e.getMessage() );
			alert.showAndWait();
			Platform.exit();
		}
	}
	
}
