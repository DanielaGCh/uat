/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var idMapa = 'mapa';//----
var zoom = 10;//----
//var poligonoSeleccionado;
//var pathsTemp;
var idInput = 'form:coordenadasJson';

//function resaltarPoligono(mapaId, zoom, strokeWeight, lat, lng, indices) {
function ubicarMarcador(mapaId, zoom, indice) {
    var mapa = PF('' + mapaId).getMap();
    console.log('indice' + indice)
//    mapa.markers[indice].setIcon({
//        path: google.maps.SymbolPath.CIRCLE,
//        scale: 8, //tamaño
//        strokeColor: '#00f', //color del borde
//        strokeWeight: 2, //grosor del borde
//        fillColor: '#f00', //color de relleno
//        fillOpacity: 1// opacidad del relleno
//    });
    mapa.setCenter(mapa.markers[indice].getPosition());
    mapa.setZoom(zoom);
}

function ubicarPoligono(mapaId, zoom, strokeWeight, lat, lng, indice) {
    var mapa = PF('' + mapaId).getMap();
    for (index = 0; index < PF('mapa').getMap().polygons.length; index++) {
        mapa.polygons[index].set('strokeWeight', 1);
    }
    ;
    mapa.polygons[indice].set('strokeWeight', strokeWeight);
    mapa.setCenter((new google.maps.LatLng(lat, lng)));
    mapa.setZoom(zoom);
}

function ubicarPoligonos(mapaId, zoomParam, strokeWeight, lat, lng, indices) {
    var mapa = PF('' + mapaId).getMap();
    for (index = 0; index < PF('' + mapaId).getMap().polygons.length; index++) {
        mapa.polygons[index].set('strokeWeight', 1);
    }
    var indicesPoligonos = indices.split(",");
    for (var i = 0; i < indicesPoligonos.length; i++) {
        console.log('index:' + i)
        mapa.polygons[indicesPoligonos[i]].set('strokeWeight', strokeWeight);
    }
    mapa.setCenter((new google.maps.LatLng(lat, lng)));
    try {
        mapa.setZoom(zoomParam);
    } catch (e) {
        console.log('erro:' + e);
        try {
            mapa.setZoom(17);
        } catch (err) {
            console.log('error:' + err);
        }
    }
}

function ubicarPolilineas(mapaId, zoomParam, strokeWeight, lat, lng, indices) {
    var mapa = PF('' + mapaId).getMap();
    for (index = 0; index < PF('' + mapaId).getMap().polylines.length; index++) {
        mapa.polylines[index].set('strokeWeight', 1);
    }
    var indicesPolilineas = indices.split(",");
    for (var i = 0; i < indicesPolilineas.length; i++) {
        console.log('index:' + i)
        mapa.polylines[indicesPolilineas[i]].set('strokeWeight', strokeWeight);
    }
    mapa.setCenter((new google.maps.LatLng(lat, lng)));
    try {
        mapa.setZoom(zoomParam);
    } catch (e) {
        console.log('erro:' + e);
        try {
            mapa.setZoom(17);
        } catch (err) {
            console.log('error:' + err);
        }
    }
}

function ubicarPolilinea(mapaId, zoom, strokeWeight, lat, lng, indice) {
    var mapa = PF('' + mapaId).getMap();
    for (index = 0; index < PF('mapa').getMap().polylines.length; index++) {
        mapa.polylines[index].set('strokeWeight', 1);
    }

    mapa.polylines[indice].set('strokeWeight', strokeWeight);
    mapa.setCenter((new google.maps.LatLng(lat, lng)));
    try {
        mapa.setZoom(zoom);
    } catch (e) {
        console.log('ubicarPolilinea erro:' + e);
        try {
            mapa.setZoom(10);
        } catch (err) {
            console.log('ubicarPolilinea error:' + err);
        }
    }
}

function limpiarCentrarMapa(mapaIdParam, zoomParam, lat, lng) {
    var mapa = PF('' + mapaIdParam).getMap();

    if (PF('mapa').getMap().polygons !== undefined) {
        for (index = 0; index < PF('mapa').getMap().polygons.length; index++) {
            mapa.polygons[index].set('strokeWeight', 1);
        }
    }

    if (PF('mapa').getMap().markers !== undefined) {
        for (index = 0; index < PF('mapa').getMap().markers.length; index++) {
            mapa.markers[index].set('icon', null);
        }
    }

    mapa.setCenter((new google.maps.LatLng(lat, lng)));
    try {
        mapa.setZoom(zoomParam);
    } catch (e) {
        mapa.setZoom(10);
    }
}

function centrarMapa(mapaId, lat, lng, zoom) {
    var mapa = PF('' + mapaId).getMap();
    mapa.setCenter((new google.maps.LatLng(lat, lng)));
    mapa.setZoom(zoom);
}

//function centrarMapa(mapa, lat, lng, zoom) {
//    console.log("centrarMapa" + mapaId);
//    mapa.setCenter((new google.maps.LatLng(lat, lng)));
//    mapa.setZoom(zoom);
//}

function habilitarEdicionPoligono(mapaId, draggabe, zoom, lat, lng, index) {
    var mapa = PF('' + mapaId).getMap();
    try {
        mapa.setCenter((new google.maps.LatLng(lat, lng)));
        mapa.setZoom(zoom);
        mapa.polygons[index].setDraggable(draggabe);
        mapa.polygons[index].setEditable(true);
//        PF('' + mapaId).getMap().polygons[index].setDraggable(true);
//        mapa.polygons[index].setDraggable(true);
//        mapa.polygons[index].set('draggable', true);
//        mapa.polygons[index].draggable = true;
//                        addDeleteButton(PF('mapa').getMap().polygons[index], 'http://i.imgur.com/RUrKV.png');
    } catch (exception) {
        console.log('Error habilitarEdicionPoligono:' + exception);
    }
}

function deshabilitarEdicionPoligono(mapaId, index) {
    var mapa = PF('' + mapaId).getMap();
    try {
        mapa.polygons[index].setDraggable(false);
        mapa.polygons[index].setEditable(false);
    } catch (exception) {
        console.log('Error inhabilitarEdicionPoligono:' + exception);
    }
}


function  validarPoligono(poligono) {
    try {
        if (poligono.getPath() !== undefined && poligono.getPath() !== null && poligono.getPath().getArray() !== undefined) {
            if (poligono.getPath().getArray().length > 0 && poligono.getPath().getArray().length < 3) {
                console.log('Error:' + 'primera opcion');
                return false;
//                alert('El Poligono debe contener minimo 3 vertices');
            } else if (poligono.getPath().getArray().length > 2) {
                console.log('Error:' + 'segunda opcion true');
                return true;
//                alert('El Poligono debe contener minimo 3 vertices');
            } else {
                console.log('Error:' + 'tercera opcion');
                return false;
            }
        } else {
            // se deja como valido para, pero no se consideraran al momento de guardar
            console.log('Error:' + 'cuarta opcion');
            return  false;
        }
    } catch (error) {
        console.log('Error:' + error);
        return false;
    }
}

function  validarPolilinea(polilinea) {
    try {
        if (polilinea.getPath() !== undefined && polilinea.getPath() !== null && polilinea.getPath().getArray() !== undefined) {
            if (polilinea.getPath().getArray().length > 0 && polilinea.getPath().getArray().length < 2) {
                return false;
            } else if (polilinea.getPath().getArray().length > 1) {
                return true;
            } else {
                return false;
            }
        } else {
            // se deja como valido para, pero no se consideraran al momento de guardar
            return  false;
        }
    } catch (error) {
        console.log('Error:' + error);
        return false;
    }
}

function cambiarColorPoligonos(idMapa, color) {
    var mapa = PF('' + idMapa).getMap();
    mapa.polygons.forEach(function (polygon) {
        polygon.set('strokeColor', color);
        polygon.set('fillColor', color);
//        polygon.set('strokeColor', '#FF0000');
//        polygon.set('fillColor', '#FF0000');
    });
}


function limpiarCapasMapa(mapa) {
    var bandera = true;
    while (bandera) {
        if (mapa.overlayMapTypes.length !== 0) {
            mapa.overlayMapTypes.pop();
        }
        bandera = mapa.overlayMapTypes.length !== 0;
    }
}

//function  validarLinea(linea) {
//    try {
//        if (linea.getPath() !== undefined && linea.getPath() !== null && linea.getPath().getArray() !== undefined) {
//            if (linea.getPath().getArray().length > 1) {
//                return true;
////                alert('El Poligono debe contener minimo 3 vertices');
//            } else {
//                return false;
//            }
//        } else {
//            return  false;
//        }
//    } catch (error) {
//        return false;
//    }
//}
//function  validarPoligono(poligono) {
//    try {
//        if (poligono.getPath().getArray() !== null && poligono.getPath().getArray().length > 2) {
//            return true;
////                alert('El Poligono debe contener minimo 3 vertices');
//        } else {
//            return false;
//        }
//    } catch (error) {
//        console.log('Error:' + error);
//        return false;
//    }
//}

