package com.jonrysimbolon.core.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.google.android.material.snackbar.Snackbar
import com.jonrysimbolon.core.extension.setupSnackbar
import com.jonrysimbolon.core.navigation.NavigationCommand

abstract class BaseFragment<T : ViewBinding, VM : BaseViewModel>(
    private val inflateMethod: (LayoutInflater, ViewGroup?, Boolean) -> T,
    private val viewModelClass: Class<VM>
) : Fragment() {

    private var _binding: T? = null
    protected val binding get() = _binding!!

    private val baseViewModelFactory by lazy { BaseViewModelFactory() }
    protected lateinit var baseViewModel: VM

    abstract fun T.initialize()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflateMethod.invoke(inflater, container, false)
        binding.initialize()
        baseViewModel = ViewModelProvider(this, baseViewModelFactory)[viewModelClass]
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeNavigation(baseViewModel)
        setupSnackbar(this, baseViewModel.snackBarError, Snackbar.LENGTH_LONG)
    }

    private fun observeNavigation(viewModel: BaseViewModel) {
        viewModel.navigation.observe(viewLifecycleOwner) {
            it?.getContentIfNotHandled()?.let { command ->
                when (command) {
                    is NavigationCommand.To -> findNavController().navigate(
                        command.directions,
                        getExtras()
                    )

                    is NavigationCommand.Back -> findNavController().navigateUp()
                }
            }
        }
    }

    open fun getExtras(): FragmentNavigator.Extras = FragmentNavigatorExtras()
}