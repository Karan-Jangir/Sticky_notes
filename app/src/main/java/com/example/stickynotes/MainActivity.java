package com.example.stickynotes;

import androidx.appcompat.app.AppCompatActivity;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText noteEdt;
    private Button increaseSizeBtn,decreaseSizeBtn,Boldbtn,UnderLineBtn,ItalicBtn;
    private TextView sizeTv;
    StickyNote note=new StickyNote();
    float current_size;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        noteEdt=findViewById(R.id.idEdt);
        increaseSizeBtn=findViewById(R.id.idBtnIncreaseSize);
        decreaseSizeBtn=findViewById(R.id.idBtnresudceSize);
        Boldbtn=findViewById(R.id.idBtnBold);
        UnderLineBtn=findViewById(R.id.idBtnUnderLine);
        ItalicBtn=findViewById(R.id.idBtnIalic);
        sizeTv=findViewById(R.id.idTVSize);
       current_size=noteEdt.getTextSize();
       sizeTv.setText(""+current_size);


        increaseSizeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sizeTv.setText(""+current_size);
                current_size++;
                noteEdt.setTextSize(current_size);
            }
        });

        decreaseSizeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sizeTv.setText(""+current_size);
                current_size--;
                noteEdt.setTextSize(current_size);
            }
        });

        Boldbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ItalicBtn.setTextColor(getResources().getColor(R.color.white));
                ItalicBtn.setBackgroundColor(getResources().getColor(R.color.purple_200));

                if(noteEdt.getTypeface().isBold())
                {
                    noteEdt.setTypeface(Typeface.DEFAULT);
                    Boldbtn.setTextColor(getResources().getColor(R.color.white));
                    Boldbtn.setBackgroundColor(getResources().getColor(R.color.purple_200));
                }
                else
                {
                    Boldbtn.setTextColor(getResources().getColor(R.color.purple_200));
                    Boldbtn.setBackgroundColor(getResources().getColor(R.color.white));
                    noteEdt.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                }

            }
        });

        UnderLineBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(noteEdt.getPaintFlags()==8)
                {
                    UnderLineBtn.setTextColor(getResources().getColor(R.color.white));
                    UnderLineBtn.setBackgroundColor(getResources().getColor(R.color.purple_200));
                    noteEdt.setPaintFlags(noteEdt.getPaintFlags() & (~Paint.UNDERLINE_TEXT_FLAG));
                }
                else
                {
                    UnderLineBtn.setTextColor(getResources().getColor(R.color.purple_200));
                    UnderLineBtn.setBackgroundColor(getResources().getColor(R.color.white));
                    noteEdt.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
                }
            }
        });
        ItalicBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boldbtn.setTextColor(getResources().getColor(R.color.white));
                Boldbtn.setBackgroundColor(getResources().getColor(R.color.purple_200));

                if(noteEdt.getTypeface().isItalic())
                {
                    noteEdt.setTypeface(Typeface.DEFAULT);
                    Boldbtn.setTextColor(getResources().getColor(R.color.white));
                    Boldbtn.setBackgroundColor(getResources().getColor(R.color.purple_200));
                }
                else
                {
                    Boldbtn.setTextColor(getResources().getColor(R.color.purple_200));
                    Boldbtn.setBackgroundColor(getResources().getColor(R.color.white));
                    noteEdt.setTypeface(Typeface.defaultFromStyle(Typeface.ITALIC));
                }

            }
        });
    }

    public void saveButton(View view) {
        note.setStick(noteEdt.getText().toString(),this);
        updateWidget();
        Toast.makeText(this, "Note has been updated....", Toast.LENGTH_SHORT).show();
    }

    private  void updateWidget()
    {
         AppWidgetManager appWidgetManager=AppWidgetManager.getInstance(this);
        RemoteViews remoteViews=new RemoteViews(this.getPackageName(),R.layout.widget_layout);
        ComponentName thisWidget= new ComponentName(this,AppWidget.class);
        remoteViews.setTextViewText(R.id.idTVwidget,noteEdt.getText().toString());
        appWidgetManager.updateAppWidget(thisWidget,remoteViews);

    }
}