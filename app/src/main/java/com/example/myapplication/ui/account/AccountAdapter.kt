import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.widget.PopupMenu
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.ui.account.AccountFragment.CompanyData
import com.example.myapplication.R // Make sure to import your R file

class AccountAdapter(private val dataList: MutableList<CompanyData>) : RecyclerView.Adapter<AccountViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.gray_box, parent, false)
        return AccountViewHolder(view,this)
    }

    override fun onBindViewHolder(holder: AccountViewHolder, position: Int) {
        val dataItem = dataList[position]
        holder.bind(dataItem)

    }

    override fun getItemCount(): Int {
        return dataList.size
    }
    fun deleteItem(position: Int) {
        dataList.removeAt(position)
        notifyItemRemoved(position)
    }
}

class AccountViewHolder(itemView: View, private val adapter: AccountAdapter) : RecyclerView.ViewHolder(itemView) {
    private val companyLogo: ImageView = itemView.findViewById(R.id.companyLogo)
    private val companyName: TextView = itemView.findViewById(R.id.companyName)
    private val menuButton: ImageButton = itemView.findViewById(R.id.menuButton)

    fun bind(data: CompanyData) {
        companyLogo.setImageResource(data.logoDrawableId)
        companyName.text = data.companyName

        menuButton.setOnClickListener {
            showPopupMenu(it)
        }
    }
    private fun showPopupMenu(view: View) {
        val popupMenu = PopupMenu(view.context, view)
        popupMenu.menuInflater.inflate(R.menu.popup_menu, popupMenu.menu)
        popupMenu.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.rename_option -> {
                    showRenameDialog()
                    true
                }
                R.id.delete_option -> {
                    showDeleteConfirmationDialog(adapterPosition)
                    true
                }
                R.id.cancel_option -> {
                    // Handle cancel option
                    true
                }
                else -> false
            }
        }
        popupMenu.show()
    }
    private fun showRenameDialog() {
        val builder = AlertDialog.Builder(itemView.context)
        val inflater = LayoutInflater.from(itemView.context)
        val dialogView = inflater.inflate(R.layout.rename_dialog, null)

        val editTextNewName = dialogView.findViewById<EditText>(R.id.editTextNewName)

        builder.setTitle("Rename Company")
            .setView(dialogView)
            .setPositiveButton("OK") { dialog, which ->
                // Get the new company name from the EditText
                val newName = editTextNewName.text.toString()
                // Update the company name
                companyName.text = newName
            }
            .setNegativeButton("Cancel") { dialog, which ->
                // Do nothing on cancel
            }
            .show()
    }
    private fun showDeleteConfirmationDialog(position: Int) {
        AlertDialog.Builder(itemView.context)
            .setTitle("Delete Company")
            .setMessage("Are you sure you want to delete this company?")
            .setPositiveButton("Delete") { dialog, which ->
                adapter.deleteItem(position)
            }
            .setNegativeButton("Cancel", null)
            .show()
    }
}