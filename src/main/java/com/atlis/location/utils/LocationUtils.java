/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atlis.location.utils;

import com.atlis.location.model.impl.BoundingBox;
import com.atlis.location.model.impl.MapPoint;
import com.atlis.location.model.impl.MapRegion;


/**
 *
 * @author nf
 */
public class LocationUtils {

    // Semi-axes of WGS-84 geoidal reference
    private static final Double WGS84_a = 6378137.0; // Major semiaxis [m]
    private static final Double WGS84_b = 6356752.3; // Minor semiaxis [m]
    private static final Double FLAT_ANGLE = 180.0;

    public BoundingBox getBoundingBox(MapRegion mapRegion) {
        //halfSideInKm is the half length of the bounding box you want in kilometers.
        Double halfSideInKm = mapRegion.getRadius() / 2;
        // Bounding box surrounding the point at given coordinates,
        // assuming local approximation of Earth surface as a sphere
        // of radius given by WGS84
        Double lat = deg2rad(mapRegion.getLatitude());
        Double lon = deg2rad(mapRegion.getLongitude());
        Double halfSide = 1000 * halfSideInKm;

        // Radius of Earth at given latitude
        Double radius = WGS84EarthRadius(lat);
        // Radius of the parallel at given latitude
        Double pradius = radius * Math.cos(lat);

        Double latMin = lat - halfSide / radius;
        Double latMax = lat + halfSide / radius;
        Double lonMin = lon - halfSide / pradius;
        Double lonMax = lon + halfSide / pradius;

        MapPoint minPoint = new MapPoint().buildMapPoint(rad2deg(latMin), rad2deg(lonMin));
        MapPoint maxPoint = new MapPoint().buildMapPoint(rad2deg(latMax), rad2deg(lonMax));
        return new BoundingBox(minPoint, maxPoint);
    }

    // degrees to radians
    private Double deg2rad(Double degrees) {
        return Math.PI * degrees / FLAT_ANGLE;
    }

    //radians to degrees
    private Double rad2deg(Double radians) {
        return FLAT_ANGLE * radians / Math.PI;
    }

    //Earth radius at a given latitude, according to the WGS-84 ellipsoid [m]
    private Double WGS84EarthRadius(Double lat) {
        // http://en.wikipedia.org/wiki/Earth_radius
        Double An = WGS84_a * WGS84_a * Math.cos(lat);
        Double Bn = WGS84_b * WGS84_b * Math.sin(lat);
        Double Ad = WGS84_a * Math.cos(lat);
        Double Bd = WGS84_b * Math.sin(lat);
        return Math.sqrt((An * An + Bn * Bn) / (Ad * Ad + Bd * Bd));
    }
}
