package br.edu.webmob.tccfreak;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

import br.edu.webmob.tccfreak.helper.DatabaseHelper;
import br.edu.webmob.tccfreak.model.Frequencia;
import br.edu.webmob.tccfreak.model.Usuario;

public class PrincipalActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

        private TextView txtUsuario;
        private TextView txtEmail;
        private ImageView imgFoto;
        private ListView lstFrequencias;
        private Usuario u;

        private DatabaseHelper dh;
        private ArrayAdapter<Frequencia> frequencias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // criando instancia DatabaseHelper

        dh = new DatabaseHelper(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /// Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                  //      .setAction("Action", null).show();
                /*Uri uri = Uri.parse("http://www.unoesc.edu.br");
                Intent itUri = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(itUri);*/

                Intent it = new Intent(PrincipalActivity.this,
                        FrequenciaActivity_.class);
                startActivity(it);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        txtUsuario = (TextView)
                navigationView.getHeaderView(0).
                        findViewById(R.id.txtUsuario);
        txtEmail = (TextView)
                navigationView.getHeaderView(0).
                        findViewById(R.id.txtEmail);
        imgFoto = (ImageView)
                navigationView.getHeaderView(0).
                        findViewById(R.id.imgFoto);

        imgFoto.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Intent itCamera = new Intent(
                        MediaStore.ACTION_IMAGE_CAPTURE);
                if (itCamera.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(itCamera, 100);
                }
                return true;
            }
        });

        u = (Usuario)getIntent().getSerializableExtra("usuario");
        txtUsuario.setText(u.getNome());
        txtEmail.setText(u.getEmail());

        if(u.getFoto() != null) {
            Bitmap foto = BitmapFactory.decodeByteArray(u.getFoto(),
                    0, u.getFoto().length);

            imgFoto.setImageBitmap(foto);
        }

        lstFrequencias = (ListView)
                findViewById(R.id.lstFrequencias);

        atualizarFrequencias();

    }

    @Override
    protected void onResume() {
        super.onResume();
        //atualizarFrequencias();
    }

    private void atualizarFrequencias() {
        frequencias = new ArrayAdapter<Frequencia>(
                this,
                android.R.layout.simple_list_item_1,
                dh.listFrequencias()
        );

        lstFrequencias.setAdapter(frequencias);

        final SwipeRefreshLayout swr = (SwipeRefreshLayout)
                findViewById(R.id.lstRefresh);
        swr.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                atualizarFrequencias();
                swr.setRefreshing(false);
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        // TODO: (2,50) Implementar a chamada de uma tela sobre(com informações do autor e do sistema). Utilizar a opção settings do menu para chamar a tela sobre.
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        // TODO: (1,50) Adicionar uma snackbar do Android para indicar qual opção foi selecionada.
        if (id == R.id.nav_aluno) {
            // Handle the camera action
        } else if (id == R.id.nav_trabalho) {

        } else if (id == R.id.nav_sair) {

        } else if (id == R.id.nav_sincronizar) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Toast.makeText(getApplicationContext(), "Alterando foto", Toast.LENGTH_SHORT).show();
        if(requestCode == 100 && resultCode == RESULT_OK){
            Bitmap foto = (Bitmap)
                    data.getExtras().get("data");
            Bitmap fotoResized = Bitmap.createScaledBitmap(
                    foto,
                    imgFoto.getWidth(),
                    imgFoto.getHeight(),
                    true
            );
            imgFoto.setImageBitmap(fotoResized);

            // Gravando foto no banco

            ByteArrayOutputStream streamFoto =
                    new ByteArrayOutputStream();
            fotoResized.compress(Bitmap.CompressFormat.PNG,
                        100, streamFoto);
            u.setFoto(streamFoto.toByteArray());
            dh.saveUsuario(u);

            Toast.makeText(getApplicationContext(), "Foto alterada", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(), "Erro na foto", Toast.LENGTH_SHORT).show();
        }
    }

}
