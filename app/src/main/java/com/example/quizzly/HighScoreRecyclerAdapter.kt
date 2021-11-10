package com.example.quizzly

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HighScoreRecyclerAdapter(val context: Context, val highscoreList: List<HighScore>) :
    RecyclerView.Adapter<HighScoreRecyclerAdapter.ViewHolder>() {

    val layoutInflater = LayoutInflater.from(context)

    override fun getItemCount(): Int {
        return highscoreList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = layoutInflater.inflate(R.layout.highscore_list_item, parent, false)

        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val highScore = highscoreList[position]

        holder.nameTextView.text = highScore.player
        holder.scoreTextView.text = highScore.score.toString()


    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView = itemView.findViewById<TextView>(R.id.nameTextView)
        val scoreTextView = itemView.findViewById<TextView>(R.id.scoreTextView)
    }

}