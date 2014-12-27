package agenda.cursoandroidavancado.com.br.agendaormlite.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import agenda.cursoandroidavancado.com.br.agendaormlite.R;
import agenda.cursoandroidavancado.com.br.agendaormlite.activity.helper.FormularioHelper;
import agenda.cursoandroidavancado.com.br.agendaormlite.activity.model.bean.Contato;
import agenda.cursoandroidavancado.com.br.agendaormlite.activity.model.dao.ContatoDAO;


public class ListagemActivity extends ActionBarActivity {

    private List<String> listaDeContatos = new ArrayList<>();

    private final String TAG = ActionBarActivity.class
            .getSimpleName();
    private ArrayAdapter<String> adapter = null;

    private FormularioHelper formularioHelper = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listagemlayout);

        int adapterLayout = android.R.layout.simple_list_item_1;

        /* Retirado para atualizar o onCreate com
        * FormularioHelper -> ArrayAdapter<String> adapter = null;*/

        // ListView que exibirá os dados do Contato
        ListView lvListagem = (ListView) findViewById(R.id.lvListagem);

        carregarLista();
        adapter = new ArrayAdapter<String>(this, adapterLayout, listaDeContatos);
        lvListagem.setAdapter(adapter);

        //Implementado depois
        formularioHelper = new FormularioHelper(this);

        Button botaoSalvar = (Button) findViewById(R.id.button_salvar);
        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContatoDAO dao = ContatoDAO.getInstance();
                dao.createOrUpdate(formularioHelper.getContato());
                carregarLista();
                adapter.notifyDataSetChanged();
                formularioHelper.setContato(new Contato());
            }
        });
    }

    ;


    /*Método que solicita serviço da camada de modelo
    *e atualiza a lista de contatos exibida
     */
    private void carregarLista() {
        //Limpa a coleção de contatos exibidos
        listaDeContatos.clear();
        //Criação do objeto de persistência
        ContatoDAO dao = ContatoDAO.getInstance();
        List<Contato> lista = null;
        lista = dao.findAll();
        //Preenchimento da Lista de Contatos
        for (Contato contato : lista) {
            listaDeContatos.add(contato.toString());
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu_contexto, menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.listagemmenu, menu);
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
}
