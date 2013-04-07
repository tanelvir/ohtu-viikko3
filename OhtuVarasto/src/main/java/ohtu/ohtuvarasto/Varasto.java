package ohtu.ohtuvarasto;

public class Varasto {

    // --- piilotettu tietorakenteen toteutus: ---
    private int tilavuus;  // paljonko varastoon mahtuu,  > 0
    private int saldo;     // paljonko varastossa on nyt, >= 0

    // --- konstruktorit: ---
    public Varasto(int tilavuus) {  // tilavuus on annettava
        if (tilavuus > 0) {
            this.tilavuus = tilavuus;
        } else // virheellinen, nollataan
        {
            this.tilavuus = 0;  // => käyttökelvoton varasto
        }
        saldo = 0;     // oletus: varasto on tyhjä
    }

    public Varasto(int tilavuus, int alkuSaldo) { // kuormitetaan
        if (tilavuus > 0) {
            this.tilavuus = tilavuus;
        } else // virheellinen, nollataan
        {
            this.tilavuus = 0;  // => käyttökelvoton varasto
        }
        if (alkuSaldo < 0) {
            this.saldo = 0;
        } else if (alkuSaldo <= tilavuus) // mahtuu
        {
            this.saldo = alkuSaldo;
        } else {
            this.saldo = tilavuus;  // täyteen ja ylimäärä hukkaan!
        }
    }

    // --- ottavat aksessorit eli getterit: ---
    public int getSaldo() {
        return saldo;
    }

    public int getTilavuus() {
        return tilavuus;
    }

    public int paljonkoMahtuu() {  // huom: ominaisuus voidaan myös laskea
        return tilavuus - saldo;        //  ei tarvita erillistä kenttää vielaTilaa tms.
    }

    // --- asettavat aksessorit eli setterit: ---
    public void lisaaVarastoon(int maara) {
        if (maara < 0) // virhetilanteessa voidaan tehdä 
        {
            return;       // tällainen pikapoistuminenkin!
        }
        if (maara <= paljonkoMahtuu()) // omia aksessoreita voi kutsua
        {
            saldo = saldo + maara;          // ihan suoraan sellaisinaan
        } else {
            saldo = tilavuus;  // täyteen ja ylimäärä hukkaan!
        }
    }

    public int otaVarastosta(int maara) {
        if (maara < 0) // virhetilanteessa voidaan tehdä 
        {
            return 0;   // tällainen pikapoistuminenkin!
        }
        if (maara > saldo) {          // annetaan mitä voidaan
            int kaikkiMitaVoidaan = saldo;
            saldo = 0;               // ja tyhjäksi menee
            return kaikkiMitaVoidaan;  // poistutaan saman tien
        }
        // jos tänne päästään, kaikki pyydetty voidaan antaa
        saldo = saldo - maara;  // vähennetään annettava saldosta
        return maara;
    }

    // --- Merkkijonoesitys Varasto-oliolle: ----
    public String toString() {
        return ("saldo = " + saldo + ", vielä tilaa " + paljonkoMahtuu());
    }
}