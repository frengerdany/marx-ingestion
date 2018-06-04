package com.mx.frengersoft.marx

import scala.collection.mutable.MutableList
import com.mx.frengersoft.marx.Element
import com.mx.frengersoft.marx.Line

object Process {

  object initModuloVariablesDispersion {
    var listaTemplateEncabezado = MutableList[Element]()
    var listaTemplateDetalleIndividual = MutableList[Element]()
    var listaTemplateCuentasPorPagar = MutableList[Element]()
    var listaTemplateSumario = MutableList[Element]()
    var lineaAnterior: Line = null
    var idLineaAnterior: String = "00"
    var totalLineasEncabezado: Int = 0
    var totalLineasDetalle02: Int = 0
    var totalLineasCuentasPorPagar: Int = 0
    var totalLineasSumario: Int = 0
    var tieneEncabezado: Boolean = false
    var tieneDetalleIndividual: Boolean = false
    var tieneCuentasPorPagar: Boolean = false
    var tieneSumario: Boolean = false
    var lineaEncabezado: Line = null
    var lineaSumario: Line = null
    var listaCuentasPorPagar = MutableList[Line]()
    var consecutivoAnterior: Long = 0
    var estadoSiguiente = 0
  }

  object initModuloSumatoriasDispersion{
    //detalle individual
    var detalle_02_id20: Long = 0
    var detalle_02_id21: Long = 0
    var detalle_02_id22: Long = 0
    var detalle_02_id23: Long = 0
    var detalle_02_id24: Long = 0
    var detalle_02_id25: Long = 0
    var detalle_02_id26_0: Long = 0
    var detalle_02_id26_1: Long = 0
    var detalle_02_id35_0: Long = 0
    var detalle_02_id35_1: Long = 0
    var detalle_02_id37: Long = 0
    var detalle_02_id38: Long = 0
    var detalle_02_id39: Long = 0
    var detalle_02_id40: Long = 0
    var detalle_02_id41: Long = 0
    var detalle_02_id42: Long = 0
    var importeTotal: Long = 0
    //cuentas por pagar
    var cuentasPorPagar_id4: Long = 0
  }

}
