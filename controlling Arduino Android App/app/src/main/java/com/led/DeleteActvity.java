package com.led;

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.led.led.R;

public class DeleteActvity extends ActionBarActivity {
    Bundle bn;
    String USERNAME;
    Button Del;
    EditText PASS;
    String Pass;
    DatabaseOperations DOP;
    Context CTX = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_actvity);
        Del = (Button) findViewById(R.id.b_delete);
        PASS = (EditText) findViewById(R.id.del_pass);
        bn = getIntent().getExtras();
        USERNAME = bn.getString("user_name");
        Del.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Pass = PASS.getText().toString();
                DOP = new DatabaseOperations(CTX);
                Cursor CR = DOP.getUserPass(DOP, USERNAME);
                CR.moveToFirst();
                boolean login_status = false;
                do
                {
                    if(Pass.equals(CR.getString(0)))
                    {
                        login_status = true;
                    }

                }while(CR.moveToNext());

                if(login_status)
                {
                    DOP.deleteUser(DOP,USERNAME , Pass);
                    Toast.makeText(getBaseContext(), "User Removed successfully.....", Toast.LENGTH_LONG).show();
                    finish();

                }
                else
                {
                    Toast.makeText(getBaseContext(), "Invalid user.....Try later", Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        });
    }
}
