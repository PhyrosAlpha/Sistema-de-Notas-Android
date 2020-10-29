package com.phyrosapp.cadastroaluno.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.phyrosapp.cadastroaluno.R;
import com.phyrosapp.cadastroaluno.dao.AlunoDAO;
import com.phyrosapp.cadastroaluno.model.Aluno;

public class CadastroAluno extends AppCompatActivity {
    private Spinner sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_aluno);

        ArrayAdapter<CharSequence> adapterSpinner = ArrayAdapter.createFromResource(
                this, R.array.cursos_list, android.R.layout.simple_spinner_dropdown_item);
        //adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        this.sp = findViewById(R.id.spinnerCursos);
        this.sp.setAdapter(adapterSpinner);

        /*
        Intent intent = getIntent();
        Bundle params = intent.getExtras();

        String chave = params.getString("chave");

         */
    }
    public void salvarAlunoDao(View view){
        EditText inputNome = (EditText) findViewById(R.id.inputNome);
        EditText inputRgm = (EditText) findViewById(R.id.inputRgm);
        EditText inputCep = (EditText) findViewById(R.id.inputCep);
        EditText inputEndereco = (EditText) findViewById(R.id.inputEndereco);
        EditText inputTelefone = (EditText) findViewById(R.id.inputTelefone);
        EditText inputEmail = (EditText) findViewById(R.id.inputEmail);

        String nome = inputNome.getText().toString();
        String rgm = inputRgm.getText().toString();
        String cep = inputCep.getText().toString();
        String endereco = inputEndereco.getText().toString();
        String telefone = inputTelefone.getText().toString();
        String email = inputEmail.getText().toString();
        String itemCurso = this.sp.getSelectedItem().toString();

        Aluno aluno = new Aluno();
        aluno.setNome(nome);
        aluno.setRgm(rgm);
        aluno.setCep(cep);
        aluno.setEndereco(endereco);
        aluno.setTelefone(telefone);
        aluno.setEmail(email);
        aluno.setCurso(itemCurso);

        AlunoDAO dao = new AlunoDAO(getBaseContext());
        boolean validar = validarCampos();

        if(validar){
            boolean sucesso = dao.salvarAluno(aluno);
            if(sucesso){
                Toast.makeText(this, "Aluno(a) " + nome + " foi criado!", Toast.LENGTH_LONG).show();
                finish();
            }else{
                Toast.makeText(this, "Erro no banco de dados", Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(this, "Campos Inválidos", Toast.LENGTH_LONG).show();
        }
    }

    public void cancelar(View view){
        finish();
    }

    public boolean validarCampos(){
        EditText inputNome = (EditText) findViewById(R.id.inputNome);
        EditText inputRgm = (EditText) findViewById(R.id.inputRgm);
        EditText inputEmail = (EditText) findViewById(R.id.inputEmail);

        String nome = inputNome.getText().toString();
        String rgm = inputRgm.getText().toString();
        String email = inputEmail.getText().toString();

        return !nome.equals("") && !rgm.equals("") && !email.equals("");
    }

    public void salvar(View view){
        EditText inputNome = (EditText) findViewById(R.id.inputNome);
        EditText inputRgm = (EditText) findViewById(R.id.inputRgm);
        EditText inputCep = (EditText) findViewById(R.id.inputCep);
        EditText inputEndereco = (EditText) findViewById(R.id.inputEndereco);
        EditText inputTelefone = (EditText) findViewById(R.id.inputTelefone);
        EditText inputEmail = (EditText) findViewById(R.id.inputEmail);

        String name = inputNome.getText().toString();
        String rgm = inputRgm.getText().toString();
        String cep = inputCep.getText().toString();
        String endereco = inputEndereco.getText().toString();
        String telefone = inputTelefone.getText().toString();
        String email = inputEmail.getText().toString();

        Intent intent = new Intent();
        intent.putExtra("nome", name);
        intent.putExtra("rgm", rgm);
        intent.putExtra("cep", cep);
        intent.putExtra("endereco", endereco);
        intent.putExtra("telefone", telefone);
        intent.putExtra("email", email);

        this.setResult(1, intent);
        finish();
    }
}