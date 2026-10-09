package test;
import domain.*;


import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class SeriesTest{
   
 
    @Test
    public void shouldCalculateSeriessRating(){                              
        Series s = new Series("The Neo Ninja", 2020);
        s.addEpisode(new Episode("The Hidden Village",s,120,199,50,430));
        s.addEpisode(new Episode("The First Mission",s,140,126,60,510));
        s.addEpisode(new Episode("The Rival",s,120,199,50,430));
        s.addEpisode(new Episode("The Tournament",s,180, 162, 90, 810));
        s.addEpisode(new Episode("The Final Battle",s, 200, 190, 120, 1080));
        try {
            assertEquals(8,s.rating());
        } catch (NeoFlixException e){
            fail("Threw a exception "+e.getMessage());
        }    
    }    

    
    @Test
    public void shouldThrowExceptionIfTheSeriessHasNoEpisodes(){
        Series s = new Series("The Neo Ninja", 2020);
        try { 
            int value=s.rating();
            fail("Did not throw exception");
        } catch (NeoFlixException e) {
            assertEquals(NeoFlixException.CONTENT_EMPTY,e.getMessage());
        }    
    }    
    
    
    @Test
    public void shouldThrowExceptionWhenAnEpisodeRatingIsUnknown(){
        Series s = new Series("The Neo Ninja", 2020);
        s.addEpisode(new Episode("The Hidden Village",s,120,199,50,430));
        s.addEpisode(new Episode("The First Mission",s,140,126,60,510));
        s.addEpisode(new Episode("The Rival",s,120,199,0,0));
        s.addEpisode(new Episode("The Tournament",s,180, 162, 90, 810));
        s.addEpisode(new Episode("The Final Battle",s, 200, 190, 120, 1080));
        try { 
            int value=s.rating();
            fail("Did not throw exception");
        } catch (NeoFlixException e) {
            assertEquals(NeoFlixException.VALUE_UNKNOWN,e.getMessage());
        }    
    } 
    
    @Test
    public void shouldThrowExceptionIfAEpisodeContainsInvalidRatingData(){
        Series s = new Series("The Neo Ninja", 2020);
        s.addEpisode(new Episode("The Hidden Village",s,120,199,50,430));
        s.addEpisode(new Episode("The First Mission",s,140,126,60,610));
        s.addEpisode(new Episode("The Rival",s,120,199,0,0));
        s.addEpisode(new Episode("The Tournament",s,180, 162, 90, 810));
        s.addEpisode(new Episode("The Final Battle",s, 200, 190, 120, 1080));
        try { 
            int value=s.rating();
            fail("Did not throw exception");
        } catch (NeoFlixException e) {
            assertEquals(NeoFlixException.DATA_ERROR,e.getMessage());
        }    
    }     
    

    
    
    @Test
    public void should1(){
        Series s = new Series("The Neo Ninja", 2020);
        s.addEpisode(new Episode("The Hidden Village",s,120,199,100,1000));
        s.addEpisode(new Episode("The First Mission",s,140,126,50,400));
        s.addEpisode(new Episode("The Rival",s,120,199,20,120));
        s.addEpisode(new Episode("The Tournament",s,180, 162, 10, 40));
        try { 
            int value=s.rating(10);
            assertEquals(7, value);
        } catch (NeoFlixException e) {
            fail("Threw a exception "+e.getMessage());
        } 
    }
    
    @Test
    public void should2(){
        Series s = new Series("The Neo Ninja", 2020);
        s.addEpisode(new Episode("The Hidden Village",s,120,199,100,1000));
        s.addEpisode(new Episode("The First Mission",s,140,126,50,400));
        s.addEpisode(new Episode("The Rival",s,120,199,0,0));
        s.addEpisode(new Episode("The Tournament",s,180, 162, 10, 40));
        try { 
            int value=s.rating(10);
            assertEquals(8, value);
        } catch (NeoFlixException e) {
            fail("Threw a exception "+e.getMessage());
        } 
    }
    
    @Test
    public void should3(){
        Series s = new Series("The Neo Ninja", 2020);
        s.addEpisode(new Episode("The Hidden Village",s,120,199,100,1000));
        s.addEpisode(new Episode("The First Mission",s,140,126,50,540));
        s.addEpisode(new Episode("The Rival",s,120,199,20,120));
        s.addEpisode(new Episode("The Tournament",s,180, 162, 10, 40));
        try { 
            int value=s.rating(10);
            assertEquals(6, value);
        } catch (NeoFlixException e) {
            fail("Threw a exception "+e.getMessage());
        } 
    }

    @Test
    public void shouldCombine(){
        Series s = new Series("The Neo Ninja", 2020);
        s.addEpisode(new Episode("The Hidden Village",s,120,199,100,1000));
        s.addEpisode(new Episode("The First Mission",s,140,126,50,540));
        s.addEpisode(new Episode("The Rival",s,120,199,0,0));
        s.addEpisode(new Episode("The Tournament",s,180, 162, 10, 40));
        try { 
            int value=s.rating(10);
            assertEquals(8, value);
        } catch (NeoFlixException e) {
            fail("Threw a exception "+e.getMessage());
        } 
    }    
    
    @Test
    public void should4(){
        Series s = new Series("The Neo Ninja", 2020);
        s.addEpisode(new Episode("The Hidden Village",s,120,199,100,10000));
        s.addEpisode(new Episode("The First Mission",s,140,126,50,540));
        s.addEpisode(new Episode("The Rival",s,120,199,20,1200));
        s.addEpisode(new Episode("The Tournament",s,180, 162, 10, 400));
        try { 
            int value=s.rating(10);
            fail("Did not throw exception");
        } catch (NeoFlixException e) {
            assertEquals(NeoFlixException.UNKNOWN_VALUE, e.getMessage());
        } 
    }
    
    
    
    
    @Test
    public void should5(){
        Series s = new Series("The Neo Ninja", 2020);
        s.addEpisode(new Episode("The Hidden Village",s,120,199,100,1000));
        s.addEpisode(new Episode("The First Mission",s,140,126,50,400));
        s.addEpisode(new Episode("The Rival",s,120,199,20,120));
        s.addEpisode(new Episode("The Tournament",s,180, 162, 10, 40));
        try { 
            int value=s.rating(true);
            assertEquals(7, value);
        } catch (NeoFlixException e) {
            fail("Threw a exception "+e.getMessage());
        } 
    }   
    
    @Test
    public void should6(){
        Series s = new Series("The Neo Ninja", 2020);
        s.addEpisode(new Episode("The Hidden Village",s,120,199,100,1000));
        s.addEpisode(new Episode("The First Mission",s,140,126,50,400));
        s.addEpisode(new Episode("The Rival",s,120,199,0,0));
        s.addEpisode(new Episode("The Tournament",s,180, 162, 10, 40));
        try { 
            int value=s.rating(true);
            assertEquals(7, value);
        } catch (NeoFlixException e) {
            fail("Threw a exception "+e.getMessage());
        } 
    }   
    
    @Test
    public void should7(){
        Series s = new Series("The Neo Ninja", 2020);
        s.addEpisode(new Episode("The Hidden Village",s,120,199,100,1000));
        s.addEpisode(new Episode("The First Mission",s,140,126,50,540));
        s.addEpisode(new Episode("The Rival",s,120,199,20,120));
        s.addEpisode(new Episode("The Tournament",s,180, 162, 10, 40));
        try { 
            int value=s.rating(true);
            assertEquals(7, value);
        } catch (NeoFlixException e) {
            fail("Threw a exception "+e.getMessage());
        } 
    }
    
    @Test
    public void shouldCombine2(){
        Series s = new Series("The Neo Ninja", 2020);
        s.addEpisode(new Episode("The Hidden Village",s,120,199,100,1000));
        s.addEpisode(new Episode("The First Mission",s,140,126,50,540));
        s.addEpisode(new Episode("The Rival",s,120,199,0,0));
        s.addEpisode(new Episode("The Tournament",s,180, 162, 10, 40));
        try { 
            int value=s.rating(true);
            assertEquals(8, value);
        } catch (NeoFlixException e) {
            fail("Threw a exception "+e.getMessage());
        } 
    }   
    
    @Test
    public void should8(){
        Series s = new Series("The Neo Ninja", 2020);
        s.addEpisode(new Episode("The Final Battle",s, 200, 190, 0, 0));
        s.addEpisode(new Episode("The Hidden Village",s,120,199,100,1000));
        s.addEpisode(new Episode("The First Mission",s,140,126,50,400));
        s.addEpisode(new Episode("The Rival",s,120,199,20,120));
        s.addEpisode(new Episode("The Tournament",s,180, 162, 10, 40));
        try { 
            int value=s.rating(true);
            fail("Did not throw exception");
        } catch (NeoFlixException e) {
            assertEquals(NeoFlixException.VALUE_UNKNOWN, e.getMessage());
        } 
    }      

    
    
    
    
    @Test
    public void should9(){                              
        Series s = new Series("The Neo Ninja", 2020);
        s.addEpisode(new Episode("The Hidden Village",s,199,120,50,430));
        s.addEpisode(new Episode("The First Mission",s,140,126,60,510));
        s.addEpisode(new Episode("The Rival",s,199,120,50,430));
        s.addEpisode(new Episode("The Tournament",s,180, 162, 90, 810));
        s.addEpisode(new Episode("The Final Battle",s, 200, 190, 120, 1080));
        try {
            assertEquals(79, s.popularity());
        } catch (NeoFlixException e){
            fail("Threw a exception "+e.getMessage());
        }    
    }  
    
    @Test
    public void should10(){                              
        Series s = new Series("The Neo Ninja", 2020);
        s.addEpisode(new Episode("The Hidden Village",s,199,120,50,430));
        s.addEpisode(new Episode("The First Mission",s,140,126,60,510));
        s.addEpisode(new Episode("The Rival",s,199,120,50,430));
        s.addEpisode(new Episode("The Tournament",s,180, 162, 90, 810));
        s.addEpisode(new Episode("The Final Battle",s, 200, 1900, 120, 1080));
        try {
           int value = s.popularity();
           fail("Did not throw exception");
        } catch (NeoFlixException e){
            assertEquals(NeoFlixException.DATA_ERROR, e.getMessage());
        }    
    }     
    
    @Test
    public void should11(){                              
        Series s = new Series("The Neo Ninja", 2020);
        s.addEpisode(new Episode("The Hidden Village",s,199,120,50,430));
        s.addEpisode(new Episode("The First Mission",s,140,126,60,510));
        s.addEpisode(new Episode("The Rival",s,0,0,50,430));
        s.addEpisode(new Episode("The Tournament",s,180, 162, 90, 810));
        s.addEpisode(new Episode("The Final Battle",s, 200, 190, 120, 1080));
        try {
           int value = s.popularity();
           fail("Did not throw exception");
        } catch (NeoFlixException e){
            assertEquals(NeoFlixException.VALUE_UNKNOWN, e.getMessage());
        }    
    }  
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}