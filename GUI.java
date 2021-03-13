import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI extends JFrame {

    private String loginNamn;
    private String loginPassword;

    private String sokBok;
    private String lanaBok;
    private String sokTidning;
    private String sokLantagare;
    private String sokPersonal;
    private String personalNamn;
    private String personalAddress;
    private String personalLon;
    private String semester;
    private String personalTelefon;



    private JPanel panel;

    private JButton kundButton;
    private JButton bibButton;
    private JButton adminButton;


    // inlog UI
    private JTextField inlogNamnTF;
    private JTextField inlogPasswordTF;
    private JButton loginButton;

    // kund UI
    private JTextField sokBokTF;
    private JButton sokButton;

    private JTextField lanaBokTF;
    private JButton lanaBokButton;

    private JTextArea sokBokTA;
    private JScrollPane sokBokTAS;

    private JTextField sokTidningTF;
    private JButton sokTidningButton;
    private JTextArea sokTidningTA;
    private JScrollPane sokTidningTAS;

    // bibliotekarie UI
    private JTextField sokLanTF;
    private JButton sokLanButton;
    private JTextArea soklanTA;
    private JScrollPane soklanTAS;

    // admin UI

    private JTextField sokPersonalTF;
    private JButton sokPersonalButton;
    private JTextArea sokPersonalTA;
    private JScrollPane sokPersonalTAS;
    private JTextArea sokPersonalTeleTA;
    private JScrollPane sokPersonalTeleTAS;

    private JTextField personalNamnTF;
    private JTextField personalAddressTF;
    private JTextField personalSalaryTF;
    private JTextField personalSemesterdagarKvarTF;
    private JButton personalUppdatering;
    private JButton personalNY;
    private JButton personalDelete;

    private JTextField personalTelefonTF;
    private JButton nyTelefonButton;
    private JButton tabortTelefonButton;



    public GUI(){

        this.setTitle("Bibliotek");
        this.setSize(1300, 1200);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        createElements();
        this.add(panel);
        this.setVisible(true);
        this.setResizable(true);

        setupInlog();

    }

   private void setupInlog(){
        this.panel.add(inlogNamnTF);
        this.panel.add(inlogPasswordTF);
        this.panel.add(loginButton);

       ActionListener loginListener = e -> {
           this.loginNamn = inlogNamnTF.getText();
           this.loginPassword = inlogPasswordTF.getText();
           Bibliotek.login(loginNamn, loginPassword);
       };

       this.loginButton.addActionListener(loginListener);

    }

    private void createElements(){

        this.panel = new JPanel(new GridLayout(5, 5));

        this.kundButton = new JButton("Kund");
        this.bibButton = new JButton("Bibliotikarie");
        this.adminButton = new JButton("Admin");


        // inlog UI
        this.inlogNamnTF = new JTextField("inloggningsnamn");
        this.inlogPasswordTF = new JTextField("lösenord");
        this.loginButton = new JButton("login");

        // kund UI
        this.sokBokTF = new JTextField("sök bok ELLER tomt fält för alla");
        this.sokButton = new JButton("sök bok");

        this.lanaBokTF = new JTextField("låna bok");
        this.lanaBokButton = new JButton("låna bok");

        this.sokBokTA = new JTextArea("sök resultat");
        this.sokBokTAS = new JScrollPane(sokBokTA);

        this.sokTidningTF = new JTextField("sök tidning ELLER tomt fält för alla");
        this.sokTidningButton = new JButton("sök tidning");
        this.sokTidningTA = new JTextArea("sök resultat");
        this.sokTidningTAS = new JScrollPane(sokTidningTA);

        // bibliotekarie UI
        this.sokLanTF = new JTextField("sök låntagare ELLER tomt fält för alla");
        this.sokLanButton = new JButton("sök låntagare");
        this.soklanTA = new JTextArea("sök resultat");
        this.soklanTAS = new JScrollPane(soklanTA);

        // admin UI

        this.sokPersonalTF = new JTextField("sök personal ELLER tomt fält för alla");
        this.sokPersonalButton = new JButton("sök personal");
        this.sokPersonalTA = new JTextArea("sök resultat");
        this.sokPersonalTAS = new JScrollPane(sokPersonalTA);
        this.sokPersonalTeleTA = new JTextArea("sök resultat telefonummer");
        this.sokPersonalTeleTAS = new JScrollPane(sokPersonalTeleTA);


        this.personalNamnTF = new JTextField("personal namn");
        this.personalAddressTF = new JTextField("address");
        this.personalSalaryTF = new JTextField("lön");
        this.personalSemesterdagarKvarTF = new JTextField("Semester dagar kvar");
        this.personalUppdatering = new JButton("uppdatera");
        this.personalNY = new JButton("ny personal");
        this.personalDelete = new JButton("ta bort personal");

        this.personalTelefonTF = new JTextField("personal telefon");
        this.nyTelefonButton = new JButton("lägg till telefonnummer");
        this.tabortTelefonButton = new JButton("ta bort telefonnummer");

        this.sokBokTF.setBackground(Color.CYAN);
        this.sokTidningTF.setBackground(Color.GREEN);
        this.lanaBokTF.setBackground(Color.PINK);
        this.sokLanTF.setBackground(Color.GREEN);
        this.sokPersonalTF.setBackground(Color.CYAN);
        this.personalAddressTF.setBackground(Color.green);
        this.personalSalaryTF.setBackground(Color.PINK);
        this.personalSemesterdagarKvarTF.setBackground(Color.MAGENTA);
        this.personalTelefonTF.setBackground(Color.yellow);
        this.personalNamnTF.setBackground(Color.ORANGE);

    }

    public void setInlogFel(){
        this.inlogNamnTF.setText("Fel användare/lösenord");
        this.inlogPasswordTF.setText("");
    }

    public void setTidning(){
        this.sokTidningTA.setText("OSSSSSSSSSSSSSSSSTTTT");

    }

    public void setBok(String x){
        this.sokBokTA.setText(x);

    }

    public void setlanaBok(String p){
        this.lanaBokTF.setText(p);
    }

    public void setLantagare(String q){
        this.soklanTA.setText(q);
    }

    public void setTidningX(String q){
        this.sokTidningTA.setText(q);
    }

    public void setAnstalldX(String q){
        this.sokPersonalTA.setText(q);
    }

    public void setAnstalldTeleX(String q){
        this.sokPersonalTeleTA.setText(q);
    }



    public void inlogCorrect(){
        this.inlogNamnTF.setVisible(false);
        this.inlogPasswordTF.setVisible(false);
        this.loginButton.setVisible(false);
        this.panel.remove(inlogNamnTF);
        this.panel.remove(inlogPasswordTF);
        this.panel.remove(loginButton);

        treeButtons();

    }

    public void treeButtons(){
        if(this.loginNamn.equals("DumRonny") || this.loginNamn.equals("Thor") || this.loginNamn.equals("AnnaBritt")){
        this.panel.add(kundButton);
        menyListenerKund();
        }
        if(this.loginNamn.equals("Thor") || this.loginNamn.equals("AnnaBritt")){
        this.panel.add(bibButton);
        menyListenerBib();
        }
        if(this.loginNamn.equals("Thor")) {
            this.panel.add(adminButton);
            menyListenerAdmin();
        }

    }

    public void menyListenerKund(){

        ActionListener kundA = g -> {
            this.panel.add(sokBokTF);
            this.panel.add(sokButton);
            this.panel.add(sokBokTAS);
            this.panel.add(lanaBokTF);
            this.panel.add(lanaBokButton);
            this.panel.add(sokTidningTF);
            this.panel.add(sokTidningButton);
            this.panel.add(sokTidningTAS);
            this.sokBokTF.setVisible(true);
            this.sokButton.setVisible(true);
            this.lanaBokTF.setVisible(true);
            this.lanaBokButton.setVisible(true);
            this.sokBokTAS.setVisible(true);
            this.sokTidningTF.setVisible(true);
            this.sokTidningButton.setVisible(true);
            this.sokTidningTAS.setVisible(true);

            this.sokLanTF.setVisible(false);
            this.sokLanButton.setVisible(false);
            this.soklanTAS.setVisible(false);
            this.panel.remove(sokLanTF);
            this.panel.remove(sokLanTF);
            this.panel.remove(sokLanTF);

            this.sokPersonalTF.setVisible(false);
            this.sokPersonalButton.setVisible(false);
            this.sokPersonalTAS.setVisible(false);
            this.sokPersonalTeleTAS.setVisible(false);
            this.personalNamnTF.setVisible(false);
            this.personalAddressTF.setVisible(false);
            this.personalSalaryTF.setVisible(false);
            this.personalSemesterdagarKvarTF.setVisible(false);
            this.personalUppdatering.setVisible(false);
            this.personalNY.setVisible(false);
            this.personalDelete.setVisible(false);
            this.personalTelefonTF.setVisible(false);
            this.nyTelefonButton.setVisible(false);
            this.tabortTelefonButton.setVisible(false);
            this.panel.remove(sokPersonalTF);
            this.panel.remove(sokPersonalButton);
            this.panel.remove(sokPersonalTAS);
            this.panel.remove(sokPersonalTeleTAS);
            this.panel.remove(personalNamnTF);
            this.panel.remove(personalAddressTF);
            this.panel.remove(personalSalaryTF);
            this.panel.remove(personalSemesterdagarKvarTF);
            this.panel.remove(personalUppdatering);
            this.panel.remove(personalNY);
            this.panel.remove(personalDelete);
            this.panel.remove(personalTelefonTF);
            this.panel.remove(nyTelefonButton);
            this.panel.remove(tabortTelefonButton);

            this.setSize(1092,1031);
        };

        ActionListener sokBokX = a -> {
            this.sokBok = this.sokBokTF.getText();
            Bibliotek.sokBokY(loginNamn, loginPassword, sokBok);
        };

        ActionListener lanaBokX = a -> {
            this.lanaBok = this.lanaBokTF.getText();
            Bibliotek.lanaBokXU(loginNamn, loginPassword, lanaBok);
        };

        ActionListener sokTidningXX = a -> {
            this.sokTidning = this.sokTidningTF.getText();
            Bibliotek.sokTidningX(loginNamn, loginPassword, sokTidning);
        };



        this.lanaBokButton.addActionListener(lanaBokX);
        this.kundButton.addActionListener(kundA);
        this.sokButton.addActionListener(sokBokX);
        this.sokTidningButton.addActionListener(sokTidningXX);

    }

    public void menyListenerBib(){

        ActionListener bibA = g -> {
            this.sokBokTF.setVisible(false);
            this.sokButton.setVisible(false);
            this.lanaBokTF.setVisible(false);
            this.lanaBokButton.setVisible(false);
            this.sokBokTAS.setVisible(false);
            this.sokTidningTF.setVisible(false);
            this.sokTidningButton.setVisible(false);
            this.sokTidningTAS.setVisible(false);
            this.panel.remove(sokBokTF);
            this.panel.remove(sokButton);
            this.panel.remove(lanaBokTF);
            this.panel.remove(lanaBokButton);
            this.panel.remove(sokBokTAS);
            this.panel.remove(sokTidningTF);
            this.panel.remove(sokTidningButton);
            this.panel.remove(sokTidningTAS);

            this.panel.add(sokLanTF);
            this.panel.add(sokLanButton);
            this.panel.add(soklanTAS);
            this.sokLanTF.setVisible(true);
            this.sokLanButton.setVisible(true);
            this.soklanTAS.setVisible(true);

            this.sokPersonalTF.setVisible(false);
            this.sokPersonalButton.setVisible(false);
            this.sokPersonalTAS.setVisible(false);
            this.sokPersonalTeleTAS.setVisible(false);
            this.personalNamnTF.setVisible(false);
            this.personalAddressTF.setVisible(false);
            this.personalSalaryTF.setVisible(false);
            this.personalSemesterdagarKvarTF.setVisible(false);
            this.personalUppdatering.setVisible(false);
            this.personalNY.setVisible(false);
            this.personalDelete.setVisible(false);
            this.personalTelefonTF.setVisible(false);
            this.nyTelefonButton.setVisible(false);
            this.tabortTelefonButton.setVisible(false);
            this.panel.remove(sokPersonalTF);
            this.panel.remove(sokPersonalButton);
            this.panel.remove(sokPersonalTAS);
            this.panel.remove(sokPersonalTeleTAS);
            this.panel.remove(personalNamnTF);
            this.panel.remove(personalAddressTF);
            this.panel.remove(personalSalaryTF);
            this.panel.remove(personalSemesterdagarKvarTF);
            this.panel.remove(personalUppdatering);
            this.panel.remove(personalNY);
            this.panel.remove(personalDelete);
            this.panel.remove(personalTelefonTF);
            this.panel.remove(nyTelefonButton);
            this.panel.remove(tabortTelefonButton);



            this.setSize(1091,1030);
        };

        ActionListener sokLanX = a -> {
            this.sokLantagare = this.sokLanTF.getText();
            Bibliotek.sokLantagare(loginNamn, loginPassword, sokLantagare);
        };

        this.sokLanButton.addActionListener(sokLanX);



        this.bibButton.addActionListener(bibA);


    }

    public void menyListenerAdmin(){

        ActionListener adminA = g -> {
            this.sokBokTF.setVisible(false);
            this.sokButton.setVisible(false);
            this.lanaBokTF.setVisible(false);
            this.lanaBokButton.setVisible(false);
            this.sokBokTAS.setVisible(false);
            this.sokTidningTF.setVisible(false);
            this.sokTidningButton.setVisible(false);
            this.sokTidningTAS.setVisible(false);
            this.panel.remove(sokBokTF);
            this.panel.remove(sokButton);
            this.panel.remove(lanaBokTF);
            this.panel.remove(lanaBokButton);
            this.panel.remove(sokBokTAS);
            this.panel.remove(sokTidningTF);
            this.panel.remove(sokTidningButton);
            this.panel.remove(sokTidningTAS);


            this.sokLanTF.setVisible(false);
            this.sokLanButton.setVisible(false);
            this.soklanTAS.setVisible(false);
            this.panel.remove(sokLanTF);
            this.panel.remove(sokLanButton);
            this.panel.remove(soklanTAS);

            this.panel.add(sokPersonalTF);
            this.panel.add(sokPersonalButton);
            this.panel.add(sokPersonalTAS);
            this.panel.add(sokPersonalTeleTAS);
            this.panel.add(personalNamnTF);
            this.panel.add(personalAddressTF);
            this.panel.add(personalSalaryTF);
            this.panel.add(personalSemesterdagarKvarTF);
            this.panel.add(personalUppdatering);

            this.panel.add(personalTelefonTF);
            this.panel.add(nyTelefonButton);

            this.sokPersonalTF.setVisible(true);
            this.sokPersonalButton.setVisible(true);
            this.sokPersonalTAS.setVisible(true);
            this.sokPersonalTeleTAS.setVisible(true);
            this.personalNamnTF.setVisible(true);
            this.personalAddressTF.setVisible(true);
            this.personalSalaryTF.setVisible(true);
            this.personalSemesterdagarKvarTF.setVisible(true);
            this.personalUppdatering.setVisible(true);

            this.personalTelefonTF.setVisible(true);
            this.nyTelefonButton.setVisible(true);



            this.setSize(1093,1033);
        };

        ActionListener sokPersonalXX = a -> {
            this.sokPersonal = this.sokPersonalTF.getText();
            Bibliotek.sokanstalldX(loginNamn, loginPassword, sokPersonal);
        };

        ActionListener uppdateraX = a -> {
            this.personalNamn = this.personalNamnTF.getText();
            this.personalAddress = this.personalAddressTF.getText();
            this.personalLon = this.personalSalaryTF.getText();
            this.semester = this.personalSemesterdagarKvarTF.getText();

            Bibliotek.uppdateraPersonal(loginNamn, loginPassword, personalNamn, personalAddress, personalLon, semester);
        };

        ActionListener uppderaTelefonX = a -> {
            this.personalTelefon = this.personalTelefonTF.getText();
            this.personalNamn = this.personalNamnTF.getText();
            Bibliotek.uppdateraTelefon(loginNamn, loginPassword, personalTelefon, personalNamn);
        };

        this.adminButton.addActionListener(adminA);
        this.sokPersonalButton.addActionListener(sokPersonalXX);
        this.personalUppdatering.addActionListener(uppdateraX);
        this.nyTelefonButton.addActionListener(uppderaTelefonX);

    }



}
