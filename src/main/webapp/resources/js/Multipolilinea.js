/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var polilineaTO;
var lineaSeleccionada;
var arrayPolilineasSeleccionadas;
var pathsTemp;
var arrayPathsTemp;
var pathsTemp;
var arrayIndicesPolilineas;
var indicesPolilineas;
var estatus = null;
var mapPolilineas;
var cMapaId;
var zoom = 10;
var listenerdblClickMapa;
var listenerClickMapa;

function inicializarDatos(indices, idMapa, zoomParam) {
    indicesPolilineas = indices;
    cMapaId = idMapa;
    var mapa = PF('' + cMapaId).getMap();
    arrayPolilineasSeleccionadas = [];
    if (indices.length > 0) {
        arrayIndicesPolilineas = indices.split(",");

        for (var idx in arrayIndicesPolilineas) {
            polilineaTO = new Object();
            lineaSeleccionada = mapa.polylines[arrayIndicesPolilineas[idx]];
            polilineaTO.polilinea = lineaSeleccionada;
            polilineaTO.pathsTemp = lineaSeleccionada.getPath().getArray().slice();
            polilineaTO.indice = arrayIndicesPolilineas[idx];
            arrayPolilineasSeleccionadas.push(polilineaTO);
        }
    }
    lineaSeleccionada = null;
    arrayPathsTemp = [];
    lineaSeleccionada = null;
    pathsTemp = null;
    zoom = zoomParam;

    listenerdblClickMapa = mapa.addListener('dblclick', function () {
        terminarAgregarPolilinea();
    });
}

function terminarAgregarPolilinea() {
    if (estatus === 'NUEVO') {
        google.maps.event.removeListener(listenerClickMapa);
    }
}

function agregarPolilinea() {
    terminarAgregarPolilinea();
    estatus = 'NUEVO';
    var mapa = PF('' + cMapaId).getMap();
    pathsTemp = [];
//    mapa.addListener('dblclick', agregarLatLngPoligono);
    lineaSeleccionada = new google.maps.Polyline({
        paths: pathsTemp,
        strokeColor: '#000000',
        strokeOpacity: 1.0,
        strokeWeight: 3
    });
    lineaSeleccionada.setEditable(true);
    lineaSeleccionada.setDraggable(true);
    lineaSeleccionada.setMap(mapa);

    polilineaTO = new Object();
    polilineaTO.polilinea = lineaSeleccionada;
    polilineaTO.pathsTemp = lineaSeleccionada.getPath();
    polilineaTO.indice = -1;
    arrayPolilineasSeleccionadas.push(polilineaTO);
    listenerClickMapa = mapa.addListener('click', agregarLatLngPolilinea);
}

function editarPolilinea() {
    estatus = 'EDITAR';
    var mapa = PF('' + cMapaId).getMap();

    for (var idx in arrayPolilineasSeleccionadas) {
        mapa.polylines[arrayPolilineasSeleccionadas[idx].indice].setDraggable(true);
        mapa.polylines[arrayPolilineasSeleccionadas[idx].indice].setEditable(true);
    }

    if (arrayPolilineasSeleccionadas.length > 0) {
        estatus = 'EDITAR';
    } else {
        alert('No existe cartografía del predio por favor trazela en el mapa');
        estatus = 'NINGUNA';
    }
}

function cancelarTrazoPolilinea() {
    if (estatus === 'EDITAR') {
        cancelarEdicionPoligono();
    } else if (estatus === 'NUEVO') {
        cancelarNuevoPolilinea();
    }
//    estatus = 'NINGUNA';
}

function cancelarEdicionPolilinea() {
    var mapa = PF('' + cMapaId).getMap();
    for (var idx in arrayPolilineasSeleccionadas) {
        mapa.polylines[arrayPolilineasSeleccionadas[idx].indice].setDraggable(false);
        mapa.polylines[arrayPolilineasSeleccionadas[idx].indice].setEditable(false);
        mapa.polylines[arrayPolilineasSeleccionadas[idx].indice].setPath(arrayPolilineasSeleccionadas[idx].pathsTemp);
    }
}



function cancelarNuevoPolilinea() {
    if (lineaSeleccionada !== null) {
        try {
            lineaSeleccionada.setDraggable(false);
            lineaSeleccionada.setEditable(false);
            lineaSeleccionada.setMap(null);
            lineaSeleccionada = null;
            pathsTemp = null;
        } catch (exception) {
            console.log('Error cancelarNuevolinea' + exception);
        }
    }
}


function agregarLatLngPolilinea(event) {
    try {
        if (lineaSeleccionada !== null) {
            pathsTemp.push(event.latLng);
            if (lineaSeleccionada.getPath() === undefined) {
                lineaSeleccionada.setPath(pathsTemp);
            } else {
                var pathPologono = lineaSeleccionada.getPath().getArray();
                pathPologono.push(event.latLng);
                lineaSeleccionada.setPath(pathPologono);
                lineaSeleccionada.setEditable(true);

            }
        }
    } catch (error) {
        console.log(error);
    }
}

function transformarCoordenadasMultiLinea(idInput) {
    var mapa = PF('' + cMapaId).getMap();
    for (var idx in arrayPolilineasSeleccionadas) {
        var poligonoSeleccionadoTO = arrayPolilineasSeleccionadas[idx];
        if (poligonoSeleccionadoTO.polilinea.getMap !== null) {
            if (poligonoSeleccionadoTO.poligono !== null && validarPolilinea(poligonoSeleccionadoTO.polilinea) === false) {
                alert('Los vialidades deben contener minimo 2 vertices');
                return;
            }
        }
//        }
    }

    var multipolineas = new Array();

    for (var idx in arrayPolilineasSeleccionadas) {
        var poligonoTO = arrayPolilineasSeleccionadas[idx];

        if (poligonoTO.polilinea.getMap !== null && poligonoTO.polilinea.getPath() !== undefined && poligonoTO.polilinea.getPath() !== null
                && poligonoTO.polilinea.getPath().getArray() !== undefined && poligonoTO.polilinea.getPath().getArray().length > 1) {
            multipolineas.push(poligonoTO.polilinea.getPath().getArray());
        }
    }
    document.getElementById('' + idInput).value = JSON.stringify(multipolineas);
    guardarTodo();
}

