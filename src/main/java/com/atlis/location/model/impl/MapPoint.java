/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atlis.location.model.impl;

import com.atlis.location.model.LocationModelAbs;

/**
 *
 * @author nf
 */
public class MapPoint extends LocationModelAbs {

    protected Double latitude;
    protected Double longitude;

    public MapPoint() {
    }

    public MapPoint buildMapPoint(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        return this;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Boolean hasLocation() {
        return longitude != null && !longitude.equals(0.0) && latitude != null && !latitude.equals(0.0);
    }

}
