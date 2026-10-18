package domain;  

import java.util.ArrayList;

public class Series extends Content{
    private int year;
    private ArrayList<Episode> episodes;
    

    public Series(String title, int year){
        super(title);
        this.year=year;
        episodes= new ArrayList<Episode>();
    }



    public void addEpisode(Episode e){
        episodes.add(e);
    }

    
    /**
    * Returns the calculated rating
    * @return the average of the ratings of all episodes
    * @throws NeoFlixException, CONTENT_EMPTY if the series has no episodes
    *                         , VALUE_UNKNOWN if the rating for any episode is unknown
    *                         , DATA_ERROR if the rating for any episode cannot be calculated due to a data error
    */
    @Override
    public int rating() throws NeoFlixException{
        if(episodes.isEmpty()) throw new NeoFlixException(NeoFlixException.CONTENT_EMPTY);
        int total = 0;
        for(Episode ep: episodes){
            total += ep.rating();
        }
        return total/episodes.size();
    }
    

    /**
    * Returns the calculated rating, using the default value for episodes with an unknown rating and ignoring those with errors.
    * @return the average of the ratings of all episodes, using the default value for episodes with an unknown rating and ignoring those with errors
    * @throws NeoFlixException, UNKNOWN_VALUE if the rating cannot be calculated.
    */
    public int rating(int default_) throws NeoFlixException{
        int cont = 0;
        int total = 0;
        
        for(Episode ep : episodes){
            try{
                total += ep.rating();
                cont++;
            }catch(NeoFlixException e){
                if(NeoFlixException.VALUE_UNKNOWN.equals(e.getMessage())){
                    total += default_;
                    cont++;
                }
            }
        }
        if (cont == 0) throw new NeoFlixException(NeoFlixException.UNKNOWN_VALUE);
        return total/cont;
    }

    //If an episode has no rating, use the average of the previous episodes or of all episodes, depending on the value of the previous parameter.
    //Throw CONTENT_EMPTY and VALUE_UNKNOWN if either of these cases occurs.
    /**
     * Returns the calculated rating for the series, using the average of previous episodes or all episodes depending on the 'previous' parameter.
     * @param previous If true, use the average of previous episodes for episodes with unknown ratings. If false, use the average of all episodes.
     * @return the average of the ratings of all episodes
     * @throws NeoFlixException 
     */
    public int rating(boolean previous) throws NeoFlixException{
        if(episodes.isEmpty()) throw new NeoFlixException(NeoFlixException.CONTENT_EMPTY);
        int cont = 0;
        int total = 0;
        
        for(Episode ep : episodes){
            try{
                total += ep.rating();
                cont++;
            }catch(NeoFlixException e){
                if(previous){
                    if(cont == 0) throw new NeoFlixException(NeoFlixException.VALUE_UNKNOWN);
                    total += (int) total/cont;
                    cont++;
                }else{
                    // Por implementar
                }
            }
        }
        return total/cont;
    }
    
    /**    (non-Javadoc)
     * Returns the calculated popularity for the series, which is the average of the popularity of all episodes.
     * @return the average of the popularity of all episodes
     * @throws NeoFlixException, CONTENT_EMPTY if the series has no episodes
     */
    public int popularity() throws NeoFlixException{
        if(episodes.isEmpty()) throw new NeoFlixException(NeoFlixException.CONTENT_EMPTY);
        int total = 0;
        try{
            for(Episode ep: episodes){
                total += ep.popularity();
            }
        }catch(NeoFlixException e){
            throw new NeoFlixException(NeoFlixException.UNKNOWN_VALUE);
        }
        return total/episodes.size();
    }
    
    /**    (non-Javadoc)
     * Returns the data of the series, including its title, year, and the data of all its episodes.
     * @param withIndicators If true, include the popularity and rating indicators in the data.
     * @return the data of the series
     * @throws NeoFlixException, CONTENT_EMPTY if the series has no episodes
     */
    @Override
    public String data(boolean withIndicators) throws NeoFlixException{
        StringBuffer answer=new StringBuffer();
        answer.append(title+": "+year+ ( withIndicators ? " ( " +popularity()+" - "+ rating()+" )":""));
        for(Episode e: episodes) {
            if (e == null) throw new NeoFlixException(NeoFlixException.CONTENT_EMPTY);
            answer.append("\n\t"+(e.data(withIndicators)));
        }
        return answer.toString();
    } 
}
