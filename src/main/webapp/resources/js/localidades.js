/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var shapeselect;
var shapeSelectTO;
var pathsTemp;
var indiceShape;
var estatus = null;
var mapaIdd;
var zoom = 10;
var shapeTO;
var listenerClickMapa;
function editar(mapaId, tipo, indice, zoomParam) {
    mapaIdd = mapaId;
    indiceShape = indice;
    var mapa = PF('' + mapaIdd).getMap();
    if (indiceShape >= 0) {
        if (tipo === 'Poligono') {
            shapeselect = mapa.polygons[indiceShape];
            pathsTemp = shapeselect.getPath().getArray().slice();
            shapeTO = new Object();
            shapeTO.shape = shapeselect;
            shapeTO.tipo = 'Poligono';
            shapeTO.indice = indice;
            shapeSelectTO = Object.assign({}, shapeTO);
            ubicarPoligono(mapaId, zoomParam, 5, pathsTemp[0].lat(), pathsTemp[0].lng(), indiceShape);
        } else if (tipo === 'Marcador') {
            shapeselect = mapa.markers[indiceShape];
            shapeTO = new Object();
            shapeTO.shape = shapeselect;
            shapeTO.tipo = 'Marcador';
            shapeTO.indice = indice;
            shapeSelectTO = Object.assign({}, shapeTO);
            //pathsTemp = shape.getPath().getArray().slice();
        }

    }
    pathsTemp = null;
    estatus = 'NINGUNA';
    zoom = zoomParam;
}

function editarPoligonoLocalidad() {
    estatus = 'EDITAR';
    var mapa = PF('' + mapaIdd).getMap();
    if (shapeSelectTO !== undefined) {
        estatus = 'EDITAR';
        if (shapeSelectTO.tipo === 'Poligono') {
            try {
// mapa.setCenter((new google.maps.LatLng(pathsTemp[0].lat(), pathsTemp[0].lng())));
//                mapa.setZoom(zoom);
//                mapa.polygons[indiceShape].setDraggable(true);
//                mapa.polygons[indiceShape].setEditable(true);
                shapeSelectTO.shape.setEditable(true);
                shapeSelectTO.shape.setDraggable(true);
                if (listenerClickMapa === undefined) {
                    if (shapeSelectTO.shape.getPath() === undefined) {
                        listenerClickMapa = mapa.addListener('click', agregarLatLngPoligono);
                    }
                } else {
                    if (shapeSelectTO.shape.getPath() !== undefined) {
                        google.maps.event.removeListener(listenerClickMapa);
                    }
                }

            } catch (exception) {
                console.log('Error:' + exception);
            }
        } else {
//shapeSelectTO.shape.setEditable(true);
            if (listenerClickMapa !== undefined) {
                google.maps.event.removeListener(listenerClickMapa);
            }
            shapeSelectTO.shape.setDraggable(false);
            shapeSelectTO.shape.setMap(null);
            agregarNuevoPoligonoLocalidad();
        }

//        shapeselect = mapa.polygons[indiceShape];
//        pathsTemp = shapeselect.getPath().getArray().slice();

    } else {
        agregarNuevoPoligonoLocalidad();
        alert('No existe cartografía del predio por favor trazela en el mapa');
        estatus = 'NINGUNA';
    }
}

function editarMarcadorLocalidad() {
    estatus = 'EDITAR';
    var mapa = PF('' + mapaIdd).getMap();
    if (shapeSelectTO !== undefined) {
        estatus = 'EDITAR';
        if (shapeSelectTO.tipo === 'Marcador') {
            try {
// mapa.setCenter((new google.maps.LatLng(pathsTemp[0].lat(), pathsTemp[0].lng())));
//                mapa.setZoom(zoom);
//                mapa.polygons[indiceShape].setDraggable(true);
//                mapa.polygons[indiceShape].setEditable(true);
//                shapeSelectTO.shape.setEditable(true);
                shapeSelectTO.shape.setDraggable(true);
                shapeSelectTO.shape.setIcon({
                    path: google.maps.SymbolPath.CIRCLE,
                    scale: 8, //tamaño
                    strokeColor: '#00f', //color del borde
                    strokeWeight: 2, //grosor del borde
                    fillColor: '#f00', //color de relleno
                    fillOpacity: 1// opacidad del relleno
                });
//                if (listenerClickMapa === undefined) {
//                    if (shapeSelectTO.shape.getPath() === undefined) {
//                        listenerClickMapa = mapa.addListener('click', agregarLatLngPoligono);
//                    }
//                } else {
//                    if (shapeSelectTO.shape.getPath() !== undefined) {
//                        google.maps.event.removeListener(listenerClickMapa);
//                    }
//                }

            } catch (exception) {
                console.log('Error:' + exception);
            }
        } else {
//shapeSelectTO.shape.setEditable(true);
            if (listenerClickMapa !== undefined) {
                google.maps.event.removeListener(listenerClickMapa);
            }
            shapeSelectTO.shape.setDraggable(false);
            shapeSelectTO.shape.setMap(null);
//            agregarNuevoPoligonoLocalidad();

            shapeselect = new google.maps.Marker({
                position: mapa.getCenter(),
                icon: {
                    path: google.maps.SymbolPath.CIRCLE,
                    scale: 8, //tamaño
                    strokeColor: '#00f', //color del borde
                    strokeWeight: 2, //grosor del borde
                    fillColor: '#f00', //color de relleno
                    fillOpacity: 1// opacidad del relleno
                }
            });
            shapeselect.setDraggable(true);
            shapeselect.setMap(mapa);
            shapeSelectTO = new Object();
            shapeSelectTO.shape = shapeselect;
            shapeSelectTO.tipo = 'Marcador';
            shapeSelectTO.indice = undefined;
            listenerClickMapa = mapa.addListener('click', modificarLatLngMarcador);
        }
    } else {
        shapeselect = new google.maps.Marker({
            position: mapa.getCenter(),
            icon: {
                path: google.maps.SymbolPath.CIRCLE,
                scale: 8, //tamaño
                strokeColor: '#00f', //color del borde
                strokeWeight: 2, //grosor del borde
                fillColor: '#f00', //color de relleno
                fillOpacity: 1// opacidad del relleno
            }
        });
        shapeselect.setDraggable(true);
        shapeselect.setMap(mapa);
        shapeSelectTO = new Object();
        shapeSelectTO.shape = shapeselect;
        shapeSelectTO.tipo = 'Marcador';
        shapeSelectTO.indice = undefined;
        listenerClickMapa = mapa.addListener('click', modificarLatLngMarcador);
        estatus = 'NINGUNA';
    }
}

function agregarNuevoPoligonoLocalidad() {
    estatus = 'NUEVO';
    var mapa = PF('' + mapaIdd).getMap();
    pathsTemp = [];
    listenerClickMapa = mapa.addListener('click', agregarLatLngPoligono);
//    mapa.addListener('dblclick', agregarLatLngPoligono);
    shapeselect = new google.maps.Polygon({
        paths: pathsTemp,
        strokeColor: '#000000',
        strokeOpacity: 1.0,
        strokeWeight: 3
    });
    shapeselect.setEditable(true);
    shapeselect.setDraggable(true);
    shapeselect.setMap(mapa);
    shapeSelectTO = new Object();
    shapeSelectTO.shape = shapeselect;
    shapeSelectTO.tipo = 'Poligono';
    shapeSelectTO.indice = undefined;
}

function agregarNuevoMarcadorLocalidad() {
    estatus = 'NUEVO';
    var mapa = PF('' + mapaIdd).getMap();
    pathsTemp = [];
    listenerClickMapa = mapa.addListener('click', agregarLatLngPoligono);
//    mapa.addListener('dblclick', agregarLatLngPoligono);
    shapeselect = new google.maps.Polygon({
        paths: pathsTemp,
        strokeColor: '#000000',
        strokeOpacity: 1.0,
        strokeWeight: 3
    });
    shapeselect.setEditable(true);
    shapeselect.setDraggable(true);
    shapeselect.setMap(mapa);
    shapeTO = new Object();
    shapeTO.shape = shapeselect;
    shapeTO.tipo = 'Poligono';
    shapeTO.indice = undefined;
}

function  cancelarTrazoPoligono() {
    if (estatus === 'EDITAR') {
        cancelarEdicionPoligono();
    } else if (estatus === 'NUEVO') {
        cancelarNuevoPoligono();
    }
}

function cancelarEdicionPoligono() {
    if (shapeselect !== null) {
        try {
            var mapa = PF('' + mapaIdd).getMap();
            mapa.polygons[indiceShape].setDraggable(true);
            mapa.polygons[indiceShape].setEditable(true);
            mapa.polygons[indiceShape].setPath(pathsTemp);
            shapeselect = mapa.polygons[indiceShape];
            pathsTemp = shapeselect.getPath().getArray().slice();
        } catch (exception) {
            console.log('Error:' + exception);
        }
    }
}

function cancelarNuevoPoligono() {
    if (shapeselect !== null) {
        try {
            shapeselect.setDraggable(false);
            shapeselect.setEditable(false);
            shapeselect.setMap(null);
            shapeselect = null;
            pathsTemp = null;
        } catch (exception) {
            console.log('Error' + exception);
        }
    }
}

function guardarLocalidad(idInput) {
    if (shapeSelectTO === undefined || shapeSelectTO === null) {
        guardarSinCartografia();
    } else {
        if (shapeSelectTO.tipo === 'Poligono') {
            if (shapeSelectTO.shape === null || shapeSelectTO.shape === undefined || validarPoligono(shapeSelectTO.shape) === false) {
                alert('El Poligono debe contener minimo 3 vertices');
            } else {
                document.getElementById(idInput).value = JSON.stringify(shapeSelectTO.shape.getPath().getArray());
                guardarLocalidadUrbana();
            }
        } else if (shapeSelectTO.tipo === 'Marcador') {
            document.getElementById(idInput).value = JSON.stringify(shapeselect.getPosition());
            guardarLocalidadRural();
        }
    }
}

function agregarLatLngPoligono(event) {
    try {
        if (shapeselect !== null) {
            pathsTemp.push(event.latLng);
            if (shapeselect.getPath() === undefined) {
                shapeselect.setPath(pathsTemp);
            } else {
                var pathPologono = shapeselect.getPath().getArray();
                pathPologono.push(event.latLng);
                shapeselect.setPath(pathPologono);
                shapeselect.setEditable(true);
            }
        }
    } catch (error) {
        console.log(error);
    }
}

function modificarLatLngMarcador(event) {
    try {
        shapeselect.setPosition(event.latLng);
    } catch (error) {
        console.log(error);
    }
}

function ubicarLocalidadRural(mapaId, zoom, indice) {
    ubicarMarcador(mapaId, zoom, indice);
    var mapa = PF('' + mapaId).getMap();
    for (index = 0; index < PF('mapa').getMap().markers.length; index++) {
        mapa.markers[index].set('http://drive.google.com/uc?export=view&id=1yoC-OpkzsPQoFsKifKBZIFin0mR4XUOc', null);
    }
}
