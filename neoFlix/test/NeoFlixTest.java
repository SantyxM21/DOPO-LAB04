package test;



import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domain.*;

/**
 * Pruebas de unidad de NeoFlix para la funcionalidad de adicionar.
 * Una ejecucion normal y una por cada excepcion definida.
 */
public class NeoFlixTest{
    private NeoFlix nf;
    private int initialSize;

    /**
     * Default constructor for test class NeoFlixTest
     */
    public NeoFlixTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp(){
        nf = new NeoFlix();
        initialSize = nf.numberContents();
    }

    /* ---------- ejecuciones normales ---------- */

    @Test
    public void shouldAddAnEpisode(){
        try {
            nf.addEpisode("Episodio 1", "?", "100", "80", "10", "70");
            assertEquals(initialSize + 1, nf.numberContents());
            assertTrue(nf.consult("Episodio 1") instanceof Episode);
        } catch (NeoFlixException e){
            fail("Threw a exception " + e.getMessage());
        }
    }

    @Test
    public void shouldAddASeries(){
        try {
            nf.addEpisode("Episodio 1", "?", "100", "80", "10", "70");
            nf.addSeries("Serie prueba", "2020", "Episodio 1");
            assertEquals(initialSize + 2, nf.numberContents());
            assertTrue(nf.consult("Serie prueba") instanceof Series);
        } catch (NeoFlixException e){
            fail("Threw a exception " + e.getMessage());
        }
    }

    /* ---------- (i) El nombre ya existe ---------- */

    @Test
    public void shouldNotAddAContentIfTheNameAlreadyExists(){
        try {
            nf.addSeries("Serie prueba", "2020", "The Hidden Village");
        } catch (NeoFlixException e){
            fail("Threw a exception " + e.getMessage());
        }
        try {
            nf.addSeries("Serie prueba", "2019", "The Hidden Village");
            fail("Did not throw exception");
        } catch (NeoFlixException e){
            assertEquals(NeoFlixException.NAME_ALREADY_EXISTS, e.getMessage());
            assertEquals(initialSize + 1, nf.numberContents());
        }
    }

    /* ---------- (ii) Valores no numericos ---------- */

    @Test
    public void shouldNotAllowLettersWhenItIsExpectingNumbers(){
        try {
            nf.addEpisode("Episodio 1", "?", "2", "abc", "abc", "130");
            fail("Did not throw exception");
        } catch (NeoFlixException e){
            assertEquals(NeoFlixException.NOT_NUMERIC, e.getMessage());
            assertEquals(initialSize, nf.numberContents());
        }
    }

    @Test
    public void shouldNotAllowLettersInTheYear(){
        try {
            nf.addSeries("Serie prueba", "dos mil", "The Hidden Village");
            fail("Did not throw exception");
        } catch (NeoFlixException e){
            assertEquals(NeoFlixException.NOT_NUMERIC, e.getMessage());
            assertEquals(initialSize, nf.numberContents());
        }
    }

    /* ---------- (iii) Valores no esperados ---------- */

    @Test
    public void shouldNotAllowMoreCompletedThanAttempts(){
        try {
            nf.addEpisode("Episodio 1", "?", "2", "5", "10", "50");
            fail("Did not throw exception");
        } catch (NeoFlixException e){
            assertEquals(NeoFlixException.UNEXPECTED_VALUE, e.getMessage());
            assertEquals(initialSize, nf.numberContents());
        }
    }

    @Test
    public void shouldNotAllowSumVotesGreaterThanTenTimesVotes(){
        try {
            nf.addEpisode("Episodio 1", "?", "10", "5", "10", "150");
            fail("Did not throw exception");
        } catch (NeoFlixException e){
            assertEquals(NeoFlixException.UNEXPECTED_VALUE, e.getMessage());
            assertEquals(initialSize, nf.numberContents());
        }
    }

    /* ---------- (iv) Nueva condicion: el episodio no existe ---------- */

    @Test
    public void shouldNotAddASeriesWithUnknownEpisodes(){
        try {
            nf.addSeries("Serie prueba", "2020", "Episodio que no existe");
            fail("Did not throw exception");
        } catch (NeoFlixException e){
            assertEquals(NeoFlixException.EPISODE_NOT_FOUND, e.getMessage());
            assertEquals(initialSize, nf.numberContents());
        }
    }
    
    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @AfterEach
    public void tearDown()
    {
    }
}