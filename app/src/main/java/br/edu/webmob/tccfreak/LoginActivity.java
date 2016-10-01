package br.edu.webmob.tccfreak;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import br.edu.webmob.tccfreak.helper.DatabaseHelper;
import br.edu.webmob.tccfreak.model.Usuario;

@EActivity(R.layout.activity_login)
public class LoginActivity extends AppCompatActivity {
    @ViewById
    EditText edtLogin;
    @ViewById
    EditText edtSenha;
    @ViewById
    ImageButton btnLogin;
    @ViewById
    ImageButton btnSair;
    private DatabaseHelper dh;


    @Click({R.id.btnLogin, R.id.btnSair})
    public void doAction(View view) {
        dh = new DatabaseHelper(this);
        switch (view.getId()) {
            case R.id.btnLogin:
                String login = edtLogin.getText().toString();
                String senha = edtSenha.getText().toString();
                if (login != null &&
                        !login.trim().equals("") &&
                        senha != null &&
                        !senha.trim().equals("")) {
                    Usuario u = dh.getUsuarioByLoginSenha(login, senha);
                    if (u != null) {
                        // chama a tela principal
                        Intent itPrincipal = new Intent(
                                this,
                                PrincipalActivity.class
                        );
                        // passando parâmetros para a activity
                        itPrincipal.putExtra("usuario", u);
                        startActivity(itPrincipal);
                        // finaliza a tela de login
                        finish();
                    } else {
                        // TODO: (1,00) Adicionar mensagem para avisar o usuário que o login e/ou senha inválidos!
                        edtLogin.setText("");
                        edtSenha.setText("");
                        edtLogin.requestFocus();
                    }
                } else {
                    // avisando o usuário da necessidade
                    // de preenchimento dos campos
                    Toast.makeText(this,
                            R.string.msgCamposObrigatorios,
                            Toast.LENGTH_LONG
                    ).show();
                    edtLogin.requestFocus();
                }
                break;
            case R.id.btnSair:
                // usando uma caixa de diálogo para o sair
                AlertDialog.Builder dialogoSair =
                        new AlertDialog.Builder(this);
                dialogoSair.setTitle(R.string.tituloSair);
                dialogoSair.setMessage(R.string.msgSair);
                dialogoSair.setIcon(R.drawable.sair);
                dialogoSair.setPositiveButton(
                        R.string.opSim,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface,
                                                int i) {
                                // finalizando a aplicação
                                finish();
                                System.exit(0);
                            }
                        }
                );
                dialogoSair.setNegativeButton(R.string.opNao, null);
                dialogoSair.show();
                break;
        }
    }
}