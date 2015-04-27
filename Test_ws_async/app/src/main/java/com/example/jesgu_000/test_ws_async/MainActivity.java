package com.example.jesgu_000.test_ws_async;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.jesgu_000.test_ws_async.ws_sharingJob.IWsdl2CodeEvents;
import com.example.jesgu_000.test_ws_async.ws_sharingJob.ws_sharingJob;


public class MainActivity extends ActionBarActivity implements IWsdl2CodeEvents{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Agregar evento a boton
        Button saludo = (Button) findViewById(R.id.bt_saludo);
        saludo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick_saludar();
            }
        });

        Button saludo_async = (Button) findViewById(R.id.bt_saludo_async);
        saludo_async.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                okClic_saludar_async();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void onClick_saludar(){
        ws_sharingJob ws = new ws_sharingJob();
        String r = ws.saludo("Mi app");
        Toast.makeText(getApplicationContext(), "Saludo normal:" + r, Toast.LENGTH_LONG).show();
    }

    private void okClic_saludar_async(){
        ws_sharingJob ws = new ws_sharingJob(this);
        try {
            ws.saludoAsync("Mi app");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void Wsdl2CodeStartedRequest() {
        //se activa antes de empezar la llamada al ws async
    }

    @Override
    public void Wsdl2CodeFinished(String methodName, Object Data) {
        //se activa cuando se obtuvo los datos del ws_async

        if(methodName.equals("saludos")){//Si es el ws saludo
            Toast.makeText(this, "Saludo asycn:" + Data.toString(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void Wsdl2CodeFinishedWithException(Exception ex) {
        //se activa si paso un error en el metodo async del ws
    }

    @Override
    public void Wsdl2CodeEndedRequest() {
        //se activa cuando termina de recibir los datos de la llamada al ws async
    }
}
