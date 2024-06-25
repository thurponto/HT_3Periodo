package org.example.models;

import java.util.List;

public class Historico {
    private long id;
    private Idoso idoso;
    private Agenda agenda;
    private Vacina vacina;
    private String alergias;
    private String condicoesMedicas;
    private String observacoes;
    private List<Vacina> vacinasNecessarias;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Idoso getIdoso() {
        return idoso;
    }

    public void setIdoso(Idoso idoso) {
        this.idoso = idoso;
    }

    public Agenda getAgenda() {
        return agenda;
    }

    public void setAgenda(Agenda agenda) {
        this.agenda = agenda;
    }

    public Vacina getVacina() {
        return vacina;
    }

    public void setVacina(Vacina vacina) {
        this.vacina = vacina;
    }

    public String getAlergias() {
        return alergias;
    }

    public void setAlergias(String alergias) {
        this.alergias = alergias;
    }

    public String getCondicoesMedicas() {
        return condicoesMedicas;
    }

    public void setCondicoesMedicas(String condicoesMedicas) {
        this.condicoesMedicas = condicoesMedicas;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public List<Vacina> getVacinasNecessarias() {
        return vacinasNecessarias;
    }

    public void setVacinasNecessarias(List<Vacina> vacinasNecessarias) {
        this.vacinasNecessarias = vacinasNecessarias;
    }

    @Override
    public String toString() {
        return "Id: " + id + ", Idoso: " + idoso.getNome() + ", Agenda: " + agenda.getId() + ", Vacina: " + vacina.getNome() +
                ", Alergias: " + alergias + ", Condições Médicas: " + condicoesMedicas + ", Observações: " + observacoes;
    }
}
