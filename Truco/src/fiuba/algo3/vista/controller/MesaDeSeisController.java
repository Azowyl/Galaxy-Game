package fiuba.algo3.vista.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import fiuba.algo3.modelo.Equipo;
import fiuba.algo3.modelo.Mano;
import fiuba.algo3.vista.controller.handler.CartaDeSeisHandler;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MesaDeSeisController extends MesaGeneralController{

	@FXML
	private ImageView carta1Jug1;
	@FXML
	private ImageView carta2Jug1;
	@FXML
	private ImageView carta3Jug1;
	@FXML
	private ImageView carta1Jug2;
	@FXML
	private ImageView carta2Jug2;
	@FXML
	private ImageView carta3Jug2;
	@FXML
	private ImageView carta1Jug3;
	@FXML
	private ImageView carta2Jug3;
	@FXML
	private ImageView carta3Jug3;
	@FXML
	private ImageView carta1Jug4;
	@FXML
	private ImageView carta2Jug4;
	@FXML
	private ImageView carta3Jug4;
	@FXML
	private ImageView carta1Jug5;
	@FXML
	private ImageView carta2Jug5;
	@FXML
	private ImageView carta3Jug5;
	@FXML
	private ImageView carta1Jug6;
	@FXML
	private ImageView carta2Jug6;
	@FXML
	private ImageView carta3Jug6;
	
	@FXML
	private ImageView contenedor1;
	@FXML
	private ImageView contenedor2;
	@FXML
	private ImageView contenedor3;
	@FXML
	private ImageView contenedor4;
	@FXML
	private ImageView contenedor5;
	@FXML
	private ImageView contenedor6;
	
	@Override
	public void initialize(URL url, ResourceBundle resources) {
		cartasJugando = new ArrayList<List<ImageView>>(Arrays.asList(
				new ArrayList<>(Arrays.asList(carta1Jug1, carta2Jug1, carta3Jug1)),
				new ArrayList<>(Arrays.asList(carta1Jug2,carta2Jug2,carta3Jug2)),
				new ArrayList<>(Arrays.asList(carta1Jug3,carta2Jug3,carta3Jug3)),
				new ArrayList<>(Arrays.asList(carta1Jug4,carta2Jug4,carta3Jug4)),
				new ArrayList<>(Arrays.asList(carta1Jug5,carta2Jug5,carta3Jug5)),
				new ArrayList<>(Arrays.asList(carta1Jug6,carta2Jug6,carta3Jug6))));
				inicializarBotones();
		contenedores = new ArrayList<ImageView>(Arrays.asList(contenedor1,contenedor2,contenedor3,contenedor4,contenedor5,contenedor6));
		
		prepararJuego();
	}

	public static void refrescarContenedores() {
		for(ImageView contenedor : contenedores) {
			contenedor.setImage(null);
		}
	}
	
	@Override
	protected void prepararMesa() {

	}
	
	@Override
	protected void setImageViewCartaHandlerYListener() {
		List<Mano> manos = obtenerManosIntercaladas();
		int i = 0;
		for(List<ImageView> cartasEnMano : cartasJugando) {
			int j = 0;
			for(ImageView carta : cartasEnMano) {
				carta.setOnMouseClicked(new CartaDeSeisHandler(cartasEnMano,cartasJugando,manos.get(i).getCartas().get(j),lblPuntosEq1,lblPuntosEq2));
				((CartaDeSeisHandler)carta.getOnMouseClicked()).setContenedorEnMesa(contenedores.get(i));
				carta.setDisable(true);
				j++;
			}
			i++;
		}
		for (ImageView contenedor : contenedores) {
			contenedor.imageProperty().addListener(new ChangeListener<Image>() {
				@Override
				public void changed(ObservableValue<? extends Image> observable, Image oldValue, Image newValue) {
					if(juegoTruco.manoFinalizada()) {
						restablecerContenedores();
					}
				}

			});
		}
	}

	@Override
	protected void mostrarCartas() {
		List<Equipo> equipos = mesa.getEquipos();
		List<ImageView> vistaCartas = cartasJugando.get(0);
		plasmarCartaEnImageView(equipos.get(0).getJugadores().get(0).getMano(),vistaCartas);
	}
}
