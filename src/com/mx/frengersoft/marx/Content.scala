package com.mx.frengersoft.marx

object Content {

    def validaContenidoLinea(numLinea: Int, lineaResultado: Line): Line = {

      var lineaContenido = new Line

      //Añadir lista de fragmentos a la linea dependiendo su id
      if (lineaResultado.idLinea.toInt == 1) { //TODO ENCABEZADO

        Process.initModuloVariablesDispersion.tieneEncabezado = true
        Process.initModuloVariablesDispersion.totalLineasEncabezado += 1

        lineaResultado.contenidoLinea = Process.initModuloVariablesDispersion.listaTemplateEncabezado
        lineaContenido = validaCampos(lineaResultado)

        //Aqui se guarda el encabezado para las validaciones finales con el sumario
        Process.initModuloVariablesDispersion.lineaEncabezado = lineaContenido

      } else if (lineaResultado.idLinea.toInt == 2) { //TODO DETALLE INDIVIDUAL

        Process.initModuloVariablesDispersion.tieneDetalleIndividual = true
        Process.initModuloVariablesDispersion.totalLineasDetalle02 += 1

        lineaResultado.contenidoLinea = Process.initModuloVariablesDispersion.listaTemplateDetalleIndividual
        lineaContenido = validaCampos(lineaResultado)

        /**
          * VALIDACION DE CONSECUTIVO
          */
        var consecutivo = lineaContenido.contenidoLinea(2).valorCampo.toLong

        //println("Contador Linea 02: " + ModuloVariablesDispersion.totalLineasDetalle02 + "  Consecutivo Linea 02: " + consecutivo)
        //TODO EL CONSECUTIVO TIENE QUE SER = AL CONTADOR DE LINEAS 02

        if (consecutivo != Process.initModuloVariablesDispersion.totalLineasDetalle02) {
          //lineaContenido.contenidoLinea(2).errorCampo = "Error en Consecutivo no valido"
          lineaContenido.contenidoLinea(2).campoValido = false
        }

        /**
          * VALIDACION DE SUMATORIAS DE IMPORTES
          * IMPORTANTE, LAS SUMATORIAS DEBEN CONTINUAR AUNQUE EXISTAN ERRORES DE CONTENIDO,
          * SE DEBERA DE VALIDAR LAS CANTIDADES APARTE PARA MANTENER LOS TOTALES INTACTOS
          */
        if (lineaContenido.importesValidos) {
          //SUMATORIAS DE CUENTAS POR PAGAR
          Process.initModuloSumatoriasDispersion.detalle_02_id20 += lineaContenido.contenidoLinea(19).valorCampo.toLong
          Process.initModuloSumatoriasDispersion.detalle_02_id21 += lineaContenido.contenidoLinea(20).valorCampo.toLong
          Process.initModuloSumatoriasDispersion.detalle_02_id22 += lineaContenido.contenidoLinea(21).valorCampo.toLong
          Process.initModuloSumatoriasDispersion.detalle_02_id23 += lineaContenido.contenidoLinea(22).valorCampo.toLong
          Process.initModuloSumatoriasDispersion.detalle_02_id24 += lineaContenido.contenidoLinea(23).valorCampo.toLong
          Process.initModuloSumatoriasDispersion.detalle_02_id25 += lineaContenido.contenidoLinea(24).valorCampo.toLong

          Process.initModuloSumatoriasDispersion.detalle_02_id37 += lineaContenido.contenidoLinea(36).valorCampo.toLong
          Process.initModuloSumatoriasDispersion.detalle_02_id38 += lineaContenido.contenidoLinea(37).valorCampo.toLong
          Process.initModuloSumatoriasDispersion.detalle_02_id39 += lineaContenido.contenidoLinea(38).valorCampo.toLong
          Process.initModuloSumatoriasDispersion.detalle_02_id40 += lineaContenido.contenidoLinea(39).valorCampo.toLong
          Process.initModuloSumatoriasDispersion.detalle_02_id41 += lineaContenido.contenidoLinea(40).valorCampo.toLong
          Process.initModuloSumatoriasDispersion.detalle_02_id42 += lineaContenido.contenidoLinea(41).valorCampo.toLong

          //id 26, id 35 caso especial separado por id 34 (Vivienda en garantia: 0 sin credito, 1 con credito)
          if (lineaContenido.contenidoLinea(33).valorCampo.toInt == 0) {
            Process.initModuloSumatoriasDispersion.detalle_02_id26_0 += lineaContenido.contenidoLinea(25).valorCampo.toLong
            Process.initModuloSumatoriasDispersion.detalle_02_id35_0 += lineaContenido.contenidoLinea(34).valorCampo.toLong
          } else if (lineaContenido.contenidoLinea(33).valorCampo.toInt == 1) {
            Process.initModuloSumatoriasDispersion.detalle_02_id26_1 += lineaContenido.contenidoLinea(25).valorCampo.toLong
            Process.initModuloSumatoriasDispersion.detalle_02_id35_1 += lineaContenido.contenidoLinea(34).valorCampo.toLong
          }
        }

      } else if (lineaResultado.idLinea.toInt == 8) { //TODO CUENTAS POR PAGAR

        Process.initModuloVariablesDispersion.tieneCuentasPorPagar = true
        Process.initModuloVariablesDispersion.totalLineasCuentasPorPagar += 1

        lineaResultado.contenidoLinea = Process.initModuloVariablesDispersion.listaTemplateCuentasPorPagar
        lineaContenido = validaCampos(lineaResultado)

        //Aqui se guardan las lineas de cuentas por pagar para su posterior validacion
        Process.initModuloVariablesDispersion.listaCuentasPorPagar += lineaContenido

        if (lineaContenido.importesValidos) {

          //Sumatorias para el sumario
          Process.initModuloSumatoriasDispersion.cuentasPorPagar_id4 += lineaContenido.contenidoLinea(3).valorCampo.toLong

          //TODO asignar de acuerdo al tipo de importe con id dependiente
          //TODO aqui se validan sumatorias de cuentas por pagar
          lineaContenido = DOIMSS.ModuloValidacionCuentasPorPagar.validaImportePorAportacion(lineaContenido)
          lineaContenido = DOIMSS.ModuloValidacionCuentasPorPagar.validaImporteAIVS(lineaContenido)
          lineaContenido = DOIMSS.ModuloValidacionCuentasPorPagar.validaImporteAIVSRemanente(lineaContenido)
          lineaContenido = DOIMSS.ModuloValidacionCuentasPorPagar.validaImporteAIVSExtemporaneo(lineaContenido)
        }

      } else if (lineaResultado.idLinea.toInt == 9) { //TODO SUMARIO

        Process.initModuloVariablesDispersion.tieneSumario = true
        Process.initModuloVariablesDispersion.totalLineasSumario += 1

        lineaResultado.contenidoLinea = Process.initModuloVariablesDispersion.listaTemplateSumario
        lineaContenido = validaCampos(lineaResultado)

        Process.initModuloVariablesDispersion.lineaSumario = lineaContenido

        //TODO SEPARAR VALIDACIONES DE IMPORTE
        if (lineaContenido.importesValidos) {
          //importe total rcv
          if (lineaContenido.contenidoLinea(9).valorCampo.toLong != Process.initModuloSumatoriasDispersion.importeTotal) {
            //lineaContenido.contenidoLinea(9).errorCampo = "Error en Diferencia RCV SUMARIO vs DETALLE 02"
            lineaContenido.contenidoLinea(9).campoValido = false
          }
          //importe total aportaciones voluntarias
          if (lineaContenido.contenidoLinea(11).valorCampo.toLong != Process.initModuloSumatoriasDispersion.detalle_02_id24) {
            //lineaContenido.contenidoLinea(11).errorCampo = "Error en Diferencia Aportaciones Voluntarias SUMARIO vs DETALLE 02"
            lineaContenido.contenidoLinea(11).campoValido = false
          }
          //importe total aportaciones voluntarias complementarias de retiro
          if (lineaContenido.contenidoLinea(13).valorCampo.toLong != Process.initModuloSumatoriasDispersion.detalle_02_id25) {
            //lineaContenido.contenidoLinea(13).errorCampo = "Error en Diferencia Aportaciones Complementarias de Retiro SUMARIO vs DETALLE 02"
            lineaContenido.contenidoLinea(13).campoValido = false
          }
          //importe total aportaciones a largo plazo
          if (lineaContenido.contenidoLinea(15).valorCampo.toLong != Process.initModuloSumatoriasDispersion.detalle_02_id41) {
            //lineaContenido.contenidoLinea(15).errorCampo = "Error en Diferencia Aportaciones a Largo Plazo SUMARIO vs DETALLE 02"
            lineaContenido.contenidoLinea(15).campoValido = false
          }
          //importe total aportaciones a subcuenta adicional
          if (lineaContenido.contenidoLinea(17).valorCampo.toLong != Process.initModuloSumatoriasDispersion.detalle_02_id42) {
            //lineaContenido.contenidoLinea(17).errorCampo = "Error en Diferencia Aportaciones a una Subcuenta Adicional SUMARIO vs DETALLE 02"
            lineaContenido.contenidoLinea(17).campoValido = false
          }
          //importe total aportacion patronal
          if (lineaContenido.contenidoLinea(19).valorCampo.toLong != Process.initModuloSumatoriasDispersion.detalle_02_id26_0) {
            //lineaContenido.contenidoLinea(19).errorCampo = "Error en Diferencia Vivienda SUMARIO vs DETALLE 02"
            lineaContenido.contenidoLinea(19).campoValido = false
          }
          //importe total cuenta por pagar
          if (lineaContenido.contenidoLinea(25).valorCampo.toLong != Process.initModuloSumatoriasDispersion.cuentasPorPagar_id4) {
            //lineaContenido.contenidoLinea(25).errorCampo = "Error en Diferencia detalle 09 vs Importe total Cuenta por Pagar Detalle 08"
            lineaContenido.contenidoLinea(25).campoValido = false
          }
          //importe total vivienda en garantia
          if (lineaContenido.contenidoLinea(26).valorCampo.toLong != Process.initModuloSumatoriasDispersion.detalle_02_id26_1) {
            //lineaContenido.contenidoLinea(26).errorCampo = "Error en Diferencia Vivienda en Garantia SUMARIO vs DETALLE 02"
            lineaContenido.contenidoLinea(26).campoValido = false
          }
          //total aplicaciones intereses de vivienda en aportacion patronal
          if (lineaContenido.contenidoLinea(28).valorCampo.toLong != Process.initModuloSumatoriasDispersion.detalle_02_id35_0) {
            //lineaContenido.contenidoLinea(28).errorCampo = "Error en Diferencia AIVS Vivienda 97 SUMARIO vs DETALLE 02"
            lineaContenido.contenidoLinea(28).campoValido = false
          }
          //total aplicaciones intereses de vivienda en garantia
          if (lineaContenido.contenidoLinea(29).valorCampo.toLong != Process.initModuloSumatoriasDispersion.detalle_02_id35_1) {
            //lineaContenido.contenidoLinea(29).errorCampo = "Error en Diferencia AIVS Vivienda 97 en Garantia SUMARIO vs DETALLE 02"
            lineaContenido.contenidoLinea(29).campoValido = false
          }
          //importe total remanente
          if (lineaContenido.contenidoLinea(30).valorCampo.toLong != Process.initModuloSumatoriasDispersion.detalle_02_id37) {
            //lineaContenido.contenidoLinea(30).errorCampo = "Error en Diferencia Remanente de Vivienda 97 del SUMARIO vs DETALLE 02"
            lineaContenido.contenidoLinea(30).campoValido = false
          }
          //total aplicaciones de intereses de vivienda por remanente
          if (lineaContenido.contenidoLinea(31).valorCampo.toLong != Process.initModuloSumatoriasDispersion.detalle_02_id38) {
            //lineaContenido.contenidoLinea(31).errorCampo = "Error en Diferencia AIVS Vivienda 97 Remanente del SUMARIO vs DETALLE 02"
            lineaContenido.contenidoLinea(31).campoValido = false
          }
          //Total de Aplicaciónes de Intereses de Vivienda por Pagos Extemporáneos
          if (lineaContenido.contenidoLinea(33).valorCampo.toLong != Process.initModuloSumatoriasDispersion.detalle_02_id40) {
            //lineaContenido.contenidoLinea(33).errorCampo = "Error en Diferencia AIVS Vivienda 97 Int Extemporaneos del SUMARIO vs DETALLE 02"
            lineaContenido.contenidoLinea(33).campoValido = false
          }
        }

        //VALIDACION CONTADORES DE TIPOS DE LINEAS
        //num registros aportaciones 02
        if (lineaContenido.contenidoLinea(23).valorCampo.toInt != Process.initModuloVariablesDispersion.totalLineasDetalle02) {
          //lineaContenido.contenidoLinea(23).errorCampo = "Error en Diferencia Registros detalle 09 vs Registros Detalle 02"
          lineaContenido.contenidoLinea(23).campoValido = false
        }
        //num registros cuentas por pagar
        if (lineaContenido.contenidoLinea(24).valorCampo.toInt != Process.initModuloVariablesDispersion.totalLineasCuentasPorPagar) {
          //lineaContenido.contenidoLinea(24).errorCampo = "Error en Diferencia Registros detalle 09 vs Registros Detalle 08"
          lineaContenido.contenidoLinea(24).campoValido = false
        }

        if (lineaContenido.contenidoValido) {
          //Validaciones Encabezado <-> Sumario
          //fecha lote
          if (!DOIMSS.ModuloValidacionSumario.validaCampoEncabezadoSumario(Process.initModuloVariablesDispersion.lineaEncabezado, lineaContenido, 7, 7)) {
            //lineaContenido.contenidoLinea(7).errorCampo = "Error en Fecha diferente vs fecha del encabezado"
            lineaContenido.contenidoLinea(7).campoValido = false
          }
          //consecutivo del dia
          if (!DOIMSS.ModuloValidacionSumario.validaCampoEncabezadoSumario(Process.initModuloVariablesDispersion.lineaEncabezado, lineaContenido, 8, 8)) {
            //lineaContenido.contenidoLinea(8).errorCampo = "Error en Consecutivo diferente vs consecutivo del encabezado"
            lineaContenido.contenidoLinea(8).campoValido = false
          }

        }
      }

      //if (lineaResultado.numLinea % 100000 == 0) {
      //println(lineaResultado.numLinea.toString + " lineas procesadas")
      //}

      return lineaContenido
    }

    def validaCampos(lineaResultado: Line): Line = {

      val lineaTemp = lineaResultado

      lineaTemp.contenidoValido = true
      lineaTemp.importesValidos = true

      lineaTemp.contenidoLinea.foreach { x =>

        var fragmentoLinea = lineaTemp.linea.substring(x.posicionInicial, x.posicionFinal)
        if (fragmentoLinea.matches(x.regexCampo)) {

          x.valorCampo = fragmentoLinea
          x.campoValido = true
        } else {

          if (x.esImporte == 1) {
            lineaTemp.importesValidos = false
          }

          x.valorCampo = fragmentoLinea
          x.campoValido = false
          lineaTemp.contenidoValido = false
        }

      }

      return lineaTemp
    }
}
