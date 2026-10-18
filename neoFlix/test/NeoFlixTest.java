package test;



import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
    @Before
    public void setUp() throws NeoFlixException {
        nf = new NeoFlix();
        initialSize = nf.numberContents();
    }

    /* ---------- Listar ----------*/
    @Test
    public void shouldListTheEpisodes(){
        try {
            nf.addEpisode("Episodio 1", "?", "100", "80", "10", "70");
            String expected = "8 elementos\n"
                            + ">The Hidden Village\n"
                            + ">The First Mission\n"
                            + ">The Secret Weapon\n"
                            + ">The Rival\n"
                            + ">The Tournament\n"
                            + ">The Final Battle\n"
                            + ">The Neo Ninja: 2020"
                            + "\n\tThe Hidden Village"
                            + "\n\tThe First Mission"
                            + "\n\tThe Secret Weapon"
                            + "\n\tThe Rival"
                            + "\n\tThe Tournament"
                            + "\n\tThe Final Battle\n"
                            + ">Episodio 1\n";
            assertEquals(expected, nf.toString());
        } catch (NeoFlixException e){
            fail("Threw a exception " + e.getMessage());
        }
    }

    /* ---------- Aceptacion ----------*/
    @Test
    public void acceptanceTestShouldAddASeriesAndFindIt(){
        try {
            nf.addEpisode("Pilot", "", "100", "80", "10", "70");
            nf.addSeries("Mi Serie", "2026", "Pilot");
            assertEquals("Mi Serie: 2026 ( 80 - 7 )\n\tPilot ( 80 - 7 )", nf.consult("Mi Serie").data(true));
        } catch (NeoFlixException e){
            fail("Threw a exception " + e.getMessage());
        }
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
    
    /* ---------- Busqueda ----------*/
    @Test
    public void shouldSearchForExistingContent(){
        try {
            nf.addEpisode("prueba", "?", "1", "1", "1", "1");
            String responSearch = nf.search("prueba");
            assertEquals("1 elementos\n>prueba ( 100 - 1 ) \n", responSearch);
        } catch (NeoFlixException e){
            fail("Threw a exception " + e.getMessage());
        }
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }
}