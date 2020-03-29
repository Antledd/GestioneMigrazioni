package it.polito.tdp.borders;
/*
 * Come procedere nell'interfaccia:
1) scrivere l'anno nella casella Anno
2) premere il bottone Calcola Confini
3) cliccare sulla freccia in giù della ComboText e scegliere una nazione
4) premere il bottone Simula

L'esito sarebbe che delle 1000 persone emigranti,
intorno alle 500 si fermerebbero nella nazione scelta 
e il resto si distribuirebbe via via in ordine di 
vicinanza delle altre nazioni, in numero decrescente 
via via che ci si allontana dalla nazione di arrivo iniziale.
N.B.:
Nel database figurano anche i nomi antichi degli Stati e ciò sarebbe da aggiornare a seconda dell'Anno (per esempio: nel 2000 non sarebbe concepibile Austria-Hungary, oppure il ducato di Modena o il regno delle Due Sicilie ecc. ecc..
 */
import it.polito.tdp.borders.model.Model;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Borders.fxml")) ;
			BorderPane root = (BorderPane)loader.load();
			BordersController controller = loader.getController() ;
			Model model = new Model() ;
			controller.setModel(model) ;
			Scene scene = new Scene(root);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
