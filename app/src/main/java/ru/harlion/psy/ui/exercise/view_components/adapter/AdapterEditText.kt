package ru.harlion.psy.ui.exercise.view_components.adapter


import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.harlion.psy.base.BindingHolder
import ru.harlion.psy.databinding.ItemEditTextVioletBinding
import ru.harlion.psy.models.Exercise


private typealias ItemHolder = BindingHolder<ItemEditTextVioletBinding>

class AdapterEditText :
    RecyclerView.Adapter<ItemHolder>() {

    var items = listOf<String>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ItemHolder(ItemEditTextVioletBinding::inflate,parent).apply {
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
       holder.binding.apply {
           //answers.text = items[position].listString //todo
       }
    }

    override fun getItemCount() = items.size


}