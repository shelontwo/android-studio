package br.edu.webmob.tccfreak;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.edu.webmob.tccfreak.helper.DatabaseHelper;
import br.edu.webmob.tccfreak.model.Frequencia;
import br.edu.webmob.tccfreak.model.Trabalho;
import br.edu.webmob.tccfreak.model.Usuario;

@EActivity(R.layout.activity_frequencia)

public class FrequenciaActivity extends AppCompatActivity {
    @ViewById
    public EditText edtData;
    @ViewById
    public Spinner spnTrabalho;
    @ViewById
    public EditText edtAluno;
    @ViewById
    public Button btnSalvar;
    @ViewById
    public Button btnFechar;

    private DatabaseHelper dh;

    private SimpleDateFormat sdf;

    @AfterViews
    public void init(){
        dh = new DatabaseHelper(this);
        ArrayAdapter<Trabalho> adaptador = new ArrayAdapter<Trabalho>(this, android.R.layout.simple_spinner_item,
                dh.listTrabalhos());
        spnTrabalho.setAdapter(adaptador);
        sdf = new SimpleDateFormat("dd/MM/yyyy");
        edtData.setText(sdf.format(new Date()));
    }

    @Click({R.id.btnSalvar, R.id.btnFechar, R.id.btnMapa})
    public void doAction(View v){
        if(v.getId() == R.id.btnSalvar){
            Frequencia f= new Frequencia();
            try {
                f.setData(sdf.parse(edtData.getText().toString()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            f.setTrabalho(
                    (Trabalho)spnTrabalho.getSelectedItem()
            );
            f.setAluno(edtAluno.getText().toString());
            dh.saveFrequencia(f);
            Toast.makeText(getApplicationContext(), "Frequencia salva com sucesso!", Toast.LENGTH_SHORT).show();
            finish();
        }else{

        }

    }
}
