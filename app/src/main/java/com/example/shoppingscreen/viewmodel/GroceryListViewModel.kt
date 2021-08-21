package com.example.shoppingscreen.viewmodel


import androidx.lifecycle.ViewModel
import com.example.shoppingscreen.model.GroceryItem

class GroceryListViewModel : ViewModel() {
  var groceryListItems: ArrayList<GroceryItem> = ArrayList()

  fun getTotal(): Double = groceryListItems.map { it.finalPrice }.sum()

  fun removeItem(position: Int) {
    groceryListItems.removeAt(position)
  }

  fun updateItem(position: Int, updatedItem: GroceryItem) {
    groceryListItems[position] = updatedItem
  }
}