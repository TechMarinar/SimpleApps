package com.techmarinar.animationapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String CONTROLLER ="Animations Controller" ;
    //widget
    private ImageView mImage;
    private AnimatorSet mAnimator;
    //context
    private Context mContext;
    //Animations controller
    private boolean mAnimatorController=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //set context
        mContext=this;

        //find widget
        mImage=(ImageView) findViewById(R.id.mImageView);
        //inflate animator
        mAnimator = (AnimatorSet) AnimatorInflater.loadAnimator(mContext,R.animator.animationsfile);
        mAnimator.setTarget(mImage);

        mImage.setOnClickListener(this);

        //for screen rotations
        if (savedInstanceState != null){
            mAnimatorController=savedInstanceState.getBoolean(CONTROLLER);
        }


    }

    @Override
    public void onClick(View v) {

        //use the picture clickListener as a (turn on/off) button
        if (!mAnimatorController) {
            //start Animations
            mAnimator.start();
            mAnimatorController=true;

        }else {
            //stop animations
            mAnimator.end();
            mAnimatorController=false;
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        mAnimator.end();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mAnimatorController){
        mAnimator.start();
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putBoolean(CONTROLLER,mAnimatorController);
        super.onSaveInstanceState(outState);
    }
}