package agenda.cursoandroidavancado.com.br.agendaormlite.activity.model.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.List;

import agenda.cursoandroidavancado.com.br.agendaormlite.activity.model.bean.Contato;

/**
 * Created by Robert on 27/12/2014.
 */
public class ContatoDAO extends OrmLiteSqliteOpenHelper {

    //Nome do arquivo de banco de dados do SQLite
    private static final String DATABASE_NAME = "agenda.db";
    //Versão atual do banco de dados
    private static final int DATABASE_VERSION = 1;

    //Atributo utilizado para a persistência de Contatos
    private Dao<Contato, Long> dao = null;

    public ContatoDAO(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource source) {
        try{
         Log.i(ContatoDAO.class.getSimpleName(), "onCreate()");
            TableUtils.createTable(source, Contato.class);
            //Exemplo de tabela
            Contato contato = new Contato();
            contato.setNome("Robert Logan");
            contato.setEmail("robert.dub.logan@gmail.com");
            contato.setTelefone("9900-8877");
            cadastrar(contato);
        } catch (SQLException ex) {
            Log.e(ContatoDAO.class.getSimpleName(), "onCreate(): Falha ao criar tabelas", ex);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource source, int oldVersion, int newVersion) {
        try {
            Log.i(ContatoDAO.class.getSimpleName(), "onUpgrade()");
            //Conexão, tabela e trues para ignorar erros
            TableUtils.dropTable(source, Contato.class, true);
            onCreate(database, source);
        } catch (SQLException ex) {
            Log.e(ContatoDAO.class.getSimpleName(), "onUpgrade(): Falha na atualização", ex);
        }
    }

    public Dao<Contato, Long> getDao() {
        if(dao == null) {
            try {
                dao = getDao(Contato.class);
            } catch (SQLException e) {
                Log.e(ContatoDAO.class.getSimpleName(), "getDao(): Falha ao criar DAO", e);
            }
        }
        return dao;
    }

    @Override
    public void close() {
        super.close();
        dao = null;
    }

    public void cadastrar(Contato contato) throws SQLException {
        getDao().create(contato);
    }
    public void excluir(Contato contato) throws SQLException {
        getDao().delete(contato);
    }
    public void alterar(Contato contato) throws SQLException {
        getDao().update(contato);
    }
    public List<Contato> listar() throws SQLException {
        return getDao().queryForAll();
    }
}
