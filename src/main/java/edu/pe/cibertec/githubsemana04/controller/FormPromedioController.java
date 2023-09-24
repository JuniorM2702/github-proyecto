package edu.pe.cibertec.githubsemana04.controller;

import edu.pe.cibertec.githubsemana04.model.PromedioModel;
import edu.pe.cibertec.githubsemana04.model.imcModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FormPromedioController {
    @GetMapping("/calcularimc")
    public String index(Model model){
        model.addAttribute("imcmodel", new imcModel());
        model.addAttribute("verresultado", true);
        return "forimc";
    }

    @PostMapping("/calcularimc")
    public String calcularImc(@ModelAttribute("imcmodel") imcModel imcModel,
                              Model model){

        Double tallam = imcModel.getTalla() / 100;
        Double valorimc = imcModel.getPeso() / (tallam * tallam);
        String condicion = "";
        String coloralert = "alert-danger";

        if(valorimc <= 18.5){
            condicion = "Por debajo del peso";
            coloralert = "alert-dark";
        } else if (valorimc <= 25){
            condicion = "Con peso normal";
            coloralert = "alert-primary";
        } else if (valorimc <= 30) {
            condicion = "Con Sobrepreso";
            coloralert = "alert-warning";
        } else if (valorimc <= 35){
            condicion = "Con obesidad leve";
        } else if (valorimc <= 39){
            condicion = "Con obesidad media";
        } else {
            condicion = "Con obesidad mórbida";
        }
        model.addAttribute("verresultado", true);
        model.addAttribute("resultado",
                "Su valor de IMC: " +
                        String.format("%.2f", valorimc) +
                ", usted se encuentra: " + condicion);
        model.addAttribute("coloralert", coloralert);

        return "forimc";
    }
}
