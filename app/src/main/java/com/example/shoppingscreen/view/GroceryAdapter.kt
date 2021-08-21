package com.example.shoppingscreen.view
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppingscreen.R
import com.example.shoppingscreen.model.GroceryItem


class GroceryAdapter(val items: ArrayList<GroceryItem>, val context: Context,
                     val itemEditListener: (position: Int) -> Unit,
                     val itemDeleteListener: (position: Int) -> Unit) : RecyclerView.Adapter<ViewHolder>() {
    // Gets the number of groceries in the list
    override fun getItemCount() = items.size

    // TODO: return the binding instead of a view
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var viewHolder = ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.grocery_list_item, parent,
            false))
        return viewHolder
    }

    // TODO: remove the text setters and replace them by the binding in the view holder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        val description = item.amount.toString() + "x: " + item.itemName
        holder.textViewGroceryItem.text = description
        holder.textViewPrice.text = item.finalPrice.toString()
        holder.buttonEdit.setOnClickListener { itemEditListener(position) }
        holder.buttonDelete.setOnClickListener { itemDeleteListener(position) }

    }
}

// TODO: put a binding object as a parameter instead of a view
class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val textViewGroceryItem: TextView = view.findViewById(R.id.tv_grocery_item_name)
    val textViewPrice: TextView = view.findViewById(R.id.tv_grocery_item_price)
    val buttonEdit: ImageButton = view.findViewById(R.id.button_edit)
    val buttonDelete: ImageButton = view.findViewById(R.id.button_delete)

}