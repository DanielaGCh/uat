/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function CenterControlMenuShapes(controlDiv, map, funct, title, urlIcon) {
    // Set CSS for the control border.
    var controlUI = document.createElement('div');
    controlUI.style.backgroundColor = '#fff';
    controlUI.style.border = '2px solid #fff';
    controlUI.style.borderRadius = '0px';
    controlUI.style.boxShadow = '0 2px 6px rgba(0,0,0,.3)';
    controlUI.style.cursor = 'pointer';
    controlUI.style.marginBottom = '22px';
    controlUI.style.marginTop = '10px';
    controlUI.style.marginLeft = '0px';
    controlUI.style.textAlign = 'center';
    controlUI.style.backgroundImage = "url(" + urlIcon + ")";
    controlUI.style.backgroundRepeat = "no-repeat";
    controlUI.style.backgroundPosition = "center";
    controlUI.title = title;
    controlDiv.appendChild(controlUI);
    // Set CSS for the control interior.
    var controlText = document.createElement('div');
    controlText.style.color = 'rgb(255,255,255)';
    controlText.style.fontFamily = 'Roboto,Arial,sans-serif';
    controlText.style.fontSize = '1px';
//    controlText.style.backgroundColor = "#000000";
    controlText.style.lineHeight = '38px';
    controlText.style.paddingLeft = '10px';
    controlText.style.paddingRight = '10px';
    controlText.innerHTML = 'xxx';
    controlUI.appendChild(controlText);
    controlUI.addEventListener('click', funct);
}

function CenterControl(controlDiv, map, funct, label, title, urlIcon) {
    // Set CSS for the control border.
    var controlUI = document.createElement('div');
    controlUI.style.backgroundColor = '#fff';
    controlUI.style.border = '2px solid #fff';
    controlUI.style.borderRadius = '0px';
    controlUI.style.boxShadow = '0 2px 6px rgba(0,0,0,.3)';
    controlUI.style.cursor = 'pointer';
    controlUI.style.marginBottom = '22px';
    controlUI.style.marginTop = '10px';
    controlUI.style.marginLeft = '0px';
    controlUI.style.textAlign = 'center';
    controlUI.style.backgroundImage = "url(" + urlIcon + ")";
    controlUI.style.backgroundRepeat = "no-repeat";
    controlUI.style.backgroundPosition = "center";
    controlUI.title = title;
    controlDiv.appendChild(controlUI);
    // Set CSS for the control interior.
    var controlText = document.createElement('div');
    controlText.style.color = 'rgb(25,25,25)';
    controlText.style.fontFamily = 'Roboto,Arial,sans-serif';
    controlText.style.fontSize = '16px';
//    controlText.style.backgroundImage = "url(" + urlIcon + ")";
    controlText.style.lineHeight = '38px';
    controlText.style.paddingLeft = '10px';
    controlText.style.paddingRight = '10px';
    controlText.innerHTML = label;
    controlUI.appendChild(controlText);
    controlUI.addEventListener('click', funct);
}

function bottomControl(controlDiv, map, funct, label, title, urlIcon) {
    // Set CSS for the control border.
    var controlUI = document.createElement('div');
    controlUI.id = 'controlUI';
    controlUI.style.backgroundColor = '#fff';
    controlUI.style.border = '2px solid #fff';
    controlUI.style.borderRadius = '0px';
    controlUI.style.boxShadow = '0 2px 6px rgba(0,0,0,.3)';
    controlUI.style.cursor = 'pointer';
    controlUI.style.marginBottom = '50px';
    controlUI.style.marginTop = '0px';
    controlUI.style.marginLeft = '0px';
    controlUI.style.textAlign = 'center';
    controlUI.style.backgroundImage = "url(" + urlIcon + ")";
    controlUI.style.backgroundRepeat = "no-repeat";
    controlUI.style.backgroundPosition = "center";
    controlUI.title = title;
    controlDiv.appendChild(controlUI);
    // Set CSS for the control interior.
    var controlText = document.createElement('div');
    controlText.id = 'controlText';
    controlText.style.color = 'rgb(25,25,25)';
    controlText.style.fontFamily = 'Roboto,Arial,sans-serif';
    controlText.style.fontSize = '16px';
//    controlText.style.backgroundImage = "url(" + urlIcon + ")";
    controlText.style.lineHeight = '15px';
    controlText.style.paddingLeft = '10px';
    controlText.style.paddingRight = '10px';
    controlText.innerHTML = label;
    controlUI.appendChild(controlText);
    controlUI.addEventListener('click', funct);
}

function CenterControlCapas(controlDiv, map, funct, label, title, urlIcon) {
    // Set CSS for the control border.
    var controlUI = document.createElement('div');
    controlUI.style.backgroundColor = '#fff';
    controlUI.style.border = '2px solid #fff';
    controlUI.style.borderRadius = '0px';
    controlUI.style.boxShadow = '0 2px 6px rgba(0,0,0,.3)';
    controlUI.style.cursor = 'pointer';
    controlUI.style.marginBottom = '22px';
    controlUI.style.marginTop = '10px';
    controlUI.style.backgroundImage = "url(" + urlIcon + ")";
    controlUI.style.backgroundRepeat = "no-repeat";
    controlUI.style.backgroundPosition = "center";
    controlUI.style.marginLeft = '0px';
    controlUI.style.textAlign = 'center';
    controlUI.title = title;
    controlDiv.appendChild(controlUI);
    // Set CSS for the control interior.
    var controlText = document.createElement('div');
    controlText.style.color = 'rgb(25,25,25)';
    controlText.style.fontFamily = 'Roboto,Arial,sans-serif';
    controlText.style.fontSize = '16px';
    controlText.style.lineHeight = '10px';
    controlText.style.paddingLeft = '10px';
//    controlText.style.backgroundImage = "url(" + urlIcon + ")";
    controlText.style.paddingRight = '10px';
    controlText.innerHTML = title;
    controlUI.appendChild(controlText);
    controlUI.addEventListener('click', funct);
}

function topRigthControlCapas(controlDiv, map, funct, label, title, urlIcon) {
    // Set CSS for the control border.
    var controlUI = document.createElement('div');
    controlUI.id = 'controlUI';
    controlUI.style.backgroundColor = '#fff';
    controlUI.style.border = '2px solid #fff';
    controlUI.style.borderRadius = '0px';
    controlUI.style.boxShadow = '0 2px 6px rgba(0,0,0,.3)';
    controlUI.style.cursor = 'pointer';
    controlUI.style.marginBottom = '53x';
    controlUI.style.marginTop = '3px';
    controlUI.style.marginLeft = '0px';
    controlUI.style.textAlign = 'center';
    controlUI.style.backgroundImage = "url(" + urlIcon + ")";
    controlUI.style.backgroundRepeat = "no-repeat";
    controlUI.style.backgroundPosition = "center";
    controlUI.title = title;
    controlDiv.appendChild(controlUI);
    // Set CSS for the control interior.
    var controlText = document.createElement('div');
    controlText.style.color = 'rgb(25,25,25)';
    controlText.style.fontFamily = 'Roboto,Arial,sans-serif';
    controlText.style.fontSize = '16px';
    controlText.style.lineHeight = '10px';
    controlText.style.paddingLeft = '10px';
    controlText.style.paddingRight = '10px';
//    controlText.style.backgroundImage = "url(" + urlIcon + ")";
    controlText.innerHTML = title;
    controlUI.appendChild(controlText);
    controlUI.addEventListener('click', funct);
}

var MapTypesEnum = {
    ROAD_MAP: 'roadmap',
    SATELLITE: 'satellite',
    HYBRID: 'hybrid',
    TERRAIN: 'terrain',
    OSM: 'osm'
};

function agregarMapaOSM(map) {
    console.log('initialize osm');
    var mapOptions = {
        mapTypeId: "OSM",
        mapTypeControl: true,
        streetViewControl: true,
        scaleControl: true,
        panControl: true,
    }

    try {
        map.setOptions(mapOptions);
        map.mapTypes.set("OSM", new google.maps.ImageMapType({
            getTileUrl: function (coord, zoom) {
                return "https://tile.openstreetmap.org/" + zoom + "/" + coord.x + "/" + coord.y + ".png";
            },
            tileSize: new google.maps.Size(256, 256),
            name: "OpenStreetMap",
            maxZoom: 22
        }));
    } catch (e) {
        console.log('' + e);
    }
}

function changeMapType(map, mapType) {
    map.setMapTypeId('' + mapType);
}