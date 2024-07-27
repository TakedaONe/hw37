import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lalafo.R
import com.example.lalafo.databinding.FragmentSubCategoryBinding
import com.example.lalafo.databinding.ItemSubCategoryBinding

class SubCategoryFragment : Fragment() {

    private lateinit var binding: FragmentSubCategoryBinding
    private lateinit var subCategoryAdapter: SubCategoryAdapter

    companion object {
        private const val ARG_PRODUCT_NAME = "product_name"

        fun newInstance(productName: String) = SubCategoryFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_PRODUCT_NAME, productName)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSubCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val productName = arguments?.getString(ARG_PRODUCT_NAME)

        binding.textViewTitle.text = getString(R.string.sub_category_title, productName)

        subCategoryAdapter = SubCategoryAdapter(getSubCategoryList(productName ?: "")) { subCategory ->
            val descriptionFragment = DescriptionFragment.newInstance(subCategory.name, subCategory.description, subCategory.imageResId)
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, descriptionFragment)
                .addToBackStack(null)
                .commit()
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = subCategoryAdapter
    }

    private fun getSubCategoryList(productName: String): List<SubCategory> {
        return when (productName) {
            "BMW X5" -> listOf(
                SubCategory("X5 M", "Характеристики X5 M", R.drawable.x5_m),
                SubCategory("X5 xDrive", "Характеристики X5 xDrive", R.drawable.x5_xdrive)
            )
            "BMW X3" -> listOf(
                SubCategory("X3 M", "Характеристики X3 M", R.drawable.x3_m),
                SubCategory("X3 xDrive", "Характеристики X3 xDrive", R.drawable.x3_xdrive)
            )
            "BMW X7" -> listOf(
                SubCategory("X7 M", "Характеристики X7 M", R.drawable.x7_m),
                SubCategory("X7 xDrive", "Характеристики X7 xDrive", R.drawable.x7_xdrive)
            )
            else -> emptyList()
        }
    }
}

data class SubCategory(val name: String, val description: String, val imageResId: Int)

class SubCategoryAdapter(private val subCategories: List<SubCategory>, private val onItemClick: (SubCategory) -> Unit) :
    RecyclerView.Adapter<SubCategoryAdapter.SubCategoryViewHolder>() {

    inner class SubCategoryViewHolder(private val binding: ItemSubCategoryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(subCategory: SubCategory) {
            binding.textViewName.text = subCategory.name
            binding.imageView.setImageResource(subCategory.imageResId)
            binding.root.setOnClickListener {
                onItemClick(subCategory)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubCategoryViewHolder {
        val binding = ItemSubCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SubCategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SubCategoryViewHolder, position: Int) {
        holder.bind(subCategories[position])
    }

    override fun getItemCount(): Int = subCategories.size
}
