package com.led.led;

import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.IOException;
import java.util.UUID;


public class ledControl extends ActionBarActivity {

    ImageButton btnright, btnleft, btnstop, btnfront, btnback;
    Button btndis;
    TextView lumn;
    String address = null;
    private ProgressDialog progress;
    BluetoothAdapter myBluetooth = null;
    BluetoothSocket btSocket = null;
    private boolean isBtConnected = false;
    //SPP UUID. Look for it
    static final UUID myUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        Intent newint = getIntent();
        address = newint.getStringExtra(DeviceList.EXTRA_ADDRESS); //receive the address of the bluetooth device

        //view of the ledControl
        setContentView(R.layout.activity_led_control);
        final MediaPlayer sound_btn1 = MediaPlayer.create(this, R.raw.forwerd);
        final MediaPlayer sound_btn2 = MediaPlayer.create(this, R.raw.left);
        final MediaPlayer sound_btn3 = MediaPlayer.create(this, R.raw.right);
        final MediaPlayer sound_btn4 = MediaPlayer.create(this, R.raw.under);
        final MediaPlayer sound_btn5 = MediaPlayer.create(this, R.raw.stop);
        //call the widgtes
        btnright = (ImageButton) findViewById(R.id.rightButton);
        btnleft = (ImageButton) findViewById(R.id.leftButton);
        btnback = (ImageButton) findViewById(R.id.backButton);
        btnfront = (ImageButton) findViewById(R.id.frontButton);
        btnstop = (ImageButton) findViewById(R.id.stopButton);
        btndis = (Button) findViewById(R.id.button4);

        lumn = (TextView) findViewById(R.id.lumn);

        new ConnectBT().execute(); //Call the class to connect

        //commands to be sent to bluetooth
        btnright.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                sound_btn3.start();
                Animation animation= AnimationUtils.loadAnimation(ledControl.this,R.anim.rotate);
                btnright.startAnimation(animation);
                turnleft();



                return false;//method to turn on
            }
        });

        btnleft.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                sound_btn2.start();
                Animation animation= AnimationUtils.loadAnimation(ledControl.this,R.anim.rotate1);
                btnleft.startAnimation(animation);
                turnright();
                //stop();
                return false;//method to turn off
            }
        });
        btndis.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                sound_btn5. start();

             disconnet();
                //stop();

                return false;//method to turn off
            }
        });
        btnfront.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                sound_btn1.start();
                Animation animation= AnimationUtils.loadAnimation(ledControl.this,R.anim.rotate);
                btnfront.startAnimation(animation);
                backward();
                //stop();
                return false;//method to turn off
            }
        });
        btnback.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                sound_btn4.start();
                Animation animation= AnimationUtils.loadAnimation(ledControl.this,R.anim.rotate);
                btnback.startAnimation(animation);

                forward();
                //stop();
                return false;//method to turn off
            }
        });

        btnstop.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                stop();
                return false;//close connection
            }
        });
        // finish();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void stop() {
        if (btSocket != null) //If the btSocket is busy
        {
            try {
                btSocket.getOutputStream().write("S".getBytes());
            } catch (IOException e) {
                msg("Error");
            }
        }


    }

    private void disconnet() {
        if (btSocket != null) //If the btSocket is busy
        {
            try {
                btSocket.close(); //close connection
            } catch (IOException e) {
                msg("Error");
            }
        }
        finish(); //return to the first layout

    }

    private void turnleft() {
        if (btSocket != null) {
            try {
                btSocket.getOutputStream().write("L".getBytes());
            } catch (IOException e) {
                msg("Error");
            }
        }
    }

    private void backward() {
        if (btSocket != null) {
            try {
                btSocket.getOutputStream().write("B".getBytes());
            } catch (IOException e) {
                msg("Error");
            }
        }
    }

    private void forward() {
        if (btSocket != null) {
            try {
                btSocket.getOutputStream().write("F".getBytes());
            } catch (IOException e) {
                msg("Error");
            }
        }
    }


    private void turnright() {
        if (btSocket != null) {
            try {
                btSocket.getOutputStream().write("R".getBytes());
            } catch (IOException e) {
                msg("Error");
            }
        }

    }

    // fast way to call Toast
    private void msg(String s) {
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_led_control, menu);
        return true;
    }

   /* @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item)
    ;
}*/
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("ledControl Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }

    private class ConnectBT extends AsyncTask<Void, Void, Void>  // UI thread
    {
        private boolean ConnectSuccess = true; //if it's here, it's almost connected

        @Override
        protected void onPreExecute() {
            progress = ProgressDialog.show(ledControl.this, "Connecting...", "Please wait!!!");  //show a progress dialog
        }

        @Override
        protected Void doInBackground(Void... devices) //while the progress dialog is shown, the connection is done in background
        {
            try {
                if (btSocket == null || !isBtConnected) {
                    myBluetooth = BluetoothAdapter.getDefaultAdapter();//get the mobile bluetooth device
                    BluetoothDevice dispositivo = myBluetooth.getRemoteDevice(address);//connects to the device's address and checks if it's available
                    btSocket = dispositivo.createInsecureRfcommSocketToServiceRecord(myUUID);//create a RFCOMM (SPP) connection
                    BluetoothAdapter.getDefaultAdapter().cancelDiscovery();
                    btSocket.connect();//start connection
                }
            } catch (IOException e) {
                ConnectSuccess = false;//if the try failed, you can check the exception here
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) //after the doInBackground, it checks if everything went fine
        {
            super.onPostExecute(result);

            if (!ConnectSuccess) {
                msg("Connection Failed. Is it a SPP Bluetooth? Try again.");
                finish();
            } else {
                msg("Connected.");
                isBtConnected = true;
            }
            progress.dismiss();
        }
    }
}
