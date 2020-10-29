package com.phyrosapp.cadastroaluno.view;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import com.phyrosapp.cadastroaluno.R;
import com.phyrosapp.cadastroaluno.dao.AlunoDAO;
import com.phyrosapp.cadastroaluno.dao.UniversidadeDBHelper;
import com.phyrosapp.cadastroaluno.model.Aluno;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final int telaCadastroAluno = 1;
    private ArrayList<String> alunos = new ArrayList<String>();
    private ArrayList<Aluno> alunos_obj = new ArrayList<Aluno>();
    RecyclerView recyclerViewAluno;
    AlunoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Context context = getApplicationContext();
        UniversidadeDBHelper universidadeDBHelper = new UniversidadeDBHelper(context);
        SQLiteDatabase conexao = universidadeDBHelper.getWritableDatabase();
    }

    @Override
    protected void onStart() {
        super.onStart();
        configurarRecycler();
    }

    private void configurarRecycler(){
        recyclerViewAluno = (RecyclerView) findViewById(R.id.recyclerViewAlunos);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewAluno.setLayoutManager(layoutManager);

        AlunoDAO dao = new AlunoDAO(this);
        adapter = new AlunoAdapter(dao.retornarAlunos());

        recyclerViewAluno.setAdapter(adapter);
        recyclerViewAluno.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    public void abrirAluno(int position){
        Intent telaAluno = new Intent(this, AlunoDisciplina.class);
        Aluno aluno = alunos_obj.get(position);
        telaAluno.putExtra("aluno", aluno);
        startActivity(telaAluno);
    }

    public void cadastrarAluno(View v){
        Intent telaCadastro = new Intent(this, CadastroAluno.class);

        /*
        Bundle params = new Bundle();
        params.putString("chave","valor");
        params.putString("chave","valor");

        telaCadastro.putExtras(params);

         */

        startActivityForResult(telaCadastro, telaCadastroAluno);
    }

    @Override
    protected void onActivityResult(int codTela, int resultado, Intent intent) {
        super.onActivityResult(codTela, resultado, intent);
        if (codTela == telaCadastroAluno) {
            if (intent != null) {
                Bundle dados = intent.getExtras();
                String nome = dados.getString("nome");
                String msg = "O Aluno(a) " + nome + " foi adicionado a base de dados";
                Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
                this.novoAluno(dados);
                alunos.add(nome);
                //atualizarListaAlunos();
            }
        }

    }

    private void novoAluno(Bundle dados){
        String nome = dados.getString("nome");
        String rgm = dados.getString("rgm");
        String cep = dados.getString("cep");
        String endereco = dados.getString("endereco");
        String telefone = dados.getString("telefone");
        String email = dados.getString("email");

        Aluno aluno = new Aluno();
        aluno.setNome(nome);
        aluno.setRgm(rgm);
        aluno.setCep(cep);
        aluno.setEndereco(endereco);
        aluno.setTelefone(telefone);
        aluno.setEmail(email);

        System.out.println(rgm);

        this.alunos_obj.add(aluno);
    }

    /*
    private void atualizarListaAlunos(){
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, alunos);
        setListAdapter(arrayAdapter);

    }
*/
        /*
    @Override
    protected void onListItemClick(ListView lista, View v, int position, long id){
        super.onListItemClick(lista, v, position, id);

        Object obj = this.getListAdapter().getItem(position);
        String elementoClicado = obj.toString();

        Toast.makeText(getApplicationContext(), "Aluno(a) " + elementoClicado + " selecionado", Toast.LENGTH_LONG).show();
        abrirAluno(position);

        int n = alunos_obj.get(0).checarNumeroDisciplinas();
        String nome = alunos_obj.get(0).getNome();

        System.out.println("Numero disciplinas - " + n);
        System.out.println("Nome é  - " + nome);
    }*/

}