package com.farmacia.model;

public class Receita {
    private int id;
    private String numeroReceita;
    private String nomeMedico;
    private String crm;
    private String medicamento;

    public Receita(int id, String numeroReceita, String nomeMedico, String crm, String medicamento) {
        this.id = id;
        this.numeroReceita = numeroReceita;
        this.nomeMedico = nomeMedico;
        this.crm = crm;
        this.medicamento = medicamento;
    }

    public int getId() {
    	return id;
    }
    public String getNumeroReceita() { 
    	return numeroReceita; }
    public String getNomeMedico() { 
    	return nomeMedico; }
    public String getCrm() { 
    	return crm; }
    public String getMedicamento() { 
    	return medicamento; }
}