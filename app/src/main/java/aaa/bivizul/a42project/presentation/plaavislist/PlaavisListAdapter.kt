package aaa.bivizul.a42project.presentation.plaavislist

import aaa.bivizul.a42project.databinding.ItemPlaavisBinding
import aaa.bivizul.a42project.domain.model.Plaavis
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load

private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Plaavis>() {
    override fun areItemsTheSame(oldItem: Plaavis, newItem: Plaavis) =
        oldItem.hashCode() == newItem.hashCode()

    override fun areContentsTheSame(oldItem: Plaavis, newItem: Plaavis) =
        oldItem == newItem
}

class PlaavisListAdapter :
    ListAdapter<Plaavis, PlaavisListAdapter.PlaavisViewHolder>(DIFF_CALLBACK) {

    inner class PlaavisViewHolder(val binding: ItemPlaavisBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaavisViewHolder {
        val binding = ItemPlaavisBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PlaavisViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlaavisViewHolder, position: Int) {
        val item = getItem(position)

        with(holder.binding) {
            textViewTitle.text = item.plaaviTitle
            if (item.plaaviImage.isNotEmpty()) {
                imageViewPlaavis.load(item.plaaviImage)
            }
            textViewDescription.text = item.plaaviDesc
        }
    }
}