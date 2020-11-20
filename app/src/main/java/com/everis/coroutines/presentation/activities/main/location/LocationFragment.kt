package com.everis.coroutines.presentation.activities.main.location

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.everis.coroutines.CoroutinesApp
import com.everis.coroutines.R
import com.everis.coroutines.databinding.FragmentLocationBinding
import com.everis.coroutines.usecase.location.GetLastLocation
import com.everis.coroutines.usecase.location.GetLocation

class LocationFragment : Fragment() {
    private var _binding: FragmentLocationBinding? = null
    private val binding get() = _binding!!
    private val app by lazy { requireActivity().application as CoroutinesApp }
    private val getLastLocation by lazy { GetLastLocation(repository = app.locationRepository) }
    private val getLocation by lazy { GetLocation(repository = app.locationRepository) }
    private val viewModel by viewModels<LocationViewModel> {
        LocationViewModelProvider(getLastLocation = getLastLocation, getLocation = getLocation)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLocationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.getLastLocationBtn.setOnClickListener {
            viewModel.onGetLastLocationClick()
        }
        binding.getLocationBtn.setOnClickListener {
            viewModel.onGetLocationClick()
        }

        viewModel.locationLD.observe(viewLifecycleOwner) { location ->
            binding.latitudeTv.text = getString(R.string.latitude, location.latitude)
            binding.longitudeTv.text = getString(R.string.longitude, location.longitude)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}