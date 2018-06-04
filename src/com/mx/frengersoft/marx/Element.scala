package com.mx.frengersoft.marx

class Element(
               var idCampo: Int,
               var idLinea: Int,
               var nombreCampo: String,
               var regexCampo: String,
               var errorCampo: String,
               var descripcionCampo: String,
               var esFecha: Int,
               var logitud: Int,
               var posicionInicial: Int,
               var posicionFinal: Int,
               var idDependiente: Int,
               var esImporte: Int) {

  var valorCampo: String = null
  var campoValido: Boolean = false
}
