package IHM;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Label;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.LineBorder;

import Noyau.Auditeur;
import Noyau.Utilites;

import com.toedter.calendar.JDateChooser;



public class AuditeurIHM extends JFrame implements ActionListener,FocusListener {
	private static final long serialVersionUID = 8658243714972444294L;
	JButton valider;
	JButton annuler;
	private JTextField nomF,prenomF,adresseF,lieuF, codeF ;
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	static Image image; 
	JFormattedTextField mailF;
	JDateChooser datenaissF;
	JDateChooser jdc;
	private JTextField dateF;
	private JButton loadPhoto;
	private File fichPhoto;

	public AuditeurIHM(){
		super();
		
		setSize(400, 300);
		setTitle("GestionCNAM");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setResizable(false);
		init();
		setVisible(true);
	}

	/*
	 * c'est une méthode qui place les composants en fonction
	 * de la ligne et colonne
	 */
	public void placerComposant(int ligne, int colonne, Component c){
		// position des composant x, y, largeur et hauteur
		int x0=5,y0=5,l=100,h=20;
		int x1=(colonne-1)*l+x0, y1=(ligne -1)*h+y0;
		c.setBounds(x1, y1, l, h);
	}

	/**
	 * cette méthode est utilisée pour vérifier une adresse email
	 * exemple :
	 *  louis@poste.fr est correct
	 *  ererer@azaza.a n'est pas correct
	 * 
	 * @param mail : represente l'adresse email en chaine de caracteres
	 * @return retour : boolean pour signaler si le paramètre en entrée est un
	 * email de format .+@.+\\.[a-z]{2,4} 
	 *
	 */
	public boolean verifierEmail(String mail){	
		String mailformat = ".+@.+\\.[a-zA-Z]{2,4}"; // exemple louis@poste.fr
		return Pattern.compile(mailformat).matcher(mail).matches();
	}

	/**
	 * cette méthode est utilisée pour initialiser les
	 * composants graphiques
	 * @author CNAM
	 * 
	 */
	public void init(){
		
		// Cette partie pour modifier le style d'affichage des fenetres
		//Windows com.sun.java.swing.plaf.windows.WindowsLookAndFeel
		//Motif com.sun.java.swing.plaf.motif.MotifLookAndFeel
		// Metal javax.swing.plaf.metal.MetalLookAndFeel (par défaut)
		// Nimbus com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel
		// GTK com.sun.java.swing.plaf.gtk.GTKLookAndFeel
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
		}
		catch (ClassNotFoundException e) {}
		catch (InstantiationException e) {}
		catch (IllegalAccessException e) {} 
		catch (UnsupportedLookAndFeelException e) {} 
		SwingUtilities.updateComponentTreeUI(this);
		
		//Niveau 0 :  fenetre
		setLayout(new BorderLayout());
		//Niveau 1
		JPanel pda = new JPanel();
		pda.setLayout(new GridLayout(7, 2));
		JPanel pdb = new JPanel();
		pdb.setLayout(new GridLayout(2, 1)); 
		//Niveau 2
		JPanel pdb1 = new JPanel();
		pdb1.setLayout(new GridLayout(3, 2));
		
		try {
			image = ImageIO.read(new File("src\\resource\\img.bmp"));
		} catch (IOException e) {}

		JPanel pdb3 = new JPanel();
		pdb3.setLayout(null);
		
		JPanel pdb2 = new JPanel(){		
			public void paintComponent(Graphics g) {
			      super.paintComponent(g);
			      g.drawImage(image, 0, 0, this);
			  }
		};
		 pdb2.setBounds(1, 50, 200, 200);
		
		//new GridLayout(7, 2));
		// Nom de l'auditeur
		Label nomL = new Label(" Nom : ");
		//placerComposant(1,1, nomL); 
		pda.add(nomL);
		nomF = new JTextField("");
		//placerComposant(1,2, nomF);
		pda.add(nomF);
		// Prenom de l'auditeur
		Label prenomL = new Label(" Prenom : ");
		//placerComposant(2,1, prenomL);
		pda.add(prenomL);
		prenomF = new JTextField("");
		//placerComposant(2,2, prenomF); 
		pda.add(prenomF);
		// date de naissance de l'auditeur
		Label datenaissL = new Label(" Date Naiss : ");
		//placerComposant(3,1,datenaissL);
		pda.add(datenaissL);
		//datenaissF=null;
		//datenaissF = new JFormattedTextField(new SimpleDateFormat("dd/MM/yyyy")); //new MaskFormatter("##/##/####"));		
		//datenaissF.addFocusListener(this);
		//placerComposant(3,2, datenaissF);
		//pda.add(datenaissF);
		// nouveau objet : JDateChooser
		datenaissF = new JDateChooser();
		pda.add(datenaissF);
		// adresse de l'auditeur
		JLabel adresseL = new JLabel(" Adresse : ");
		//placerComposant(4,1, adresseL);
		pda.add(adresseL);
		adresseF = new JTextField("");
		//placerComposant(4,2, adresseF);
		pda.add(adresseF);
		// lieu de naissance de l'auditeur
		Label lieuL = new Label(" Lieu de Naiss : ");
		//placerComposant(5,1, lieuL);
		pda.add(lieuL);
		lieuF= new JTextField("");
		//placerComposant(5,2, lieuF);
		pda.add(lieuF);
		// email de l'auditeur
		Label mailL = new Label(" Email : ");
		//placerComposant(6,1, mailL);
		pda.add(mailL);
		mailF = new JFormattedTextField() ; //new MaskFormatter(""));
		//placerComposant(6,2, mailF);
		pda.add(mailF);
		// les boutons valider et annuler
		valider = new JButton();
		valider.setText("Valider");
		//placerComposant(7,1, valider);
		annuler = new JButton();
		annuler.setText("Fermer");
		//placerComposant(7,2,annuler);
		//gestion des evenements
		valider.addActionListener(this);
		annuler.addActionListener(this);
		pda.add(valider);
		pda.add(annuler);	
		
		loadPhoto = new JButton();
		loadPhoto.setText("Charger Photo");

		//INE
		JLabel ineL = new JLabel(" n° INE : ");
		pdb1.add(ineL);
		JLabel ineF = new JLabel("          ");
		ineF.setOpaque(true);
		ineF.setBackground(Color.YELLOW);
		ineF.setBorder(new LineBorder(Color.BLACK,2));
		pdb1.add(ineF);
		//code
		JLabel codeL = new JLabel(" Code : ");
		pdb1.add(codeL);
		codeF = new JTextField("") ;
		codeF.setEnabled(false);
		pdb1.add(codeF);
		//date inscription
		JLabel dateL = new JLabel(" Date inscrip. : ");
		pdb1.add(dateL);
		Calendar c = Calendar.getInstance();
		String datejour=""+ c.get(Calendar.DAY_OF_MONTH)+"/"+(c.get(Calendar.MONTH)+1) +"/"+ c.get(Calendar.YEAR);		
		dateF = new JTextField(""+datejour);
		dateF.setEnabled(false);
		dateF.setForeground(Color.RED);
		pdb1.add(dateF);
		loadPhoto.setBounds(1, 1, 130, 30);
		loadPhoto.addActionListener(this);
		pdb3.add(loadPhoto);
		pdb3.add(pdb2);
		
		pdb.add(pdb1);
		pdb.add(pdb3,BorderLayout.CENTER);
		add(pda, BorderLayout.WEST);
		add(pdb, BorderLayout.EAST);
		
	}

	public void actionPerformed(ActionEvent evt) {
		if (evt.getSource().equals(annuler)){
			dispose(); // Libérer les ressources 
			setVisible(false);
		}else if (evt.getSource().equals(valider)){
			if (! verifierEmail(mailF.getText())){
				JOptionPane.showMessageDialog(this, "Erreur sur adresse email ! " ,"INSCRIPTION :", JOptionPane.ERROR_MESSAGE);
			}else{
				String info = "nom = "+ nomF.getText() +"  Prénom = "+prenomF.getText()+ " adresse="+ adresseF.getText();
				info += " email=" + mailF.getText()+ " dateNaiss="+ datenaissF.getDate();				
				//JOptionPane.showMessageDialog(this, "message : "+info ,"INSCRIPTION :", JOptionPane.INFORMATION_MESSAGE);
				
				Auditeur nouvA = new Auditeur();
				nouvA.setNom(nomF.getText());
				nouvA.setPrenom(prenomF.getText());
				nouvA.setDateNaissance(datenaissF.getDate());
				
				Boolean ajout = GestionCNAMIHM.gestion.getListeAuditeurs().addAuditeur(nouvA);
			
				if (ajout){
					JOptionPane.showMessageDialog(this,"Auditeur ajouté", "INFO", JOptionPane.INFORMATION_MESSAGE );
				}else{
					JOptionPane.showMessageDialog(this,"Auditeur exite !!!!!", "ERREUR", JOptionPane.ERROR_MESSAGE );
				}
				//sauvegarde automatique dans le fichier Objets BDD.txt 
				Utilites.save();
			}
		}else if (evt.getSource().equals(loadPhoto)){
			JFileChooser jfc = new JFileChooser();
			jfc.showOpenDialog(this);
			fichPhoto = jfc.getSelectedFile();			
			try {
				image = ImageIO.read(fichPhoto);
				image.flush();
			} catch (IOException e) {}
			
		}
	}

	public static void main(String[] arg){
		new AuditeurIHM();
	}

	@Override
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void focusLost(FocusEvent arg0) {
		
		/*if (arg0.getSource().equals(datenaissF)){
						
			Calendar date = Calendar.getInstance();
			
			try {
				date.setTime(sdf.parse(datenaissF.getText()));
			} catch (ParseException e) {
				e.printStackTrace();
			}

			System.out.println(date.getTime());
			
			if ((datenaissF.getText().equals("")) || (datenaissF.getText().isEmpty()) ){
				datenaissF.setBackground(Color.RED);
				
			}
			else{
				datenaissF.setBackground(Color.WHITE);
			}
		}*/
		
	}
}
