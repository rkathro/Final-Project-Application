import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.ui.account.AccountFragment.CompanyData
import com.example.myapplication.R // Make sure to import your R file

class AccountAdapter(private val dataList: List<CompanyData>) : RecyclerView.Adapter<AccountViewHolder>() {

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
}

class AccountViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val companyLogo: ImageView = itemView.findViewById(R.id.companyLogo)
    private val companyName: TextView = itemView.findViewById(R.id.companyName)

    fun bind(data: CompanyData) {
        companyLogo.setImageResource(data.logoDrawableId)
        companyName.text = data.companyName
    }
}