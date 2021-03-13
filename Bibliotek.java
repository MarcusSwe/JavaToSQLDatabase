import java.sql.*;


public class Bibliotek {

    static GUI gui = new GUI();

    public Bibliotek(){

    }


    //LOGIN
    public static void login(String x, String y){

      try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/bibliotek", x, y);
            Bibliotek.gui.inlogCorrect();
            con.close();

      } catch (Exception e) {

          Bibliotek.gui.setInlogFel();
      }
    }


    //SOK BOK
    public static void sokBokY(String loginNamn, String loginPassword, String y){
        String resultat = "";
        String query = "select title, pages, ID, bokklassfikation.klassFikation, författare.författare from books inner join bokklassfikation on books.BokKlassfikation = bokklassfikation.klassFikaID INNER join författare on books.Författare = författare.författareID WHERE bibliotek.books.title LIKE ?;";
        y = y.replaceAll("[^a-zA-Z0-9öäå ]","");

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/bibliotek", loginNamn, loginPassword);
           // Statement stmt = con.createStatement();
           // ResultSet rs = stmt.executeQuery("select title, pages, ID, bokklassfikation.klassFikation, författare.författare from books inner join bokklassfikation on books.BokKlassfikation = bokklassfikation.klassFikaID INNER join författare on books.Författare = författare.författareID WHERE bibliotek.books.title LIKE '%"+y+"%';");
           PreparedStatement p = con.prepareStatement(query);
           p.setString(1,"%"+y+"%");
           ResultSet rs = p.executeQuery();

            while(rs.next()) {

                if(rs.getInt(3) >0){
                    resultat = resultat + rs.getString(1) +" Antal Sidor: " + rs.getString(2)+ " Klassfikation: " + rs.getString(4)+ " Författare: " + rs.getString(5)+ " UTLÅNAD"+"\n";
                } else {
                    resultat = resultat + rs.getString(1) +" Antal Sidor: " + rs.getString(2)+ " Klassfikation: " + rs.getString(4)+ " Författare: " + rs.getString(5)+ " EJ UTLÅNAD"+"\n";
                }
            }

            Bibliotek.gui.setBok(resultat);

            con.close();

        } catch (Exception e) {
            //System.out.println("fel !!!!!!!!!!");

        }

    }


    // SOK låntagare
    public static void sokLantagare(String loginNamn, String loginPassword, String y){
        String resultat = "";
        y = y.replaceAll("[^a-zA-Z0-9öäå ]","");
        String query = "select title, lånnamninfo.Namn, lånnamninfo.Address, lånnamninfo.Telefonnummer, lånnamninfo.LånekortsNummer from books inner join lånnamninfo on books.ID = lånnamninfo.lånID WHERE bibliotek.lånnamninfo.Namn LIKE ?;";

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/bibliotek", loginNamn, loginPassword);
            //Statement stmt = con.createStatement();
            //ResultSet rs = stmt.executeQuery("select title, lånnamninfo.Namn, lånnamninfo.Address, lånnamninfo.Telefonnummer, lånnamninfo.LånekortsNummer from books inner join lånnamninfo on books.ID = lånnamninfo.lånID WHERE bibliotek.lånnamninfo.Namn LIKE '%"+y+"%';");
            PreparedStatement p = con.prepareStatement(query);
            p.setString(1,"%"+y+"%");
            ResultSet rs = p.executeQuery();


            while(rs.next()) {
                System.out.println(rs.getString(1));
                    resultat = resultat + rs.getString(1) +" Namn: " + rs.getString(2)+ " Address: " + rs.getString(3)+ " Telefon: " + rs.getString(4)+ " Lånekortsnummer: "+ rs.getString(5) +"\n";
            }

            Bibliotek.gui.setLantagare(resultat);

            con.close();

        } catch (Exception e) {
            //System.out.println("fel !!!!!!!!!!");

        }

    }



    //LÅNA bok
    public static void lanaBokXU(String loginNamn, String loginPassword, String y){
        String resultat = "";
        Boolean uthyrd = false;
        int IDx = 0;
        int bokID = 0;
        String query = "select title,ID from books where books.title like ?;";
        y = y.replaceAll("[^a-zA-Z0-9öäå ]","");

        switch(loginNamn){
            case "Thor":
                IDx = 8;
                break;
            case "AnnaBritt":
                IDx = 7;
                break;
            case "DumRonny":
                IDx = 6;
        }

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/bibliotek", loginNamn, loginPassword);
            Statement stmt = con.createStatement();
            //ResultSet rs = stmt.executeQuery("select title,ID from books where books.title like '%"+y+"%';");
            PreparedStatement p = con.prepareStatement(query);
            p.setString(1,"%"+y+"%");
            ResultSet rs = p.executeQuery();


            while(rs.next()) {
                resultat = resultat + rs.getString(1);
            }

            ResultSet rs3 = stmt.executeQuery("select title,titleID from books where books.title like '%%';");
            while(rs3.next()) {
                if(resultat.equals(rs3.getString(1))){
                    bokID = rs3.getInt(2);
                }
            }


            //ResultSet rs2 = stmt.executeQuery("select title,ID from books where books.title like '%"+y+"%';");
            PreparedStatement p2 = con.prepareStatement(query);
            p2.setString(1,"%"+y+"%");
            ResultSet rs2 = p2.executeQuery();

            while(rs2.next()) {
                //System.out.println(rs2.getString(1));
                if(resultat.equals(rs2.getString(1)) && !(rs2.getInt(2)>0)){
                    System.out.println(resultat);
                    System.out.println(resultat);
                    System.out.println(resultat);
                    System.out.println(IDx);
                    System.out.println(bokID);
                    stmt.execute("UPDATE books SET ID = "+IDx+" WHERE titleID = "+bokID+";");
                    Bibliotek.gui.setlanaBok(resultat + " är nu lånad i ditt namn!");
                    uthyrd = true;
                }
            }

            if(!uthyrd){
                Bibliotek.gui.setlanaBok("Är ythyrd eller existerar ej!");
            }

            con.close();

        } catch (Exception e) {
            //System.out.println("fel !!!!!!!!!!");

        }

    }



    //sok tidning
    public static void sokTidningX(String loginNamn, String loginPassword, String y){
        String resultat = "";
        String query = "select Tidning, tidningsutgivningsdatum.UtgivningsDatum,(SELECT Lagerplats from lagerplats WHERE tidning.HyllaID = lagerplats.lagerplatsID) from tidning inner join tidningsutgivningsdatum on tidning.tidningsID = tidningsutgivningsdatum.ID WHERE bibliotek.tidning.Tidning LIKE ?;";
        y = y.replaceAll("[^a-zA-Z0-9öäå ]","");

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/bibliotek", loginNamn, loginPassword);
            //Statement stmt = con.createStatement();
            //ResultSet rs = stmt.executeQuery("select Tidning, tidningsutgivningsdatum.UtgivningsDatum,(SELECT Lagerplats from lagerplats WHERE tidning.HyllaID = lagerplats.lagerplatsID) from tidning inner join tidningsutgivningsdatum on tidning.tidningsID = tidningsutgivningsdatum.ID WHERE bibliotek.tidning.Tidning LIKE '%"+y+"%';");
            PreparedStatement p = con.prepareStatement(query);
            p.setString(1,"%"+y+"%");
            ResultSet rs = p.executeQuery();


            while(rs.next()) {
                System.out.println(rs.getString(1) + rs.getString(2) + rs.getString(3));
                resultat = resultat + rs.getString(1) +" Utgviningsdatum: " + rs.getString(2)+ " Plats: " + rs.getString(3)+ "\n";
            }

            Bibliotek.gui.setTidningX(resultat);

            con.close();

        } catch (Exception e) {
            //System.out.println("fel !!!!!!!!!!");

        }

    }



    // sok anstalld
    public static void sokanstalldX(String loginNamn, String loginPassword, String y){
        String resultat = "";
        String query = "select Namn, Adress, Månadslön, SemesterdagarKvar from anställningsnamn WHERE bibliotek.anställningsnamn.Namn LIKE ?;";
        String query2 = "select Telefonnummer, anställningsnamn.Namn from telefonnummeranställda inner join anställningsnamn on telefonnummeranställda.anställdID = anställningsnamn.AnställningsID WHERE anställningsnamn.Namn LIKE ?;";
        y = y.replaceAll("[^a-zA-Z0-9öäå ]","");

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/bibliotek", loginNamn, loginPassword);
            //Statement stmt = con.createStatement();
            //ResultSet rs = stmt.executeQuery("select Namn, Adress, Månadslön, SemesterdagarKvar from anställningsnamn WHERE bibliotek.anställningsnamn.Namn LIKE '%"+y+"%';");
            PreparedStatement p = con.prepareStatement(query);
            p.setString(1,"%"+y+"%");
            ResultSet rs = p.executeQuery();

            while(rs.next()) {
                System.out.println(rs.getString(1) + rs.getString(2) + rs.getString(3));
                resultat = resultat + rs.getString(1) +" Address: " + rs.getString(2)+ "   Månadslön: " + rs.getString(3)+ "   Semesterdagar kvar: " + rs.getString(4)+ "\n";
            }

            Bibliotek.gui.setAnstalldX(resultat);

            resultat = "";
            PreparedStatement p2 = con.prepareStatement(query2);
            p2.setString(1,"%"+y+"%");
            ResultSet rs2 = p2.executeQuery();

           // ResultSet rs2 = stmt.executeQuery("select Telefonnummer, anställningsnamn.Namn from telefonnummeranställda inner join anställningsnamn on telefonnummeranställda.anställdID = anställningsnamn.AnställningsID WHERE anställningsnamn.Namn LIKE '%"+y+"%';");

            while(rs2.next()) {
                System.out.print((rs2.getString(1)));
                resultat = resultat + " Telefonnummer: " + rs2.getString(1)+ "   namn: " + rs2.getString(2)+ "\n";
            }

            Bibliotek.gui.setAnstalldTeleX(resultat);

            con.close();

        } catch (Exception e) {
            //System.out.println("fel !!!!!!!!!!");

        }

    }



    // uppdatera personal - siffer check på lön osv sker automatiskt i sql.. throwar exception så edit inte går in..
    public static void uppdateraPersonal(String loginNamn, String loginPassword, String personalNamnX, String personalAddressX, String personalLonX, String personalSemesterX){
        String resultat = "";
        Boolean userExist = false;
        String query = "select Namn from anställningsnamn WHERE anställningsnamn.Namn like ?;";

        int anstallningsID = 0;


        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/bibliotek", loginNamn, loginPassword);
            //Statement stmt = con.createStatement();
            //ResultSet rs = stmt.executeQuery("select Namn from anställningsnamn WHERE anställningsnamn.Namn like '%"+personalNamnX+"%';");
            PreparedStatement p = con.prepareStatement(query);
            p.setString(1,"%"+personalNamnX+"%");
            ResultSet rs = p.executeQuery();


            while(rs.next()) {
                resultat = resultat + rs.getString(1);
            }


            Statement stmt = con.createStatement();
            ResultSet rs3 = stmt.executeQuery("select Namn, AnställningsID from anställningsnamn WHERE anställningsnamn.Namn like '%%';");
            while(rs3.next()) {
                if(resultat.equals(rs3.getString(1))){
                    anstallningsID = rs3.getInt(2);
                    userExist = true;
                }
            }


            if(userExist){
                System.out.println(resultat);
                System.out.println(resultat);
                System.out.println(resultat);

               stmt.execute("UPDATE anställningsnamn SET Namn = "+"'"+personalNamnX+"'"+", Adress = "+"'"+personalAddressX+"'"+", Månadslön = "+"'"+personalLonX+"'"+", SemesterdagarKvar = "+"'"+personalSemesterX+"'" +" WHERE AnställningsID = "+"'"+anstallningsID+"'"+";");

                Bibliotek.gui.setAnstalldTeleX("UPPDATERAD");

                } else Bibliotek.gui.setAnstalldTeleX("Anställd finns ej!");


            con.close();

        } catch (Exception e) {
            //System.out.println("fel !!!!!!!!!!");

        }

    }



    //uppatera teleofn
    public static void uppdateraTelefon(String loginNamn, String loginPassword, String y, String personalNamnX){
        String resultat = "";
        Boolean userExist = false;
        String query = "select Namn from anställningsnamn WHERE anställningsnamn.Namn like ?;";
        int anstallningsID = 0;


        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/bibliotek", loginNamn, loginPassword);
           // Statement stmt = con.createStatement();
            //ResultSet rs = stmt.executeQuery("select Namn from anställningsnamn WHERE anställningsnamn.Namn like '%"+personalNamnX+"%';");
            PreparedStatement p = con.prepareStatement(query);
            p.setString(1,"%"+personalNamnX+"%");
            ResultSet rs = p.executeQuery();


            while(rs.next()) {
                resultat = resultat + rs.getString(1);
            }

            Statement stmt = con.createStatement();
            ResultSet rs3 = stmt.executeQuery("select Namn, AnställningsID from anställningsnamn WHERE anställningsnamn.Namn like '%%';");
            while(rs3.next()) {
                if(resultat.equals(rs3.getString(1))){
                    anstallningsID = rs3.getInt(2);
                    userExist = true;
                }
            }


            if(userExist){
                System.out.println(resultat);
                System.out.println(resultat);
                System.out.println(resultat);

                stmt.execute( "INSERT INTO telefonnummeranställda (Telefonnummer, anställdID) VALUES ('"+y+"', '"+anstallningsID+"');");

                Bibliotek.gui.setAnstalldTeleX("LAGT TELEFON NUMMER");

            } else Bibliotek.gui.setAnstalldTeleX("Anställd finns ej!");


            con.close();

        } catch (Exception e) {
            //System.out.println("fel !!!!!!!!!!");

        }

    }


}
