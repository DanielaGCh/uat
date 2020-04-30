/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var poligonoSeleccionado;
var pathsTemp;
var indicePoligono;
var estatus = null;
var mapaIdd;
var zoom = 10;

function editar(indice, mapaId, zoomParam) {
    mapaIdd = mapaId;
    indicePoligono = indice;
    var mapa = PF('' + mapaIdd).getMap();
    if (indicePoligono >= 0) {
        poligonoSeleccionado = mapa.polygons[indicePoligono];
        pathsTemp = poligonoSeleccionado.getPath().getArray().slice();
        ubicarPoligonos(mapaId, zoomParam, 5, pathsTemp[0].lat(), pathsTemp[0].lng(), indicePoligono);
    }else{
        poligonoSeleccionado = undefined;
    }
    pathsTemp = null;
    estatus = 'NINGUNA';
    zoom = zoomParam;
}

function nuevo(mapaIdParam) {
    mapaIdd = mapaIdParam;
    pathsTemp = null;
    estatus = 'NINGUNA';
}


function editarPoligono() {
    if (poligonoSeleccionado === undefined || poligonoSeleccionado === null) {
        agregarNuevoPoligono(mapaIdd);
    } else {
        mostrarVertices();
    }
}

function mostrarVertices() {
    estatus = 'EDITAR';
    var mapa = PF('' + mapaIdd).getMap();
    if (indicePoligono >= 0) {
        estatus = 'EDITAR';
        poligonoSeleccionado = mapa.polygons[indicePoligono];
        pathsTemp = poligonoSeleccionado.getPath().getArray().slice();
        try {
            mapa.setCenter((new google.maps.LatLng(pathsTemp[0].lat(), pathsTemp[0].lng())));
            mapa.setZoom(zoom);
            mapa.polygons[indicePoligono].setDraggable(true);
            mapa.polygons[indicePoligono].setEditable(true);
        } catch (exception) {
            console.log('Error:' + exception);
        }
    } else {
        agregarNuevoPoligono(mapaIdd);
        alert('No existe cartografía del predio por favor trazela en el mapa');
        estatus = 'NINGUNA';
    }
}

//function editarPoligono(indice, mapaId, zoomParam) {
//    editar(indice, mapaId, zoomParam);
//    zoom = zoomParam;
//    mostrarVertices();
//}

function agregarNuevoPoligono(mapaIdParam) {
    nuevo(mapaIdParam);
    estatus = 'NUEVO';
    var mapa = PF('' + mapaIdd).getMap();
    pathsTemp = [];
    mapa.addListener('click', agregarLatLngPoligono);
//    mapa.addListener('dblclick', agregarLatLngPoligono);
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

function  cancelarTrazoPoligono() {
    if (estatus === 'EDITAR') {
        cancelarEdicionPoligono();
    } else if (estatus === 'NUEVO') {
        cancelarNuevoPoligono();
    }
}

function cancelarEdicionPoligono() {
    if (poligonoSeleccionado !== null) {
        try {
            var mapa = PF('' + mapaIdd).getMap();
            mapa.polygons[indicePoligono].setDraggable(true);
            mapa.polygons[indicePoligono].setEditable(true);
            mapa.polygons[indicePoligono].setPath(pathsTemp);
            poligonoSeleccionado = mapa.polygons[indicePoligono];
            pathsTemp = poligonoSeleccionado.getPath().getArray().slice();
        } catch (exception) {
            console.log('Error:' + exception);
        }
    }
}

function cancelarNuevoPoligono() {
    if (poligonoSeleccionado !== null) {
        try {
            poligonoSeleccionado.setDraggable(false);
            poligonoSeleccionado.setEditable(false);
            poligonoSeleccionado.setMap(null);
            poligonoSeleccionado = null;
            pathsTemp = null;
        } catch (exception) {
            console.log('Error' + exception);
        }
    }
}

function guardarPoligono(idInput) {
    if (poligonoSeleccionado !== null && poligonoSeleccionado !== undefined) {
        if (poligonoSeleccionado.getPath() !== null && poligonoSeleccionado.getPath() !== undefined) {
            if (validarPoligono(poligonoSeleccionado) === false) {
                alert('El Poligono debe contener minimo 3 vertices');
            } else {
                document.getElementById(idInput).value = JSON.stringify(poligonoSeleccionado.getPath().getArray());
                guardarTodo();
            }
        } else {
            guardarTodo();
        }
    } else {
        guardarTodo();
        
    }
}

function agregarLatLngPoligono(event) {
    try {
        if (poligonoSeleccionado !== null) {
            pathsTemp.push(event.latLng);
            if (poligonoSeleccionado.getPath() === undefined) {
                poligonoSeleccionado.setPath(pathsTemp);
            } else {
                var pathPologono = poligonoSeleccionado.getPath().getArray();
                pathPologono.push(event.latLng);
                poligonoSeleccionado.setPath(pathPologono);
                poligonoSeleccionado.setEditable(true);
            }
        }
    } catch (error) {
        console.log(error);
    }
}

// .....................................
//var map;
//
//
//
////Define custom WMS tiled layer
//
//
//
//
//function initialize() {
//    console.log('initialize');
//    var josefov = new google.maps.LatLng(19.311794, -98.241169);
////Define OSM as base layer in addition to the default Google layers
//    var osmMapType = new google.maps.ImageMapType({
//        getTileUrl: function (coord, zoom) {
//            return "http://tile.openstreetmap.org/" +
//                    zoom + "/" + coord.x + "/" + coord.y + ".png";
//        },
//        tileSize: new google.maps.Size(256, 256),
//        isPng: true,
//        alt: "OpenStreetMap",
//        name: "OSM",
//        maxZoom: 19
//    });
//
//    var SLPLayer = new google.maps.ImageMapType({
//        getTileUrl: function (coord, zoom) {
//            var proj = map.getProjection();
//            var zfactor = Math.pow(2, zoom);
//            // get Long Lat coordinates
//            var top = proj.fromPointToLatLng(new google.maps.Point(coord.x * 256 / zfactor, coord.y * 256 / zfactor));
//            var bot = proj.fromPointToLatLng(new google.maps.Point((coord.x + 1) * 256 / zfactor, (coord.y + 1) * 256 / zfactor));
//
//            //corrections for the slight shift of the SLP (mapserver)
//            var deltaX = 0.0013;
//            var deltaY = 0.00058;
//
//            //create the Bounding box string
//            var bbox = (top.lng() + deltaX) + "," +
//                    (bot.lat() + deltaY) + "," +
//                    (bot.lng() + deltaX) + "," +
//                    (top.lat() + deltaY);
//
//            //base WMS URL
//            console.log('bbox:' + bbox);
//            var url = "http://localhost:1024/geoserver/cite/wms?";
//            url += "&REQUEST=GetMap"; //WMS operation
//            url += "&SERVICE=WMS";    //WMS service
//            url += "&VERSION=1.1.0";  //WMS version  
//            url += "&LAYERS=" + "cite%3Ac_municipio"; //WMS layers
//            url += "&FORMAT=image/png"; //WMS format
//            url += "&BGCOLOR=0xFFFFFF";
//            url += "&TRANSPARENT=TRUE";
//            url += "&SRS=EPSG:4326";     //set WGS84 
//            url += "&BBOX=" + bbox;      // set bounding box
//            url += "&WIDTH=256";         //tile size in google
//            url += "&HEIGHT=256";
//            console.log('url:' + url);
//            return url;                 // return URL for the tile
//            /*
//             var url = "http://mapserver-slp.mendelu.cz/cgi-bin/mapserv?map=/var/local/slp/krtinyWMS.map&";
//             url += "&REQUEST=GetMap"; //WMS operation
//             url += "&SERVICE=WMS";    //WMS service
//             url += "&VERSION=1.1.1";  //WMS version  
//             url += "&LAYERS=" + "typologie,hm2003"; //WMS layers
//             url += "&FORMAT=image/png" ; //WMS format
//             url += "&BGCOLOR=0xFFFFFF";  
//             url += "&TRANSPARENT=TRUE";
//             url += "&SRS=EPSG:4326";     //set WGS84 
//             url += "&BBOX=" + bbox;      // set bounding box
//             url += "&WIDTH=256";         //tile size in google
//             url += "&HEIGHT=256";
//             console.log('url:' + url);
//             return url;                 // return URL for the tile
//             */
//
//        },
//        tileSize: new google.maps.Size(256, 256),
//        isPng: true
//    });
//
//
//    var mapOptions = {
//        zoom: 10,
//        center: josefov,
//        mapTypeId: 'OSM',
//        mapTypeControlOptions: {
//            mapTypeIds: ['OSM', google.maps.MapTypeId.ROADMAP, google.maps.MapTypeId.SATELLITE, google.maps.MapTypeId.HYBRID, google.maps.MapTypeId.TERRAIN],
//            style: google.maps.MapTypeControlStyle.DROPDOWN_MENU
//        }
//    };
//
//    map = PF('' + 'mapa').getMap();
////    map = new google.maps.Map(document.getElementById("mapa"), mapOptions);
//    map.setOptions(mapOptions);
//    map.mapTypes.set('OSM', osmMapType);
//    map.setMapTypeId(google.maps.MapTypeId.HYBRID);
//    //add WMS layer
//    map.overlayMapTypes.push(SLPLayer);
//
//}
 