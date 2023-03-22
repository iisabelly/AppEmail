package andrades.isabelly.appemail;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.net.URI;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Encontra o botão de envio na interface
        Button btnEnviar = findViewById(R.id.btnEnviar);

        // Define a ação onclick do botão de envio
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtem os dados inseridos pelo usuário
                EditText etEmail = findViewById(R.id.etEmail);
                EditText etAssunto = findViewById(R.id.etAssunto);
                EditText etTexto = findViewById(R.id.etTexto);

                // Transforma os textos inseridos do editText em String
                String email = etEmail.getText().toString();
                String assunto = etAssunto.getText().toString();
                String texto = etTexto.getText().toString();

                // Cria uma intent implícita e define a sua action
                Intent i = new Intent(Intent.ACTION_SENDTO);
                i.setData(Uri.parse("mailto:"));

                // Adiciona o conteúdo na intent
                String[] emails = new String[]{email};
                i.putExtra(Intent.EXTRA_EMAIL, emails);
                i.putExtra(Intent.EXTRA_SUBJECT, assunto);
                i.putExtra(Intent.EXTRA_TEXT, texto);

                // Permite o usuário escolher o aplicativo de preferência para o envio do email e
                // avisa caso não tenha nenhum aplicaivo que execute a action
                try {
                    startActivity(Intent.createChooser(i, "Escolha o APP"));
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(MainActivity.this, "Não há nenhum app que posso " +
                    "realizar essa operação", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}