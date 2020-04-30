/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function  createLayerFromWMS(map, urlParam, layerParam, styleParam, cqlParam, srsParam) {
    console.log('URLWMS: ' + urlParam + layerParam + styleParam + cqlParam + srsParam);
    var SLPLayer = new google.maps.ImageMapType({
        getTileUrl: function (coord, zoom) {
            var proj = map.getProjection();
            var zfactor = Math.pow(2, zoom);
            // get Long Lat coordinates
            var top = proj.fromPointToLatLng(new google.maps.Point(coord.x * 256 / zfactor, coord.y * 256 / zfactor));
            var bot = proj.fromPointToLatLng(new google.maps.Point((coord.x + 1) * 256 / zfactor, (coord.y + 1) * 256 / zfactor));

            //corrections for the slight shift of the SLP (mapserver)
//            var deltaX = 0.0013;
//            var deltaY = 0.00058;
            var deltaX = 0.0;
            var deltaY = 0.0;

            //create the Bounding box string
            var bbox = (top.lng() + deltaX) + "," +
                    (bot.lat() + deltaY) + "," +
                    (bot.lng() + deltaX) + "," +
                    (top.lat() + deltaY);

            //base WMS URL
            console.log('bbox:' + bbox);
            var url = urlParam;
//            var url = "http://localhost:1024/geoserver/cite/wms?";
            url += "&REQUEST=GetMap"; //WMS operation
            url += "&SERVICE=WMS";    //WMS service
            url += "&VERSION=1.1.";  //WMS version  
            url += "&LAYERS=" + layerParam; //WMS layers
//            url += "&LAYERS=" + "cite%3Ac_municipio"; //WMS layers
            url += "&FORMAT=image/png"; //WMS format
            url += "&BGCOLOR=0xFFFFFF";
            url += "&TRANSPARENT=TRUE";
            url += "&SRS=" + srsParam;     //set WGS84 
//            url += "&SRS=EPSG:4326";     //set WGS84 
            url += "&BBOX=" + bbox;
            url += cqlParam;
//            url += "&CQL_FILTER=cve_mun='031'";
//            url += "&CQL_FILTER=" + cql;
            //&CQL_FILTER=POP_EST%20%3C=%205000000%20AND%20POP_EST%20%3E100000
            url += "&STYLES=" + styleParam;
//            url += "&STYLES=municipios";
            url += "&WIDTH=256";         //tile size in google
            url += "&HEIGHT=256";
            return url;                 // return URL for the tile
        },
        tileSize: new google.maps.Size(256, 256),
        isPng: true
    });
    return  SLPLayer;
}
