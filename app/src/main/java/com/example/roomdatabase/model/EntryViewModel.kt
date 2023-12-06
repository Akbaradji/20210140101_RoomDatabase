package com.example.roomdatabase.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.roomdatabase.repositori.RepositoriSiswa

class EntryViewModel(private val repositoriSiswa: RepositoriSiswa): ViewModel() {
    /**
     * Berisi Status Siswa Saat ini
     **/
    var UiStateSiswa by mutableStateOf(UIStateSiswa())
        private set

    /** Fungsi untuk memvalidasi input*/

    private fun validasiInput(uiState: DetailSiswa = UiStateSiswa.detailSiswa): Boolean {
        return with(uiState) {
            nama.isNotBlank() && alamat.isNotBlank() && telpon.isNotBlank()
        }
    }

    fun updateUiState(detailSiswa: DetailSiswa){
        UiStateSiswa =
            UIStateSiswa(detailSiswa = detailSiswa, isEntryValid = validasiInput(detailSiswa))
    }

    suspend fun saveSiswa() {
        if (validasiInput()) {
            repositoriSiswa.insertSiswa(UiStateSiswa.detailSiswa.toSiswa())
        }
    }
}