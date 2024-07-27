import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.lalafo.databinding.FragmentDescriptionBinding

class DescriptionFragment : Fragment() {

    private lateinit var binding: FragmentDescriptionBinding

    companion object {
        private const val ARG_NAME = "name"
        private const val ARG_DESCRIPTION = "description"
        private const val ARG_IMAGE_RES_ID = "image_res_id"

        fun newInstance(name: String, description: String, imageResId: Int) = DescriptionFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_NAME, name)
                putString(ARG_DESCRIPTION, description)
                putInt(ARG_IMAGE_RES_ID, imageResId)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDescriptionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val name = arguments?.getString(ARG_NAME)
        val description = arguments?.getString(ARG_DESCRIPTION)
        val imageResId = arguments?.getInt(ARG_IMAGE_RES_ID)

        binding.textViewName.text = name
        binding.textViewDescription.text = description
        imageResId?.let {
            binding.imageView.setImageResource(it)
        }
    }
}
