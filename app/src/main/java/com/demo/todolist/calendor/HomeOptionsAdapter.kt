package com.demo.todolist.calendor

import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.demo.todolist.R
import com.demo.todolist.databinding.HomeOptionsItemViewBinding
import com.kizitonwose.calendarviewsample.Example3Fragment
import com.kizitonwose.calendarviewsample.layoutInflater

data class ExampleItem(
    @StringRes val titleRes: Int,
    @StringRes val subtitleRes: Int,
    val createView: () -> BaseFragment
)

class HomeOptionsAdapter(val onClick: (ExampleItem) -> Unit) :
    RecyclerView.Adapter<HomeOptionsAdapter.HomeOptionsViewHolder>() {

    val examples = listOf(
        ExampleItem(R.string.example_3_title, R.string.example_3_subtitle) { Example3Fragment() }

    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeOptionsViewHolder {
        return HomeOptionsViewHolder(
            HomeOptionsItemViewBinding.inflate(parent.context.layoutInflater, parent, false)
        )
    }

    override fun onBindViewHolder(viewHolder: HomeOptionsViewHolder, position: Int) {
        viewHolder.bind(examples[position])
    }

    override fun getItemCount(): Int = examples.size

    inner class HomeOptionsViewHolder(private val binding: HomeOptionsItemViewBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                onClick(examples[bindingAdapterPosition])
            }
        }

        fun bind(item: ExampleItem) {
            val context = itemView.context
            binding.itemOptionTitle.apply {
                text = if (item.titleRes != 0) context.getString(item.titleRes) else null
                isVisible = text.isNotBlank()
            }

            binding.itemOptionSubtitle.apply {
                text = if (item.subtitleRes != 0) context.getString(item.subtitleRes) else null
                isVisible = text.isNotBlank()
            }
        }
    }
}
