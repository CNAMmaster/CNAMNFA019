package Noyau;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Utilites {

	static public void save(){		
		try{
			ObjectOutputStream sortie= new ObjectOutputStream(new FileOutputStream("bdd.txt"));
			sortie.writeObject(GestionCNAM.getListeAuditeurs());
			sortie.close();
		}catch(Exception e){}
	}
	

	static public ListeAuditeurs restore(){
		ListeAuditeurs la=null;
		try {
			ObjectInputStream entree= new ObjectInputStream(new FileInputStream("bdd.txt"));
			la = (ListeAuditeurs)entree.readObject();
			entree.close();
			
		}catch(Exception e){}
		return la;
	}
}

