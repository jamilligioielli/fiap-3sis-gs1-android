package jamilligioielli.com.github.alunos_rm552414_rm552263.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import jamilligioielli.com.github.alunos_rm552414_rm552263.model.Evento

class EventosViewModel(application: Application) : AndroidViewModel(application) {

    val eventosLista: MutableList<Evento>;
    val eventosListaLiveData: MutableLiveData<MutableList<Evento>>;

    init {
        eventosLista = mutableListOf();
        eventosListaLiveData = MutableLiveData(eventosLista);
    }
    
    fun addItem(evento: Evento) {
        val newEvento = Evento(
            nomeLocal = evento.nomeLocal,
            tipoEvento = evento.tipoEvento,
            grauImpacto = evento.grauImpacto,
            dataEvento = evento.dataEvento,
            noPessoasAfetadas = evento.noPessoasAfetadas
        )
        eventosLista.add(newEvento);
        eventosListaLiveData.value = eventosLista;
    }
    
    fun removeItem(evento: Evento) {
        eventosLista.remove(evento);
        eventosListaLiveData.value = eventosLista;
    }
}