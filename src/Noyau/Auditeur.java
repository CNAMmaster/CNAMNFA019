package Noyau;

import java.io.Serializable;
import java.util.Date;

import IHM.AuditeurIHM;

//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : Auditeur.java
//  @ Date : 13/11/2012
//  @ Author : 
//
//

public class Auditeur extends Personne implements Serializable{
	private String niveau;
	private String code;
	private Date dateInscription;
	private String ine;
	

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getDateInscription() {
		return dateInscription;
	}

	public void setDateInscription(Date dateInscription) {
		this.dateInscription = dateInscription;
	}

	public String getIne() {
		return ine;
	}

	public void setIne(String ine) {
		this.ine = ine;
	}

	public void setNiveau(String niveau) {
		this.niveau = niveau;
	}

	public String getNiveau() {
		return niveau;
	}
	
	
}