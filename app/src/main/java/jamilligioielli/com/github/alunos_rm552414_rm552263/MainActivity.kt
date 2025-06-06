package jamilligioielli.com.github.alunos_rm552414_rm552263

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import jamilligioielli.com.github.alunos_rm552414_rm552263.model.Evento
import jamilligioielli.com.github.alunos_rm552414_rm552263.viewmodel.EventosViewModel
import jamilligioielli.com.github.listadecompras.viewmodel.EventosAdapter
import java.util.Date

class MainActivity : AppCompatActivity() {
    // O ViewModel usado para interagir com o banco de dados.
    private lateinit var viewModel: EventosViewModel

    /**
     * Chamado quando a activity é criada.
     * Este método configura a interface do usuário e inicializa o ViewModel.
     *
     * @param savedInstanceState Se a activity está sendo recriada a partir de um estado salvo, este é o estado.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        // Chama o método onCreate da superclasse para completar a criação da activity.
        super.onCreate(savedInstanceState)
        // Define o layout da activity.
        setContentView(R.layout.activity_main)

        // Encontra a barra de ferramentas pelo seu ID e a define como a barra de ação para esta activity.
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        // Define o título da barra de ação.
        supportActionBar?.title = "Lista de Compras"

        // Encontra o RecyclerView pelo seu ID.
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        // Cria um novo adaptador para o RecyclerView. O adaptador é responsável por exibir os itens na lista.
        // Quando um item é clicado, ele é removido da lista.
        val itemsAdapter = EventosAdapter { item ->
            viewModel.removeItem(item)
        }
        // Define o adaptador do RecyclerView.
        recyclerView.adapter = itemsAdapter

        // Encontra o botão e o campo de texto pelo seus IDs.
        val button = findViewById<Button>(R.id.button)
        val editText = findViewById<EditText>(R.id.editText)

        // Define o que acontece quando o botão é clicado.
        button.setOnClickListener {
            // Se o campo de texto estiver vazio, exibe um erro e retorna.
            if (editText.text.isEmpty()) {
                editText.error = "Preencha um valor"
                return@setOnClickListener
            }

            // Adiciona o item ao ViewModel e limpa o campo de texto.
            viewModel.addItem(Evento(
                nomeLocal = editText.text.toString(),
                tipoEvento = "",
                grauImpacto = "",
                dataEvento = Date(2025),
                noPessoasAfetadas = 0,
            ))
            editText.text.clear()
        }

        // Obtém uma instância do ViewModel.
        viewModel = ViewModelProvider(this).get(EventosViewModel::class.java)

        // Observa as mudanças na lista de itens e atualiza o adaptador quando a lista muda.
        viewModel.eventosLiveData.observe(this){
            eventos -> itemsAdapter.updateEventos(eventos);
        }

    }
}