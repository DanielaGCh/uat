/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var lineaSelect;
var pathsTemp;
var indiceLinea;
var estatus = null;
var lineaIdMapa;
var zoom = 10;

function editar(indice, mapaId, zoomParam) {
    lineaIdMapa = mapaId;
    indiceLinea = indice;
    var mapa = PF('' + lineaIdMapa).getMap();
    if (indiceLinea >= 0) {
        lineaSelect = mapa.polylines[indiceLinea];
        pathsTemp = lineaSelect.getPath().getArray().slice();
        ubicarPolilinea(mapaId, zoomParam, 5, pathsTemp[0].lat(), pathsTemp[0].lng(), indiceLinea);
    }
    pathsTemp = null;
    estatus = 'NINGUNA';
    zoom = zoomParam;
}

function nuevo(mapaIdParam) {
    lineaIdMapa = mapaIdParam;
    pathsTemp = null;
    estatus = 'NINGUNA';
}

function mostrarLinea() {
    estatus = 'EDITAR';
    var mapa = PF('' + lineaIdMapa).getMap();
    if (indiceLinea >= 0) {
        estatus = 'EDITAR';
        lineaSelect = mapa.polylines[indiceLinea];
        pathsTemp = lineaSelect.getPath().getArray().slice();
        try {
            mapa.setCenter((new google.maps.LatLng(pathsTemp[0].lat(), pathsTemp[0].lng())));
            mapa.setZoom(zoom);
            mapa.polylines[indiceLinea].setDraggable(true);
            mapa.polylines[indiceLinea].setEditable(true);
        } catch (exception) {
            console.log('Error  :' + exception);
        }
    } else {
        agregarNuevaLinea(lineaIdMapa);
        alert('No existe cartografía del predio por favor trazela en el mapa');
        estatus = 'NINGUNA';
    }
}

function editarLinea(indice, mapaId, zoomParam) {
    editar(indice, mapaId, zoomParam);
    zoom = zoomParam;
    mostrarLinea();
}

function agregarNuevaLinea(mapaIdParam) {
    nuevo(mapaIdParam);
    estatus = 'NUEVO';
    var mapa = PF('' + lineaIdMapa).getMap();
    pathsTemp = [];
    mapa.addListener('click', agregarLatLngLinea);
    mapa.addListener('dblclick', agregarLatLngLinea);
    lineaSelect = new google.maps.Polyline({
        paths: pathsTemp,
        strokeColor: '#000000',
        strokeOpacity: 1.0,
        strokeWeight: 3
    });
    lineaSelect.setEditable(true);
    lineaSelect.setDraggable(true);
    lineaSelect.setMap(mapa);
}

function  cancelarTrazoLinea() {
    if (estatus === 'EDITAR') {
        cancelarEdicionLinea();
    } else if (estatus === 'NUEVO') {
        cancelarNuevoLinea();
    }
//    estatus = 'NINGUNA';
}

function cancelarEdicionLinea() {
    if (lineaSelect !== null) {
        try {
            var mapa = PF('' + lineaIdMapa).getMap();
            mapa.polylines[indiceLinea].setDraggable(true);
            mapa.polylines[indiceLinea].setEditable(true);
            mapa.polylines[indiceLinea].setPath(pathsTemp);
            lineaSelect = mapa.polylines[indiceLinea];
            pathsTemp = lineaSelect.getPath().getArray().slice();
        } catch (exception) {
            console.log('Error inhabilitarEdicionLinea:' + exception);
        }
    }
}

function cancelarNuevoLinea() {
    if (lineaSelect !== null) {
        try {
            lineaSelect.setDraggable(false);
            lineaSelect.setEditable(false);
            lineaSelect.setMap(null);
            lineaSelect = null;
            pathsTemp = null;
        } catch (exception) {
            console.log('Error  ' + exception);
        }
    }
}

function guardarLinea(idInput) {
    console.log("guardarLinea Util:" + idInput);
    if (lineaSelect !== null && validarLinea(lineaSelect) === false) {
        alert('El Linea debe contener minimo 3 vertices');
    } else {
        console.log('poligonoSeleccionado.getPath().getArray():' + lineaSelect.getPath().getArray());
        document.getElementById(idInput).value = JSON.stringify(lineaSelect.getPath().getArray());
        guardarTodo();
    }
}

function agregarLatLngLinea(event) {
    try {
        if (lineaSelect !== null) {
            pathsTemp.push(event.latLng);
            if (lineaSelect.getPath() === undefined) {
                lineaSelect.setPath(pathsTemp);
            } else {
                var pathPologono = lineaSelect.getPath().getArray();
                pathPologono.push(event.latLng);
                lineaSelect.setPath(pathPologono);
                lineaSelect.setEditable(true);
            }
        }
    } catch (error) {
        console.log(error);
    }
}
