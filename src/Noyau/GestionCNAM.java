package Noyau;

public class GestionCNAM {
	private static ListeAuditeurs  listeAuditeurs= new ListeAuditeurs();

	
	public GestionCNAM(){
		ListeAuditeurs la=Utilites.restore();
		if (la != null)	listeAuditeurs = la;
	}
	
	public static ListeAuditeurs getListeAuditeurs() {
		return listeAuditeurs;
	}

	public static void setListeAuditeurs(ListeAuditeurs listeAuditeurs) {
		GestionCNAM.listeAuditeurs = listeAuditeurs;
	}
		
	
}
