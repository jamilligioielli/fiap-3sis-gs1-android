package jamilligioielli.com.github.alunos_rm552414_rm552263

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import jamilligioielli.com.github.alunos_rm552414_rm552263.model.Evento
import jamilligioielli.com.github.alunos_rm552414_rm552263.viewmodel.EventosViewModel
import jamilligioielli.com.github.listadecompras.viewmodel.EventosAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: EventosViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "EcoRisco"

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val itemsAdapter = EventosAdapter { item ->
            viewModel.removeItem(item)
        }
        recyclerView.adapter = itemsAdapter

        val button = findViewById<Button>(R.id.button)
        val campos = listOf(
            findViewById<EditText>(R.id.nomeLocal),
            findViewById<EditText>(R.id.tipoEvento),
            findViewById<EditText>(R.id.grauImpacto),
            findViewById<EditText>(R.id.dataEvento),
            findViewById<EditText>(R.id.noPessoasAfetadas)
        )

        button.setOnClickListener {
            for (campo in campos){
                if (campo.text.isEmpty()){
                    campo.error = "Preencha um valor"
                    return@setOnClickListener
                }
            }
            viewModel.addItem(Evento(
                nomeLocal = campos[0].text.toString(),
                tipoEvento = campos[1].text.toString(),
                grauImpacto = campos[2].text.toString(),
                dataEvento = campos[3].text.toString(),
                noPessoasAfetadas = campos[4].text.toString().toInt(),
            ))
            for (campo in campos){
                campo.text.clear()
            }
        }

        viewModel = ViewModelProvider(this).get(EventosViewModel::class.java)

        viewModel.eventosListaLiveData.observe(this){
            eventos -> itemsAdapter.updateEventos(eventos);
        }

    }
}