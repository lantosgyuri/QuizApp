
package com.example.android.quizapp;

import android.widget.EditText;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    int points;
    int checkBoxCounter;
    int radioBoxCounter;
    int checkBoxPoint;
    public View.OnClickListener setcheckBoxQuestion3 = new View.OnClickListener() {
        public void onClick(View view) {

            CheckBox checkBoxAnswer1 = findViewById(R.id.checkbox_answer1);
            boolean answer1 = checkBoxAnswer1.isChecked();

            CheckBox checkBoxAnswer2 = findViewById(R.id.checkbox_answer2);
            boolean answer2 = checkBoxAnswer2.isChecked();

            CheckBox checkBoxAnswer3 = findViewById(R.id.checkbox_answer3);
            boolean answer3 = checkBoxAnswer3.isChecked();

            if (answer1 && answer2 && !answer3) {
                points++;
                checkBoxPoint++;
            }

            /*
             make toast. Using the checkBoxpoint as a condition.
             Im call this just by the 3. Question. So I know he has already answered 3 question.
              */
            if (checkBoxPoint == 3) {
                Toast.makeText(getApplicationContext(), getApplicationContext().getString(R.string.toast_good),
                        Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplicationContext(), getApplicationContext().getString(R.string.toast_bad),
                        Toast.LENGTH_LONG).show();
            }
            checkBoxCounter++;
            setCheckBox();
        }
    };
    public View.OnClickListener setcheckBoxQuestion2 = new View.OnClickListener() {
        public void onClick(View view) {

            CheckBox checkBoxAnswer1 = findViewById(R.id.checkbox_answer1);
            boolean answer1 = checkBoxAnswer1.isChecked();

            CheckBox checkBoxAnswer2 = findViewById(R.id.checkbox_answer2);
            boolean answer2 = checkBoxAnswer2.isChecked();

            CheckBox checkBoxAnswer3 = findViewById(R.id.checkbox_answer3);
            boolean answer3 = checkBoxAnswer3.isChecked();

            if (answer1 && !answer2 && !answer3) {
                points++;
                checkBoxPoint++;
            }
            checkBoxCounter++;
            setCheckBox();
        }
    };
    // Method for checkig the right answer by case 0
    public View.OnClickListener setcheckBoxQuestion1 = new View.OnClickListener() {
        public void onClick(View view) {
            CheckBox checkBoxAnswer1 = findViewById(R.id.checkbox_answer1);
            boolean answer1 = checkBoxAnswer1.isChecked();

            CheckBox checkBoxAnswer2 = findViewById(R.id.checkbox_answer2);
            boolean answer2 = checkBoxAnswer2.isChecked();

            CheckBox checkBoxAnswer3 = findViewById(R.id.checkbox_answer3);
            boolean answer3 = checkBoxAnswer3.isChecked();

            if (!answer1 && !answer2 && answer3) {
                points++;
                checkBoxPoint++;
            }

            checkBoxCounter++;
            //calling the setCheckbox method/ set the new case
            setCheckBox();
        }
    };
    int radioBoxPoint;
    public View.OnClickListener setRadioBoxQuestion3 = new View.OnClickListener() {
        public void onClick(View view) {

            RadioButton radioBoxAnswer1 = findViewById(R.id.radiobutton_answer1);
            boolean answer1 = radioBoxAnswer1.isChecked();

            if (answer1) {
                points++;
                radioBoxPoint++;
            }

            radioBoxCounter++;

            if (radioBoxPoint == 3) {
                Toast.makeText(getApplicationContext(), getApplicationContext().getString(R.string.toast_good),
                        Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplicationContext(), getApplicationContext().getString(R.string.toast_bad),
                        Toast.LENGTH_LONG).show();
            }

            setRadioBox();
        }
    };
    public View.OnClickListener setRadioBoxQuestion2 = new View.OnClickListener() {
        public void onClick(View view) {

            RadioButton radioBoxAnswer3 = findViewById(R.id.radiobutton_answer3);
            boolean answer3 = radioBoxAnswer3.isChecked();

            if (answer3) {
                points++;
                radioBoxPoint++;
            }

            radioBoxCounter++;
            setRadioBox();
        }
    };
    public View.OnClickListener setRadioBoxQuestion1 = new View.OnClickListener() {
        public void onClick(View view) {
            RadioButton radioBoxAnswer2 = findViewById(R.id.radiobutton_answer2);
            boolean answer2 = radioBoxAnswer2.isChecked();

            if (answer2) {
                points++;
                radioBoxPoint++;
            }

            radioBoxCounter++;
            setRadioBox();
        }
    };
    boolean isNotAnswered = true;
    boolean isPhotoTaken = false;
    String name;
    Bitmap photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) getNamePop();

        setCheckBox();
        setRadioBox();
        displayName();
        setFindCatQuestion();
        setCertificateBg();
        setPoints();
    }

    //pop up window for get the name
    public void getNamePop() {
        startActivityForResult(new Intent(getApplicationContext(), pop.class), 1);
    }

    public void displayName() {
        TextView headText = findViewById(R.id.name_text_head);
        headText.setText(name);

        TextView imageText = findViewById(R.id.name_text_image);
        imageText.setText(name);
    }

    //on rotation problem solved
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        points = savedInstanceState.getInt("points");
        checkBoxCounter = savedInstanceState.getInt("checkBoxCounter");
        radioBoxCounter = savedInstanceState.getInt("radioBoxCounter");
        checkBoxPoint = savedInstanceState.getInt("checkBoxPoint");
        radioBoxPoint = savedInstanceState.getInt("radioBoxPoint");
        name = savedInstanceState.getString("name");
        isNotAnswered = savedInstanceState.getBoolean("isNotAnswered");
        isPhotoTaken = savedInstanceState.getBoolean("isPhotoTaken");
        photo = savedInstanceState.getParcelable("photo");
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("points", points);
        outState.putInt("checkBoxCounter", checkBoxCounter);
        outState.putInt("radioBoxCounter", radioBoxCounter);
        outState.putInt("checkBoxPoint", checkBoxPoint);
        outState.putInt("radioBoxPoint", radioBoxPoint);
        outState.putString("name", name);
        outState.putBoolean("isNotAnswered", isNotAnswered);
        outState.putBoolean("isPhotoTaken", isPhotoTaken);
        outState.putParcelable("photo", photo);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onResume() {
        setCheckBox();
        setRadioBox();
        displayName();
        setFindCatQuestion();
        setCertificateBg();
        setPoints();
        super.onResume();
    }

    //Get the name from PopActivity and save it in the name variable
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    name = data.getStringExtra("name");
                    displayName();
                }
                break;

            case 2:
                // get the background picture and set it for "certificate" layout
                RelativeLayout certificate_layout = findViewById(R.id.certificate_layout);
                if (data != null) {
                    Bundle extras = data.getExtras();
                    if (extras != null) {
                        photo = (Bitmap) extras.get("data");
                        if (photo != null) {
                            isPhotoTaken = true;
                            certificate_layout.setBackground(new BitmapDrawable(getResources(), photo));
                        }
                    }
                }
                break;
        }
    }

    /*
    set the checkbox with switch/case.
    the checkBoxCounter is for count the row and switch the cases.
     */
    public void setCheckBox() {

        String[] checkBoxQuestions = {getString(R.string.checkbox_q1), getString(R.string.checkbox_q2),
                getString(R.string.checkbox_q3)};

        String[][] checkBoxAnswers = {
                {getString(R.string.checkbox_a11), getString(R.string.checkbox_a12), getString(R.string.checkbox_a13)},
                {getString(R.string.checkbox_a21), getString(R.string.checkbox_a22), getString(R.string.checkbox_a23)},
                {getString(R.string.checkbox_a31), getString(R.string.checkbox_a32), getString(R.string.checkbox_a33)}
        };

        Button next_button = findViewById(R.id.checkbox_button);
        TextView points_text = findViewById(R.id.point_text_head);
        points_text.setText(getString(R.string.points) + points);


        switch (checkBoxCounter) {
            case 0:
                // setting up the texts for Question and answers
                TextView checkBoxQuestion = findViewById(R.id.checkbox_question);
                checkBoxQuestion.setText(checkBoxQuestions[checkBoxCounter]);

                CheckBox checkBoxAnswer1 = findViewById(R.id.checkbox_answer1);
                checkBoxAnswer1.setText(checkBoxAnswers[checkBoxCounter][0]);

                CheckBox checkBoxAnswer2 = findViewById(R.id.checkbox_answer2);
                checkBoxAnswer2.setText(checkBoxAnswers[checkBoxCounter][1]);

                CheckBox checkBoxAnswer3 = findViewById(R.id.checkbox_answer3);
                checkBoxAnswer3.setText(checkBoxAnswers[checkBoxCounter][2]);

                //I call by every case, another method, which pass for the case.
                next_button.setOnClickListener(setcheckBoxQuestion1);

                break;

            case 1:
                TextView checkBoxQuestion2 = findViewById(R.id.checkbox_question);
                checkBoxQuestion2.setText(checkBoxQuestions[checkBoxCounter]);

                CheckBox checkBoxAnswer21 = findViewById(R.id.checkbox_answer1);
                checkBoxAnswer21.setText(checkBoxAnswers[checkBoxCounter][0]);

                CheckBox checkBoxAnswer22 = findViewById(R.id.checkbox_answer2);
                checkBoxAnswer22.setText(checkBoxAnswers[checkBoxCounter][1]);

                CheckBox checkBoxAnswer23 = findViewById(R.id.checkbox_answer3);
                checkBoxAnswer23.setText(checkBoxAnswers[checkBoxCounter][2]);

                next_button.setOnClickListener(setcheckBoxQuestion2);

                break;

            case 2:
                TextView checkBoxQuestion3 = findViewById(R.id.checkbox_question);
                checkBoxQuestion3.setText(checkBoxQuestions[checkBoxCounter]);

                CheckBox checkBoxAnswer31 = findViewById(R.id.checkbox_answer1);
                checkBoxAnswer31.setText(checkBoxAnswers[checkBoxCounter][0]);

                CheckBox checkBoxAnswer32 = findViewById(R.id.checkbox_answer2);
                checkBoxAnswer32.setText(checkBoxAnswers[checkBoxCounter][1]);

                CheckBox checkBoxAnswer33 = findViewById(R.id.checkbox_answer3);
                checkBoxAnswer33.setText(checkBoxAnswers[checkBoxCounter][2]);

                next_button.setOnClickListener(setcheckBoxQuestion3);

                break;

            case 3:
                //set answers and button invisible, and Inform the user, this part is over
                TextView checkBoxQuestion4 = findViewById(R.id.checkbox_question);
                checkBoxQuestion4.setText(getString(R.string.all_question));

                CheckBox checkBoxAnswer41 = findViewById(R.id.checkbox_answer1);
                checkBoxAnswer41.setVisibility(View.INVISIBLE);

                CheckBox checkBoxAnswer42 = findViewById(R.id.checkbox_answer2);
                checkBoxAnswer42.setVisibility(View.INVISIBLE);

                CheckBox checkBoxAnswer43 = findViewById(R.id.checkbox_answer3);
                checkBoxAnswer43.setVisibility(View.INVISIBLE);

                next_button.setVisibility(View.INVISIBLE);

        }


    }

    public void setRadioBox() {

        String[] radioBoxQuestions = {getString(R.string.radiobutton_q1), getString(R.string.radiobutton_q2),
                getString(R.string.radiobutton_q3)};

        String[][] radioBoxAnswers = {
                {getString(R.string.radiobutton_a11), getString(R.string.radiobutton_a12), getString(R.string.radiobutton_a13)},
                {getString(R.string.radiobutton_a21), getString(R.string.radiobutton_a22), getString(R.string.radiobutton_a23)},
                {getString(R.string.radiobutton_a31), getString(R.string.radiobutton_a32), getString(R.string.radiobutton_a33)}
        };

        Button next_button = findViewById(R.id.radiobutton_button);
        TextView points_text = findViewById(R.id.point_text_head);
        points_text.setText(getString(R.string.points) + points);


        switch (radioBoxCounter) {
            case 0:
                TextView radioBoxQuestion = findViewById(R.id.radiobutton_question);
                radioBoxQuestion.setText(radioBoxQuestions[radioBoxCounter]);

                RadioButton radioBoxAnswer1 = findViewById(R.id.radiobutton_answer1);
                radioBoxAnswer1.setText(radioBoxAnswers[radioBoxCounter][0]);

                RadioButton radioBoxAnswer2 = findViewById(R.id.radiobutton_answer2);
                radioBoxAnswer2.setText(radioBoxAnswers[radioBoxCounter][1]);

                RadioButton radioBoxAnswer3 = findViewById(R.id.radiobutton_answer3);
                radioBoxAnswer3.setText(radioBoxAnswers[radioBoxCounter][2]);

                next_button.setOnClickListener(setRadioBoxQuestion1);

                break;

            case 1:
                TextView radioBoxQuestion1 = findViewById(R.id.radiobutton_question);
                radioBoxQuestion1.setText(radioBoxQuestions[radioBoxCounter]);

                RadioButton radioBoxAnswer21 = findViewById(R.id.radiobutton_answer1);
                radioBoxAnswer21.setText(radioBoxAnswers[radioBoxCounter][0]);

                RadioButton radioBoxAnswer22 = findViewById(R.id.radiobutton_answer2);
                radioBoxAnswer22.setText(radioBoxAnswers[radioBoxCounter][1]);

                RadioButton radioBoxAnswer23 = findViewById(R.id.radiobutton_answer3);
                radioBoxAnswer23.setText(radioBoxAnswers[radioBoxCounter][2]);

                next_button.setOnClickListener(setRadioBoxQuestion2);

                break;

            case 2:
                TextView radioBoxQuestion2 = findViewById(R.id.radiobutton_question);
                radioBoxQuestion2.setText(radioBoxQuestions[radioBoxCounter]);

                RadioButton radioBoxAnswer31 = findViewById(R.id.radiobutton_answer1);
                radioBoxAnswer31.setText(radioBoxAnswers[radioBoxCounter][0]);

                RadioButton radioBoxAnswer32 = findViewById(R.id.radiobutton_answer2);
                radioBoxAnswer32.setText(radioBoxAnswers[radioBoxCounter][1]);

                RadioButton radioBoxAnswer33 = findViewById(R.id.radiobutton_answer3);
                radioBoxAnswer33.setText(radioBoxAnswers[radioBoxCounter][2]);

                next_button.setOnClickListener(setRadioBoxQuestion3);

                break;

            case 3:
                TextView radioBoxQuestion3 = findViewById(R.id.radiobutton_question);
                radioBoxQuestion3.setText(getString(R.string.all_question));

                RadioButton radioBoxAnswer41 = findViewById(R.id.radiobutton_answer1);
                radioBoxAnswer41.setVisibility(View.INVISIBLE);

                RadioButton radioBoxAnswer42 = findViewById(R.id.radiobutton_answer2);
                radioBoxAnswer42.setVisibility(View.INVISIBLE);

                RadioButton radioBoxAnswer43 = findViewById(R.id.radiobutton_answer3);
                radioBoxAnswer43.setVisibility(View.INVISIBLE);

                next_button.setVisibility(View.INVISIBLE);

        }


    }

    // shows the final sore and check the FindCat answer
    public void showMyScore(View view) {
        TextView textView = findViewById(R.id.findCat_question);
        EditText editText = findViewById(R.id.image_editText);

        //The null must have been tested or app crashes
        String testIsEmpty = editText.getText().toString();
        if (!testIsEmpty.isEmpty()) {
            //if not null than make an integer
            int row = new Integer(testIsEmpty).intValue();
            if (row == 3 || row == 8) {
                isNotAnswered = false;
                points += 4;
                Toast.makeText(getApplicationContext(), getApplicationContext().getString(R.string.image_toast_right),
                        Toast.LENGTH_LONG).show();
                /*
                 after input make the field invisible, so user cant answer more than once
                 and cant earn free points
                  */
                editText.setVisibility(View.INVISIBLE);
                textView.setText(getString(R.string.image_answered));
            } else {
                isNotAnswered = false;
                Toast.makeText(getApplicationContext(), getApplicationContext().getString(R.string.image_toast_false),
                        Toast.LENGTH_LONG).show();
                editText.setVisibility(View.INVISIBLE);
                textView.setText(getString(R.string.image_answered));
            }
        } else {
            // if null than new hint text.
            editText.setHint(getString(R.string.image_empty));
        }
        setPoints();


    }

    /*
     make findCat image Bigger, using a new activity.
     closes on outside click
      */
    public void makeImageBigger(View view) {
        startActivity(new Intent(getApplicationContext(), catimagebig.class));

    }

    //take photo intent (onActivityResult see above)
    public void takePhoto(View view) {

        Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(takePicture, 2);


    }

    public void setFindCatQuestion() {
        EditText editText = findViewById(R.id.image_editText);
        TextView textView = findViewById(R.id.findCat_question);
        if (!isNotAnswered) {
            editText.setVisibility(View.INVISIBLE);
            textView.setText(getString(R.string.image_answered));
        }
    }

    public void setCertificateBg() {
        if (isPhotoTaken) {
            RelativeLayout certificate_layout = findViewById(R.id.certificate_layout);
            certificate_layout.setBackground(new BitmapDrawable(getResources(), photo));
        }
    }

    public void setPoints() {
        TextView points_text = findViewById(R.id.point_text_head);
        points_text.setText(getString(R.string.points) + points);

        TextView final_score_textView = findViewById(R.id.final_score_text);
        final_score_textView.setText(getString(R.string.final_score) + points);

        TextView on_image_text = findViewById(R.id.points_text_image);
        on_image_text.setText(getString(R.string.on_image_score, points));
    }
}



