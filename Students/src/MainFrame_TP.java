import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.plaf.DimensionUIResource;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.StringTokenizer;
import java.util.Vector;


public class MainFrame_TP extends JFrame  {
	DefaultMutableTreeNode racine,Class1,Class2 ;
	JLabel lb_first,lb_last,lb_mail,lb_class,lb_addre,lb_num_fix,_lb_num,lb_niveau,af_first,af_last,af_mail,af_class,af_addre,af_num_fix,_af_num,af_niveau,notes_nom,notes_mail;
	JTextField tf_first,tf_last,tf_mail,tf_class,tf_addr,tf_numm_fix,_tf_num,tf_niveau,tf_sec,tf_reseau,tf_java,tf_android,tf_sgbd,tf_math,tf_francias,tf_php,tf_anglais,tf_pfe;
	JPanel pan,tabop,affich,BottomPan,form;
	JTree tree;
	DefaultTableModel dtm;
	JTable table_etud;
	JButton ok,Supp;
	JMenuBar bar;
	JScrollPane tscroll;
	String[] columns= {"Prenom","Nom","Email"};
	String[] columns1= {"Classe","Nombre Etudiants"};
	File f1=new File("FSM.txt");
	String infos_etud[]=new String[9];

public MainFrame_TP () {

	JFrame fen = new JFrame ("Formulaire");
	fen.setLayout(new BorderLayout());
	
	//new Connexion().createdatabase();
	CreateBar();
	
	pan = new JPanel();
	//pan.setPreferredSize(new DimensionUIResource(500, 200));
	pan.setLayout(new GridBagLayout());
	JTabbedPane tabPan=new JTabbedPane();
	 tabop =new JPanel();
	 tabop.setLayout(new BorderLayout());
	 tabPan.add("Etudiants",pan);
	 tabPan.add("Notes",tabop);
	CreateForm();
	
	
	racine= new DefaultMutableTreeNode("FSM");
	tree=new JTree(racine);
	table_etud=new JTable();
	dtm=(DefaultTableModel) table_etud.getModel();
	
	table_etud.setModel(dtm);
	tscroll=new JScrollPane(table_etud);
	tscroll.setPreferredSize(new DimensionUIResource(200, 400));
	
	
	
	Ouvrir();
	ok.addActionListener((ActionListener) new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		
			
			DefaultTreeModel treemodel	=(DefaultTreeModel) tree.getModel();
			DefaultMutableTreeNode racine=(DefaultMutableTreeNode) treemodel.getRoot();
			DefaultMutableTreeNode Etudiant=new DefaultMutableTreeNode(new InfoEtudiant(tf_first.getText(),tf_last.getText(),tf_mail.getText(),tf_addr.getText(),tf_class.getText(),tf_numm_fix.getText(),_tf_num.getText(),tf_niveau.getText()));
			System.out.println((tf_class.getText()+tf_niveau.getText()));
			if ((tf_class.getText()+tf_niveau.getText()).equals("MPISI1")){
				
				String new_nom=tf_first.getText();
				char[] lettres=new_nom.toCharArray();
				
				for (int i=0;i<Class1.getChildCount();i++) {
					DefaultMutableTreeNode node= (DefaultMutableTreeNode) Class1.getChildAt(i);
					InfoEtudiant etud=(InfoEtudiant) node.getUserObject();
			
					String tree_nom=etud.Prenom;
					char[] lettre_tree=tree_nom.toCharArray();
					char p1,p2;
					p1=Character.toUpperCase(lettres[0]);
					p2=Character.toUpperCase(lettre_tree[0]);
					int compare=Character.compare(p2,p1);
					
					System.out.println(tf_first.getText()+" "+etud.Prenom);
					System.out.println(p1+" "+p2);
					System.out.println(compare);
					if (compare<=0) {
						
						Class1.insert(Etudiant, i);
						treemodel.reload();
						DefaultMutableTreeNode NewNode=(DefaultMutableTreeNode)Class1.getChildAt(i);
						tree.setSelectionPath(new TreePath(NewNode.getPath()));
						tree.expandPath(new TreePath(node.getPath()));
						
					}
				
		
				
					
					
				}
				
				
			}
			else if((tf_class.getText()+tf_niveau.getText()).equals("MPISI2")) {
				String new_nom=tf_first.getText();
				char[] lettres=new_nom.toCharArray();
				
				for (int i=0;i<Class2.getChildCount();i++) {
					DefaultMutableTreeNode node= (DefaultMutableTreeNode) Class2.getChildAt(i);
					InfoEtudiant etud=(InfoEtudiant) node.getUserObject();
			
					String tree_nom=etud.Prenom;
					char[] lettre_tree=tree_nom.toCharArray();
					char p1,p2;
					p1=Character.toUpperCase(lettres[0]);
					p2=Character.toUpperCase(lettre_tree[0]);
					int compare=Character.compare(p2,p1);
					
					System.out.println(tf_first.getText()+" "+etud.Prenom);
					System.out.println(p1+" "+p2);
					System.out.println(compare);
					if (compare<=0) {
						
						Class2.insert(Etudiant, i);
						treemodel.reload();
						DefaultMutableTreeNode NewNode=(DefaultMutableTreeNode)Class2.getChildAt(i);
						tree.setSelectionPath(new TreePath(NewNode.getPath()));
						tree.expandPath(new TreePath(node.getPath()));
						
					}
				
		
				
					
					
				}
			
			}else {
				DefaultMutableTreeNode Class=new DefaultMutableTreeNode((tf_class.getText()+tf_niveau.getText()));
				racine.add(Class);
				String new_nom=tf_first.getText();
				char[] lettres=new_nom.toCharArray();
				
				for (int i=0;i<Class.getChildCount();i++) {
					DefaultMutableTreeNode node= (DefaultMutableTreeNode) Class.getChildAt(i);
					InfoEtudiant etud=(InfoEtudiant) node.getUserObject();
			
					String tree_nom=etud.Prenom;
					char[] lettre_tree=tree_nom.toCharArray();
					char p1,p2;
					p1=Character.toUpperCase(lettres[0]);
					p2=Character.toUpperCase(lettre_tree[0]);
					int compare=Character.compare(p2,p1);
					
					System.out.println(tf_first.getText()+" "+etud.Prenom);
					System.out.println(p1+" "+p2);
					System.out.println(compare);
					if (compare<=0) {
						
						Class.insert(Etudiant, i);
						treemodel.reload();
						DefaultMutableTreeNode NewNode=(DefaultMutableTreeNode)Class.getChildAt(i);
						tree.setSelectionPath(new TreePath(NewNode.getPath()));
						tree.expandPath(new TreePath(node.getPath()));
						
					}
				
		
				
					
					
				}
			}
			
			
			
			JOptionPane.showMessageDialog(pan, "Ajout avec Succès");
			
		}
		
	});
	Supp.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			DefaultTreeModel treemodel = (DefaultTreeModel) tree.getModel();
			DefaultMutableTreeNode racine =(DefaultMutableTreeNode) treemodel.getRoot();
			racine.removeAllChildren();
			treemodel.reload();
			
		}
	});
	TreeSelectionListener arbreListener = new TreeSelectionListener() {
		

		public void valueChanged(TreeSelectionEvent e) {
			DefaultMutableTreeNode node =(DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
			if (node==null) {
				return;
			}
		
			if (node.isLeaf()) {
				
		  InfoEtudiant etud=(InfoEtudiant) node.getUserObject();
		  pan.remove(tscroll);
		  pan.remove(affich);
		  GridBagConstraints c=new GridBagConstraints();
		  c.fill = GridBagConstraints.HORIZONTAL;
		  c.gridx= 1;
		  c.gridy= 1;
		  c.gridwidth=1;
		  pan.add(affich,c);
		  fen.repaint();
		  fen.validate();
		  vider();
			af_last.setText("Prénom :"+etud.Prenom);
				af_first.setText("Nom :"+etud.Nom);
				af_mail.setText("Mail :"+etud.Email);
				af_addre.setText("Adresse :"+etud.NumP);
				af_class.setText("Class :"+etud.NumF);
				af_num_fix.setText("Fix :"+etud.Classe);
				_af_num.setText("Portable :"+etud.Adresse);
				af_niveau.setText(" Niveau :"+etud.Niv);
				notes_nom.setText(etud.Nom+" "+etud.Prenom);
				notes_mail.setText(etud.Email);
				
				afficherNotesEtudiant(etud.Nom,etud.Email);
			//	tabPan.setSelectedIndex(1);
			
				
		
			
		}else
		{
			pan.remove(tscroll);
			
			pan.remove(affich);
			dtm.setRowCount(0);
			if (node.getUserObject().toString().equals("FSM")) {
				for (int i=0;i<node.getChildCount();i++) {
					DefaultMutableTreeNode class1 =(DefaultMutableTreeNode) node.getChildAt(i);
					
					Vector v = new Vector();
					
					v.add(class1.getUserObject().toString());
					v.add(class1.getChildCount());
					
					dtm.setColumnIdentifiers(columns1);
					dtm.addRow(v);
				}
				
			}else {
			for (int i=0;i<node.getChildCount();i++) {
				DefaultMutableTreeNode leaf =(DefaultMutableTreeNode) node.getChildAt(i);
				InfoEtudiant etud=(InfoEtudiant) leaf.getUserObject();
				Vector v = new Vector();
				
				v.add(etud.Prenom);
				v.add(etud.Nom);
				v.add(etud.Email);
				dtm.setColumnIdentifiers(columns);
				dtm.addRow(v);
			}
			
			}
			GridBagConstraints c1 =new GridBagConstraints();
			c1.fill = GridBagConstraints.HORIZONTAL;
			  c1.gridx= 1;
			  c1.gridy= 1;
			  c1.gridwidth=2;
			  
			pan.add(tscroll,c1);
			fen.repaint();
			fen.validate();
			
		}
		}
	};
	
	JScrollPane treescroll =new JScrollPane(tree);

	fen.add(bar,BorderLayout.NORTH);
	fen.add(tabPan,BorderLayout.CENTER);
	

	affich=new JPanel();
	affich.setLayout(new GridLayout(0,1));
	af_first=new JLabel("Nom");
	af_last=new JLabel("Prénom :");
	af_mail=new JLabel("Mail :");
	af_class=new JLabel("Class :");
	af_addre=new JLabel("Adresse :");
	af_num_fix=new JLabel("Numéro Fix :");
	_af_num=new JLabel("Numéro Portable :");
	af_niveau=new JLabel("Niveau :");
	affich.add(af_first);
	affich.add(af_last);
	affich.add(af_mail);
	affich.add(af_class);
	affich.add(af_addre);

	affich.add(_af_num);
	affich.add(new JLabel(""));
	affich.add(new JLabel(""));
	affich.add(new JLabel(""));
	affich.add(new JLabel(""));
	affich.add(new JLabel(""));
	affich.add(new JLabel(""));
	affich.add(new JLabel(""));
	

	ok.setPreferredSize(new DimensionUIResource(200, 40));
	//ok.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
	ok.setMargin(new Insets(10,10,10,10));
	tree.addTreeSelectionListener(arbreListener);
	GridBagConstraints c2 = new GridBagConstraints();
	c2.fill = GridBagConstraints.HORIZONTAL;
	c2.gridx=0;
	c2.gridy=0;
	c2.gridwidth=2;
	pan.add(form,c2);
	affich.setPreferredSize(new DimensionUIResource(200, 400));
	

	JPanel treepan=new JPanel();
	treepan.setLayout(new GridBagLayout());
	
	GridBagConstraints c = new GridBagConstraints();
	treescroll.setPreferredSize(new DimensionUIResource(410, 400));
	JButton tab_enreg=new JButton("Enregistrer");
	JButton tab_actua = new JButton("Actualiser");
	JButton tab_Supp = new JButton("Supprimer");
	tab_actua.addActionListener((ActionListener)new ActionListener() {
		


		public void actionPerformed(ActionEvent e) {
			Afficher();
			
		}
	});
tab_enreg.addActionListener((ActionListener)new ActionListener() {
		


		public void actionPerformed(ActionEvent e) {
			Enregistrer();
			
		}
	});
tab_Supp.addActionListener((ActionListener)new ActionListener() {
	


	public void actionPerformed(ActionEvent e) {
		DefaultMutableTreeNode node =(DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
		if (node.isLeaf()) {
			DefaultTreeModel treeModel = (DefaultTreeModel) tree.getModel();
			treeModel.removeNodeFromParent(node);
			JOptionPane.showMessageDialog(fen,"Etudiant Supprimé");
		}
	}
});
	c.fill = GridBagConstraints.HORIZONTAL;
	c.gridx = 0;
	c.gridy = 0;
	c.gridwidth=2;
	treepan.add(treescroll,c);
	c.fill = GridBagConstraints.HORIZONTAL;
	c.gridx = 0;
	c.gridy = 1;
	c.gridwidth=1;
	treepan.add(tab_actua,c);
	c.fill = GridBagConstraints.HORIZONTAL;
	c.gridx = 1;
	c.gridy = 1;
	c.gridwidth=1;
	treepan.add(tab_enreg,c);
	c.fill = GridBagConstraints.HORIZONTAL;
	c.gridx = 1;
	c.gridy = 2;
	c.gridwidth=1;
	treepan.add(tab_Supp,c);
	
	GridBagConstraints c1=new GridBagConstraints();
	c1.fill = GridBagConstraints.HORIZONTAL;
	c1.gridx = 0;
	c1.gridy = 1;
	c1.gridwidth=1;
	pan.add(treepan,c1);
	c1.fill = GridBagConstraints.HORIZONTAL;
	c1.gridx = 1;
	c1.gridy = 1;
	c1.gridwidth=1;
	pan.add(affich,c1);
	
	createNotesForm();
	
	

	
	fen.pack();
	fen.setVisible(true);
	fen.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	
	
}

	public static void main(String[] args) {
		MainFrame_TP f=new MainFrame_TP();

	}
	
	public void CreateForm() {
		
		 lb_first=new JLabel("Nom*");
		 lb_last=new JLabel("Prénom*");
		 lb_mail=new JLabel("E_mail*");
		 lb_class=new JLabel("Class*");
		 lb_addre=new JLabel("Adresse*");
		 lb_num_fix=new JLabel("Numéro Fix");
		 _lb_num=new JLabel("Numéro Portable*");
		
		 tf_first=new JTextField();
		 lb_niveau=new JLabel("Niveau*");
			
		 tf_niveau=new JTextField();
		 tf_last=new JTextField();
		 tf_mail=new JTextField();
		 tf_class=new JTextField();
		 tf_addr=new JTextField();
		 tf_numm_fix=new JTextField();
		 _tf_num=new JTextField();
		form=new JPanel();
		
		ok=new JButton("Ajouter");
		Supp=new JButton("Supprimer");
		 form=new JPanel();
		 form.setLayout(new GridLayout(0,4));
		
		form.add(lb_first);
		form.add(tf_first);
		
		form.add(lb_addre);
		form.add(tf_addr);
		form.add(lb_num_fix);
		form.add(tf_numm_fix);
		form.add(_lb_num);
		form.add(_tf_num);
		form.add(lb_last);
		form.add(tf_last);
		form.add(lb_mail);
		form.add(tf_mail);
		form.add(lb_class);
		form.add(tf_class);
		form.add(lb_niveau);
		form.add(tf_niveau);
		form.add(new JLabel("*Champ Obligatoire"));
		form.add(new Label(""));
		form.add(new Label(""));
		form.add(new Label(""));
		form.add(new Label(""));
		form.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		form.add(ok);
		form.add(Supp);
		
		
	}
	public void Ouvrir () {
		DefaultTreeModel treemodel = (DefaultTreeModel) tree.getModel();
		String line=null;
		try {
			racine.removeAllChildren();
			Class1 =new DefaultMutableTreeNode("MPISI1");
			Class2=new DefaultMutableTreeNode("MPISI2");
			racine.add(Class1);
			racine.add(Class2);
			BufferedReader br=new BufferedReader(new FileReader(f1));
			while ((line=br.readLine())!=null) {
			 infos_etud=line.split(";");
			 for (int i=0;i<8;i++) {
				//System.out.println(infos_etud[i]);
				
				
			 }
			 if (infos_etud[0].equals("MPISI1")) {
				 DefaultMutableTreeNode node = new DefaultMutableTreeNode(new InfoEtudiant(infos_etud[1], infos_etud[2], infos_etud[3], infos_etud[4],infos_etud[5],infos_etud[6], infos_etud[7], infos_etud[8]));
				 Class1.add(node);
			 }
			 if (infos_etud[0].equals("MPISI2")) {
				 DefaultMutableTreeNode node = new DefaultMutableTreeNode(new InfoEtudiant(infos_etud[1], infos_etud[2], infos_etud[3], infos_etud[4],infos_etud[5],infos_etud[6], infos_etud[7], infos_etud[8]));
				 Class2.add(node);
			 }
			 
			}
			
			treemodel.reload();
		}catch (Exception e) {
			
		}
		
	
		
	}
	public void CreateBar() {
		bar=new JMenuBar();
		JMenu fich_menu=new JMenu("Fichier");
		JMenuItem ouvr=new JMenuItem("Ouvrir");
		JMenuItem enreg=new JMenuItem("Enregistrer");
		JMenuItem Show=new JMenuItem("Afficher");
		JMenuItem quitt=new JMenuItem("Quitter");
		
		fich_menu.add(ouvr);
		fich_menu.add(enreg);
		fich_menu.add(quitt);
		fich_menu.add(Show);
		Show.addActionListener(menuItemActionListener);
		ouvr.addActionListener(menuItemActionListener);
		enreg.addActionListener(menuItemActionListener);
		quitt.addActionListener(menuItemActionListener);
		
		bar.add(fich_menu);
	
	}
	public void Enregistrer() {
		
		
		
			try {
				DefaultMutableTreeNode node;
				

				int c1=Class1.getChildCount();
				int c2=Class2.getChildCount();
				
				//System.out.println("La liste des Etudiant MPISI1 :");
			
				FileWriter Wr =new FileWriter(f1);
				
				if (c1<=0) {
					System.out.println("Vide");
				}
				
				for (int i=0 ;i<c1;i++) {
					node = (DefaultMutableTreeNode) Class1.getChildAt(i);
					
					  InfoEtudiant E = (InfoEtudiant )node.getUserObject();
		              //  System.out.println(" * " + E.Nom +" "+ E.Prenom + " " + E.Email + " " + E.NumP + " " + E.NumF + " " + E.Adresse + " " + 
		               // E.Classe + " " + E.Niv);
		                Wr.write("MPISI1;"+E.Nom +";"+ E.Prenom + ";" + E.Email + ";" + E.NumP + ";" + E.NumF + ";" + E.Adresse + ";" + 
				                E.Classe + ";" + E.Niv+"\n");
				
					//System.out.println(node.getUserObject());
					}
				System.out.println("La liste des Etudiant MPISI2 :");
				if (c2<=0) {
					System.out.println("Vide");
				}
				for (int i=0 ;i<c2;i++) {
					node = (DefaultMutableTreeNode) Class2.getChildAt(i);
					
					 InfoEtudiant E = (InfoEtudiant )node.getUserObject();
		                //System.out.println(" * " + E.Nom +" "+ E.Prenom + " " + E.Email + " " + E.NumP + " " + E.NumF + " " + E.Adresse + " " + 
		               // E.Classe + " " + E.Niv);
		                Wr.write("MPISI2;"+E.Nom +";"+ E.Prenom + ";" + E.Email + ";" + E.NumP + ";" + E.NumF + ";" + E.Adresse + ";" + 
				                E.Classe + ";" + E.Niv+"\n");
					
					System.out.println(node.getUserObject());
				}
				Wr.close();
			}catch (Exception e) {
				System.out.println(e);
			}
			
			
			
		
	
	}
	public void Quitter() {
		System.exit(0);
	}
	public void Afficher() {
		DefaultTreeModel treeModel = (DefaultTreeModel) tree.getModel();
        DefaultMutableTreeNode racine = (DefaultMutableTreeNode) treeModel.getRoot();
        System.out.println(racine.toString() + " :"); //texte de la racine
        for (int i = 0; i < racine.getChildCount(); i++) {
        DefaultMutableTreeNode categorie =
        (DefaultMutableTreeNode) racine.getChildAt(i);
       System.out.println(" - " + categorie.toString());
        for (int j = 0; j < categorie.getChildCount(); j++) {
        DefaultMutableTreeNode etudiant = (DefaultMutableTreeNode) categorie.getChildAt(j);
        InfoEtudiant E = (InfoEtudiant )etudiant.getUserObject();
        System.out.println(" * " + E.Nom +" "+ E.Prenom + " " + E.Email + " " + E.NumP + " " + E.NumF + " " + E.Adresse + " " + 
        E.Classe + " " + E.Niv);
       }
     }
    	
		
	}
	public void createNotesForm() {
		JPanel p=new JPanel();
		p.setLayout(new GridLayout(0,6,10,10));
		JLabel l_nom=new JLabel("Nom");
		notes_nom =new JLabel();
		JLabel l_mail=new JLabel("Email");
		notes_mail=new JLabel();
		p.add(l_nom);
		p.add(notes_nom);
		p.add(new JLabel(""));
		p.add(new JLabel(""));
		p.add(new JLabel(""));
		p.add(new JLabel(""));
		
		
		p.add(l_mail);
		
		p.add(notes_mail);
		p.add(new JLabel(""));
		p.add(new JLabel(""));
		p.add(new JLabel(""));
		p.add(new JLabel(""));
		
		tf_sec=new JTextField();
		tf_reseau=new JTextField();
		tf_java=new JTextField();
		tf_android=new JTextField();
		tf_php=new JTextField();
		tf_sgbd=new JTextField();
		tf_math=new JTextField();
		tf_francias=new JTextField();
		tf_anglais=new JTextField();
		tf_pfe=new JTextField();
		p.add(new JLabel("Securite"));
		p.add(tf_sec);
		p.add(new JLabel(""));
		p.add(new JLabel(""));
		p.add(new JLabel(""));
		p.add(new JLabel(""));
		p.add(new JLabel("Reseau"));
		p.add(tf_reseau);
		p.add(new JLabel(""));
		p.add(new JLabel(""));
		p.add(new JLabel(""));
		p.add(new JLabel(""));
		p.add(new JLabel("Java"));
		p.add(tf_java);
		p.add(new JLabel(""));
		p.add(new JLabel(""));
		p.add(new JLabel(""));
		p.add(new JLabel(""));
		p.add(new JLabel("php"));
		p.add(tf_php);
		p.add(new JLabel(""));
		p.add(new JLabel(""));
		p.add(new JLabel(""));
		p.add(new JLabel(""));
		p.add(new JLabel("Android"));
		p.add(tf_android);
		p.add(new JLabel(""));
		p.add(new JLabel(""));
		p.add(new JLabel(""));
		p.add(new JLabel(""));
		p.add(new JLabel("SGBD"));
		p.add(tf_sgbd);
		p.add(new JLabel(""));
		p.add(new JLabel(""));
		p.add(new JLabel(""));
		p.add(new JLabel(""));
		p.add(new JLabel("Math"));
		p.add(tf_math);
		p.add(new JLabel(""));
		p.add(new JLabel(""));
		p.add(new JLabel(""));
		p.add(new JLabel(""));
		p.add(new JLabel("Francais"));
		p.add(tf_francias);
		p.add(new JLabel(""));
		p.add(new JLabel(""));
		p.add(new JLabel(""));
		p.add(new JLabel(""));
		p.add(new JLabel("Anglais"));
		p.add(tf_anglais);
		p.add(new JLabel(""));
		p.add(new JLabel(""));
		p.add(new JLabel(""));
		p.add(new JLabel(""));
		p.add(new JLabel("Pfe"));
		p.add(tf_pfe);
		p.add(new JLabel(""));
		p.add(new JLabel(""));
		p.add(new JLabel(""));
		p.add(new JLabel(""));
		JPanel p2 =new JPanel();
		p2.setLayout(new GridLayout(0,6,10,10));
		JButton Enr=new JButton("Enregistrer");
		JButton Ann=new JButton("Annuler");
		JButton Vid=new JButton("Vider");
		tabop.add(p,BorderLayout.NORTH);
		p2.add(Enr);
		
		p2.add(Ann);
		
		p2.add(Vid);
		p2.add(new JLabel(""));
		p2.add(new JLabel(""));
		p2.add(new JLabel(""));
		tabop.add(p2,BorderLayout.SOUTH);
		Enr.addActionListener((ActionListener) new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				sauvegarderNotesEtudiant(notes_mail.getText());
				
			}
		
		});
		Ann.addActionListener((ActionListener) new ActionListener() {

			public void actionPerformed(ActionEvent e) {
			afficherNotesEtudiant(notes_nom.getText(),notes_mail.getText());
				
			}
		
		});
		Vid.addActionListener((ActionListener) new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				vider();
			
				
			}
		
		});
		
		
	
		
	}
	public void vider() {
		notes_nom.setText("");
		notes_mail.setText("");
		tf_sec.setText("");
		tf_reseau.setText("");
		tf_java.setText("");
		tf_php.setText("");
		tf_android.setText("");
		tf_sgbd.setText("");
		tf_math.setText("");
		tf_francias.setText("");
		tf_anglais.setText("");
		tf_pfe.setText("");
		
	}
 public void afficherNotesEtudiant(String nom, String email) {
	Connection conn=new Connexion().getSession();
	String req="Select * from notes where email=?";
			
	PreparedStatement st;
	try {
		st = conn.prepareStatement(req);
		st.setString(1, email);
	//	st.setString(2, nom);
		ResultSet rs =st.executeQuery();
		while(rs.next()) {
			tf_sec.setText(rs.getString("securite"));
			tf_reseau.setText(rs.getString("reseau"));
			tf_java.setText(rs.getString("Java"));
			tf_php.setText(rs.getString("php"));
			tf_android.setText(rs.getString("android"));
			tf_sgbd.setText(rs.getString("sgbd"));
			tf_math.setText(rs.getString("math"));
			tf_francias.setText(rs.getString("francais"));
			tf_anglais.setText(rs.getString("anglais"));
			tf_pfe.setText(rs.getString("pfe"));
			
		}
		rs.close();
	} catch (SQLException e) {
		
		e.printStackTrace();
	}

		
	}
 void sauvegarderNotesEtudiant(String email) {
	 Connection conn=new Connexion().getSession();
	 String req="Select * from notes where email=?  ";
	 String req1="";
	 String req2="";
		
		PreparedStatement st;
		try {
			st = conn.prepareStatement(req);
			st.setString(1, email);
			
		
			ResultSet rs =st.executeQuery();
			if(rs.next()==false) {
				req1="Insert into notes (nom,email,securite,reseau,Java,php,android,sgbd,"
						+ "math,francais,anglais,pfe) values(?,?,?,?,?,?,?,?,?,?,?,?) ";
				st=conn.prepareStatement(req1);
				st.setString(1, notes_nom.getText());
				st.setString(2, notes_mail.getText());
				st.setString(3, tf_sec.getText());
				st.setString(4, tf_reseau.getText());
				st.setString(5, tf_java.getText());
				st.setString(6, tf_php.getText());
				st.setString(7, tf_android.getText());
				st.setString(8, tf_sgbd.getText());
				st.setString(9, tf_math.getText());
				st.setString(10, tf_francias.getText());
				st.setString(11, tf_anglais.getText());
				st.setString(12, tf_pfe.getText());
				st.executeUpdate();
				
				
			}else {
				req2="Update notes set securite=?,reseau=?,Java=?,php=?,android=?,sgbd=?,"
						+ "math=?,francais=?,anglais=?,pfe=? where email=? ";
				st=conn.prepareStatement(req2);
				
				st.setString(11, notes_mail.getText());
				st.setString(1, tf_sec.getText());
				st.setString(2, tf_reseau.getText());
				st.setString(3, tf_java.getText());
				st.setString(4, tf_php.getText());
				st.setString(5, tf_android.getText());
				st.setString(6, tf_sgbd.getText());
				st.setString(7, tf_math.getText());
				st.setString(8, tf_francias.getText());
				st.setString(9, tf_anglais.getText());
				st.setString(10, tf_pfe.getText());
				st.executeUpdate();
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

 }
	ActionListener menuItemActionListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			String itemText=e.getActionCommand();
			switch (itemText) {
			case "Ouvrir" :
				Ouvrir();
				break;
			case "Enregistrer" :
				Enregistrer();
			
				break;
			case "Quitter" :
				Quitter();
				break;
			case "Afficher" :
				Afficher();
			break;
				
				}
			
		}
	};
	
}
