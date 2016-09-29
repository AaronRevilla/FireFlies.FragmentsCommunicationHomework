package com.example.aaron.fragmentscommunicationhomework;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment1 extends Fragment {

    TextView textView;
    EditText editText;
    Button button;

    public Fragment1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_fragment1, container, false);

        Log.d("FRAGMENT1","CREATED");

        textView = ((TextView) v.findViewById(R.id.textViewF1));
        editText = ((EditText) v.findViewById(R.id.textF1));
        button = ((Button) v.findViewById(R.id.sendTextButtonF1));

        button.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          FragmentsCommunication fc = (FragmentsCommunication) getActivity();
                                          fc.sendMessage(editText.getText().toString(), R.id.fragment1);
                                      }
                                  }
        );

        return v;
    }

    public void reciveMessage(String message){
        textView.setText(message);
    }


}
