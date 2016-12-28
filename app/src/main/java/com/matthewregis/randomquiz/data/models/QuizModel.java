package com.matthewregis.randomquiz.data.models;

import java.util.List;

/**
 * Created by reg on 27/11/2016.
 */

public class QuizModel {

    /**
     * response_code : 0
     * results : [{"category":"Entertainment: Film","type":"multiple","difficulty":"easy","question":"What breed of dog was &#039;Marley&#039; in the film &#039;Marley &amp; Me&#039;?","correct_answer":"Labrador Retriever","incorrect_answers":["Golden Retriever","Dalmatian","Shiba Inu"]},{"category":"History","type":"multiple","difficulty":"hard","question":"When did the French Revolution begin?","correct_answer":"1789","incorrect_answers":["1823","1756","1799"]},{"category":"Entertainment: Music","type":"multiple","difficulty":"easy","question":"Which rap group released the album &quot;Straight Outta Compton&quot;?","correct_answer":"N.W.A","incorrect_answers":["Wu-Tang Clan","Run-D.M.C.","Beastie Boys"]},{"category":"Entertainment: Video Games","type":"multiple","difficulty":"medium","question":"In what engine was Titanfall made in?","correct_answer":"Source Engine","incorrect_answers":["Frostbite 3","Unreal Engine","Cryengine"]},{"category":"Entertainment: Books","type":"boolean","difficulty":"easy","question":"The book 1984 was published in 1949.","correct_answer":"True","incorrect_answers":["False"]},{"category":"Vehicles","type":"boolean","difficulty":"medium","question":"The Chevrolet Corvette has always been made exclusively with V8 engines only.","correct_answer":"False","incorrect_answers":["True"]},{"category":"Sports","type":"multiple","difficulty":"medium","question":"The F1 season of 1994 is remembered for what tragic event?","correct_answer":"Death of Ayrton Senna (San Marino)","incorrect_answers":["The Showdown (Australia)","Verstappen on Fire (Germany)","Schumacher&#039;s Ban (Britain)"]},{"category":"Entertainment: Japanese Anime & Manga","type":"multiple","difficulty":"easy","question":"What is the name of the stuffed lion in Bleach?","correct_answer":"Kon","incorrect_answers":["Jo","Urdiu","Chad"]},{"category":"Entertainment: Music","type":"multiple","difficulty":"medium","question":"Which of these is the name of an American psychedelic rock band formed in 2002 by Benjamin Goldwasser and Andrew VanWyngarden?","correct_answer":"MGMT","incorrect_answers":["MSTRKRFT","STRFKR","SBTRKT"]},{"category":"Entertainment: Television","type":"multiple","difficulty":"medium","question":"Which character does voice actress Tara Strong NOT voice?","correct_answer":"Bubbles (2016)","incorrect_answers":["Twilight Sparkle","Timmy Turner","Harley Quinn"]}]
     */

    private int response_code;
    private List<ResultsBean> results;

    public int getResponse_code() {
        return response_code;
    }

    public void setResponse_code(int response_code) {
        this.response_code = response_code;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }


}
