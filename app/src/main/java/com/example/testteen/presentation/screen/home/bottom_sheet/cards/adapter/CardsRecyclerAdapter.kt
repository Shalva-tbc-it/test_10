package com.example.testteen.presentation.screen.home.bottom_sheet.cards.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.testteen.databinding.RecyclerCardsBinding
import com.example.testteen.presentation.model.Cards

class CardsRecyclerAdapter : ListAdapter<Cards, CardsRecyclerAdapter.CardsViewHolder>(CardsDiffUtils()) {
    inner class CardsViewHolder(private val binding: RecyclerCardsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind() = with(binding) {
            val cards = currentList[adapterPosition]

            tvAccountName.text = cards.accountName
            tvCardType.text = cards.cardType
            tvValueType.text = cards.valueType

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardsViewHolder {
        return CardsViewHolder(
            RecyclerCardsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CardsViewHolder, position: Int) {
        holder.bind()
    }

    class CardsDiffUtils : DiffUtil.ItemCallback<Cards>() {
        override fun areItemsTheSame(oldItem: Cards, newItem: Cards): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Cards, newItem: Cards): Boolean {
            return oldItem == newItem
        }

    }
}