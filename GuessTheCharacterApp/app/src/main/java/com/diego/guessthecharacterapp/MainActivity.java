package com.diego.guessthecharacterapp;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    public ImageView imageView;
    public TextView score;
    public Button button0;
    public Button button1;
    public Button button2;
    public Button button3;
    public List<String> imageList;
    public List<String> nameList;
    int correctAnswer;
    String nameAnswer;
    ArrayList<Integer> excludeList;

    int rightAnswers;
    int numquestions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageList = new ArrayList<String>();
        nameList = new ArrayList<String>();

        score = (TextView) findViewById(R.id.score);

        imageView = (ImageView) findViewById(R.id.imageView);
        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);

        String website = downloadUrl();
        getCharactersPictures(website);
        getCharactersNames(website);
        excludeList = new ArrayList<Integer>();

        playGame();

    }

    public void checkAnswer(View view){

        int botaoSelected = Integer.valueOf(view.getTag().toString());

        if(botaoSelected == correctAnswer){

            Toast.makeText(getApplicationContext(), "You are right! It is "+nameAnswer+"!",Toast.LENGTH_SHORT).show();
            rightAnswers++;

        } else {

            Toast.makeText(getApplicationContext(), "Wrong! The answer is "+nameAnswer+"!",Toast.LENGTH_SHORT).show();


        }

        numquestions++;
        score.setText("SCORE: "+rightAnswers + "/" + numquestions);
        playGame();
    }

    public void playGame(){

        if(excludeList.size() < 50){

            Random rand = new Random();
            int characterValue = 0;
            int random1;
            int random2;
            int random3;

            ArrayList randomExcludes = new ArrayList();

            //list of exclusions for characters
            characterValue = getRandomWithExclusion(rand, 50, excludeList);
            excludeList.add(characterValue);

            downloadImage(imageList.get(characterValue));
            nameAnswer = nameList.get(characterValue);

            //generates the position of correct answer
            correctAnswer = rand.nextInt(3);

            if (correctAnswer == 0) {
                button0.setText(nameList.get(characterValue));
                randomExcludes.add(characterValue);
                random1 = getRandomWithExclusion(rand, 50, randomExcludes);
                randomExcludes.add(random1);
                random2 = getRandomWithExclusion(rand, 50, randomExcludes);
                randomExcludes.add(random2);
                random3 = getRandomWithExclusion(rand, 50, randomExcludes);
                button1.setText(nameList.get(random1));
                button2.setText(nameList.get(random2));
                button3.setText(nameList.get(random3));
            } else if (correctAnswer == 1) {
                button1.setText(nameList.get(characterValue));
                randomExcludes.add(characterValue);
                random1 = getRandomWithExclusion(rand, 50, randomExcludes);
                randomExcludes.add(random1);
                random2 = getRandomWithExclusion(rand, 50, randomExcludes);
                randomExcludes.add(random2);
                random3 = getRandomWithExclusion(rand, 50, randomExcludes);
                button0.setText(nameList.get(random1));
                button2.setText(nameList.get(random2));
                button3.setText(nameList.get(random3));
            } else if (correctAnswer == 2) {
                button2.setText(nameList.get(characterValue));
                randomExcludes.add(characterValue);
                random1 = getRandomWithExclusion(rand, 50, randomExcludes);
                randomExcludes.add(random1);
                random2 = getRandomWithExclusion(rand, 50, randomExcludes);
                randomExcludes.add(random2);
                random3 = getRandomWithExclusion(rand, 50, randomExcludes);
                button0.setText(nameList.get(random1));
                button1.setText(nameList.get(random2));
                button3.setText(nameList.get(random3));
            } else if (correctAnswer == 3) {
                button3.setText(nameList.get(characterValue));
                randomExcludes.add(characterValue);
                random1 = getRandomWithExclusion(rand, 50, randomExcludes);
                randomExcludes.add(random1);
                random2 = getRandomWithExclusion(rand, 50, randomExcludes);
                randomExcludes.add(random2);
                random3 = getRandomWithExclusion(rand, 50, randomExcludes);
                button0.setText(nameList.get(random1));
                button1.setText(nameList.get(random2));
                button2.setText(nameList.get(random3));
            }
        } else {

            endGame();

        }
    }

    public int getRandomWithExclusion(Random rnd, int bound, ArrayList exclude) {
        int random = rnd.nextInt(bound);
        boolean newOne = false;
        if(!exclude.contains(random)){
            newOne = true;
        } else {
            while(!newOne){
                random = rnd.nextInt(bound);
                if(!exclude.contains(random)){
                    newOne = true;
                }
            }
        }


        return random;
    }

    public void downloadImage(String url){

        ImageDownloader task = new ImageDownloader();
        Bitmap myImage;

        try {
            myImage = task.execute(url).get();
            imageView.setImageBitmap(myImage);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    public String downloadUrl(){

        DownloadTask task = new DownloadTask();
        String result = null;

        try {
            result = task.execute("https://www.gamedesigning.org/gaming/characters/").get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return result;
    }

    public void getCharactersPictures(String website){

        Pattern pattern = Pattern.compile("src=\"(.*?)\"");
        Matcher matcher = pattern.matcher(website);

        //we need just 50 images
        int i = 0;
        while(matcher.find()){

            if(i<50) {
                if (!matcher.group(1).contains("data:image") && !matcher.group(1).contains("characters.png")) {
                    if (!imageList.contains(matcher.group(1))) {
                        imageList.add(matcher.group(1));
                        i++;
                    }
                }

            }
        }

    }

    public void getCharactersNames(String website){

        Pattern pattern = Pattern.compile("alt=\"(.*?)\"");
        Matcher matcher = pattern.matcher(website);

        //we need just 50 names
        int i = 0;
        while(matcher.find()){

            if(i<50) {
                if (!matcher.group(1).contains("data:image") && !matcher.group(1).contains("characters")) {
                    if (!nameList.contains(matcher.group(1))) {
                        nameList.add(matcher.group(1));
                        i++;
                    }
                }

            }
        }

    }

    public void endGame(){

        button0.setVisibility(View.INVISIBLE);
        button1.setVisibility(View.INVISIBLE);
        button2.setVisibility(View.INVISIBLE);
        button3.setVisibility(View.INVISIBLE);
        Toast.makeText(getApplicationContext(), "Congratulations! You did "+rightAnswers+"/"+numquestions+"!",Toast.LENGTH_SHORT).show();

    }

}


