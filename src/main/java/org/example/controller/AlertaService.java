package org.example.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AlertaService {

    public String[] listarAlertas() {
        return new String[]{
                "Alerta 1: Vacinação pendente",
                "Alerta 2: Visita agendada para amanhã"
        };
    }

    public String obterAlertaVacina(LocalDate dataVacina) {
        LocalDate dataAtual = LocalDate.now();
        if (dataVacina.isEqual(dataAtual)) {
            return "Hoje é o dia da sua vacinação!";
        } else if (dataVacina.isAfter(dataAtual)) {
            return "Sua vacinação está agendada para o dia " + dataVacina.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } else {
            return "Sua vacinação já passou.";
        }
    }
}
