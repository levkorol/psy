package ru.harlion.psy.ui.exercise.base.view_components.adapter


import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.harlion.psy.base.BindingHolder
import ru.harlion.psy.databinding.ItemEditTextVioletBinding


private typealias ItemHolder = BindingHolder<ItemEditTextVioletBinding>

class AdapterEditText :
    RecyclerView.Adapter<ItemHolder>() {

    var items : List<String>
        get() = _items
        set(value) {
            _items = value.toMutableList()
            notifyDataSetChanged()
        }

    private var _items = mutableListOf<String>()

    fun addItem(item : String) {
        _items.add(0, item)
        notifyItemInserted(0)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ItemHolder(ItemEditTextVioletBinding::inflate,parent).apply {
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
       holder.binding.apply {
           answers.setText(items[position])
       }
    }

    override fun getItemCount() = items.size


}