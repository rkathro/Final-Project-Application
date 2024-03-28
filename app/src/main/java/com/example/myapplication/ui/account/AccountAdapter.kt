import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.ui.account.AccountFragment.CompanyData
import com.example.myapplication.R // Make sure to import your R file

class AccountAdapter(private val dataList: List<CompanyData>, private val optionClickListener: OnOptionClickListener) : RecyclerView.Adapter<AccountViewHolder>() {

    private var menuClickListener: ((view: View, position: Int) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.gray_box, parent, false)
        return AccountViewHolder(view)
    }

    override fun onBindViewHolder(holder: AccountViewHolder, position: Int) {
        val dataItem = dataList[position]
        holder.bind(dataItem)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    interface OnOptionClickListener {
        fun onRenameClicked()
        fun onDeleteClicked()
        fun onCancelClicked()
    }
    fun setOnMenuClickListener(listener: (view: View, position: Int) -> Unit) {
        menuClickListener = listener
    }

    fun getMenuClickListener(): ((view: View, position: Int) -> Unit)? {
        return menuClickListener
    }
}

class AccountViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val companyLogo: ImageView = itemView.findViewById(R.id.companyLogo)
    private val companyName: TextView = itemView.findViewById(R.id.companyName)
    private val optionsButton: Button = itemView.findViewById(R.id.menuButton)

    fun bind(data: CompanyData) {
        companyLogo.setImageResource(data.logoDrawableId)
        companyName.text = data.companyName

        val listener = (itemView.context as? AccountAdapter.OnOptionClickListener)
        optionsButton.setOnClickListener {
            listener?.let { clickListener ->
                getMenuClickListener()?.invoke(it, adapterPosition)
            }
        }
    }

    private fun getMenuClickListener(): ((view: View, position: Int) -> Unit)? {
        return (itemView.context as? AccountAdapter)?.getMenuClickListener()
    }

}