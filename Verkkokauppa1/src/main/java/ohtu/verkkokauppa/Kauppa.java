package ohtu.verkkokauppa;

public class Kauppa implements Kaubba {

    private Varasto varasto;
    private Pankki pankki;
    private Ostoskori ostoskori;
    private Viitegeneraattori viitegeneraattori;
    private String kaupanTili;

    public Kauppa(Varasto varasto, Pankki pankki, Viitegeneraattori vg) {
        this.varasto = varasto;
        this.pankki = pankki;
        this.viitegeneraattori = vg;
        kaupanTili = "33333-44455";
    }
    
    public Kirjanpito kirjanpito() {
        return varasto.kirjanpito;
    }

    @Override
    public void aloitaAsiointi() {
        ostoskori = new Ostoskori();
    }

    @Override
    public void poistaKorista(int id) {
        Tuote t = varasto.haeTuote(id); 
        varasto.palautaVarastoon(t);
    }

    @Override
    public void lisaaKoriin(int id) {
        if (varasto.saldo(id)>0) {
            Tuote t = varasto.haeTuote(id);             
            ostoskori.lisaa(t);
            varasto.otaVarastosta(t);
        }
    }

    @Override
    public boolean tilimaksu(String nimi, String tiliNumero) {
        int viite = viitegeneraattori.uusi();
        int summa = ostoskori.hinta();
        
        return pankki.tilisiirto(nimi, viite, tiliNumero, kaupanTili, summa);
    }

}
