package jamilligioielli.com.github.listadecompras.viewmodel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import jamilligioielli.com.github.alunos_rm552414_rm552263.R
import jamilligioielli.com.github.alunos_rm552414_rm552263.model.Evento

class EventosAdapter(private val onItemRemoved: (Evento) -> Unit) :
    RecyclerView.Adapter<EventosAdapter.EventoViewHolder>() {

    private var eventos = mutableListOf<Evento>()
    inner class EventoViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val nomeLocal = view.findViewById<TextView>(R.id.textNomeLocal)
        val tipoEvento = view.findViewById<TextView>(R.id.textTipoEvento)
        val grauImpacto = view.findViewById<TextView>(R.id.textGrauImpacto)
        val dataEvento = view.findViewById<TextView>(R.id.textDataEvento)
        val noPessoasAfetadas = view.findViewById<TextView>(R.id.textPessoasAfetadas)
        val button = view.findViewById<ImageButton>(R.id.imageButton)

        fun bind(evento: Evento) {
            nomeLocal.text = evento.nomeLocal
            tipoEvento.text = evento.tipoEvento
            grauImpacto.text = evento.grauImpacto
            dataEvento.text = evento.dataEvento
            noPessoasAfetadas.text = evento.noPessoasAfetadas.toString()
            button.setOnClickListener {
                onItemRemoved(evento)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout, parent, false)
        return EventoViewHolder(view)
    }
    override fun getItemCount(): Int = eventos.size

    override fun onBindViewHolder(holder: EventoViewHolder, position: Int) {
        val item = eventos[position]
        holder.bind(item)
    }

    fun updateEventos(newEventos: MutableList<Evento>) {
        eventos = newEventos
        notifyDataSetChanged()
    }
}