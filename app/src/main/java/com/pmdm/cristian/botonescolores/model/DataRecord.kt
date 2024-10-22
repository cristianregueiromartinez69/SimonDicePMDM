package com.pmdm.cristian.botonescolores.model

data class DataRecord(var record:Int) {

    fun saveRecord(record: Int) {
      this.record = record
    }

}