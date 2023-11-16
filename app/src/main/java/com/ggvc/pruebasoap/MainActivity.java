package com.ggvc.pruebasoap;

import static com.google.android.material.internal.ContextUtils.getActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button botonInsertar = (Button) findViewById(R.id.btnConsultar);

        botonInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ejecutarservicio("https://lectorenesenas.000webhostapp.com/insertarlinea.php");


            }
        });

    }
    private void ejecutarservicio(String URL){

        String txtcodigo="500"; //txtCodigo.getText().toString();
        String txtnombre= "500"; //txtNombre.getText().toString();
        String txtapellido= "500"; //txtApellido.getText().toString();
        String txtmail= "500"; //txtMail.getText().toString();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "EXITO", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        } ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<String, String>();
                parametros.put("codigo", txtcodigo);
                parametros.put("nombre", txtnombre);
                parametros.put("apellido", txtapellido);
                parametros.put("mail", txtmail);
                return parametros;
            }
        };

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}