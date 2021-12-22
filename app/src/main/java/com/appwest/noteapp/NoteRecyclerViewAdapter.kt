package com.appwest.noteapp

import android.content.Context
import android.icu.text.Transliterator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NoteRecyclerViewAdapter(val context: Context, val noteClickDeleteInterface: MainActivity, val noteClickInterface: NoteClickInterface) :
    RecyclerView.Adapter<NoteRecyclerViewAdapter.ViewHolder>() {

    private val allNotes = ArrayList<Note>()

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
         // we are creating an initializing all our variables which we have added in layout file.
        val noteTV = itemView.findViewById<TextView>(R.id.idTVNote)
        val dateTV = itemView.findViewById<TextView>(R.id.idTVDate)
        val deleteIV = itemView.findViewById<ImageView>(R.id.idIVDelete)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
         // inflating our layout file for each item of recycler view.
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item,parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //  we are setting data to item of recycler view.
        holder.noteTV.setText(allNotes.get(position).noteTitle)
        holder.dateTV.setText("Last Updated:"+allNotes.get(position).timeStamp)
        //  we are adding click listener to our delete image view icon.
        holder.deleteIV.setOnClickListener{
//            noteClickDeleteInterface.onDeleteIconClick(allNotes.get(position))
            noteClickDeleteInterface.onDeleteIconClick(allNotes.get(position))
        }
        //  we are adding click listener to our recycler view item.
        holder.itemView.setOnClickListener{
            //  we are calling a note click interface and we are passing a position to it.
            noteClickInterface.onNoteClick(allNotes.get(position))
        }
    }

    override fun getItemCount(): Int {
        return allNotes.size
    }
    fun updateList(newList:List<Note>){
        // we are clearing our notes array list and then adding a new list and then notiifying our adapter
        allNotes.clear()
        allNotes.addAll(newList)
        notifyDataSetChanged()
    }

}

interface NoteClickDeleteInterface{
    fun onDeleteIconClick(note: Note)
}
// creating a method for click action on recycler view item for updating it.
interface NoteClickInterface{
    fun onNoteClick(note: Note)
}