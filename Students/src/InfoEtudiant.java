public class InfoEtudiant {
    public String Prenom,Nom,Email,NumP,NumF,Adresse,Classe,Niv;
    
    public InfoEtudiant(String Prenom,String Nom,String Email,String NumP,String NumF,String Adresse,String Classe,String Niv){
        this.Prenom=Prenom;
        this.Nom=Nom;
        this.Email=Email;
        this.NumP=NumP;
        this.NumF=NumF;
        this.Adresse=Adresse;
        this.Classe=Classe;
        this.Niv=Niv;
    }

 
    @Override
    public String toString() {
        return  Prenom + " " + Nom ;
    }  
    }
    
