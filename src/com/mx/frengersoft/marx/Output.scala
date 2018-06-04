package com.mx.frengersoft.marx

class Output {

  def crearArchivoSalida(lineaResultado: Line): String = {

    var registro: String = ""
    lineaResultado.contenidoLinea.foreach { x =>

      if (!x.campoValido) {
        var fragmentoRegistro = "\"" + Launcher.initModuloVariablesArchivoSalida.idArchivo + """","""" + Launcher.initModuloVariablesArchivoSalida.folioProceso + """","""" + Launcher.ModuloVariablesTiempo.date + """","""" + lineaResultado.idLinea + """","""" + lineaResultado.numLinea + """","""" + x.nombreCampo + """","""" + "desc validacion" + """","""" + x.valorCampo + """","""" + "000" + """","""" + x.errorCampo + """","""" + Process.initModuloVariablesDispersion.consecutivoAnterior + "\"" + sys.props("line.separator")
        registro += fragmentoRegistro
      }

    }

    return registro
  }
}
