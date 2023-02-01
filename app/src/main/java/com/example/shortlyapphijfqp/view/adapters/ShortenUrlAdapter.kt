package com.example.shortlyapphijfqp.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.Shorten
import com.example.shortlyapphijfqp.R
import com.example.shortlyapphijfqp.databinding.ViewShortenItemBinding
import com.example.shortlyapphijfqp.utils.orLinkDefault

class ShortenUrlAdapter(
    private var onDelete: (Int) -> Unit,
    private var onCopy: (String) -> Unit,
    var data: List<Shorten> = emptyList()
) : RecyclerView.Adapter<ShortenUrlAdapter.MyViewHolder>() {

    inner class MyViewHolder(private val binding: ViewShortenItemBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val shorten = getItem(position)
            binding.apply {
                shortenUrlTextView.text = shorten.shortLink.orLinkDefault()
                originalUrlTextView.text = shorten.originalLink.orLinkDefault()
                deleteImageView.setOnClickListener {
                    onDelete.invoke(shorten.shortenId)
                }
                copyButton.apply {
                    setOnClickListener {
                        onCopy.invoke(shorten.shortLink.orEmpty())
                        isEnabled = false
                        setBackgroundColor(ContextCompat.getColor(context, R.color.dark_violet))
                        text = context.getString(R.string.copied_label)
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ShortenUrlAdapter.MyViewHolder {
        return MyViewHolder(
            ViewShortenItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = data.size

    private fun getItem(position: Int): Shorten = data[position]

    fun updateList(data: List<Shorten>) {
        this.data = data
        notifyDataSetChanged()
    }
}