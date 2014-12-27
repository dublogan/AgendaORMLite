package agenda.cursoandroidavancado.com.br.agendaormlite.activity.model.dao;

import com.j256.ormlite.dao.Dao;

import agenda.cursoandroidavancado.com.br.agendaormlite.activity.model.bean.Contato;

/**
 * Created by Robert on 27/12/2014.
 */
public class ContatoDAO extends AbstractDAO<Contato> {
    private static ContatoDAO instance;

    //Nome do arquivo de banco de dados do SQLite
    private static final String DATABASE_NAME = "agenda.db";
    //Versão atual do banco de dados
    private static final int DATABASE_VERSION = 1;

    //Atributo utilizado para a persistência de Contatos
    private Dao<Contato, Long> dao = null;

    public ContatoDAO() {
    }

    public static ContatoDAO getInstance() {
        if (instance == null) {
            instance = new ContatoDAO();
        }
        return instance;
    }
}


