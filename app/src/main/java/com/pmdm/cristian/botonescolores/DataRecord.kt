package com.pmdm.cristian.botonescolores

data class DataRecord(var record:Int) {

    fun saveRecord(record: Int) {
      this.record = record
    }

}