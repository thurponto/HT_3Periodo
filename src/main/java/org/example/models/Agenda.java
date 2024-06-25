package org.example.models;

import java.util.Date;

public class Agenda {
    private long id;
    private AgenteSaude agenteSaude;
    private Idoso idoso;
    private Date data;
    private String horario;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public AgenteSaude getAgenteSaude() {
        return agenteSaude;
    }

    public void setAgenteSaude(AgenteSaude agenteSaude) {
        this.agenteSaude = agenteSaude;
    }

    public Idoso getIdoso() {
        return idoso;
    }

    public void setIdoso(Idoso idoso) {
        this.idoso = idoso;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    @Override
    public String toString() {
        return "Agenda{" +
                "id=" + id +
                ", agenteSaude=" + agenteSaude.getNome() + // ou qualquer outro campo que deseje exibir
                ", idoso=" + idoso.getNome() + // ou qualquer outro campo que deseje exibir
                ", data=" + data +
                ", horario='" + horario + '\'' +
                '}';
    }
}
