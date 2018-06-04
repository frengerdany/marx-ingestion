package com.mx.frengersoft.marx

import scala.collection.mutable.MutableList
import com.mx.frengersoft.marx.Element

class Line {
  var idLinea: String = null
  var numLinea: Int = 0
  var estructuraValida: Boolean = false
  var ordenValido: Boolean = false
  var contenidoValido: Boolean = false
  var importesValidos: Boolean = false
  var tipoError: String = null
  var mensajeError: String = null
  var descripcion: String = null
  var linea: String = null
  var contenidoLinea: MutableList[Element] = MutableList[Element]()


  object initModuloContadoresLineas {
    var numLinea: Int = 0
    var lineasValidasEstructura: Int = 0
    var lineasValidasContenido: Int = 0
    var lineasErrorEstructura: Int = 0
    var lineasErrorContenido: Int = 0
  }
}
