package presentationlayer;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.*;

import businesslayer.TextNoteBL;
import com.example.kushal.rihabhbhandari.R;

import java.util.ArrayList;
import java.util.Arrays;

import persistancelayer.TextNotePL;

public class MainActivity extends AppCompatActivity {

    private int MY_PERMISSIONS_REQUEST_READ_AND_WRITE_EXTERNAL_STORAGE;     // is used in requestStoragePermission()

    ListView listView;
    Button newNote;
    TextNotePL textNotePL = new TextNotePL();
    TextNoteBL textNoteBL = new TextNoteBL();
    int alSize = 0;// arraylist size
    ArrayAdapter<String> arrayAdapter;
    static MainActivity mainObj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // request permission ... required for API 23 or above
        requestStoragePermission();


        mainObj = this;
        setContentView(R.layout.activity_main);
        populateListView();

        newNote = (Button) findViewById(R.id.button_take_note);
        newNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), NoteTakerUI.class);
                startActivityForResult(intent, 0);

            }
        });


        newNote = (Button) findViewById(R.id.button_open_photonote);
        newNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), PhotoNoteUI.class);
                startActivity(intent);
            }
        });

        newNote = (Button) findViewById(R.id.button_handwrite);
        newNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), HandwritingUI.class);
                startActivity(intent);
            }
        });
    }


    public static MainActivity getInstance(){
        return mainObj;
    }

    private void populateListView() {

        textNoteBL.getSavedData();

        listView= (ListView) findViewById(R.id.listView);;

        alSize = textNotePL.getNoteList().size();
        String noteName[] = new String[alSize];

        for (int i = 0; i < alSize; i++) {

            noteName[i] = textNotePL.getNoteList().get(i).noteName.toString();

        }

        ArrayList<String> noteList = new ArrayList<String>();
        noteList.addAll(Arrays.asList(noteName) );


        arrayAdapter = new ArrayAdapter<String>(this, R.layout.view_note_data , R.id.textView_notename,noteList );

        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                Toast.makeText(MainActivity.this, "Opening a note is not yet implemented", Toast.LENGTH_SHORT).show();

            }
        });
    }


    public void dataAdded(TextView noteName)
    {
        //TextView nameET=(TextView)findViewById(R.id.);
        String name=noteName.getText().toString();
        Log.d("my tag", name) ;
//        Toast.makeText(getApplicationContext(),  name, Toast.LENGTH_SHORT).show();

        if(!name.isEmpty() && name.length()>0)
        {
            arrayAdapter.add(name);

            arrayAdapter.notifyDataSetChanged();

            //Toast.makeText(getApplicationContext(), "Note Added" + name, Toast.LENGTH_SHORT).show();
        }




    }

    // request permission ... required for API 23 or above
    private void requestStoragePermission()
    {
        if((ContextCompat.checkSelfPermission(MainActivity.this,
                                              Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
           || (ContextCompat.checkSelfPermission(MainActivity.this,
                                                 Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED))

        {
            ActivityCompat.requestPermissions
                    (MainActivity.this, new String[]{
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                    }, MY_PERMISSIONS_REQUEST_READ_AND_WRITE_EXTERNAL_STORAGE);
        }
    }

}