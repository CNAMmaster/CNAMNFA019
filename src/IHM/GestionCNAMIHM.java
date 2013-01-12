package IHM;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.text.Utilities;

import org.dyno.visual.swing.layouts.GroupLayout;

import Noyau.ListeAuditeurs;
import Noyau.Utilites;
  
public class GestionCNAMIHM extends JFrame implements ActionListener, WindowListener{

	private static final long serialVersionUID = 1L;
	private static final String PREFERRED_LOOK_AND_FEEL = null;
	static JButton gestionAuditeursB = new JButton("Ajouter Auditeurs");
	static JButton listeAuditeursB = new JButton("Lister Auditeurs");
	static Noyau.GestionCNAM gestion = new Noyau.GestionCNAM(); 
	
	public GestionCNAMIHM() {
		initComponents();
	}

	private void initComponents() {
		setLayout(null);
		setSize(320, 240);
	}


	/**
	 * Main entry of the class.
	 * Note: This class is only created so that you can easily preview the result at runtime.
	 * It is not expected to be managed by the designer.
	 * You can modify it as you like.
	 */
	public static void main(String[] args) {
		
		GestionCNAMIHM frame = new GestionCNAMIHM();
		gestionAuditeursB.addActionListener(frame);
		listeAuditeursB.addActionListener(frame);
		frame.addWindowListener(frame);
	
		
		frame.setDefaultCloseOperation(GestionCNAMIHM.EXIT_ON_CLOSE);
		frame.setTitle("GestionCNAM");
		frame.getContentPane().setPreferredSize(frame.getSize());
		frame.pack();
		frame.setLocationRelativeTo(null);
		gestionAuditeursB.setBounds(1, 1, 160, 40);
		listeAuditeursB.setBounds(1, 45, 160, 40);
		frame.add(gestionAuditeursB);
		frame.add(listeAuditeursB);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(gestionAuditeursB)){
			new AuditeurIHM();
		}else if (e.getSource().equals(listeAuditeursB)){
			
			ListeAuditeurs listeaud = gestion.getListeAuditeurs();
			String message="";
			for (int i=0; i< listeaud.liste.size();i++){
				message+= listeaud.liste.get(i).getNom() +" "+
				listeaud.liste.get(i).getPrenom()+ " "+
				listeaud.liste.get(i).getDateNaissance()+"\n";
			}
			
			JOptionPane.showMessageDialog(this, message, "liste des auditeurs",JOptionPane.INFORMATION_MESSAGE);
		}
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		//Utilites.save();
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		Utilites.save();
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		Utilites.save();
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

}
