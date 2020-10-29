package com.phyrosapp.cadastroaluno.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.phyrosapp.cadastroaluno.R;
import com.phyrosapp.cadastroaluno.dao.DisciplinaDAO;
import com.phyrosapp.cadastroaluno.model.Aluno;
import java.util.ArrayList;


public class AlunoDisciplina extends AppCompatActivity{
    private Aluno aluno;
    private Spinner sp;
    private ArrayList<String> disciplinas = new ArrayList<String>();
    private String nomeAluno;
    private String rgm;
    RecyclerView recyclerViewDisciplina;
    DisciplinaAdapter adapter;

    //private String[] disciplinasSpinner = new String[]{"Estrutura de dados", "Desenvolvimento Web",
      //                                          "Programação POO", "Técnicas de Programação"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aluno_disciplina);

        ArrayAdapter<CharSequence> adapterSpinner = ArrayAdapter.createFromResource(
                this, R.array.disciplinas_list, android.R.layout.simple_spinner_dropdown_item);
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        this.sp = findViewById(R.id.spinnerDisciplina);
        this.sp.setAdapter(adapterSpinner);

        this.aluno = (Aluno) getIntent().getSerializableExtra("aluno");

        Intent intent = getIntent();
        Bundle params = intent.getExtras();
        rgm = params.getString("rgm");
        nomeAluno = params.getString("nome");

        alterarNomeCabecalho();
        configurarRecycler();

        //carregarDadosAluno();
        //gerarDisciplinasAluno();
        //atualizarListaDisciplinas();
    }

    @Override
    protected void onResume() {
        super.onResume();
        configurarRecycler();
    }

    private void configurarRecycler(){
        recyclerViewDisciplina = (RecyclerView) findViewById(R.id.recyclerViewDisciplinas);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewDisciplina.setLayoutManager(layoutManager);

        DisciplinaDAO dao = new DisciplinaDAO(this);
        adapter = new DisciplinaAdapter(dao.retornarDisciplinas(this.rgm));

        recyclerViewDisciplina.setAdapter(adapter);
        recyclerViewDisciplina.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    public void novaDisciplina(View view){
        String disciplina = sp.getSelectedItem().toString();
        DisciplinaDAO disciplinaDAO = new DisciplinaDAO(getBaseContext());
        boolean sucesso = disciplinaDAO.salvarDisciplina(this.rgm, disciplina);

        if (sucesso){
            Toast.makeText(this, "Aluno foi matriculado na disciplina", Toast.LENGTH_SHORT).show();
            System.out.println(this.rgm);
            configurarRecycler();
        }else{
            Toast.makeText(this, "Ocorre algum erro inesperado", Toast.LENGTH_SHORT).show();
        }
    }

    public void infoAluno(View view){
        Intent intent = new Intent(this, InfoAluno.class);
        Bundle params = new Bundle();
        params.putString("rgm", this.rgm);
        intent.putExtras(params);
        startActivity(intent);
    }

    public void alterarNomeCabecalho(){
        TextView nome = (TextView) findViewById(R.id.lblNomeAluno);
        String txt = "Aluno(a) " + this.nomeAluno;
        nome.setText(txt);
    }

















    /*
    public void carregarDadosAluno(){
        TextView nome = (TextView) findViewById(R.id.nome);
        TextView rgm = (TextView) findViewById(R.id.rgm);
        TextView cep = (TextView) findViewById(R.id.cep);
        TextView endereco = (TextView) findViewById(R.id.endereco);
        TextView telefone = (TextView) findViewById(R.id.telefone);
        TextView email = (TextView) findViewById(R.id.email);

        nome.setText(aluno.getNome());
        rgm.setText(aluno.getRgm());
        cep.setText(aluno.getCep());
        endereco.setText(aluno.getEndereco());
        telefone.setText(aluno.getTelefone());
        email.setText(aluno.getEmail());
    }
*/
/*
    public void gerarDisciplinasAluno(){
        if(aluno.checarNumeroDisciplinas() > 0){
            ArrayList<Disciplina> disciplinas = aluno.getDisciplinas();

            for(int i = 0; i < aluno.checarNumeroDisciplinas(); i++){
                String nomeDisciplina = disciplinas.get(i).getDisciplina();
                this.disciplinas.add(nomeDisciplina);

            }

        }
    }
*/
    /*
    public void adicionarDisciplina(View view){
        String itemDisciplina = this.sp.getSelectedItem().toString();
        Disciplina disciplina = new Disciplina(itemDisciplina);
        this.disciplinas.add(itemDisciplina);
        this.aluno.addDisciplina(disciplina);
        atualizarListaDisciplinas();
        int n = aluno.checarNumeroDisciplinas();
        System.out.println("Numero disciplinas - " + n);
    }
    */

    /*
    private void atualizarListaDisciplinas(){
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, disciplinas);
        setListAdapter(arrayAdapter);}

*/
}