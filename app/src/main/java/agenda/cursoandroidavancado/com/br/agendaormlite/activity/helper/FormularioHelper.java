package agenda.cursoandroidavancado.com.br.agendaormlite.activity.helper;

import android.widget.EditText;

import agenda.cursoandroidavancado.com.br.agendaormlite.R;
import agenda.cursoandroidavancado.com.br.agendaormlite.activity.ListagemActivity;
import agenda.cursoandroidavancado.com.br.agendaormlite.activity.model.bean.Contato;

/**
 * Created by Robert on 27/12/2014.
 */
public class FormularioHelper {

    private EditText nome;
    private EditText telefone;
    private EditText email;

    private Contato contato;

    public FormularioHelper(ListagemActivity activity) {

        //Bind dos componentes da tela com atributos do Helper
        nome = (EditText) activity.findViewById(R.id.edNome);
        telefone = (EditText) activity.findViewById(R.id.edTelefone);
        email = (EditText) activity.findViewById(R.id.edEmail);

        //Criando o objeto Aluno
        contato = new Contato();
    }

    public Contato getContato() {
        contato.setNome(nome.getText().toString());
        contato.setTelefone(telefone.getText().toString());
        contato.setEmail(email.getText().toString());
        return contato;
    }

    public void setContato(Contato contato) {
        nome.setText(contato.getNome());
        telefone.setText(contato.getTelefone());
        email.setText(contato.getEmail());
        this.contato = contato;

    }
}
