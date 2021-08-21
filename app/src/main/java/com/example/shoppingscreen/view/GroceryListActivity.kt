package com.example.shoppingscreen.view
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppingscreen.R
import com.example.shoppingscreen.model.GroceryItem
import com.example.shoppingscreen.viewmodel.GroceryListViewModel
import com.google.android.material.snackbar.Snackbar

/**
 * Main Screen
 */
class GroceryListActivity : AppCompatActivity(), NewItemDialogFragment.NewItemDialogListener {

  lateinit var viewModel: GroceryListViewModel
  // TODO: remove the view items and change them for the binding object
  private lateinit var addItemButton: Button
  private lateinit var groceryListRecyclerView: RecyclerView
  private lateinit var groceriesTotal: TextView

  override fun onCreate(savedInstanceState: Bundle?) {
    setTheme(R.style.ThemeOverlay_AppCompat)

    super.onCreate(savedInstanceState)
    // TODO: remove setContentView after data binding is added
    setContentView(R.layout.activity_grocery_list)
    viewModel = ViewModelProviders.of(this).get(GroceryListViewModel::class.java)
    // TODO: initialize the binding object and remove the view invocations
    addItemButton = findViewById(R.id.add_item_button)
    groceryListRecyclerView = findViewById(R.id.rv_grocery_list)
    groceriesTotal = findViewById(R.id.total_text_view)
    // TODO: associate the layout manager, adapter and button listener with the binding object
    groceryListRecyclerView.layoutManager = LinearLayoutManager(this)
    groceryListRecyclerView.adapter = GroceryAdapter(viewModel.groceryListItems, this,
        ::editGroceryItem, ::deleteGroceryItem)

    addItemButton.setOnClickListener {
      addGroceryItem()
    }

  }

  private fun addGroceryItem() {
    val newFragment = NewItemDialogFragment.newInstance(R.string.add_new_item_dialog_title, null)
    newFragment.show(supportFragmentManager, "newItem")
  }

  private fun editGroceryItem(position: Int) {
    Log.d("GoBuy", "edit")
    val newFragment = NewItemDialogFragment.newInstance(R.string.edit_item_dialog_title,
        position)
    newFragment.show(supportFragmentManager, "newItem")
  }

  private fun deleteGroceryItem(position: Int) {
    Log.d("GoBuy", "delete")
    viewModel.removeItem(position)
    groceriesTotal.text = viewModel.getTotal().toString()
    // TODO: call the adapter from the binding object
    groceryListRecyclerView.adapter?.notifyDataSetChanged()
  }

  override fun onDialogPositiveClick(dialog: DialogFragment, item: GroceryItem, isEdit: Boolean,
                                     position: Int?) {
    if (!isEdit) {
      viewModel.groceryListItems.add(item)
    } else {
      viewModel.updateItem(position!!, item)
      // TODO: call the adapter from the binding object
      groceryListRecyclerView.adapter?.notifyDataSetChanged()
    }

    // TODO: update the total amount and addItemButton with the binding
    groceriesTotal.text = viewModel.getTotal().toString()

    Snackbar.make(addItemButton, "Item Added Successfully", Snackbar.LENGTH_LONG).setAction("Action", null).show()
  }

  override fun onDialogNegativeClick(dialog: DialogFragment) {
    // TODO: update the addItemButton with the binding
    Snackbar.make(addItemButton, "Nothing Added", Snackbar.LENGTH_LONG).setAction("Action", null).show()
  }
}