/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var poligogoTO;
var poligonoSeleccionado;
var arrayPoligonosSeleccionados;
var pathsTemp;
var arrayPathsTemp;
var pathsTemp;
var arrayIndicesPoligonos;
var indicesPoligonos;
var estatus = null;
var mapPoligonos;
var mpMapaId;
var zoom = 10;

function editar(indices, idMapa, zoomParam) {
    indicesPoligonos = indices;
    mpMapaId = idMapa;
    arrayIndicesPoligonos = indices.split(",");
    var mapa = PF('' + mpMapaId).getMap();
    arrayPoligonosSeleccionados = [];

    if (arrayIndicesPoligonos.length > 0) {
        poligonoSeleccionado = mapa.polygons[arrayIndicesPoligonos[0]];
        pathsTemp = poligonoSeleccionado.getPath().getArray().slice();
        ubicarPoligonos(mpMapaId, zoomParam, 5, pathsTemp[0].lat(), pathsTemp[0].lng(), indicesPoligonos);
    }
    arrayPathsTemp = [];
    poligonoSeleccionado = null;
    pathsTemp = null;
}

function nuevo(mapaId) {
    mpMapaId = mapaId;
    pathsTemp = null;
    estatus = 'NINGUNA';
}

function agregarNuevoPoligono(mapaIdParam) {
//function agregarNuevoPoligono() {
    nuevo(mapaIdParam);
    estatus = 'NUEVO';
    var mapa = PF('' + mpMapaId).getMap();
    pathsTemp = [];
    mapa.addListener('click', agregarLatLngPoligono);
    mapa.addListener('dblclick', agregarLatLngPoligono);
    poligonoSeleccionado = new google.maps.Polygon({
        paths: pathsTemp,
        strokeColor: '#000000',
        strokeOpacity: 1.0,
        strokeWeight: 3
    });
    poligonoSeleccionado.setEditable(true);
    poligonoSeleccionado.setDraggable(true);
    poligonoSeleccionado.setMap(mapa);
}

function mostrarVertices() {
    estatus = 'EDITAR';
    var mapa = PF('' + mpMapaId).getMap();
//    poligonoSeleccionado = mapa.polygons[indicePoligono];
//    pathsTemp = poligonoSeleccionado.getPath().getArray().slice();

    arrayPoligonosSeleccionados = [];
    for (var idx in arrayIndicesPoligonos) {
        mapa.polygons[arrayIndicesPoligonos[idx]].setDraggable(true);
        mapa.polygons[arrayIndicesPoligonos[idx]].setEditable(true);
        poligogoTO = new Object();
        poligonoSeleccionado = mapa.polygons[arrayIndicesPoligonos[idx]];
        poligogoTO.poligono = poligonoSeleccionado;
        poligogoTO.pathsTemp = poligonoSeleccionado.getPath().getArray().slice();
        poligogoTO.indice = arrayIndicesPoligonos[idx];
        arrayPoligonosSeleccionados.push(poligogoTO);
    }

    if (arrayIndicesPoligonos.length > 0) {
        estatus = 'EDITAR';
        try {
            mapa.setCenter((new google.maps.LatLng(arrayPoligonosSeleccionados[0].pathsTemp[0].lat(), arrayPoligonosSeleccionados[0].pathsTemp[0].lng())));
            mapa.setZoom(zoom);
        } catch (exception) {
            console.log('Error :' + exception);
        }
    } else {
        alert('No existe cartografía del predio por favor trazela en el mapa');
        estatus = 'NINGUNA';
    }
}
//function mostrarVertices() {
//    console.log("multiPoligonoUtil mostrarVertices:" + mpMapaId);
//    estatus = 'EDITAR';
//    var mapa = PF('' + mpMapaId).getMap();
//    poligonoSeleccionado = mapa.polygons[indicePoligono];
//    pathsTemp = poligonoSeleccionado.getPath().getArray().slice();
//    if (indicePoligono >= 0) {
//        estatus = 'EDITAR';
//        poligonoSeleccionado = mapa.polygons[indicePoligono];
//        pathsTemp = poligonoSeleccionado.getPath().getArray().slice();
//        try {
//            mapa.setCenter((new google.maps.LatLng(pathsTemp[0].lat(), pathsTemp[0].lng())));
//            mapa.setZoom(zoom);
//            mapa.polygons[indicePoligono].setDraggable(true);
//            mapa.polygons[indicePoligono].setEditable(true);
//        } catch (exception) {
//            console.log('Error habilitarEdicionPoligono:' + exception);
//        }
//    } else {
//        alert('No existe cartografía del predio por favor trazela en el mapa');
//        estatus = 'NINGUNA';
//    }
//}

function editarPoligono() {
    var mapa = PF('' + mpMapaId).getMap();
    try {
        for (var i = 0; i < indicesPoligonos.length; i++) {
            poligonoSeleccionado = mapa.polygons[i];
            arrayPoligonosSeleccionados.push(poligonoSeleccionado);
            arrayPoligonosSeleccionados.push(poligonoSeleccionado.getPath().getArray().slice());
            mapa.polygons[i].setDraggable(true);
            mapa.polygons[i].setEditable(true);
        }
        mapa.setCenter((new google.maps.LatLng(
                poligonoSeleccionado.getPath().getArray().slice()[0].lat(),
                poligonoSeleccionado.getPath().getArray().slice()[0].lng())));
        mapa.setZoom(zoom);
//        mapa.polygons[index].setDraggable(true);
//        mapa.polygons[index].setEditable(true);
    } catch (exception) {
        console.log('Error :' + exception);
    }
}

function editarPoligono(indices, idMapa) {
    editar(indices, idMapa);
    var mapa = PF('' + mpMapaId).getMap();
//    poligonoSeleccionado = mapa.polygons[index];
//    pathsTemp = poligonoSeleccionado.getPath().getArray().slice();
    try {
        for (var i = 0; i < indicesPoligonos.length; i++) {
            poligonoSeleccionado = mapa.polygons[i];
            arrayPoligonosSeleccionados.push(poligonoSeleccionado);
            arrayPoligonosSeleccionados.push(poligonoSeleccionado.getPath().getArray().slice());
            mapa.polygons[i].setDraggable(true);
            mapa.polygons[i].setEditable(true);
        }
        mapa.setCenter((new google.maps.LatLng(poligonoSeleccionado.getPath().getArray().slice()[0].lat(), poligonoSeleccionado.getPath().getArray().slice()[0].lng())));
        mapa.setZoom(zoom);
//        mapa.polygons[index].setDraggable(true);
//        mapa.polygons[index].setEditable(true);
    } catch (exception) {
        console.log('Error:' + exception);
    }
}

function agregarPoligono() {
    var mapa = PF('' + idMapa).getMap();

    pathsTemp = [];
    mapa.addListener('click', agregarLatLngPoligono);
    poligonoSeleccionado = new google.maps.Polygon({
        paths: pathsTemp,
        strokeColor: '#000000',
        strokeOpacity: 1.0,
        strokeWeight: 3
    });
    poligonoSeleccionado.setEditable(true);
    poligonoSeleccionado.setDraggable(true);
    poligonoSeleccionado.setMap(mapa);
}

function cancelarTrazoPoligono() {
    if (estatus === 'EDITAR') {
        cancelarEdicionPoligono();
    } else if (estatus === 'NUEVO') {
        cancelarNuevoPoligono();
    }
//    estatus = 'NINGUNA';
}

function cancelarEdicionPoligono() {
    var mapa = PF('' + mpMapaId).getMap();
    for (var idx in arrayPoligonosSeleccionados) {
        mapa.polygons[arrayPoligonosSeleccionados[idx].indice].setDraggable(false);
        mapa.polygons[arrayPoligonosSeleccionados[idx].indice].setEditable(false);
        mapa.polygons[arrayPoligonosSeleccionados[idx].indice].setPath(arrayPoligonosSeleccionados[idx].pathsTemp);
    }
}

//function cancelarEdicionPoligono() {
//    console.log("cancelarEdicionPoligono");
//    for (var idx in arrayIndicesPoligonos) {
//        console.log('idx:' + idx);
//        mapa.polygons[arrayIndicesPoligonos[idx]].setDraggable(true);
//        mapa.polygons[arrayIndicesPoligonos[idx]].setEditable(true);
//        poligogoTO = new Object();
//        poligonoSeleccionado = mapa.polygons[arrayIndicesPoligonos[idx]];
//        poligogoTO.poligono = poligonoSeleccionado;
//        poligogoTO.pathsTemp = poligonoSeleccionado.getPath().getArray().slice();
//        poligogoTO.indice = idx;
//        arrayPoligonosSeleccionados.push(poligogoTO);
//    }
//}

//function cancelarEdicionPoligono() {
//    console.log("cancelarEdicionPoligono");
//    if (poligonoSeleccionado !== null) {
//        try {
//            poligonoSeleccionado.setDraggable(false);
//            poligonoSeleccionado.setEditable(false);
//            poligonoSeleccionado.setPath(pathsTemp);
//            poligonoSeleccionado = null;
//            pathsTemp = null;
//        } catch (exception) {
//            console.log('Error inhabilitarEdicionPoligono:' + exception);
//        }
//    }
//}

function cancelarNuevoPoligono() {
    if (poligonoSeleccionado !== null) {
        try {
            poligonoSeleccionado.setDraggable(false);
            poligonoSeleccionado.setEditable(false);
            poligonoSeleccionado.setMap(null);
            poligonoSeleccionado = null;
            pathsTemp = null;
        } catch (exception) {
            console.log('Error cancelarNuevoPoligono' + exception);
        }
    }
}

//function guardarPoligono(idInput) {
//    console.log("guardarPoligono" + poligonoSeleccionado);
//    if (poligonoSeleccionado !== null && validarPoligono(poligonoSeleccionado) === false) {
//        alert('El Poligono debe contener minimo 3 vertices');
//    } else {
//        document.getElementById(idInput).value = JSON.stringify(poligonoSeleccionado.getPath().getArray());
//        guardarTodo();
//    }
//}

function agregarLatLngPoligono(event) {
    try {
        if (poligonoSeleccionado !== null) {
//                            console.log('existe poligon');
            pathsTemp.push(event.latLng);
//                            console.log('lat mar:' + event.latLng);
//                            console.log('puntosRuta :' + puntosRuta);
            if (poligonoSeleccionado.getPath() === undefined) {
//                                console.log('Sin paths');
                poligonoSeleccionado.setPath(pathsTemp);
            } else {
//                                console.log('Cn paths:' + poligono.getPath());
//                                console.log('Cn paths:' + poligono.getPath().getArray());
                var pathPologono = poligonoSeleccionado.getPath().getArray();
                pathPologono.push(event.latLng);
                poligonoSeleccionado.setPath(pathPologono);
//                console.log('poligono.getPaths() :' + poligono.getPath().getArray());
                poligonoSeleccionado.setEditable(true);

            }
        }
    } catch (error) {
        console.log(error);
    }
}

function guardarMultiPoligono(idInput) {
    var mapa = PF('' + mpMapaId).getMap();
    for (var idx in arrayPoligonosSeleccionados) {
        poligonoSeleccionado = mapa.polygons[arrayPoligonosSeleccionados[idx].indice];
        if (poligonoSeleccionado !== null && validarPoligono(poligonoSeleccionado) === false) {
            alert('El Poligono debe contener minimo 3 vertices');
        }
        break;
    }

    var multipoligonos = new Array();

    for (var idx in arrayPoligonosSeleccionados) {
        poligonoSeleccionado = mapa.polygons[arrayPoligonosSeleccionados[idx].indice];
        multipoligonos.push(poligonoSeleccionado.getPath().getArray());
    }
    document.getElementById(idInput).value = JSON.stringify(multipoligonos);
    guardarTodo();
}

function display(estatusParam) {
    return estatus === estatusParam;
}
//var poligonoSeleccionado;
//var poligonosSeleccionado;
//var pathsTemp;
//var indicesPoligonos;
//var estatus = "";
//var mapPoligonos;
//
//function editar(indices) {
//    console.log("indices:" + indices);
//    indicesPoligonos = indices.split(",");
//    for (var i = 0; i < indicesPoligonos.length; i++) {
//        n += i;
//        mifuncion(n);
//    }
//    poligonoSeleccionado = null;
//    pathsTemp = null;
//    mapPoligonos = new Map();
////    mapPoligonos.put();
//}
//
//function editarPoligono(index) {
//    console.log("editarPoligono:" + index);
//    var mapa = PF('' + idMapa).getMap();
//    poligonoSeleccionado = mapa.polygons[index];
//    pathsTemp = poligonoSeleccionado.getPath().getArray().slice();
//    try {
//        mapa.setCenter((new google.maps.LatLng(pathsTemp[0].lat(), pathsTemp[0].lng())));
//        mapa.setZoom(zoom);
//        mapa.polygons[index].setDraggable(true);
//        mapa.polygons[index].setEditable(true);
//    } catch (exception) {
//        console.log('Error habilitarEdicionPoligono:' + exception);
//    }
//}
//
//function agregarPoligono() {
//    console.log("indexRegionSeleccionada");
//    var mapa = PF('' + idMapa).getMap();
//
//    pathsTemp = [];
//    mapa.addListener('click', agregarLatLngPoligono);
//    poligonoSeleccionado = new google.maps.Polygon({
//        paths: pathsTemp,
//        strokeColor: '#000000',
//        strokeOpacity: 1.0,
//        strokeWeight: 3
//    });
//    poligonoSeleccionado.setEditable(true);
//    poligonoSeleccionado.setDraggable(true);
//    poligonoSeleccionado.setMap(mapa);
//}
//
//function cancelarEdicionPoligono() {
//    console.log("cancelarEdicionPoligono");
//    if (poligonoSeleccionado !== null) {
//        try {
//            poligonoSeleccionado.setDraggable(false);
//            poligonoSeleccionado.setEditable(false);
//            poligonoSeleccionado.setPath(pathsTemp);
//            poligonoSeleccionado = null;
//            pathsTemp = null;
//        } catch (exception) {
//            console.log('Error inhabilitarEdicionPoligono:' + exception);
//        }
//    }
//}
//
//function cancelarNuevoPoligono() {
//    console.log("cancelarEdicionPoligono");
//    if (poligonoSeleccionado !== null) {
//        try {
//            poligonoSeleccionado.setDraggable(false);
//            poligonoSeleccionado.setEditable(false);
//            poligonoSeleccionado.setMap(null);
//            poligonoSeleccionado = null;
//            pathsTemp = null;
//        } catch (exception) {
//            console.log('Error cancelarNuevoPoligono' + exception);
//        }
//    }
//}
//
////function guardarPoligono(idInput) {
////    console.log("guardarPoligono" + poligonoSeleccionado);
////    if (poligonoSeleccionado !== null && validarPoligono(poligonoSeleccionado) === false) {
////        alert('El Poligono debe contener minimo 3 vertices');
////    } else {
////        document.getElementById(idInput).value = JSON.stringify(poligonoSeleccionado.getPath().getArray());
////        guardarTodo();
////    }
////}
//
//function agregarLatLngPoligono(event) {
//    console.log('agregarLatLngPoligono');
//    try {
//        if (poligonoSeleccionado !== null) {
////                            console.log('existe poligon');
//            pathsTemp.push(event.latLng);
////                            console.log('lat mar:' + event.latLng);
////                            console.log('puntosRuta :' + puntosRuta);
//            if (poligonoSeleccionado.getPath() === undefined) {
////                                console.log('Sin paths');
//                poligonoSeleccionado.setPath(pathsTemp);
//            } else {
////                                console.log('Cn paths:' + poligono.getPath());
////                                console.log('Cn paths:' + poligono.getPath().getArray());
//                var pathPologono = poligonoSeleccionado.getPath().getArray();
//                pathPologono.push(event.latLng);
//                poligonoSeleccionado.setPath(pathPologono);
////                console.log('poligono.getPaths() :' + poligono.getPath().getArray());
//                poligonoSeleccionado.setEditable(true);
//
//            }
//        }
//    } catch (error) {
//        console.log(error);
//    }
//}
