package com.uady.operacionesconpolinomios;

import Modelo.ListaDoble;
import Modelo.Polinomio;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.VBox;
import javafx.embed.swing.SwingFXUtils;
import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PolinomiosController {
    public VBox polinomiosView;
    private ListaDoble lista = new ListaDoble();
    private ListaDoble listaDos = new ListaDoble();
    private Polinomio pol1 = new Polinomio();

    //Polinomio 1
    @FXML private TextField x6;
    @FXML private TextField x5;
    @FXML private TextField x4;
    @FXML private TextField x3;
    @FXML private TextField x2;
    @FXML private TextField x1;
    @FXML private TextField x0;

    //Polinomio 2
    @FXML private TextField y6;
    @FXML private TextField y5;
    @FXML private TextField y4;
    @FXML private TextField y3;
    @FXML private TextField y2;
    @FXML private TextField y1;
    @FXML private TextField y0;

    @FXML private ImageView result;

    public void guardarPolinomio(){

        String[] coeficientesX = {
                x6.getText(),
                x5.getText(),
                x4.getText(),
                x3.getText(),
                x2.getText(),
                x1.getText(),
                x0.getText()
        };

        String[] coeficientesY = {
                y6.getText(),
                y5.getText(),
                y4.getText(),
                y3.getText(),
                y2.getText(),
                y1.getText(),
                y0.getText()
        };

        copiarCoeficientes(coeficientesX, lista);
        copiarCoeficientes(coeficientesY, listaDos);
    }

    private void copiarCoeficientes(String[] coeficientes, ListaDoble lista) {
        int x = 6;
        for (int i = 0; i < coeficientes.length; i++) {
            String s = coeficientes[i];

            int coeficiente = Integer.parseInt( s.isBlank() ? "0" : s );

            lista.insertarInicio(new Polinomio( coeficiente, x-i ));
        }
    }

    public void onButtonClick(ActionEvent actionEvent){
        guardarPolinomio();
        ListaDoble listaDoble = pol1.sumarPolinomios(lista, listaDos,7);
        TeXFormula formula = new TeXFormula(listaDoble.imprimir());
        BufferedImage image = (BufferedImage) formula.createBufferedImage(TeXConstants.STYLE_DISPLAY,200, Color.BLACK, Color.WHITE);
        result.setImage(SwingFXUtils.toFXImage(image, null));
    }

    public void botonResta(ActionEvent actionEvent) {
        pol1.restarPolinomios(lista, listaDos,7);

    }

    public void botonMultiplicar(ActionEvent actionEvent) {
        pol1.multiplicarPolinomios(lista, listaDos);

    }

    public void botonEscalar(ActionEvent actionEvent) {
        pol1.multiplicarEscalarPolinomios(lista, 7);

    }

    public void botonLimpiar(ActionEvent actionEvent) {
       for(int i=0; i< lista.getCantidadElementos(); i++) {
           lista.eliminarInicio();
       }

        for(int i=0; i< listaDos.getCantidadElementos(); i++) {
            listaDos.eliminarInicio();
        }

        x1.setText("0");
        x2.setText("0");
        x3.setText("0");
        x4.setText("0");
        x5.setText("0");
        x6.setText("0");
        x0.setText("0");
        y1.setText("0");
        y2.setText("0");
        y3.setText("0");
        y4.setText("0");
        y5.setText("0");
        y6.setText("0");
        y0.setText("0");

    }
}