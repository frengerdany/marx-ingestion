package com.mx.frengersoft.marx

object DOIMSS {

  object ModuloValidacionCuentasPorPagar {
    def validaImportePorAportacion(lineaCuentaPorPagar: Line): Line = {

      var tipoSubCuenta = lineaCuentaPorPagar.contenidoLinea(2).valorCampo.substring(14, 15).toInt
      var importeAValidar = lineaCuentaPorPagar.contenidoLinea(3).valorCampo.toLong
      var sumatoriaImporte: Long = 0

      tipoSubCuenta match {

        case 1 =>
          //IMPORTE RCV - SUBCUENTA 1
          sumatoriaImporte = Process.initModuloSumatoriasDispersion.detalle_02_id20 + Process.initModuloSumatoriasDispersion.detalle_02_id21 + Process.initModuloSumatoriasDispersion.detalle_02_id22 + Process.initModuloSumatoriasDispersion.detalle_02_id23
          println("Tipo subcuenta: " + tipoSubCuenta + " : " + sumatoriaImporte + " = " + importeAValidar)
          if (sumatoriaImporte == importeAValidar) {

            Process.initModuloSumatoriasDispersion.importeTotal = sumatoriaImporte //TODO ???
            return lineaCuentaPorPagar
          } else {
            lineaCuentaPorPagar.contenidoLinea(3).campoValido = false
            //lineaCuentaPorPagar.contenidoLinea(3).errorCampo = "Error en Diferencia RCV detalle 08 vs detalle 02"
            return lineaCuentaPorPagar
          }
        case 3 =>
          //IMPORTE VIVIENDA 97 - SUBCUENTA 3 - TRABAJADOR SIN CREDITO
          sumatoriaImporte = Process.initModuloSumatoriasDispersion.detalle_02_id26_0
          println("Tipo subcuenta: " + tipoSubCuenta + " : " + sumatoriaImporte + " = " + importeAValidar)
          if (sumatoriaImporte == importeAValidar) {
            return lineaCuentaPorPagar
          } else {
            lineaCuentaPorPagar.contenidoLinea(4).campoValido = false
            //lineaCuentaPorPagar.contenidoLinea(4).errorCampo = "Error en Diferencia VIVIENDA 97 detalle 08 vs detalle 02"
            return lineaCuentaPorPagar
          }
        case 4 =>
          //IMPORTE VIVIENDA 97 EN GARANTIA - SUBCUENTA 4 - TRABAJADOR CON CREDITO
          sumatoriaImporte = Process.initModuloSumatoriasDispersion.detalle_02_id26_1
          println("Tipo subcuenta: " + tipoSubCuenta + " : " + sumatoriaImporte + " = " + importeAValidar)
          if (sumatoriaImporte == importeAValidar) {
            return lineaCuentaPorPagar
          } else {
            lineaCuentaPorPagar.contenidoLinea(5).campoValido = false
            //lineaCuentaPorPagar.contenidoLinea(5).errorCampo = "Error en Diferencia VIVIENDA 97 EN GARANTÍA detalle 08 vs detalle 02"
            return lineaCuentaPorPagar
          }
        case 5 =>
          //IMPORTE APORTACIONES VOLUNTARIAS - SUBCUENTA 5
          sumatoriaImporte = Process.initModuloSumatoriasDispersion.detalle_02_id24
          println("Tipo subcuenta: " + tipoSubCuenta + " : " + sumatoriaImporte + " = " + importeAValidar)
          if (sumatoriaImporte == importeAValidar) {
            return lineaCuentaPorPagar
          } else {
            lineaCuentaPorPagar.contenidoLinea(6).campoValido = false
            //lineaCuentaPorPagar.contenidoLinea(6).errorCampo = "Error en Diferencia APORTACIONES VOLUNTARIAS detalle 08 vs detalle 02"
            return lineaCuentaPorPagar
          }
        case 6 =>
          //IMPORTE APORTACIONES COMPLEMENTARIAS DE RETIRO - SUBCUENTA 6
          sumatoriaImporte = Process.initModuloSumatoriasDispersion.detalle_02_id25
          println("Tipo subcuenta: " + tipoSubCuenta + " : " + sumatoriaImporte + " = " + importeAValidar)
          if (sumatoriaImporte == importeAValidar) {
            return lineaCuentaPorPagar
          } else {
            lineaCuentaPorPagar.contenidoLinea(7).campoValido = false
            //lineaCuentaPorPagar.contenidoLinea(7).errorCampo = "Error en Diferencia APORTACIONES COMPLEMENTARIAS DE RETIRO detalle 08 vs detalle 02"
            return lineaCuentaPorPagar
          }
        case 7 =>
          //IMPORTE VIVIENDA 97 POR CONCEPTO DE REMANENTE - SUBCUENTA 7
          sumatoriaImporte = Process.initModuloSumatoriasDispersion.detalle_02_id37
          println("Tipo subcuenta: " + tipoSubCuenta + " : " + sumatoriaImporte + " = " + importeAValidar)
          if (sumatoriaImporte == importeAValidar) {
            return lineaCuentaPorPagar
          } else {
            lineaCuentaPorPagar.contenidoLinea(8).campoValido = false
            //lineaCuentaPorPagar.contenidoLinea(8).errorCampo = "Error en Diferencia VIVIENDA 97 POR CONCEPTO DE REMANENTE (PAGO 13) detalle 08 vs detalle 02"
            return lineaCuentaPorPagar
          }
        case 8 =>
          //IMPORTE VIVIENDA 97 POR CONCEPTO PAGOS EXTEMPORANEOS - SUBCUENTA 8
          sumatoriaImporte = Process.initModuloSumatoriasDispersion.detalle_02_id39
          println("Tipo subcuenta: " + tipoSubCuenta + " : " + sumatoriaImporte + " = " + importeAValidar)
          if (sumatoriaImporte == importeAValidar) {
            return lineaCuentaPorPagar
          } else {
            lineaCuentaPorPagar.contenidoLinea(9).campoValido = false
            //lineaCuentaPorPagar.contenidoLinea(9).errorCampo = "Error en Diferencia VIVIENDA 97 POR CONCEPTO DE PAGOS EXTEMPORÁNEOS DE VIVIENDA detalle 08 vs detalle 02"
            return lineaCuentaPorPagar
          }
        case 9 =>
          //IMPORTE APORTACIONES DE AHORRO A LARGO PLAZO - SUBCUENTA 9
          sumatoriaImporte = Process.initModuloSumatoriasDispersion.detalle_02_id41
          println("Tipo subcuenta: " + tipoSubCuenta + " : " + sumatoriaImporte + " = " + importeAValidar)
          if (sumatoriaImporte == importeAValidar) {
            return lineaCuentaPorPagar
          } else {
            lineaCuentaPorPagar.contenidoLinea(10).campoValido = false
            //lineaCuentaPorPagar.contenidoLinea(10).errorCampo = "Error en Diferencia APORTACIONES DE AHORRO A LARGO PLAZO detalle 08 vs detalle 02"
            return lineaCuentaPorPagar
          }
        case _ =>
          return lineaCuentaPorPagar
      }
    }

    def validaImporteAIVS(lineaCuentaPorPagar: Line): Line = {

      var tipoSubCuenta = lineaCuentaPorPagar.contenidoLinea(2).valorCampo.substring(14, 15).toInt
      var importeAValidar = lineaCuentaPorPagar.contenidoLinea(14).valorCampo.toLong //antes era 7
      var sumatoriaImporte: Long = 0

      tipoSubCuenta match {

        case 3 =>
          //IMPORTE AIVS - APLICACIONES_DE_INTERESES_DE_VIVIENDA_ENVIADAS - TRABAJADOR SIN CREDITO - SUBCUENTA 3
          sumatoriaImporte = Process.initModuloSumatoriasDispersion.detalle_02_id35_0
          println("Tipo subcuenta: " + tipoSubCuenta + " : " + sumatoriaImporte + " = " + importeAValidar)
          if (sumatoriaImporte == importeAValidar) {
            return lineaCuentaPorPagar
          } else {
            lineaCuentaPorPagar.contenidoLinea(14).campoValido = false
            return lineaCuentaPorPagar
          }
        case 4 =>
          //IMPORTE AIVS - APLICACIONES_DE_INTERESES_DE_VIVIENDA_ENVIADAS - TRABAJADOR CON CREDITO - SUBCUENTA 4
          sumatoriaImporte = Process.initModuloSumatoriasDispersion.detalle_02_id35_1
          println("Tipo subcuenta: " + tipoSubCuenta + " : " + sumatoriaImporte + " = " + importeAValidar)
          if (sumatoriaImporte == importeAValidar) {
            return lineaCuentaPorPagar
          } else {
            lineaCuentaPorPagar.contenidoLinea(15).campoValido = false
            return lineaCuentaPorPagar
          }
        case _ =>
          return lineaCuentaPorPagar
      }
    }

    def validaImporteAIVSRemanente(lineaCuentaPorPagar: Line): Line = {

      var tipoSubCuenta = lineaCuentaPorPagar.contenidoLinea(2).valorCampo.substring(14, 15).toInt
      var importeAValidar = lineaCuentaPorPagar.contenidoLinea(18).valorCampo.toLong
      var sumatoriaImporte: Long = 0

      tipoSubCuenta match {

        case 7 =>
          //IMPORTE AIVS REMANENTE - SUBCUENTA 7
          sumatoriaImporte = Process.initModuloSumatoriasDispersion.detalle_02_id38
          println("Tipo subcuenta: " + tipoSubCuenta + " : " + sumatoriaImporte + " = " + importeAValidar)
          if (sumatoriaImporte == importeAValidar) {
            return lineaCuentaPorPagar
          } else {
            lineaCuentaPorPagar.contenidoLinea(18).campoValido = false
            //lineaCuentaPorPagar.contenidoLinea(18).errorCampo = "Error en Diferencia AIVS VIVIENDA 97 POR CONCEPTO DE REMANENTE (PAGO 13) detalle 08 vs detalle 02"
            return lineaCuentaPorPagar
          }
        case _ =>
          return lineaCuentaPorPagar
      }
    }

    def validaImporteAIVSExtemporaneo(lineaCuentaPorPagar: Line): Line = {

      var tipoSubCuenta = lineaCuentaPorPagar.contenidoLinea(2).valorCampo.substring(14, 15).toInt
      var importeAValidar = lineaCuentaPorPagar.contenidoLinea(21).valorCampo.toLong
      var sumatoriaImporte: Long = 0

      tipoSubCuenta match {

        case 8 =>
          //IMPORTE AIVS REMANENTE - SUBCUENTA 8
          sumatoriaImporte = Process.initModuloSumatoriasDispersion.detalle_02_id40
          println("Tipo subcuenta: " + tipoSubCuenta + " : " + sumatoriaImporte + " = " + importeAValidar)
          if (sumatoriaImporte == importeAValidar) {
            return lineaCuentaPorPagar
          } else {
            lineaCuentaPorPagar.contenidoLinea(21).campoValido = false
            //lineaCuentaPorPagar.contenidoLinea(21).errorCampo = "Error en Diferencia AIVS VIVENDA 97 POR CONCEPTO DE PAGOS EXTEMPORÁNEOS detalle 08 vs detalle 02"
            return lineaCuentaPorPagar
          }
        case _ =>
          return lineaCuentaPorPagar
      }
    }
  }

  object ModuloValidacionSumario {

    def validaCampoEncabezadoSumario(lineaEncabezado: Line, lineaSumario: Line, idEncabezado: Int, idSumario: Int): Boolean = {

      var campoEncabezado = lineaEncabezado.contenidoLinea(idEncabezado).valorCampo.toString
      var campoSumario = lineaSumario.contenidoLinea(idSumario).valorCampo.toString

      if (campoEncabezado == campoSumario) {
        return true
      } else {
        return false
      }
    }
  }
}
