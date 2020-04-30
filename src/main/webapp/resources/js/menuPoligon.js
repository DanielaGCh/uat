/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var AccionMenuPoligonEnum = {
    NUEVO_POLIGONO: 1,
    NUEVO_HOLE: 2,
    NINGUNA: 3,
};

/**
 * A menu that lets a user delete a selected vertex of a path.
 * @constructor
 */

DeleteMenu.prototype = new google.maps.OverlayView();

function DeleteMenu() {
    console.log('DeleteMenu');
    this.div_delete_vertex = document.createElement('div');
    this.div_delete_vertex.className = 'poligon-menu';
    this.div_delete_vertex.id = 'deleteVertex';
    this.div_delete_vertex.innerHTML = 'Delete';

    this.div_add_hole = document.createElement('div');
    this.div_add_hole.className = 'poligon-menu';
    this.div_add_hole.id = 'addHole';
    this.div_add_hole.innerHTML = 'Agregar hueco';

    this.div_delete_shape = document.createElement('div');
    this.div_delete_shape.className = 'poligon-menu';
    this.div_delete_shape.id = 'deleteShape';
    this.div_delete_shape.innerHTML = 'Eliminar shape';

    var menu = this;

    google.maps.event.addDomListener(this.div_delete_vertex, 'click', function () {
        menu.removeVertex();
    });
    google.maps.event.addDomListener(this.div_add_hole, 'click', function () {
        menu.addHole();
    });
    google.maps.event.addDomListener(this.div_delete_shape, 'click', function () {
        menu.removeShape();
    });
}


DeleteMenu.prototype.onAdd = function () {
    var deleteMenu = this;
    var map = this.getMap();

    if (this.get('vertex') === undefined) {
    } else {

        this.set('pathIdx', undefined);

        if (this.get('shape') instanceof google.maps.Polygon) {
            if (this.get('shape').getPaths() !== undefined) {
                for (var i in this.get('shape').getPaths().getArray()) {
                    if (this.get('shape').getPaths().getArray()[i].getArray().includes(this.get('position'))) {
                        this.set('pathIdx', i);
                        break;
                    }
                }
            }
        } else {
            console.log('Es linea');
        }

        this.div_delete_vertex.innerHTML = 'Eliminar vertice';
        this.div_add_hole.innerHTML = 'Agregar hueco';
        this.div_delete_shape.innerHTML = (this.get('pathIdx') < 1 ? 'Elimiar poligono' : 'Eliminar hueco');

        if ((this.get('shape') instanceof google.maps.Polygon) && this.get('pathIdx') !== undefined && this.get('pathIdx') == 0) {
            this.getPanes().floatPane.appendChild(this.div_add_hole);
        }
        if (this.listener_clic_mapa) {
            google.maps.event.removeListener(this.listener_clic_mapa);
        }

        this.getPanes().floatPane.appendChild(this.div_delete_vertex);
        this.getPanes().floatPane.appendChild(this.div_delete_shape);
    }
    // mousedown anywhere on the map except on the menu div will close the
    this.divListener_ = google.maps.event.addDomListener(map.getDiv(), 'mousedown', function (e) {
        if ((e.target !== deleteMenu.div_delete_vertex) && (e.target !== deleteMenu.div_add_hole) && (e.target !== deleteMenu.div_delete_shape)) {
            deleteMenu.close();
        }
    }, true);
};

DeleteMenu.prototype.onRemove = function () {
    console.log('onRemove');
    if (this.get('estatus') === AccionMenuPoligonEnum.NUEVO_HOLE) {
        google.maps.event.removeListener(this.listener_clic_mapa);
        var path = this.get('newPath');
        path.clic = 0;
        var deleteMenu = this;
        var mapa = this.get('mapa');
        this.listener_clic_mapa = google.maps.event.addListener(mapa, 'click', function (e) {
            if (path.clic !== 0) {
                path.path.push(e.latLng);
            }
            path.clic = path.clic + 1;
        });
        this.listener_right_clic_mapa = google.maps.event.addListener(mapa, 'rightclick', function (e) {
            deleteMenu.endAddHole();
        });
    }

    google.maps.event.removeListener(this.divListener_);

    if (this.div_add_hole.parentNode) {
        this.div_add_hole.parentNode.removeChild(this.div_add_hole);
    }
    if (this.div_delete_shape.parentNode) {
        this.div_delete_shape.parentNode.removeChild(this.div_delete_shape);
    }
    if (this.div_delete_vertex.parentNode) {
        this.div_delete_vertex.parentNode.removeChild(this.div_delete_vertex);
    }
    this.set('position');
    this.set('path');
    this.set('vertex');
};

DeleteMenu.prototype.close = function () {
    console.log('close');
    this.setMap(null);
};

DeleteMenu.prototype.draw = function () {
    console.log('draw');
    var position = this.get('position');
    var projection = this.getProjection();

    if (!position || !projection) {
        return;
    }

    var point = projection.fromLatLngToDivPixel(position);

    this.div_delete_vertex.style.top = point.y + 'px';
    this.div_delete_vertex.style.left = point.x + 'px';
    this.div_add_hole.style.top = (point.y) + 'px';
    this.div_add_hole.style.left = (point.x) + 'px';
    this.div_delete_shape.style.top = (point.y) + 'px';
    this.div_delete_shape.style.left = (point.x) + 'px';
};


/**
 * Opens the menu at a vertex of a given path.
 */
DeleteMenu.prototype.open = function (map, poligon, vertex, latLng, funct) {
//DeleteMenu.prototype.open = function (map, poligon, vertex, latLng, calculos) {
    console.log('open');
    this.set('position', latLng);

    if (vertex === undefined) {
    } else {
        this.set('vertex', vertex);
    }
//    deseleccionarPoligonos();
    funct();
    poligon.set('zindex', 300);
    poligon.set('clickable', true);
    poligon.set('draggable', true);
    poligon.set('strokeColor', '#1a53ff');
    poligon.set('fillColor', '#1a53ff');

    this.set('shape', poligon);
//    this.set('path', poligon.getPath());
//    this.set('paths', poligon.getPaths());

    if (poligon instanceof google.maps.Polygon) {
        var paths = this.get('paths');
    }
    //calculos.innerHTML = google.maps.geometry.spherical.computeArea(paths.getArray()[0]);

    this.set('mapa', map);
//    this.set('idMapa', idMapa);
    this.setMap(map);
    this.draw();
};

/**
 * Elimina vertice del path.
 */
DeleteMenu.prototype.removeVertex = function () {
    console.log('removeVertex');
    var vertex = this.get('vertex');
    var path;

    if (this.get('shape') instanceof google.maps.Polygon) {
        path = this.get('shape').getPaths();
    } else {
        path = this.get('shape').getPath();
    }

    if (!path || vertex === undefined) {
        this.close();
        return;
    }

    if (this.get('shape') instanceof google.maps.Polygon) {
        if (this.get('pathIdx') !== undefined) {
            this.get('shape').getPaths().getArray()[this.get('pathIdx')].removeAt(vertex);
        }
    } else {
        console.log('eliminar vertex linea');
        this.get('shape').getPath().removeAt(vertex);
    }
    this.close();
};

/*
 Eliminar poligono/hueco
 */
DeleteMenu.prototype.removeShape = function () {
    console.log('removeShape');
    if (this.get('shape') instanceof google.maps.Polygon) {
        if (this.get('pathIdx') !== undefined) {
            if (this.get('pathIdx') < 1) {
                this.get('shape').setPaths([]);
            } else {
                console.log('eliminar hueco:' + this.get('pathIdx'));
                this.get('shape').getPaths().getArray()[this.get('pathIdx')].clear();
            }
        }
    } else {
        this.get('shape').getPath().clear();
    }
    this.close();
};

/*
 Agrega un hueco al poligono
 */
DeleteMenu.prototype.addPoligono = function (poligono, mapa, idMapa) {
    console.log('addPoligono');
    this.set('shape', poligono);
//    this.set('paths', poligono.getPaths());
    this.set('mapa', mapa);
    this.set('idMapa', idMapa);

    var paths;
    var newPat = new google.maps.MVCArray([]);
    if (this.get('shape') instanceof google.maps.Polygon) {
        paths = this.get('shape').getPaths();
        paths.getArray().push(newPat);
    } else if (this.get('shape') instanceof google.maps.Polyline) {
        this.get('shape').setPath(newPat);
    }

    var shapeT = this.get('shape');
    shapeT.set('clickable', false);

//    var paths = this.get('shape').getPaths();
//    var newPat = new google.maps.MVCArray([]);
//    paths.getArray().push(newPat);
//    var shapeT = this.get('shape');
//    shapeT.set('clickable', false);

    if (this.listener_clic_mapa) {
        google.maps.event.removeListener(this.listener_clic_mapa);
    }

    if (this.listener_right_clic_mapa) {
        google.maps.event.removeListener(this.listener_right_clic_mapa);
    }

    this.set('estatus', AccionMenuPoligonEnum.NUEVO_POLIGONO);
    var newPatTo = {path: newPat, clic: 1};
    this.set('newPath', newPatTo);

    this.listener_clic_mapa = google.maps.event.addListener(mapa, 'click', function (e) {
        if (newPatTo.clic !== 0) {
            newPatTo.path.push(e.latLng);
        }
        newPatTo.clic = newPatTo.clic + 1;
    });

    var menu = this;
    this.listener_right_clic_mapa = google.maps.event.addListener(mapa, 'rightclick', function (e) {
        menu.endAddPoligono();
    });

    this.close();
};

DeleteMenu.prototype.endAddPoligono = function () {

    if (this.listener_clic_mapa) {
        google.maps.event.removeListener(this.listener_clic_mapa);
    }

    if (this.listener_right_clic_mapa) {
        google.maps.event.removeListener(this.listener_right_clic_mapa);
    }

    var shapeT = this.get('shape');
    shapeT.set('clickable', true);
    this.set('estatus', AccionMenuPoligonEnum.NINGUNA);
}

/*
 Agrega un hueco al poligono
 */
DeleteMenu.prototype.addHole = function () {
    console.log('addHole');
    var path2 = this.get('shape').getPaths();
    var newPat = new google.maps.MVCArray([]);

    path2.getArray().push(newPat);
    var shapeT = this.get('shape');
    shapeT.set('clickable', false);

    if (this.get('pathIdx') !== undefined) {
        shapeT.setPaths(path2);
    }

    if (this.listener_clic_mapa) {
        google.maps.event.removeListener(this.listener_clic_mapa);
    }

    this.set('estatus', AccionMenuPoligonEnum.NUEVO_HOLE);
    var newPatTo = {path: newPat, clic: 0};
    this.set('newPath', newPatTo);
    this.close();
};

DeleteMenu.prototype.endAddHole = function () {
    console.log('endAddHole');
    if (this.listener_clic_mapa) {
        google.maps.event.removeListener(this.listener_clic_mapa);
    }
    if (this.listener_right_clic_mapa) {
        google.maps.event.removeListener(this.listener_right_clic_mapa);
    }
    this.set('estatus', AccionMenuPoligonEnum.NINGUNA);
    var shapeT = this.get('shape');
    shapeT.set('clickable', true);
    this.set('newPath');
    this.close();
};

function calcularAreaPoligon(poligono) {
    var paths = poligono.getPaths();
    try {
        if (paths.getArray().length > 1) {
            var pathsTemp = paths.getArray().slice(1, (paths.getArray().length));

            var areaTemp = 0;
            pathsTemp.forEach(function (element) {
                areaTemp = areaTemp + google.maps.geometry.spherical.computeArea(element);
            });
            return google.maps.geometry.spherical.computeArea(paths.getArray()[0]) - areaTemp;
        } else {
            return google.maps.geometry.spherical.computeArea(paths.getArray()[0]);
        }
    } catch (e) {

    }
}

function calcularPerimetroPoligon(poligono) {
    var paths = poligono.getPaths();
    try {
        return google.maps.geometry.spherical.computeLength(paths.getArray()[0]);
    } catch (e) {
        console.log('' + e);
        return '';
    }
}


/* ****************************************************************************************************************** */
//function DeleteMenu() {
//    console.log('DeleteMenu');
//    this.div_delete_vertex = document.createElement('div');
//    this.div_delete_vertex.className = 'poligon-menu';
//    this.div_delete_vertex.id = 'deleteVertex';
//    this.div_delete_vertex.innerHTML = 'Delete';
//
//    this.div_add_hole = document.createElement('div');
//    this.div_add_hole.className = 'poligon-menu';
//    this.div_add_hole.id = 'addHole';
//    this.div_add_hole.innerHTML = 'Agregar hueco';
//
//    this.div_delete_shape = document.createElement('div');
//    this.div_delete_shape.className = 'poligon-menu';
//    this.div_delete_shape.id = 'deleteShape';
//    this.div_delete_shape.innerHTML = 'Eliminar shape';
//
//    var menu = this;
//
//    google.maps.event.addDomListener(this.div_delete_vertex, 'click', function () {
//        menu.removeVertex();
//    });
//    google.maps.event.addDomListener(this.div_add_hole, 'click', function () {
//        menu.addHole();
//    });
//    google.maps.event.addDomListener(this.div_delete_shape, 'click', function () {
//        menu.removeShape();
//    });
//}
//
//DeleteMenu.prototype = new google.maps.OverlayView();
//
//DeleteMenu.prototype.onAdd = function () {
//    console.log('onAdd');
//    var deleteMenu = this;
//    var map = this.getMap();
//
//    if (this.get('vertex') === undefined) {
//    } else {
//
//        this.set('pathIdx', undefined);
//        if (this.get('paths') !== undefined) {
//            for (var i in this.get('paths').getArray()) {
//                if (this.get('paths').getArray()[i].getArray().includes(this.get('position'))) {
//                    this.set('pathIdx', i);
//                    break;
//                }
//            }
//        }
//
//        this.div_delete_vertex.innerHTML = 'Eliminar vertice';
//        this.div_add_hole.innerHTML = 'Agregar hueco';
//        this.div_delete_shape.innerHTML = (this.get('pathIdx') < 1 ? 'Elimiar poligono' : 'Eliminar hueco');
//
//        if (this.get('pathIdx') !== undefined && this.get('pathIdx') == 0) {
//            this.getPanes().floatPane.appendChild(this.div_add_hole);
//        }
//        if (this.listener_clic_mapa) {
//            google.maps.event.removeListener(this.listener_clic_mapa);
//        }
//
//        this.getPanes().floatPane.appendChild(this.div_delete_vertex);
//        this.getPanes().floatPane.appendChild(this.div_delete_shape);
//    }
//
//    // mousedown anywhere on the map except on the menu div will close the
//    // menu.
//    this.divListener_ = google.maps.event.addDomListener(map.getDiv(), 'mousedown', function (e) {
//        if ((e.target != deleteMenu.div_delete_vertex) && (e.target != deleteMenu.div_add_hole) && (e.target != deleteMenu.div_delete_shape)) {
//            deleteMenu.close();
//        }
//    }, true);
//};
//
//DeleteMenu.prototype.onRemove = function () {
//    console.log('onRemove');
//
//    if (this.get('estatus') === AccionMenuEnum.NUEVO_HOLE) {
//        google.maps.event.removeListener(this.listener_clic_mapa);
//        var path = this.get('newPath');
//        path.clic = 0;
//        var deleteMenu = this;
//        var mapa = this.get('mapa');
//        this.listener_clic_mapa = google.maps.event.addListener(mapa, 'click', function (e) {
//            if (path.clic !== 0) {
//                path.path.push(e.latLng);
//            }
//            path.clic = path.clic + 1;
//        });
//        this.listener_right_clic_mapa = google.maps.event.addListener(mapa, 'rightclick', function (e) {
//            deleteMenu.endAddHole();
//        });
//    }
//
//    google.maps.event.removeListener(this.divListener_);
//
//    if (this.div_add_hole.parentNode) {
//        this.div_add_hole.parentNode.removeChild(this.div_add_hole);
//    }
//    if (this.div_delete_shape.parentNode) {
//        this.div_delete_shape.parentNode.removeChild(this.div_delete_shape);
//    }
//    if (this.div_delete_vertex.parentNode) {
//        this.div_delete_vertex.parentNode.removeChild(this.div_delete_vertex);
//    }
//    this.set('position');
//    this.set('path');
//    this.set('vertex');
//};
//
//DeleteMenu.prototype.close = function () {
//    console.log('close');
//    this.setMap(null);
//};
//
//DeleteMenu.prototype.draw = function () {
//    console.log('draw');
//    var position = this.get('position');
//    var projection = this.getProjection();
//
//    if (!position || !projection) {
//        return;
//    }
//
//    var point = projection.fromLatLngToDivPixel(position);
//
//    this.div_delete_vertex.style.top = point.y + 'px';
//    this.div_delete_vertex.style.left = point.x + 'px';
//    this.div_add_hole.style.top = (point.y) + 'px';
//    this.div_add_hole.style.left = (point.x) + 'px';
//    this.div_delete_shape.style.top = (point.y) + 'px';
//    this.div_delete_shape.style.left = (point.x) + 'px';
//};
//
//
///**
// * Opens the menu at a vertex of a given path.
// */
//DeleteMenu.prototype.open = function (map, poligon, vertex, latLng, deseleccionarPoligonos, idMapa) {
//    console.log('open');
//    poligon;
//    this.set('position', latLng);
//
//    if (vertex === undefined) {
//    } else {
//        this.set('vertex', vertex);
//    }
//
////    cambiarColorPoligonos(idMapa, '#FF0000', '#FF0000');
//    deseleccionarPoligonos();
//    poligon.set('clickable', true);
//    poligon.set('draggable', true);
//    poligon.set('strokeColor', '#1a53ff');
//    poligon.set('fillColor', '#1a53ff');
//    console.log('max zindexpoli');
//    poligon.set('zindex', 300);
//    poligon;
//
//    this.set('shape', poligon);
//    this.set('path', poligon.getPath());
//    this.set('paths', poligon.getPaths());
//
//    this.set('mapa', map);
//    this.set('idMapa', idMapa);
//    this.setMap(map);
//    this.draw();
//};
//
///**
// * Elimina vertice del path.
// */
//DeleteMenu.prototype.removeVertex = function () {
//    console.log('removeVertex');
//    var path = this.get('paths');
//    var vertex = this.get('vertex');
//
//    if (!path || vertex == undefined) {
//        this.close();
//        return;
//    }
//
//    if (this.get('pathIdx') !== undefined) {
//        path.getArray()[this.get('pathIdx')].removeAt(vertex);
//    }
//    this.close();
//};
//
///*
// Eliminar poligono/hueco
// */
//DeleteMenu.prototype.removeShape = function () {
//    console.log('removeShape');
//    var path = this.get('paths');
//    if (this.get('pathIdx') !== undefined) {
//        if (this.get('pathIdx') < 1) {
//            this.get('shape').setPaths([]);
//        } else {
//            console.log('eliminar hueco:' + this.get('pathIdx'));
//            path.getArray()[this.get('pathIdx')].clear();
//        }
//    }
//    this.close();
//};
//
///*
// Agrega un hueco al poligono
// */
//DeleteMenu.prototype.addPoligono = function (poligono, mapa, idMapa) {
//    console.log('addPoligono');
//    this.set('shape', poligono);
//    this.set('paths', poligono.getPaths());
//    this.set('mapa', mapa);
//    this.set('idMapa', idMapa);
//
//    var paths = this.get('shape').getPaths();
//    var newPat = new google.maps.MVCArray([]);
//    paths.getArray().push(newPat);
//    var shapeT = this.get('shape');
//    shapeT.set('clickable', false);
//
//    var map = this.getMap();
//    if (this.listener_clic_mapa) {
//        google.maps.event.removeListener(this.listener_clic_mapa);
//    }
//
//    this.set('estatus', AccionMenuEnum.NUEVO_POLIGONO);
//    var newPatTo = {path: newPat, clic: 1};
//    this.set('newPath', newPatTo);
//
//    this.listener_clic_mapa = google.maps.event.addListener(mapa, 'click', function (e) {
//        if (newPatTo.clic !== 0) {
//            newPatTo.path.push(e.latLng);
//        }
//        newPatTo.clic = newPatTo.clic + 1;
//    });
//
//    this.close();
//};
//
///*
// Agrega un hueco al poligono
// */
//DeleteMenu.prototype.addHole = function () {
//    console.log('addHole');
//    var path2 = this.get('shape').getPaths();
//    var newPat = new google.maps.MVCArray([]);
//    path2.getArray().push(newPat);
//    var shapeT = this.get('shape');
//    shapeT.set('clickable', false);
//
//    if (this.get('pathIdx') !== undefined) {
//        shapeT.setPaths(path2);
//    }
//
//    var map = this.getMap();
//    if (this.listener_clic_mapa) {
//        google.maps.event.removeListener(this.listener_clic_mapa);
//    }
//
//    this.set('estatus', AccionMenuEnum.NUEVO_HOLE);
//    var newPatTo = {path: newPat, clic: 0};
//    this.set('newPath', newPatTo);
//    this.close();
//};
//
//DeleteMenu.prototype.endAddHole = function () {
//    console.log('endAddHole');
//    if (this.listener_clic_mapa) {
//        google.maps.event.removeListener(this.listener_clic_mapa);
//    }
//    if (this.listener_right_clic_mapa) {
//        google.maps.event.removeListener(this.listener_right_clic_mapa);
//    }
//    this.set('estatus', AccionMenuEnum.NINGUNA);
//    this.set('newPath');
//    this.close();
//};