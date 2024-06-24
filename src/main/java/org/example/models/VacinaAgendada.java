package org.example.models;

import java.time.LocalDate;

public class VacinaAgendada {
    private long id;
    private Idoso idoso;
    private Vacina vacina;
    private LocalDate dataAgendamento;

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

    public Vacina getVacina() {
        return vacina;
    }

    public void setVacina(Vacina vacina) {
        this.vacina = vacina;
    }

    public LocalDate getDataAgendamento() {
        return dataAgendamento;
    }

    public void setDataAgendamento(LocalDate dataAgendamento) {
        this.dataAgendamento = dataAgendamento;
    }
}
