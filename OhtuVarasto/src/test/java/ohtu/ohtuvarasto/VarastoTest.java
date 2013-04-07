package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    int vertailuTarkkuus = 1;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void konstruktoriLuoSaldonVarastolle() {
        varasto = new Varasto(10,10);
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
        assertEquals(0, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void konstruktoriLuoToisenSaldonVarastolle() {
        varasto = new Varasto(10,9);
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
        assertEquals(1, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void uudellaVarastollaNollaTilavuus() {
        varasto = new Varasto(0);
        assertEquals(0, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(1);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(1, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void lisaysLisaaJuuriSaldoa() {
        varasto.lisaaVarastoon(10);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
        assertEquals(0, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void nollalisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(0);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(10);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(0, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(2);

        double saatuMaara = varasto.otaVarastosta(1);

        assertEquals(1, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisaaTilaa() {
        varasto.lisaaVarastoon(2);

        varasto.otaVarastosta(1);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(9, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void ottaminenJuuriLisaaTilaa() {
        varasto.lisaaVarastoon(10);

        int luku1 = varasto.otaVarastosta(10);
        int luku2 = varasto.otaVarastosta(11);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
        assertEquals(10, luku1, vertailuTarkkuus);
        assertEquals(0, luku2, vertailuTarkkuus);
    }
    
    @Test
    public void tilaeimeneMiinukselle() {
        varasto.lisaaVarastoon(1);
        varasto.otaVarastosta(2);
        // varastossa pitäisi olla 0
        assertEquals(0.0000, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void tilaeiYlity() {
        varasto = new Varasto(11,11);
        varasto.lisaaVarastoon(11);
        // varastossa pitäisi olla 10
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void alkuSaldoeineg() {
        varasto = new Varasto(-1);
        //pitäisi olla 0
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void testaaToString() {
        boolean sama = false;
        if (varasto.toString().equals("saldo = " + varasto.getSaldo() + ", vielä tilaa " + (varasto.getTilavuus()-varasto.getSaldo())))
            sama = true;
            //pitäisi olla true
        assertEquals(true, sama);
    }
    
    @Test
    public void annanegSaldo() {
        varasto.lisaaVarastoon(-1);
        assertEquals(0.0000, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void luonegVarasto() {
        varasto = new Varasto(-1);
        assertEquals(0, varasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void otanegSaldo() {
        varasto.lisaaVarastoon(1);
        varasto.otaVarastosta(2);
        assertEquals(1, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void konstr() {
        varasto = new Varasto(-1);
        varasto = new Varasto(0);
        varasto = new Varasto(1,1);
        varasto = new Varasto(1,2);
        varasto = new Varasto(-1,2);
        varasto = new Varasto(-1,-1);
        varasto.toString();
    }
}