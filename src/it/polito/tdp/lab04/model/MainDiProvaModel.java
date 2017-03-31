package it.polito.tdp.lab04.model;

public class MainDiProvaModel {

	public static void main(String[] args) {
		
		Model m = new Model();
		
		for(Corso c : m.getCorsi()){
			System.out.println(c.getCodins());
		}

	}

}
